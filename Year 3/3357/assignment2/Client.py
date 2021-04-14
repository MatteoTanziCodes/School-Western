import socket
BUFFER_SIZE = 4096 # send 4096 bytes each time step

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)


# the ip address or hostname of the server, the receiver
host = input("HOSTNAME: \n")
# the port, let's use 5001
port = input("PORT: \n")
# the name of file we want to send, make sure it exists
file = input("FILE: \n")
extension = file.split(".")[1]
# Create Server var
server = "http://"+host+":"+port+"/"+file

print(f"[+] Connecting to {host}:{port}\n")
try:
    s.connect((host, int(port)))
    print("[+] Connected.\n")
except socket.error as e:
    print("failed to connect to socket\n")
    print(str(e))

s.send(b"GET /" + file.encode() + b"HTTP/1.1\r\nHost:" + server.encode()+b"\r\n\r\n")

with open('received_file.'+extension, 'wb') as f:
    print(s.recv(BUFFER_SIZE).decode())
    print(s.recv(BUFFER_SIZE).decode())
    while True:
        print("downloading\n")
        data = s.recv(BUFFER_SIZE)
        if not data:
            break
        # write data to a file
        f.write(data)

f.close()
# close the socket
s.close()
