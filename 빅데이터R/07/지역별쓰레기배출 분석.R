
getdata <- function(year,m){
  apikey <- "I0v72Y%2Bf9Tyi6eUtR7V4ZLgCOxxTruWnt7gKZ39icrFpCwW85%2B40%2FxvOuPjAHXUoHvDjMCJrE2zmO4Pq6Yptcw%3D%3D"
  c <-"W10"
  d <- "2020"
  
  url2 <- paste("http://apis.data.go.kr/B552584/RfidFoodWasteServiceNew/getCityTimeList?",
  "ServiceKey=",apikey,
  "&type=json&page=1&rowNum=24",
  "&disYear=",year,
  "&disMonth=",m,
  "&cityCode="
  ,c,sep="")
  url2
  tr <- fromJSON(url2)
  tr
  class(tr)
  mode(tr)
  x <- tr$data$list
  return (x)
}

#년도를 입력받아 데이터출력
df2020 <-getdata("2020",m)
df2021 <-getdata("2021",m)
df2022 <-getdata("2022",m)

#데이터합치기
df <- rbind(df2020,df2021,df2022)
df
names(df)
#1~12월 추출
dft2022 <- data.frame();
dft2021 <- data.frame();
dft2020 <- data.frame();
for (i in 1:12){
  if (i < 10) { m = paste("0", i, sep="")}
  else m = as.character(i);

  temp2022 <- getdata("2022", m)
  temp2021 <- getdata("2021", m)
  temp2020 <- getdata("2020", m)
  dft2022 <- rbind(dft2022, temp2022)
  dft2021 <- rbind(dft2021, temp2021)
  dft2020 <- rbind(dft2020, temp2020)
}

dft <- rbind(dft2020,dft2021,dft2022)

library(dplyr)
#데이터열생성
#mutate = 열생성
# df <- df%>% mutate(disweek = case_when(disDay == 1 ~ "일",
#                                        disDay == 2 ~ "월",
#                                        disDay == 3 ~ "화",
#                                        disDay == 4 ~ "수",
#                                        disDay == 5 ~ "목",
#                                        disDay == 6 ~ "금",
#                                        disDay == 7 ~ "토"))

#시간별배출량 그래프(평균배출량선추가)
library(ggplot2) 
ggplot(mapping =aes(x=disHour, y=disQuantity, fill=disYear), data=df) +
  geom_bar(stat="identity", position=position_dodge()) +
  ggtitle("시간별배출량")+
  geom_hline(yintercept = mean(df$disQuantity),col ="red")
theme(plot.title = element_text(hjust = 0.5,size=20,face='bold'))

#월별배출량 그래프
ggplot(mapping =aes(x=disMonth, y=disQuantity, fill=disYear), data=dft) +
  geom_bar(stat="identity", position=position_dodge()) +
  ggtitle("월별배출량")+
  geom_hline(yintercept = mean(df$disQuantity),col ="red")
theme(plot.title = element_text(hjust = 0.5,size=20,face='bold'))


