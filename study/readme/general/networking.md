Brief detail about a Non-blocking I/O Server using Java NIO (New Input/Output). 
It can handle multiple client connections concurrently without relying on multiple threads, making it more efficient in terms of system resources 
when compared to traditional blocking I/O servers. Here's a breakdown of the key components and logic involved:

1. Constants and Member Variables
PORT: The server listens on port 12345.

BUFFER_SIZE: Defines the size of the buffer used for reading/writing data (1 KB).

selector: An instance of Selector, which is used to monitor multiple channels for readiness (such as read/write events).

serverChannel: A ServerSocketChannel that listens for incoming client connections.

readBuffer: A ByteBuffer used to temporarily store data read from clients.

writeBuffers: A ConcurrentHashMap mapping each SocketChannel (client) to its corresponding buffer of data to be written.

running: A flag that controls the running state of the server. It is set to true when the server is running, and false when stopping.

2. Constructor
The constructor doesn't do much in this implementation, as the main logic is in the start() method. It's here to set up the server instance.

3. start() Method
This is the main method where the server's lifecycle is controlled.

Selector: A Selector is opened to monitor the ServerSocketChannel and SocketChannels for readiness.

ServerSocketChannel: The server is set up as a non-blocking socket, meaning the server doesn’t block while waiting for connections.

Binding: The server socket is bound to port 12345 using serverChannel.socket().bind().

Shutdown Hook: A shutdown hook is added to allow the server to close gracefully when the JVM is shutting down.

Main Event Loop: The selector.select() method is called to block until at least one channel is ready for I/O operations. Then the server processes each selected key (ready channel).

4. Main Loop: Event Handling
The while (running) loop is the heart of the server. It constantly:

Selects: Calls selector.select() to block and wait for channels to be ready.

Processes Selected Keys: selector.selectedKeys() contains the keys for channels that are ready for I/O events like connection acceptance, data reading, and data writing.

For each selected key:

Accepts new client connections.

Reads data from clients if the channel is ready to read.

Writes data to clients if the channel is ready to write.

Handles exceptions and cleans up resources (such as closing client connections).

5. acceptConnection()
When the server socket is ready to accept a connection (OP_ACCEPT), the server accepts the incoming client connection.

The client’s channel is configured to non-blocking mode, and it is registered with the selector to be monitored for reading events (OP_READ).

6. readData()
When a client channel is ready to read (OP_READ), the server reads the data into a ByteBuffer.

If the client sends the special message "bye", the server prepares to close the connection after responding.

The message from the client is processed (in this case, converted to uppercase and prepended with a "SERVER RESPONSE").

The server then stores the processed response in the writeBuffers map, associated with the client’s channel, and changes the selector’s interest to include OP_WRITE so the server will later be ready to send the response.

7. writeData()
When a channel is ready to write (OP_WRITE), the server writes the data stored in the writeBuffers map back to the client.

Once the buffer is empty (i.e., the response has been fully written), the buffer is removed from the map.

If the client sent a "bye" message, the server closes the client connection after writing the response.

8. closeClientChannel()
A helper method that handles the cleanup of a client connection. It:

Tries to close the client’s SocketChannel.

Cancels the corresponding SelectionKey.

Removes the client’s write buffer from the writeBuffers map.

Outputs a message confirming the client has been closed.

9. stop()
When the server needs to stop, the running flag is set to false, causing the main loop to exit.

The method ensures proper cleanup of resources by closing the selector and the server socket.

10. main()
The main method instantiates the NIOServer and starts it by calling the start() method.

Key NIO Concepts Used:

Selector: This is the main element for multiplexing I/O operations. It watches multiple channels for events like readiness to read or write data.

Non-blocking I/O: The server doesn't block when waiting for I/O operations. Instead, it checks for readiness using the Selector.

ByteBuffer: Used for reading and writing data. It’s a direct memory buffer for handling I/O.

SelectionKey: Represents the interest of a channel (i.e., whether the server is interested in reading, writing, or accepting connections on that channel).

Error Handling:

ClosedChannelException: Caught specifically when a channel is closed unexpectedly, and the server gracefully handles the cleanup.

IOException: Caught for general I/O errors during reading or writing, triggering client cleanup.

Exception: Any other unexpected runtime errors are caught, logged, and also trigger the cleanup of the client connection.

Graceful Shutdown:

The shutdown is handled via a shutdown hook, which ensures the server can close resources cleanly when the JVM is exiting.

Strengths:

Efficient for handling many clients because it uses a single or few threads to handle multiple connections using the Selector.

Avoids blocking operations, making it highly scalable for I/O-bound tasks.

Limitations:

This model is better suited for I/O-bound operations rather than CPU-bound tasks. If the server needs heavy computation for each client, using a thread pool to handle the computation would be more efficient.
