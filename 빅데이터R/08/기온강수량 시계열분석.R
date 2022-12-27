getwd()
setwd('C:/Rwork/08')
#데이터 불러오기
df <- read.csv("./07_기온강수량.csv", 
               header = T, 
               stringsAsFactors = F, 
               fileEncoding = 'euc-kr') 
df <- df[, 3:5]
df
str(df)

str(df)

library(dplyr)
train <- df %>% filter(일시 < '2022-01-01' & 일시 >= '2020-01-01')
test <- df %>% filter(일시 >= '2022-01-01')

#1. 데이터준비
temp <- train $평균기온

#2. 시계열 데이터 생성 (시계열 데이터:시간에따라바뀌는데이터)ts()시
temp_ts <- ts(temp, frequency = 12, start = c(2020, 1))

#3. 시계열추세 확인**
#추세 (Trend): 데이터의 장기적인 증가 또는 감소를 추세라고 한다. 그것은 반드시 선형적인 것은 아니다. 그것은 시간의 경과에 따른 데이터의 기본 패턴이다. 
#계절성 (Seasonal): 시리즈가 계절적 요인에 의해 영향을 받는 경우(즉, 분기, 월 또는 요일) 시리즈에는 계절성이 존재한다. 그것은 항상 고정되고 알려진 기간이다. 예: -크리스마스 기간 동안 매출이 급 상승하는 경우 등.
#주기 (Cyclic): 일정 기간이 아닌 데이터가 상승하거나 하락할 때 우리는 이를 주기적 패턴이라고 부른다. 예를들어 - 이러한 변동 지속기간은 일반적으로 최소 2년이다. 
components.ts = decompose(temp_ts)

#관측값 ( Observed ) – 실제 데이터 플롯 
#추세 ( Trend ) – 데이터 점들의 전반적인 상향 또는 하향 움직임
#계절성 ( Seasonal )  – 데이터 점들의 월별/년별 패턴 
#임의값 ( Random ) – 데이터의 설명할 수 없는 부분 
plot(components.ts)

acf(temp_ts,lag.max=34) 

#4.모형의 식별과 추청
#비정상성 시계열이면 차분하여 정상성 시계로 변환 

install.packages("forecast")
library(forecast)


#auto.arima() : 시계열 모형을 식별하는 알고리즘에 의해
#최적의 모형과 파라미터를 추정하여 제공
#ARIMA(p,d,q)모형 : #*ARIMA(p,d,q)찾는게 중요*
#p =자기회귀 부분의 차수, d=1차 차분이 포함된 정도, q= 이동 평균 부분의 차수
# - d = 0이면, ARMA(p,q)모형, 정상성 만족
# - p = 0이면, IMA(d,q)모형, d번 차분하면 MA(q)모형을 따름
# - q = 0이면, IAR(p,d)모형, d번 차분하면 AR(p)모형을 따름 

arima <- auto.arima(temp_ts)
arima

#5.모형생성
model <- arima(temp_ts, order=c(3,0,0))
model

#6.모형타당성 검사 
#자기 상관함수에 의한 모형 진단
tsdiag(model)

#box-Ljungn잔차항 모형 진단
#p-value >= 0.05 통계적으로 적절 
Box.test(model$residuals, lag=1, type="Ljung")

#7.예측
fore <- forecast(model, h=11)
fore
 
plot(fore) 

test$평균기온
class(fore$mean)
pred = as.vector(fore$mean)

result <- data.frame(test = test$평균기온, pred=pred)
result

plot(result$test, type="o", col="red")
par(new=T)
plot(result$pred, type="o", col="blue")

