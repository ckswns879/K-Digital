getwd()
dff <- read.csv('06_국민건강보험공단500.csv', header = T, stringsAsFactors = F, fileEncoding = 'euc-kr')
dff
names(dff)
dff <- dff[c("신장.5Cm단위.","체중.5Kg.단위.","성별코드")]
names(dff) <- c('키','몸무게','성별')
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
#수치데이터로 변환
x <- sample(1:nrow(dff),0.7*nrow(dff))
x
str(dff)
train <- dff[x, ] 
test <- dff[-x, ]

#학습
names(dff)
summary(dff)
model <- ctree(비만도 ~키+몸무게+성별, data=train)
mode2 <- lm(formula =  BMI ~키+몸무게+성별, data=train)

plot(model)
#예측
pred <- predict(model, test)
pred2 <- predict(mode2, test)

#혼돈행렬
t <- table(test$비만도, pred)
t
t2 <- table(test$BMI, pred2)
t2

#accuracy
acc <- (t[1,1]+t[2,2]+t[3,3]+t[4,4])/sum(t)
acc
acc2 <- (t2[1,1]+t2[2,2]+t2[3,3]+t2[4,4])/sum(t)
acc2
