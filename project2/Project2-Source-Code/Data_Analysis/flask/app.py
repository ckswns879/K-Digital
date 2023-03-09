from flask import Flask, request
from flask_cors import CORS
import pickle
from recommend import recommend_items
import pandas as pd
import dbModule

app = Flask(__name__)
#CORS config
cors = CORS(app, resources={r"*": {"origins": "*"}})

# 카테고리 분류용 모델, vectorizer, label encoder 불러오기
with open('./model.pickle', 'rb') as f:
    model = pickle.load(f)
with open('./vectorizer.pickle', 'rb') as f:
    vectorizer = pickle.load(f)
with open('./label_encoder.pickle', 'rb') as f:
    le = pickle.load(f)
    
# 리드타임 예측용 모델, vectorizer 불러오기
with open('./lead_Linear.pickle', 'rb') as f:
    model_lead = pickle.load(f)
with open('./lead_linear_vectorizer.pickle', 'rb') as f:
    vectorizer_lead = pickle.load(f)

@app.route('/data/classifier', methods=['GET', 'POST'])
def classify():
    if request.method == 'POST':
        params = request.get_json()
        # 예측값 입력 받기
        data1 = params['data1']
        data2 = params['data2']
        data3 = params['data3']
        data4 = params['data4']
        data5 = params['data5']
        data = [data1 + ' ' + data2 + ' ' + data3 + ' ' + data4 + ' ' + data5]
        
        # feature vector 생성
        X = vectorizer.transform(data)
        
        # 모델 예측
        y_pred = model.predict(X)
        # 예측 결과 label로 변환
        y_pred_label = le.inverse_transform(y_pred)
        # 결과 출력 페이지 렌더링
        # return render_template('result.html', data=data[0], prediction=y_pred_label[0])
        return {"prediction":y_pred_label[0]}
    
@app.route('/data/prediction', methods=['GET', 'POST'])
def predict():
    if request.method == 'POST':
        params = request.get_json()
        # 예측값 입력 받기
        data1 = params['data1']
        data2 = params['data2']
        data3 = params['data3']
        data = [data1 + ' ' + data2 + ' ' + data3]
        
        # feature vector 생성
        X = vectorizer_lead.transform(data)

        # 모델 예측
        y_pred = model_lead.predict(X)

        # Object를 Json화 할때 에러 방지를 위해 int 사용
        return {"prediction": int(y_pred[0])}
    
@app.route('/data/predictAll', methods=['GET', 'POST'])
def predictAll():
    if request.method == 'POST':
        params = request.get_json()
        for i in params['basket']:
            data1 = i['items']['subjects']
            data2 = i['items']['part1']
            data3 = i['items']['assembly']
            data = [data1 + ' ' + data2 + ' ' + data3]
            
            # feature vector 생성
            X = vectorizer_lead.transform(data)

            # 모델 예측
            y_pred = model_lead.predict(X)
            
            i['predictLead'] = int(y_pred[0])
        return params

@app.route('/data/recommendation', methods=['GET', 'POST'])
def recommend():
    if request.method == 'POST':
        db_class = dbModule.Database()
        sql = "SELECT * FROM ship.raw_data"
        row      = db_class.executeAll(sql)
        data = pd.DataFrame(row)
        params = request.get_json()
        # 예측값 입력 받기
        inputItems = params['items']
        
        # return model_recommend.recommend_items(data, inputItems)
        return recommend_items(data, inputItems)

if __name__ == '__main__':
    app.run(debug=True)
    # 'set FLASK_DEBUG=1' (코드가 수정되면 자동 서버 재시작) 를 먼저 입력한 후 'flask run --host=0.0.0.0'(외부 접속 허용)을 하면 스프링의 dev tool처럼 변경이후 바로 서버 재실행

