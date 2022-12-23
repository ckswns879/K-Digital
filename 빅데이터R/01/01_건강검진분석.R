# 파일 위치 설정
getwd()
setwd("C:/Rwork/01")

# 해결문제 
# BMI는 몸무게와 키를 이용하여 체지방율을 측정하는 지수이다. 
# 자신의 몸무게와 키를 각각 변수 weight와 height에 저장하고 BMI지수를 계산해 본다. 
# 단, 키는 cm로 입력 받아서 처리한다.
# 
# BMI = 체중(kg) / (키(m) x키(m))

# 키와 몸무게 scala입력, BMI 계산
weight <- scan()
height <- scan()
BMI <- weight / (height / 100) ** 2

print("키와 몸무게 입력")
data <- scan()
BMI = data[2] / (data[1] / 100) ** 2

# 키와 몸무게 vector입력
# 문자열 입력
print("키와 몸무게 입력")
data <- readline()
data

# 데이터 타입 확인
mode(data)

# stringr패키지 설치
install.packages("stringr")

# 실습: 설치된 패키지 확인
installed.packages()

# 실습: 패키지 로드 
library(stringr)

# 문자열 분리
data <- str_split(data, " ")

#mode로 확인해보니 list형이네
# 자료형 확인
mode(data)

# 단계 1: 문자열 벡터 만들기
# list를 vector형으로 변환함
data <- unlist(data)

# 벡터인지 확인
is.vector(data)

# 숫자 벡터로 변경
data <- as.numeric(data)

#계산
height <- data[1]
weight < data[2]
BMI <- weight / (height / 100) ** 2



# 데이터프레임 입력
df <- data.frame()
mode(df)
class(df)

df <- edit(df)

# 데이터프레임 열명 변경
names(df) <- c("키", "몸무게")

# 데이터의 BMI 계산
df$BMI <- df$몸무게 / (df$키 / 100) ** 2


# 해결문제 
# 국민건강보험공단 자료를 이용하여 BMI와 비만도를 구하시오.
# 비만도 
# 저체중 :	20미만
# 정상 :	20이상 24미만
# 과체중 :	25이상 29미만
# 비만 :	30이상

#빈도 테이블
dfbmi <- read.csv("./01_국민건강보험공단500.csv", sep = ",", header = TRUE, fileEncoding = "euc-kr")

#BMI생성
dfbmi$BMI <- bohum$체중/(bohum$신장/100)**2

#비만도 분석
dfbmi$비만도 <- ifelse(dfbmi$BMI < 20, "저체중",
                    ifelse(dfbmi$BMI < 25, "정상",
                           ifelse(dfbmi$BMI < 30, "과체중", "비만"
                           )))

# 빈도 테이블 저장
table(dfbmi$성별)
table(dfbmi$비만도, dfbmi$성별)

# 비만도 저장
write.csv(table(dfbmi$비만도, dfbmi$성별), "비만도.csv", fileEncoding = "euc-kr", quote = F)
# 원본 자료 저장
write.csv(dfbmi, "dfbmi.csv", fileEncoding = "euc-kr", quote = F)


# 해결문제
# # 국민건강보험 관리공단의 건강검진 자료를 이용하여 대사증후군을 판별하시오.
# 높은 혈압(130/85mmHg 이상)
# 높은 혈당(공복 혈당 100mg/dL 이상)
# 높은 중성지방(트리글리세라이드 150mg/dL 이상)
# 낮은 HDL 콜레스테롤 수치(남성은 40mg/dL 미만, 여성은 50mg/dL 미만)
# 복부 비만(남성 90cm 이상, 여성 85cm 이상) 판별
# 0 : 정상, 1~2 : 주의군, 3~5 : 위험군

#빈도 테이블
healthe <- read.csv("./국민건강보험공단_건강검진정보_20211229.csv", header = TRUE, fileEncoding = "euc-kr")
head(healthe)

# 열이름 확인
names(healthe)

# 필요한 열 추출
h <- healthe[c("성별코드", "허리둘레", "수축기.혈압", "이완기.혈압", "트리글리세라이드", "HDL.콜레스테롤", "식전혈당.공복혈당." )]
head(h)

# NA값 제거 하고 싶다
h <- na.omit(h)

#열이름 변경
names(h) <- c("성별코드", "허리둘레", "수축기혈압", "이완기혈압", "트리글리세라이드", "HDL콜레스테롤", "공복혈당" )

# • 높은 혈압(130/85mmHg 이상)
h$높은혈압 <- ((h$수축기혈압 >= 130) | (h$이완기혈압 >= 85 ))

# • 높은 혈당(공복 혈당 100mg/dL 이상)
h$높은혈당 <- (h$공복혈당 >= 100)

# • 높은 중성지방(트리글리세라이드 150mg/dL 이상)
h$높은중성지방 <- h$트리글리세라이드 >= 150

# • 낮은 HDL 콜레스테롤 수치(남성은 40mg/dL 미만, 여성은 50mg/dL 미만)
h$낮은콜레스테롤 <- ((h$성별코드 == 1) & (h$HDL콜레스테롤 < 40)) | ((h$성별코드 == 2) & (h$HDL콜레스테롤 < 50))

# • 복부 비만(남성 90cm 이상, 여성 85cm 이상)
h$복부비만 <- ((h$성별코드 == 1) & (h$허리둘레 >= 90)) | ((h$성별코드 == 2) & (h$허리둘레 >= 85))

# • 판별, 대사증후군 수
# ◦ 0 : 정상, 1~2 : 주의군, 3~5 : 위험군
h$대사증후군 <- h$높은혈압+
  h$높은혈당+
  h$높은중성지방+
  h$낮은콜레스테롤+
  h$복부비만

h$판별 <- ifelse(h$대사증후군 == 0, "정상", 
               ifelse(h$대사증후군 <= 2, "주의군", "위험군") )

# 보기 좋게 변경
h$성별 <- ifelse(h$성별코드 == 1, "남", "여")

# 성별 대사증후군
table(h$판별, h$성별)

#출력
write.csv(table(h$판별, h$성별), "성별 대사증후군.csv", fileEncoding = "euc-kr", quote = F)