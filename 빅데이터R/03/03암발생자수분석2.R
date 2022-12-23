#암종류별 성별 분석
install.packages("dplyr")
library(dplyr)
getwd()
setwd("C:/Rwork/03")
#데이터 불러오기(암발생자수)
 df1 <- read.csv("03_암발생자수_.csv",header = T, fileEncoding = 'euc-kr')

head(df1)

# 열명 변경
# "암종별", "성별", "연령별", "발생자수", "조발생률"
names(df1) <- c("암종별", "성별", "연령별", "발생자수", "조발생률")
names(df1)

# 데이터셋 조회
# 1) 특정 변수 조회
t1 <- df1$암종별
mode(t1)
class(t1)
is.vector(t1)
# 2) 특정 열명을 사용하여 조회
t2 <- df1['암종별']
mode(t2)
class(t2)
# 3) 특정 행 조회 :1행 조회
df1[1,]
df1[c(2,4),]#2행4행보기
# 4)특정행 제거 : 1행제거
df <- df1[-1,]
head(df)
#암종류확인
unique(df$'암종별')
df2 <- df %>% filter(암종별 != "모든 암(C00-C96)") %>% 
              filter(연령별 == "계")

unique(df2$'암종별')
df2

df21 <- df2 %>% filter(성별 == "계") 
df21

df22 <- df2 %>% filter(성별 != "계")
df22
# 5) 특정행 열 가져오기
df21 <- df21[, c('암종별','발생자수')]
df21
df22 <- df22[, c('암종별','성별','발생자수')]
df22

# 5) 특정행 열 조회
df[1:3,c('암종별','발생자수')]


# 열 데이터 타입 확인
 str(df21)

# 값 변경 : - => 0
df$발생자수 <- ifelse(df$발생자수 == "-","0",df$발생자수)
df$조발생률 <- ifelse(df$조발생률 == "-","0",df$조발생률) 
# 열 데이터타입 변경
df$발생자수 <- as.numeric(df$발생자수)
df$조발생률 <- as.numeric(df$조발생률)
str(df22)
str(df21)
str(df)
# 모든암 제거하고 연령별이 계인 데이터 
 
# 그래프  
library(ggplot2)

plot(df21$발생자수,type="o",col="red",xlab='',data=df22)
 
  
  
