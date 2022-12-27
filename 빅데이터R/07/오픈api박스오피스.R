#json처리하는데 많이 사용하는 패키지
install.packages("jsonlite") 
library(jsonlite)

#일일박스 오피스 자료 가져오기
#url <- "https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=f5eef3421c602c6cb7ea224104795888&targetDt=20221220"

box <- function(dt) {
  apikey <- "f5eef3421c602c6cb7ea224104795888"
  url <- paste("http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?",
               "key=", apikey ,
               "&targetDt=" , dt, sep="")  
  mv <- fromJSON(url) 
  
  #박스오피스 목록 추출
  BoxOfficeList <- mv$boxOfficeResult$dailyBoxOfficeList
  return (BoxOfficeList)
}

box("20221221")


#데이터형변환 : 수치데이터 변환
names(BoxOfficeList)
str(BoxOfficeList)

col <- c("rnum", "rank", "rankInten",
         "salesAmt", "salesShare", "salesInten" ,
         "salesChange","salesAcc",
         "audiCnt" ,"audiInten","audiChange",
         "audiAcc","scrnCnt","showCnt")


for (c in col ){
  BoxOfficeList[c] <- as.numeric(unlist(BoxOfficeList[c]))
}

# 매출평균보다 매출이 높은 영화
library(dplyr)
BoxOfficeList %>%
  filter(salesAmt > mean(salesAmt) ) %>%
  select(rank, movieNm, salesAmt)

mean(BoxOfficeList$salesAmt)


plot(BoxOfficeList$salesAmt,
     type='o',
     col ='blue',
     main = '박스오피스 매출액',
     xlab='', ylab='')

barplot(BoxOfficeList$salesAmt)


library(ggplot2)
ggplot(data=BoxOfficeList, aes(x=reorder(movieNm,rank) , y=audiAcc, fill=movieNm)) +
  geom_bar(stat="identity") +
  theme(axis.text.x=element_text(angle=90, hjust=1), legend.position = "none")
