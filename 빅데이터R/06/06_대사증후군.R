getwd()
library(ggplot2)
library(dplyr)
library(party)
df <- read.csv('06_국민건강보험공단500.csv', header = T, stringsAsFactors = F, fileEncoding = 'euc-kr')
df
names(df)


# 대사증후군에 필요한 열을 추출하여 열명을 변경
# "시도코드", "성별코드", "수축기혈압","이완기혈압", "공복혈당","트리글리세라이드","HDL콜레스테롤","허리둘레“
df <- df[c("시도코드","성별코드","수축기.혈압","이완기.혈압","식전혈당.공복혈당.","트리글리세라이드","HDL.콜레스테롤","허리둘레")]

names(df) <- c('시도코드','성별코드','수축기혈압','이완기혈압','공복혈당','트리글리세라이드','HDL콜레스테롤','허리둘레')
names(df)
# 결측치 자료가 있는 행을 모두 삭제
sum(df$수축기혈압, na.rm=T)
df <- na.omit(df)
summary(df)
# 대사증후군을 판별
# • 높은 혈압(130/85mmHg 이상)
# • 높은 혈당(공복 혈당 100mg/dL 이상)
# • 높은 중성지방(150mg/dL 이상)
# • 낮은 HDL 콜레스테롤 수치(남성은 40mg/dL 미만, 여성은 50mg/dL 미만)
# • 복부 비만(남성 90cm 이상, 여성 85cm 이상)
# • 0 : 정상
# • 1~2 : 주의군
# • 3~5 : 위험
# • 높은 혈압(130/85mmHg 이상)
df$높은혈압 <- df$수축기혈압 >= 130 & df$이완기혈압 >= 85
# • 높은 혈당(공복 혈당 100mg/dL 이상)
df$높은혈당 <- df$공복혈당 >= 100
# • 높은 중성지방(트리글리세라이드 150mg/dL 이상)
df$높은중성지방 <- df$트리글리세라이드 >= 150
# • 낮은 HDL 콜레스테롤 수치(남성은 40mg/dL 미만, 여성은 50mg/dL 미만)
df$낮은콜레스테롤 <- ((df$성별코드 == 1) & (df$HDL콜레스테롤 < 40)) | ((df$성별코드 == 2) & (df$HDL콜레스테롤 < 50))
# • 복부 비만(남성 90cm 이상, 여성 85cm 이상)
df$복부비만 <- ((df$성별코드 == 1) & (df$허리둘레 >= 90)) | ((df$성별코드 == 2) & (df$허리둘레 >= 85))
# • 판별, 대사증후군 수
# ◦ 0 : 정상, 1~2 : 주의군, 3~5 : 위험군
df$대사증후군 <- df$높은혈압+
  df$높은혈당+
  df$높은중성지방+
  df$낮은콜레스테롤+
  df$복부비만

df$판별 <- ifelse(df$대사증후군 == 0, "정상", 
               ifelse(df$대사증후군 <= 2, "주의군", "위험군") )
df$판별

df$대사증후군 <- as.factor(df$대사증후군)
df$성별코드 <- as.factor(df$성별코드)
df$판별 <- as.factor(df$판별)

head(df)

# 대사증후군을 판별하는 지도학습모델을 만들고 정확도
#분류모델
str(df)
names(df)
df1 <- df


head(df1)

#수치데이터로 변환
x <- sample(1:nrow(df1),0.7*nrow(df1))
x

train <- df1[x, ] 
test <- df1[-x, ]

#학습
names(df1)

model1 <- ctree(판별 ~  수축기혈압 + 이완기혈압 + 
                  공복혈당 + 트리글리세라이드 + 허리둘레, data=train)
model2 <- ctree(판별 ~  수축기혈압 + 이완기혈압 + 성별코드 +
                  공복혈당 + 트리글리세라이드 + 허리둘레+HDL콜레스테롤, data=train)
plot(model1)
#예측
pred <- predict(model, test)
pred2 <- predict(model2, test)

#혼돈행렬
t <- table(test$대사증후군, pred)
t
t2 <- table(test$판별, pred2)
t2
#accuracy
acc <- (t[1,1]+t[2,2]+t[3,3]+t[4,4])/sum(t)
acc
acc2 <- (t2[1,1]+t2[2,2]+t2[3,3])/sum(t2)
acc2


dff <- read.csv('06_국민건강보험공단500.csv', header = T, stringsAsFactors = F, fileEncoding = 'euc-kr')
dff
names(dff)
dff <- dff[c("신장.5Cm단위.","체중.5Kg.단위.")]
names(dff) <- c('키','몸무게')
# BMI 지수 산출
dff$BMI <- dff$몸무게 / (dff$키 / 100) ** 2
head(dff)
# 비만도 판별
dff$비만도 <- ifelse(dff$BMI < 20,"저체중",
                  ifelse(dff$BMI <= 24,"정상",
                        ifelse(dff$BMI <= 29,"과체중","비만")))
head(dff)
dff$비만도 <- as.factor(dff$비만도)
# 지도학습 모델

# BMI 예측

# 비만도 예측






