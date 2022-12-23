# 치매환자현황분석
getwd()
setwd("C:/Rwork/03")
df1 <- read.csv("03_치매환자현황.csv",header = T, stringsAsFactor = F,fileEncoding = 'euc-kr')

df1
install.packages("ggplot2")
library(ggplot2)
head(df1)
names(df1)

#테이블을 데이터프레임으로바꾸기
table(df1$거주지역)
table(df1$성별)
tb1 <- table(df1$거주지역, df1$성별)
class(tb1)
tb1 <- as.data.frame(tb1)
tb1

# • 거주지역별 치매환자 빈도표를 구하고 그래프
# 작성
qplot(거주지역, data=df1,fill=거주지역)+
  ggtitle("거주지역별 치매환자")
  
#데이터타입확인
class(df1$진단일자)
class(df1$데이터기준일자)



# • 기준일자와 진단일자를 이용하여 진단일수를
# 계산하고 평균 진단일수 산출


# ◦ 날짜형으로 변환 : as.Date()
df1$진단일자 <- as.Date(df1$진단일자)
df1$데이터기준일자 <- as.Date(df1$데이터기준일자)

# ◦ 날짜사이 간격 : difftime(시작일, 종료일, units ='days’)
df1$진단일수 <- difftime(df1$데이터기준일자, df1$진단일자, units ='days')

head(df1)
# ▫ units = 'mins’ 분단위, units = 'secs’ 초단위
install.packages('tidyverse')
library('tidyverse')
install.packages('lubridate')
library('lubridate')
df1$연도 <- year(df1$진단일자)
df1$나이 <- df1$연도 - df1$출생년도
head(df1)
# • 연령대별 빈도수 그래프를 그려서 치매환자가
# 많은 연령대 분석 
df1$연령대 <- ifelse(df1$나이 < 10,"어린이",
                  ifelse(df1$나이 < 20,"10대",
                   ifelse(df1$나이 < 30,"20대",
                    ifelse(df1$나이 < 40,"30대",
                     ifelse(df1$나이 < 50,"40대",
                      ifelse(df1$나이 < 60,"50대",
                        ifelse(df1$나이 < 70,"60대",
                         ifelse(df1$나이 < 80,"70대",
                           ifelse(df1$나이 < 90,"80대",
                             ifelse(df1$나이 < 100,"90대","90대"))))))))))
qplot(연령대, data=df1,fill=연령대)+
  ggtitle("연령대")
df1
# ◦ 열 내용에 문자열 붙이기 : paste(열, “대“, sep=“”)   몫연산자 %/%
df1$연령대2 <- paste((df1$나이%/%10)*10, "대", sep="")
subset(df1, df1$연령대2 == "100대")
df1$연령대2 <- ifelse(df1$연령대2 == "100대","90대",df1$연령대2)
#연령대로 시각화
barplot(table(df1$연령대2))
# ◦ 100세 이상은 90대에 포함
