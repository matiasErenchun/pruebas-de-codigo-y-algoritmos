import os

directorioBase = "D:\\fotos pajaros\\imagenes"

nombreDir=[]
with os.scandir(directorioBase) as carpetas:
    for carpeta in carpetas:
        nombreDir.append(carpeta.name)
print(nombreDir)
for dirActual in nombreDir:
    misDir=[]
    print(directorioBase+'\\'+dirActual)
    misDir = os.listdir(directorioBase+'\\'+dirActual)
    print(misDir)


