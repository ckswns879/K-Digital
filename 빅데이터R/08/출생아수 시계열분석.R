getwd()
setwd('C:/Rwork/08')
#데이터 불러오기
df <- read.csv("./07_출생아수.csv", 
               header = T, 
               stringsAsFactors = F, 
               fileEncoding = 'euc-kr') 
df
names(df) <- c('시점','출생아수')
names(df)
str(df)

library(dplyr)
train <- df %>% filter(시점 < '2018' & 시점 >= '2000')
test <- df %>% filter(시점 >= '2015')

temp <- train$출생아수

temp_ts <- ts(temp,frequency = 1, start = c(2015))


library(forecast)
arima <- auto.arima(temp_ts)
arima

model <- arima(temp_ts)
model

tsdiag(model)

Box.test(model$residuals, lag=1, type="Ljung")

#7.예측
fore <- forecast(model, h=11)
fore

plot(fore) 

test$시점
class(fore$mean)
pred = as.vector(fore$mean)

result <- data.frame(test = test$시점, pred=pred)
result

plot(result$test, type="o", col="red")
par(new=T)
plot(result$pred, type="o", col="blue")

