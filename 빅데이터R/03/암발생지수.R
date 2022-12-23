getwd()
df2 <- read.csv("03_암발생자수_.csv",header = T, stringsAsFactor = F,fileEncoding = 'euc-kr')
df2
head(df2)
names(df2)
# 연령대별 성별 암발생자 현황분석
# • 모든 암에 대한 자료만 추출
# ◦ dplyr 패키지 filter
# • 연령대 별 그룹핑
# ◦ dplyr 패키지 group_by
# • 행인덱스 초기화
# ◦rownames(df) <- NULL

install.packages("dplyr")
library(dplyr)

#열명
names(df2) = c('암종별','성별','연령별','2019년','2019년_1')
df2

#모든암보기
df21 <- subset(df2, df2$암종별 == '모든 암(C00-C96)')
df21

#필터로 모든암보기 %in%으로'계','연령미상' 아닌것만보기 !()
df22 <- df2%>%filter(df2$암종별 == '모든 암(C00-C96)'& 
                       !(연령별 %in% c('계','연령미상')))
df22


unique(df22$연령별)

df22$연령대 <- ifelse(df22$연령별 %in% c("0-4세","5-9세","10-14세","15-19세","20-24세","25-29세","30-34세","35-39세"), "30대이하",
                   ifelse(df22$연령별 %in% c("40-44세","45-49세","50-54세","55-59세"), "40대~50대",
                           ifelse(df22$연령별 %in% c("60-64세","65-69세","70-74세","75-79세"),"60대~70","80대이상")))
unique(df22$연령대)
names(df22) <- c('암종별','성별','연령별','y2019년','y2019년_1')
class(df22)
df22$y2019년 <- as.numeric(df22$y2019년)

df22g <- df22 %>% group_by(연령대, 성별) %>% summarise(계 = sum(y2019년))

install.packages("ggplot2")
library(ggplot2)
ggplot(mapping=aes(x=연령대, y=계,fill=성별),data=df21g)
       geom_bar(stat='identity',position = position_dodge())
  ggtitle("연령대별 성별 분석")+
    theme(plot.title = element_text(hjust = 0.5,size=20,face='bold'))
  
qplot(연령대, data=df22, fill=연령대)+
ggtitle("연령대별 성별 분석")


