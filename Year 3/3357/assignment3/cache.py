import socket
import os
import datetime
import signal
import sys

# Constant for cache expiration time

BUFFER_SIZE = 1024
EXP_TIME = 120
CACHE_HOST = socket.gethostname()
CACHE_PORT = 8001

# Signal handler for graceful exiting.

def signal_handler(sig, frame):
    print('Interrupt received, shutting down ...')
    sys.exit(0)


def prepare_get_message_with_time(host, port, file_name, time):
    request = f'Time {time} GET {file_name} HTTP/1.1\r\nHost: {host}:{port}\r\n\r\n'
    return request


def prepare_get_message(host, port, file_name):
    request = f'GET {file_name} HTTP/1.1\r\nHost: {host}:{port}\r\n\r\n'
    return request


def prepare_response_message(value):
    date = datetime.datetime.now()
    date_string = 'Date: ' + date.strftime('%a, %d %b %Y %H:%M:%S EDT')
    message = 'HTTP/1.1 '
    if value == '200':
        message = message + value + ' OK\r\n' + date_string + '\r\n'
    elif value == '404':
        message = message + value + ' Not Found\r\n' + date_string + '\r\n'
    elif value == '501':
        message = message + value + ' Method Not Implemented\r\n' + date_string + '\r\n'
    elif value == '505':
        message = message + value + ' Version Not Supported\r\n' + date_string + '\r\n'
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

    # Open the file, read it, and send it

    with open(file_name, 'rb') as file_to_send:
        while True:
            chunk = file_to_send.read(BUFFER_SIZE)
            if chunk:
                sock.send(chunk)
            else:
                break


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


def print_file_from_socket(sock, bytes_to_read):
    bytes_read = 0
    while bytes_read < bytes_to_read:
        chunk = sock.recv(BUFFER_SIZE)
        bytes_read += len(chunk)
        print(chunk.decode())


# Read a file from the socket and save it out.

def save_file_from_socket(sock, bytes_to_read, file_name):
    with open(file_name, 'wb') as file_to_write:
        bytes_read = 0
        while bytes_read < bytes_to_read:
            chunk = sock.recv(BUFFER_SIZE)
            bytes_read += len(chunk)
            file_to_write.write(chunk)


def file_processing_time_comp(client_socket, host, port, file_name, time):
    print('Connection established. Sending message...\n')
    message = prepare_get_message_with_time(host, port, file_name, time)
    client_socket.send(message.encode())

    # Receive the response from the server and start taking a look at it

    response_line = get_line_from_socket(client_socket)
    response_list = response_line.split(' ')
    headers_done = False

    # If an error is returned from the server, we dump everything sent and
    # exit right away.

    if response_list[1] != '200':
        print('Error:  An error response was received from the server.  Details:\n')
        print(response_line)
        bytes_to_read = 0
        while not headers_done:
            header_line = get_line_from_socket(client_socket)
            print(header_line)
            header_list = header_line.split(' ')
            if header_line == '':
                headers_done = True
            elif header_list[0] == 'Content-Length:':
                bytes_to_read = int(header_list[1])
        print_file_from_socket(client_socket, bytes_to_read)
        sys.exit(1)

    # If it's OK, we retrieve and write the file out.

    else:

        print('Success:  Server is sending file.  Downloading it now.')

        # If requested file begins with a / we strip it off.

        while file_name[0] == '/':
            file_name = file_name[1:]

        # Go through headers and find the size of the file, then save it.

        bytes_to_read = 0
        while not headers_done:
            header_line = get_line_from_socket(client_socket)
            header_list = header_line.split(' ')
            if header_line == '':
                headers_done = True
            elif header_list[0] == 'Content-Length:':
                bytes_to_read = int(header_list[1])
        save_file_from_socket(client_socket, bytes_to_read, file_name)


def file_processing(client_socket, host, port, file_name):
    print('Connection established. Sending message...\n')
    message = prepare_get_message(host, port, file_name)
    client_socket.send(message.encode())

    # Receive the response from the server and start taking a look at it

    response_line = get_line_from_socket(client_socket)
    response_list = response_line.split(' ')
    headers_done = False

    # If an error is returned from the server, we dump everything sent and
    # exit right away.

    if response_list[1] != '200':
        print('Error:  An error response was received from the server.  Details:\n')
        print(response_line)
        bytes_to_read = 0
        while not headers_done:
            header_line = get_line_from_socket(client_socket)
            print(header_line)
            header_list = header_line.split(' ')
            if header_line == '':
                headers_done = True
            elif header_list[0] == 'Content-Length:':
                bytes_to_read = int(header_list[1])
        print_file_from_socket(client_socket, bytes_to_read)
        sys.exit(1)

    # If it's OK, we retrieve and write the file out.

    else:

        print('Success:  Server is sending file.  Downloading it now.')

        # If requested file begins with a / we strip it off.

        while file_name[0] == '/':
            file_name = file_name[1:]

        # Go through headers and find the size of the file, then save it.

        bytes_to_read = 0
        while not headers_done:
            header_line = get_line_from_socket(client_socket)
            header_list = header_line.split(' ')
            if header_line == '':
                headers_done = True
            elif header_list[0] == 'Content-Length:':
                bytes_to_read = int(header_list[1])
        save_file_from_socket(client_socket, bytes_to_read, file_name)


def main():
    # Register our signal handler for shutting down.

    signal.signal(signal.SIGINT, signal_handler)

    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.bind((CACHE_HOST, CACHE_PORT))
    print(f"[*] Listening as {CACHE_HOST}:{CACHE_PORT}")
    s.listen(1)

    while 1:
        print('Waiting for incoming client connection ...')
        conn, addr = s.accept()
        print('Accepted connection from client address:', addr)
        print('Connection to client established, waiting to receive message...')

        # We obtain our request from the socket.  We look at the request and
        # figure out what to do based on the contents of things.

        request = get_line_from_socket(conn)
        print('Received request:  ' + request)
        request_list = request.split()

        HostPortInfo = get_line_from_socket(conn)
        host = HostPortInfo.split(' ')[1].split(":")[0]
        port = HostPortInfo.split(' ')[1].split(":")[1]

        # This server doesn't care about headers, so we just clean them up.

        while get_line_from_socket(conn) != '':
            pass

        # If we did not get a GET command respond with a 501.

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

            # Check if requested file exists

            if not os.path.exists(req_file):
                # Make a connection to the server
                print('Connecting to Server ...')
                try:
                    client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
                    client_socket.connect((host, int(port)))
                except ConnectionRefusedError:
                    print('Error:  That host or port is not accepting connections.')
                    sys.exit(1)
                # The connection was successful, so we can prep and send our message.
                file_processing(client_socket, host, port, req_file)
                print('Requested file good to go!  Sending file ...')
                send_response_to_client(conn, '200', req_file)

            # File exists, so prepare to send it!

            else:
                print('Connecting to Server ...')
                try:
                    client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
                    client_socket.connect((host, int(port)))
                except ConnectionRefusedError:
                    print('Error:  That host or port is not accepting connections.')
                    sys.exit(1)
                # The connection was successful, so we can prep and send our message.
                time = os.path.getmtime(req_file)
                time = time - EXP_TIME
                file_processing_time_comp(client_socket, host, port, req_file, time)
                print('Requested file good to go!  Sending file ...')
                send_response_to_client(conn, '200', req_file)

                # We are all done with this client, so close the connection and
        # Go back to get another one!


if __name__ == '__main__':
    main()
