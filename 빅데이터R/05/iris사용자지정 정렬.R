# Iris 데이터 불러오기
library(datasets)

data(iris)
unique(iris$Species)

# 사용자지정 정렬을 위한 vector 생성
order_v <- c('versicolor', 'setosa', 'virginica')

# Species 컬럼을 factor 자료형으로 변경하고, levels에 사용자지정 정렬용 벡터 지정
iris$Species <- factor(iris$Species, levels = order_v)
unique(iris$Species)

# iris$Species <- ordered(iris$Species, levels = order_v)
# unique(iris$Species)

# 정렬하기
iris_orderd <- iris[order(iris$Species, decreasing = FALSE), ]