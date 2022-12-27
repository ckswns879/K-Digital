#한국환경공단_에어코리아_대기오염통계 현황
#시도별 실시간 평균정보 조회 상세기능명세
#최근 한달간 지역별 일평균 대기오염 정보
#기준초과인경우 
#https://www.airkorea.or.kr/web/contents/contentView/?pMENU_NO=132&cntnts_no=6

#json처리
install.packages("jsonlite") 
library(jsonlite)

#데이터 가져오기함수
getData <- function(item, gubun) {
  apikey <- "I0v72Y%2Bf9Tyi6eUtR7V4ZLgCOxxTruWnt7gKZ39icrFpCwW85%2B40%2FxvOuPjAHXUoHvDjMCJrE2zmO4Pq6Yptcw%3D%3D"
  
  url <- paste("http://apis.data.go.kr/B552584/ArpltnStatsSvc/getCtprvnMesureLIst?",
               "itemCode=",item,
               "&dataGubun=",gubun,
               "&searchCondition=MONTH",
               "&pageNo=1",
               "&numOfRows=100",
               "&returnType=json",
               "&serviceKey=",apikey ,sep="")
  url
  a <- fromJSON(url)
  a
  class(a)
  mode(a)
  
  
  x <- a$response$body$items
  x
  return (x)
}

#자료처리
library(dplyr)


#1. pm10, O3 데이터 추출하여 합치기
O3 <- getData("O3","DAILY")
O3
PM10 <- getData("PM10","DAILY")
PM10

df <- bind_rows(O3,PM10)
df
names(df)

#2. 지역명 벡터
area <- c("seoul", "busan", "daegu","incheon","gwangju",
          "daejeon", "ulsan", "gyeonggi", "gangwon",
          "chungbuk", "chungnam", "jeonbuk", "jeonnam",
          "gyeongbuk", "gyeongnam", "jeju", "sejong")

areaname <- c("서울","부산","대구","인천","광주","대전",
              "울산","경기","강원","충북","충남","전북",
              "전남","경북","경남","제주","세종")

length(area)
length(areaname)
names(df)





#3. 통합데이터프레임 만들기
 dft <- data.frame()
for(i in 1:length(area)){
  temp <- c()
  temp$dataTime <- unlist(df["dataTime"])
  temp$itemCode <- unlist(df["itemCode"])
  temp$area <- areaname[i]
  temp$item <- unlist(df[area[i]])
  
  dft <- bind_rows(dft,temp)
}
 dft
 
#. 통합데이터프레임 만들기2
 dft2 <- data.frame()
 for(i in 1:length(area)){
   t<-df[c("dataTime","itemCode",area[i])]
   t$area <- areaname[i]
   names(t) <- c("dataTime","itemCode","item","area")
   dft2 <- bind_rows(dft,t)
 }
dft2 
str(dft)
dft$item <- as.numeric(dft$item)
#4.주의보
#https://www.airkorea.or.kr/web/dustForecast?pMENU_NO=113
 

#5.일자별 주의보정보
# 등급
#       좋음	보통	나쁨	매우나쁨
# PM10	0~30	31~80	81~150	151 이상
# 예측농도
# O3   	0~0.030	0.031~0.090	0.091~0.150	0.151 이상
dft$기준 <- ifelse(dft$itemCode == "PM10",
                ifelse(dft$item <=30,"좋음",ifelse(dft$item <= 80,"보통",ifelse(dft$item <=150, "나쁨","매우나쁨"))),
                ifelse(dft$item <=0.03,"좋음",ifelse(dft$item <= 0.09,"보통",ifelse(dft$item <=0.15, "나쁨","매우나쁨"))))


names(dft)
dfn <- table(dft$dataTime, dft$기준)
dfn1 <- as.data.frame.matrix(dfn)
dfn2 <- as.data.frame(dfn)

dfn
library(ggplot2) 
names(dfn2) <- c("일자", "기준","기준수")
ggplot(dfn2, aes(x=일자, y =기준수, group=기준, color=기준)) +
  geom_line() +
  geom_point() +
  labs(x="", y="")+
  theme(axis.text.x=element_text(angle=90, hjust=1))

#5.지역별 PM10이 좋음인 날 수 
names(dft)
dfPM10 <- dft %>% filter(dft$기준=='좋음'& dft$itemCode == 'PM10')

ggplot(dfPM10, aes(x=area, y =기준, fill=area)) +
  geom_bar(stat="identity") +
  labs(x="", y="")+
  theme(axis.text.x=element_text(angle=90, hjust=1))
