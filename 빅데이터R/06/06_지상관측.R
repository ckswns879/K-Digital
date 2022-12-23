getwd()
setwd('C:/Rwork/06')
#자료 불러오기
df <- read.csv('06_지상관측.csv', header = T, stringsAsFactors = F, fileEncoding = 'euc-kr')
df

# • 열명을 '지점', '지점명', '일시', '기온', '풍속','상대습도'로 변경하여 작성하시오.
names(df) <- c('지점', '지점명', '일시', '기온', '풍속','상대습도')
names(df)

head(df)
summary(df)

# • 체감온도 = 13.12 + 0.6215*T – 11.37*V*0.16 + 0.3965*V*0.16*T 
# T : 기온(°C), V : 풍속(km/h)
df$체감온도 <- 13.12 + 0.6215*df$기온 - 11.37 * df$풍속 * 0.16 + 0.3965 * df$풍속 * 0.16 * df$기온
df$체감온도


# • 겨울철 체감온도는 기온 10°C 이하, 풍속 1.3
# m/s 이상인 날이다. 부산지역의 겨울철
# 체감온도에 해당되는 자료를 추출하여
# 그래프를 그리시오.
library(ggplot2)
df2 <- subset(df, df$기온 <= 10 & df$풍속 >= 1.3 & df$지점명 == '부산')
df2

ggplot(mapping =aes(x=일시, y=체감온도, fill=일시), data=df2) +
  geom_bar(stat="identity", position=position_dodge()) +
  ggtitle("겨울철체감온도")+
  theme(plot.title = element_text(hjust = 0.5,size=20,face='bold'))


# • 열지수 = HI = 0.5 * {T + 61.0 + [(T-68.0)*1.2] + (RH*0.094)}
# ◦ T : 기온(°C), RH : 상대습도(%)
df$열지수 <- 0.5 * (df$기온 + 61.0 + ((df$기온-68.0)*1.2)+(df$상대습도*0.094))
df$열지수
df
# 지점명이 ’서울‘,’부산‘,’제주‘인 자료를 추출
df3 <- subset(df, df$지점명 == '서울' | df$지점명 == '부산' | df$지점명 == '제주')

# 일자별 기온그래프 : 평균기온을 수평선으로 표시
ggplot(mapping =aes(x=일시, y=기온, fill=지점명), data=df3) +
  geom_bar(stat="identity", position=position_dodge()) +
  ggtitle("일자별 기온그래프")+
  geom_hline(yintercept = mean(df$기온),col ="red")
  theme(plot.title = element_text(hjust = 0.5,size=20,face='bold'))
  
# 일자별 열지수 그래프 : 열지수 5를 수평선으로 표시
  ggplot(mapping =aes(x=일시, y=열지수, fill=지점명), data=df3) +
    geom_bar(stat="identity", position=position_dodge()) +
    ggtitle("일자별 열지수 그래프")+
    geom_hline(yintercept = (df$열지수=5),col ="red")
  theme(plot.title = element_text(hjust = 0.5,size=20,face='bold'))
  

    plot(df$기온,type="o")
  
  
  
# 일자별로 그룹핑하여 열지수가 5이하인 자료를추출하시오.
    library(dplyr)
subset(df,df$열지수 <= 5)
    
df4 <- df %>% group_by(df$일시,df$열지수 <= 5) %>% summarize(count = n())
df4











