import socket
import os
import datetime
import signal
import sys
import random
from threading import Timer
from urllib.parse import urlparse

# Define the port
return_val = []
indices = []
BUFFER_SIZE = 1024
SERVER_PORT = 8085
SERVER_HOST = socket.gethostname()


# Signal handler for graceful exiting.

def signal_handler(sig, frame):
    print('Interrupt received, shutting down ...')
    sys.exit(0)

# A function for creating HTTP GET messages.

def prepare_get_message(host, port, file_name):
    request = f'GET {file_name} HTTP/1.1\r\nHost: {host}:{port}\r\n\r\n'
    return request


# Read a file from the socket and print it out.  (For errors primarily.)

def print_file_from_socket(sock, bytes_to_read):
    bytes_read = 0
    while (bytes_read < bytes_to_read):
        chunk = sock.recv(BUFFER_SIZE)
        bytes_read += len(chunk)
        print(chunk.decode())


# Read a file from the socket and save it out.

def save_file_from_socket(sock, bytes_to_read, file_name):
    bytes_read = 0
    while (bytes_read < bytes_to_read):
        chunk = sock.recv(BUFFER_SIZE)
        bytes_read += len(chunk)

# Create an HTTP response

def prepare_response_message(value):
    date = datetime.datetime.now()
    date_string = 'Date: ' + date.strftime('%a, %d %b %Y %H:%M:%S EDT')
    message = 'HTTP/1.1 '
    if value == '501':
        message = message + value + ' Method Not Implemented\r\n' + date_string + '\r\n'
    elif value == '505':
        message = message + value + ' Version Not Supported\r\n' + date_string + '\r\n'
    elif value == '301':
        message = message + value + ' Moved Permanently\r\n' + date_string + '\r\n'
    return message


# Send the given response and file back to the client.

def send_response_to_client(sock, code, file_name):
    # Determine content type of file

    if (file_name.endswith('.jpg')) or (file_name.endswith('.jpeg')):
        contentType = 'image/jpeg'
    elif file_name.endswith('.gif'):
        contentType = 'image/gif'
    elif file_name.endswith('.png'):
        contentType = 'image/jpegpng'
    elif (file_name.endswith('.html')) or (file_name.endswith('.htm')):
        contentType = 'text/html'
    else:
        contentType = 'application/octet-stream'

    # Get size of file

    file_size = os.path.getsize(file_name)

    # Construct header and send it

    header = prepare_response_message(code) + 'Content-Type: ' + contentType + '\r\nContent-Length: ' + str(
        file_size) + '\r\n\r\n'
    sock.send(header.encode())


# Read a single line (ending with \n) from a socket and return it.
# We will strip out the \r and the \n in the process.

def get_line_from_socket(sock):
    done = False
    line = ''
    while not done:
        char = sock.recv(1).decode()
        if char == '\r':
            pass
        elif char == '\n':
            done = True
        else:
            line = line + char
    return line

def test_servers(filename):
    file = open("config.txt", "r")
    times = []
    global return_val
    global indices
    for x in file:
        start = datetime.datetime.now()
        connect_to_server(x, filename)
        end = datetime.datetime.now()
        times.append((start - end).seconds)

    file.close()
    timesCopy = times
    times.sort()
    probabilities = []
    indices = []

    for x in times:
        indices.append(timesCopy.index(x))

    for x in range(len(indices)):
        probabilities.append(x)

    probabilities.sort(reverse=True)
    return_val = probabilities


# Connect to the servers to check the transfer rate

def connect_to_server(line, filename):
    host = line.split(":")[0]
    port = line.split(":")[1]

    url = "http://" + host + ":" + port + "/" + filename

    try:
        parsed_url = urlparse(url)
        if ((parsed_url.scheme != 'http') or (parsed_url.port == None) or (parsed_url.path == '') or (
                parsed_url.path == '/') or (parsed_url.hostname == None)):
            raise ValueError
        host = parsed_url.hostname
        port = parsed_url.port
        file_name = parsed_url.path
    except ValueError:
        print('Error:  Invalid URL.  Enter a URL of the form:  http://host:port/file')
        sys.exit(1)

    # Now we try to make a connection to the server.

    print('Connecting to server ...')
    try:
        client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        client_socket.connect((host, port))
    except ConnectionRefusedError:
        print('Error:  That host or port is not accepting connections.')
        sys.exit(1)

    # The connection was successful, so we can prep and send our message.

    print('Connection to server established. Sending message...\n')
    message = prepare_get_message(host, port, file_name)
    client_socket.send(message.encode())

    # Receive the response from the server and start taking a look at it

    response_line = get_line_from_socket(client_socket)
    response_list = response_line.split(' ')
    headers_done = False

    # If it's OK, we retrieve and write the file out.

    if response_list[1] == '200':

        print("Beginning file transfer")

        # If requested file begins with a / we strip it off.

        while (file_name[0] == '/'):
            file_name = file_name[1:]

        # Go through headers and find the size of the file, then save it.

        bytes_to_read = 0
        while (not headers_done):
            header_line = get_line_from_socket(client_socket)
            header_list = header_line.split(' ')
            if (header_line == ''):
                headers_done = True
            elif (header_list[0] == 'Content-Length:'):
                bytes_to_read = int(header_list[1])
        save_file_from_socket(client_socket, bytes_to_read, file_name)


    # An error was returned

    else:
        print('Error:  An error response was received from the server.  Details:\n')
        print(response_line)
        bytes_to_read = 0
        while (not headers_done):
            header_line = get_line_from_socket(client_socket)
            print(header_line)
            header_list = header_line.split(' ')
            if (header_line == ''):
                headers_done = True
            elif (header_list[0] == 'Content-Length:'):
                bytes_to_read = int(header_list[1])
        print_file_from_socket(client_socket, bytes_to_read)
        sys.exit(1)


def config_client():
    # Register our signal handler for shutting down.

    signal.signal(signal.SIGINT, signal_handler)

    # Create the socket.  We will ask this to work on any interface and to pick
    # a free port at random.  We'll print this out for clients to use.

    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_socket.bind((SERVER_HOST, SERVER_PORT))
    print(f"[*] Listening as {SERVER_HOST}:{SERVER_PORT}")
    server_socket.listen(1)

    # Keep the server running forever.

    while 1:
        print('Waiting for incoming client connection ...')
        conn, addr = server_socket.accept()

        # We obtain our request from the socket.  We look at the request and
        # figure out what to do based on the contents of things.

        request = get_line_from_socket(conn)
        print('Received request:  ' + request)
        request_list = request.split()

        # This server doesn't care about headers, so we just clean them up.

        while get_line_from_socket(conn) != '':
            pass

        if request_list[0] != 'GET':
            print('Invalid type of request received ... responding with error!')
            send_response_to_client(conn, '501', '501.html')

            # If we did not get the proper HTTP version respond with a 505.

        elif request_list[2] != 'HTTP/1.1':
            print('Invalid HTTP version received ... responding with error!')
            send_response_to_client(conn, '505', '505.html')

        else:

            # If requested file begins with a / we strip it off.

            req_file = request_list[1]
            while req_file[0] == '/':
                req_file = req_file[1:]

            # We are all done with this client, so close the connection and
            # Go back to get another one!
            val = random.choices(indices, return_val, k=1)
            val = val[0]
            file = open("config.txt", "r")
            line = file.readlines()[val]
            newHost = line.split(":")[0]
            newPort = line.split(":")[1]
            newPort = newPort.strip('\r\n')
            header = prepare_response_message('301') + "Location: http://" + newHost + ":" + newPort + "/" + req_file + '\r\n\r\n'
            conn.send(header.encode())

        conn.close()


# Begin program by waiting for client connection

def main():
    test_servers('test.txt')
    t = Timer(30, test_servers, ['test.txt'])
    t.start()

    config_client()





if __name__ == '__main__':
    main()
