import tensorflow as tf
import numpy as np
import os
import cv2
from keras.preprocessing.image import img_to_array, ImageDataGenerator, image
import matplotlib.pyplot as plt
import threading


class camThread(threading.Thread):

    def __init__(self, previewName, camID):
        threading.Thread.__init__(self)
        self.previewName = previewName
        self.camID = camID

    def run(self):
        print("Starting " + self.previewName)
        camPreview(self.previewName, self.camID)


def camPreview(previewName, camID):
    cv2.namedWindow(previewName)
    cam = cv2.VideoCapture(camID)
    if cam.isOpened():  # try to get the first frame
        rval, frame = cam.read()
    else:
        rval = False

    while rval:
        cv2.imshow(previewName, frame)
        rval, frame = cam.read()
        key = cv2.waitKey(20)
        if key == 27:  # exit on ESC
            break
    cv2.destroyWindow(previewName)


def capturarimagen():
    captura = cv2.VideoCapture(0)
    captura.set(cv2.CAP_PROP_FRAME_WIDTH, 800)
    captura.set(cv2.CAP_PROP_FRAME_HEIGHT, 600)
    ret, frame = captura.read()
    cv2.imwrite('D:\\datos_capturados\\imagen1.jpg', frame)
    captura.release()


def miprimera_red():
    a = False
    if (a):
        variable = tf.constant([[30, 20], [10, 45]])
        num_tensor = tf.constant(np.array([[12, 10], [10, 30]]))  # creamos arreglo con numpy y luego lo tranformamos en
        # tensor
        tff = tf.Variable(
            [[1., 2.], [5., 6.]])  # si lsod atos se requieren cambiar  se nesesita usar el metodo Variable()
        print(tff)
        tff[1, 1].assign(120)
        print(tff)
        tff = tff + 2.
        print(tff)
        tff = np.sqrt(tff)
        print(tff)
        ts = tf.Variable("hola")  # los string pueden ser variables o enteros segun corresponda.
        print(ts)
        tsaux = tf.strings.unicode_decode(ts, "UTF8")
        print(tsaux)
    else:
        # https://www.tensorflow.org/datasets/catalog/mnist
        mnist = tf.keras.datasets.mnist

        (x_train, y_train), (x_test, y_test) = mnist.load_data()
        x_train, x_test = x_train / 255.0, x_test / 255.0

        model = tf.keras.models.Sequential([
            tf.keras.layers.Flatten(input_shape=(28, 28)),
            tf.keras.layers.Dense(128, activation='relu'),
            tf.keras.layers.Dropout(0.2),
            tf.keras.layers.Dense(10, activation='softmax')
        ])

        model.compile(optimizer='adam',
                      loss='sparse_categorical_crossentropy',
                      metrics=['accuracy'])

        model.summary()

        model.fit(x_train, y_train, epochs=5)
        model.evaluate(x_test, y_test)


def preprocesarImagenes():
    carpeta_datos_aumentados = 'datos_aumentados'
    cantidad_de_imagenes = 5
    try:
        os.mkdir(carpeta_datos_aumentados)
    except:
        print('Error al crear carpeta, puede que la carpeta ya exista')

    generadorDeDatos = ImageDataGenerator(
        rotation_range=20,
        zoom_range=0.2,
        width_shift_range=0.1,
        height_shift_range=0.1,
        horizontal_flip=True,
        vertical_flip=False)
    carpeta_datos = "E:\\repoGit\\memoria\\experimentos_memoria\\imagenes"
    lista_carpetas = os.listdir(carpeta_datos)
    print(lista_carpetas)
    ancho_imagen, alto_imagen = 1000, 800
    path = carpeta_datos + '\\' + lista_carpetas[0]
    print('path:' + path)
    lista_imagenes = os.listdir(path)
    print("lista_imagenes:")
    print(lista_imagenes)
    i = 0
    num_imagenes = 0
    plt.figure(figsize=(20, 30))
    for imagen_file in lista_imagenes:
        imagen_path = path + "\\" + imagen_file
        imagen = cv2.imread(imagen_path, -1)
        imagen_resize = cv2.resize(image.img_to_array(imagen), (ancho_imagen, alto_imagen),
                                   interpolation=cv2.INTER_AREA)
        x = imagen_resize / 255
        x = np.expand_dims(x, axis=0)
        t = 1
        for output_batch in generadorDeDatos.flow(x, batch_size=1):
            a = image.img_to_array(output_batch[0])
            imagen_out = output_batch[0, :, :] * 255
            # ojo al escribir imagenes tenemso que tener claro el formato de color actual y con cual queremos leerlo
            # aqui dejo la documentacion:https://docs.opencv.org/3.4/d8/d01/group__imgproc__color__conversions.html
            imagen_final = cv2.cvtColor(imagen_out, cv2.COLOR_BGR2BGRA)
            cv2.imwrite(carpeta_datos_aumentados + "\\%i%i.jpg" % (i, t), imagen_final)
            t += 1
            num_imagenes += 1
            if (t > cantidad_de_imagenes):
                break
        i += 1
    print("se crearon:%i" % num_imagenes)


if __name__ == '__main__':
    print("hola")
    #preprocesarImagenes()
    #capturarimagen()
    thread1 = camThread("Camera 1", 0)
    #thread2 = camThread("Camera 2", 1)
    thread1.start()
    #thread2.start()
