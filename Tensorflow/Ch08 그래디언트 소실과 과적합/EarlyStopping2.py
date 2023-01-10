# step30_02
import tensorflow as tf
from tensorflow.keras.datasets import mnist
import numpy as np
import matplotlib.pyplot as plt

#1
(x_train, y_train), (x_test, y_test) = mnist.load_data()

#2: normalize images
x_train = x_train.astype('float32')
x_test = x_test.astype('float32')
x_train /= 255.0
x_test /= 255.0

#3: one-hot encoding
y_train = tf.keras.utils.to_categorical(y_train)
y_test = tf.keras.utils.to_categorical(y_test)

#4: x_train.shape = (60000, 28, 28)
model = tf.keras.Sequential()
model.add(tf.keras.layers.Flatten(input_shape=(28,28)))
model.add(tf.keras.layers.Dense(units=5, activation = 'sigmoid'))
model.add(tf.keras.layers.Dense(units=10, activation = 'softmax'))
## model.summary()

opt = tf.keras.optimizers.RMSprop(learning_rate=1.0)
model.compile(optimizer=opt, loss='categorical_crossentrpy', metrics=['accuracy'])

#5
callback = tf.keras.callbacks.EarlyStopping(monitor='val_loss',
                                              min_delta = 0.001, # 오차가 이거 이하면 최적화 된 것임
                                              patience=1,
                                              verbose=1,
                                              mode = 'auto')

#6
ret = model.fit(x_train, y_train, epochs=100, batch_size=200, 
                validation_split=0.2, verbose=2, callbacks=[callback])

# scheduler 함수는 learning_rate를 초반에는 크게, 나중에는 세밀하게 해주는 함수
# learning_rate는 loss의 변화량에 따라 가중치(w or weight)를 조절하는데 그것을 변화량 그대로 적용할 건지, 비율적으로 적용할 건지에 대한 숫자
def scheduler(epoch, lr):
  if epoch % 2 == 0 and epoch:
    return 0.1 * lr
  return lr
callback = tf.keras.callbacks.LearningRateScheduler(scheduler, verbose=1)


    