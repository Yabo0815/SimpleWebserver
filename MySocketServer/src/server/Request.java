package server;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;

import util.HttpWrap;

public class Request {
    AsynchronousSocketChannel clientSocket;
    private final int BUFFER_SIZE = 1024;

    public Request(AsynchronousSocketChannel clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void doGet(String uri) {
        HttpWrap wrap = new HttpWrap();
        ByteBuffer outBuffer = ByteBuffer.allocate(BUFFER_SIZE);
        WriteHandler writeHandler = new WriteHandler(clientSocket);
        clientSocket.write(outBuffer, outBuffer, writeHandler);
    }
}
