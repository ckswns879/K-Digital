import time
import pandas as pd
import pymysql
from sqlalchemy import create_engine
import configparser


df = pd.read_csv("./totaldata/totalmain.csv", encoding='cp949')

# 컬럼 리스트 생성
date = df.iloc[:,0].to_list()
model_name = df.iloc[:,1].to_list()
car_type = df.iloc[:,2].to_list()
car_num = df.iloc[:,3].to_list()
driver_code = df.iloc[:,4].to_list()
daily_distance = df.iloc[:,5].to_list()
cum_distance = df.iloc[:,6].to_list()
information_date = df.iloc[:,7].to_list()
car_speed = df.iloc[:,8].to_list()
RPM = df.iloc[:,9].to_list()
brake_signal = df.iloc[:,10].to_list()
car_location_GPS_X = df.iloc[:,11].to_list()
car_location_GPS_Y = df.iloc[:,12].to_list()
Global_Positioning_System_GPS_azimuth = df.iloc[:,13].to_list()
accelerationVx = df.iloc[:,14].to_list()
accelerationVy = df.iloc[:,15].to_list()
status_code = df.iloc[:,16].to_list()
RPMS = df.iloc[:,17].to_list()
ACC = df.iloc[:,18].to_list()
FS = df.iloc[:,19].to_list()
RPMSR = df.iloc[:,20].to_list()
ACCR = df.iloc[:,21].to_list()
SRA = df.iloc[:,22].to_list()

list_sqlData = []

# sql문 생성
for i in range(len(df)) :
    a = date[i], model_name[i] ,car_type[i],car_num[i],driver_code[i],daily_distance[i], cum_distance[i],information_date[i],car_speed[i],RPM[i],brake_signal[i],\
    car_location_GPS_X[i],car_location_GPS_Y[i],Global_Positioning_System_GPS_azimuth[i],accelerationVx[i],accelerationVy[i],status_code[i],RPMS[i],ACC[i],FS[i],\
    RPMSR[i],ACCR[i],SRA[i]
    list_sqlData.append(a)
    
# mysql 아마존 db에 데이터 넣기
# conn = pymysql.connect(host='database-1.cd1ds11df3el.ap-northeast-2.rds.amazonaws.com',
#                                      database='p1',
#                                      user='admin',
#                                      password='tigertiger')

#mysql local db에 데이터넣기
conn = pymysql.connect(host='127.0.0.1', user='first', password='Xptmxm1!', db='p1', charset='utf8')

try:
    with conn.cursor() as cursor:
        sql = 'INSERT INTO raw_data (date, model_name, car_type, car_num, driver_code, daily_distance, cum_distance, information_date, car_speed,RPM, brake_signal, car_location_GPS_X, car_location_GPS_Y, Global_Positioning_System_GPS_azimuth, accelerationVx,accelerationVy, status_code, RPMS, ACC, FS, RPMSR, ACCR, SRA) VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)'
        cursor.executemany(sql, list_sqlData)
    conn.commit()
finally:
    conn.close()
    