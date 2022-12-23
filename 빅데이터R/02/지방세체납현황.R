getwd()

dftax <- read.csv("02_부산광역시_지방세 체납현황.csv",header = T, stringsAsFactor = F,fileEncoding = 'euc-kr')
dftax
head(dftax)
names(dftax)
dftax <- dftax[c("과세년도","세목명","체납액구간","누적체납건수","누적체납금액")]
dftax
#세목명
cols <- unique(dftax$세목명)
cols

#과세년도 범주형 
dftax$과세년도 <- as.factor(dftax$과세년도)

#함수 만들기
install.packages("ggplot2")
library(ggplot2)


makedf <- function(item){
  temp <- subset(dftax, dftax$세목명 == item)
  ggplot(mapping =aes(x=과세년도, y=누적체납건수, fill=과세년도), data=dftax) +
    geom_bar(stat="identity", position=position_dodge()) +
    ggtitle(item)+
    theme(plot.title = element_text(hjust = 0.5,size=20,face='bold'))
}


makedf("지방소득세")
makedf("자동차세")




ggplot(mapping =aes(x=시도명, y=세목명, fill=세목명), data=dftax) +
  geom_bar(stat="identity", position=position_dodge()) +
  ggtitle('년도별 사고건수')+
  theme(plot.title = element_text(hjust = 0.5,size=20,face='bold'))