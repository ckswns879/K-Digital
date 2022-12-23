#경로설정
getwd()
setwd("C:/Rwork/02")
#엑셀파일불러오기 패키지다운
install.packages("readxl")
library(readxl)
#엑셀파일 보기
x <- read_xlsx(path = "02_역주행사고.xlsx")
x
#나누기
str(x)
x1 <- subset(x,구분 == "역주행")
x2 <- subset(x,구분 == "전체")
x1
x2
#index로나누기
df1 <- x[x$구분 == "전체",]
df2 <- x[x$구분 == "역주행",]
df1
df2
#일반교통사고
x3 <- x1
x3$구분 <- "일반"
x3

x3[c("사고","사망")] <- x2[c("사고","사망")] - x1[c("사고","사망")]
x3


#치명률구하기
x1$치명률 <- round(x1$사망 / x1$사고 * 100,2)
x2$치명률 <- round(x2$사망 / x2$사고 * 100,2)
x3$치명률 <- round(x3$사망 / x3$사고 * 100,2)

x2
#기초통계값
summary(x1)
summary(x2)

mean(x2$치명률)
mean(x3$치명률)

cat("최근 3년간 역주행 교통사고의 치명률이",
    round(mean(x2$치명률),1),
    "%로 일반 교통사고(",
    round(mean(x3$치명률),1),
    "%)보다",
    round(mean(x2$치명률),1) / round(mean(x3$치명률),1),
          "배 높은 것으로 나타났다.")

#그래프그리기
install.packages("ggplot2")
library(ggplot2)
ggplot(mapping =aes(x=년도, y=사고, fill=구분), data=x) +
  geom_bar(stat="identity", position=position_dodge()) +
  ggtitle('년도별 사고건수')+
  theme(plot.title = element_text(hjust = 0.5,size=20,face='bold'))


