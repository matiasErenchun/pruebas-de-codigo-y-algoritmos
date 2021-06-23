import socket

mi_socket = socket.socket()
mi_socket.bind(('localhost', 8000))
mi_socket.listen(5)

print("hola1")
while True:
    conexion, addr = mi_socket.accept()
    print("hola2")
    print(addr)

    respuesta = conexion.recv(1024)
    print(respuesta)

