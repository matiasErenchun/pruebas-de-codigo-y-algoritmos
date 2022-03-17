import os
from time import sleep

import cv2


# la funcion recibe un string que representa la ruta y carpeta crear
# al ejecutar la funccion retorna la variable sucess con una valor True si se creo la carpeta
# en caso de que ocurriera algun error retornra success con un valor False.
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


directorioBase = "D:\\DataSets\\imagenes"

nombreDir = []
with os.scandir(directorioBase) as carpetas:
    for carpeta in carpetas:
        nombreDir.append(carpeta.name)
print(nombreDir)
if crear_carpeta("D:\\fotos pajaros\\train"):
    for dirActual in nombreDir:
        print(directorioBase + '\\' + dirActual)
        if crear_carpeta("D:\\fotos pajaros\\train\\" + dirActual):
            misDir = os.listdir(directorioBase + '\\' + dirActual)
            print(misDir)
            for dirImageActual in misDir:
                misImagenes = os.listdir(directorioBase + '\\' + dirActual + '\\' + dirImageActual)
                print(directorioBase + '\\' + dirActual + '\\' + dirImageActual)
                print(misImagenes)
                i = 0
                for imagenActul in misImagenes:
                    print(directorioBase + '\\' + dirActual + '\\' + dirImageActual + '\\' + imagenActul)
                    imagen = cv2.imread(directorioBase + '\\' + dirActual + '\\' + dirImageActual + '\\' + imagenActul,
                                        -1)
                    '''
                     cv2.imshow("mi imageen ", imagen)
                    key = cv2.waitKey(20)
                    if key == 27:  # exit on ESC
                        break
                    '''
                    ruta = "D:\\fotos pajaros\\train\\" + dirActual + "\\" + dirImageActual + str(i) + ".jpg"
                    cv2.imwrite(ruta, imagen)
                    i += 1
else:
    ra = os.listdir("D:\\fotos pajaros\\train\\rapaz")
    nora = os.listdir("D:\\fotos pajaros\\train\\no rapaz")
    print("total rapaces:"+str(len(ra)))
    print("total NO rapaces:" + str(len(nora)))