#json처리에 많이사용하는패키지
install.packages("jsonlite")
library(jsonlite)
box <- function(dt){
  apikey <- "f5eef3421c602c6cb7ea224104795888"
  #paste함수사용시 마지막에 sep=""추가
  url <- paste("http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?",
               "key=",apikey,
               "&targetDt=" , dt, sep="")

  mv <- fromJSON(url)

  
  #박스오피스 목록추출
  BoxofficeList <- mv$boxOfficeResult$dailyBoxOfficeList
  
  return (BoxofficeList)
}
box("20221221")

#데이터형변환 : 수치데이터 변환
names(BoxofficeList)
str(BoxofficeList)

col <- c("rnum","rank","rankInten","rankOldAndNew","movieCd",
         "salesAmt","salesShare","salesInten","salesChange","salesAcc","audiCnt","audiInten",
         "audiChange","audiAcc","scrnCnt","showCnt")
col
#리스트를 벡터화 시키려고 unlist사용
for (c in col){
  BoxofficeList[c] <- as.numeric(unlist(BoxofficeList[c]))
}
#매출평균보다 매출이높은영화
library(dplyr)

BoxofficeList %>% filter(salesAmt > mean(salesAmt)) %>% select(rank, movieNm, salesAmt)

mean(BoxofficeList$salesAmt)


