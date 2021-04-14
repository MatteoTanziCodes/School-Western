import socket
import os
BUFFER_SIZE = 4096
SERVER_HOST = socket.gethostname()
SERVER_PORT = 8080

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.bind((SERVER_HOST, SERVER_PORT))
s.listen(1)

print(f"[*] Listening as {SERVER_HOST}:{SERVER_PORT}")

while True:
    client_socket, address = s.accept()
    try:
        request = client_socket.recv(BUFFER_SIZE).decode()
        start = request.find("/") + 1
        end = request.find("H")
        filename = request[start:end]
        extension = filename.split(".")[1]

        if request.find("GET") == -1:
            raise Exception(client_socket.send(b'HTTP/1.1 501 Method Not Implemented:\r\n Invalid method in request.'))
        elif request.find("HTTP/1.1") == -1:
            raise Exception(client_socket.send(b'HTTP/1.1 505 Version Not Supported:\r\n This web server only supports HTTP/1.1.'))

        f = open(filename, 'rb')
        filesize = os.path.getsize(filename)
        if filesize > 0:
            client_socket.send(b'HTTP/1.1 200 OK\r\n')
            client_socket.send(b'The filesize is ' + str(filesize).encode() + b'\r\n')
            client_socket.send(b'The content type is ' + extension.encode())
            l = f.read(BUFFER_SIZE)
            while l:
                client_socket.send(l)
                l = f.read(BUFFER_SIZE)
            f.close()
            client_socket.close()
        else:
            client_socket.send(b'EMPTY FILE')

    except IOError:
        client_socket.send(b'HTTP/1.1 404 Not found:\r\n The requested document does not exist on this server.')
        client_socket.close()
        s.close()
