import socket
import time

a = 0
while a < 10:
    mi_socket = socket.socket()
    mi_socket.connect(('localhost', 8000))
    minsaje = " hola" + str(a)
    mi_socket.send(str.encode(minsaje))
    a += 1
    print("Start : %s" % time.ctime())
    time.sleep(1)
    print("End : %s" % time.ctime())
    mi_socket.close()

