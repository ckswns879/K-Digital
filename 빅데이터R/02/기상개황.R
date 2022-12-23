# • https://kosis.kr/statHtml/statHtml.do?orgId=735&tblId=DT_A1040&vw_cd=M
# T_ZTITLE&list_id=215_215A_735_73503_A&seqNo=&lang_mode=ko&languag
# e=kor&obj_var_id=&itm_id=&conn_path=MT_ZTITLE
dfdi <- read.csv("02_기상개황.csv",header = T, stringsAsFactor = F,fileEncoding = 'euc-kr')
head(dfdi)
names(dfdi)
#필요한열추출
dfdi <- dfdi[c("월별.1.","평균기온....","평균상대습도....")]
names(dfdi) <- c("월별","평균기온","상대습도")
dfdi
# • 불쾌지수 공식
# ◦ DI = 0.81 * Ta + 0.01 * RH(0.99 * Ta - 14.3) + 46.3
# ▫ DI: 불쾌지수
# ▫ Ta: 건구온도(평균기온)
# ▫ RH: 상대습도(평균상대습도)

dfdi$불쾌지수 <- (0.81*dfdi$평균기온)+(0.01*dfdi$상대습도*(0.99*dfdi$평균기온-14.3))+46.3
dfdi

dfdi$불쾌지수단계 <- ifelse(dfdi$불쾌지수 >= 80,"매우높음",
                    ifelse(dfdi$불쾌지수 >= 75,"높음",
                      ifelse(dfdi$불쾌지수 >= 68,"보통","낮음")))
dfdi
#연간자료 제외
dfdi <- dfdi[2:13, ]
#불쾌지수단계 테이블
dfdit <- table(dfdi$불쾌지수단계)
class(dfdit)
barplot(dfdit)

dfdit2 <- as.data.frame(dfdit)
class(dfdit2)
dfdit2
# • 불쾌지수 단계
# ◦ 매우높음: 80이상
# ◦ 높음: 75이상 80미만
# ◦ 보통: 68이상 75미만
# ◦ 낮음: 68미만

install.packages("ggplot2")
library(ggplot2)


  ggplot(mapping =aes(x=월별, y=불쾌지수, fill=월별), data=dfdi) +
    geom_bar(stat="identity", position=position_dodge()) +
    ggtitle("불쾌지수")+
    theme(plot.title = element_text(hjust = 0.5,size=20,face='bold'))
  