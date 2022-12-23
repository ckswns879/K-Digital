# 기상개황 자료를 이용하여 월별 불쾌지수를 계산하고 불쾌지수가 높음이상인 월을 구하시오.
# 
# https://kosis.kr/statHtml/statHtml.do?orgId=735&tblId=DT_A1040&vw_cd=MT_ZTITLE&list_id=215_215A_735_73503_A&seqNo=&lang_mode=ko&language=kor&obj_var_id=&itm_id=&conn_path=MT_ZTITLE
# 
df <- read.csv("기상개황_20221216174602.csv",header = T, fileEncoding = 'euc-kr')
df
df <- df[-c(1:2), ]
df
names(df) <- c('월별','평균기온','평균상대습도')
head(df)
df
# 불쾌지수 공식
# 
# DI = 0.81 * Ta + 0.01 * RH * (0.99 * Ta - 14.3) + 46.3
# DI: 불쾌지수
# Ta: 건구온도(평균기온)
# RH: 상대습도(평균상대습도)
str(df)
df$평균기온 <- as.numeric(df$평균기온)
df$평균상대습도 <- as.numeric(df$평균상대습도)
df$불쾌지수 <- 0.81 * df$평균기온 + 0.01 * df$평균상대습도 * (0.99 * df$평균기온-14.3) + 46.3
df
# 불쾌지수 단계
# 
# 매우높음: 80이상
# 높음: 75이상 80미만
# 보통: 68이상 75미만
# 낮음: 68미만
df$불쾌지수단계 <- ifelse(df$불쾌지수 >= 80,"매우높음",
             ifelse(df$불쾌지수 >= 75,"높음",
                    ifelse(df$불쾌지수 >= 68,"보통","낮음")))
df

library(ggplot2)
plot(df)
