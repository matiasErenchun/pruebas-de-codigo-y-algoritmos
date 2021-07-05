from cryptography.fernet import Fernet

def genera_clave():
	clave = Fernet.generate_key()
	with open("clave.key","wb") as archivo_clave:
		archivo_clave.write(clave)

genera_clave()
