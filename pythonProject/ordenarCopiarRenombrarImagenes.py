import os
import cv2


def crear_carpeta(b):
    success = False
    try:
        os.mkdir(b)
        success = True
    except FileExistsError:
        print("el directorio ya existe")
    except FileNotFoundError:
        print("error en la ruta")
    return success


directorioBase = "D:\\fotos pajaros\\imagenes"

nombreDir = []
with os.scandir(directorioBase) as carpetas:
    for carpeta in carpetas:
        nombreDir.append(carpeta.name)
print(nombreDir)
if crear_carpeta("D:\\fotos pajaros\\train"):
    for dirActual in nombreDir:
        print(directorioBase + '\\' + dirActual)
        misDir = os.listdir(directorioBase + '\\' + dirActual)
        print(misDir)
        i = 0
        for imagenActul in misDir:
            imagen = cv2.imread(directorioBase + '\\' + dirActual + '\\' + imagenActul, -1)
            cv2.imshow('imagen', imagen)
            cv2.imwrite("D:\\fotos pajaros\\train\\%s%i.jpg" % (dirActual, i), imagen)
            i += 1
            cv2.destroyWindow('imagen')
