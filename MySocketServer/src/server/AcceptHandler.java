package server;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class AcceptHandler
        implements
        CompletionHandler<AsynchronousSocketChannel, AsynchronousServerSocketChannel> {

    private final int BUFFER_SIZE = 1024;
    
    @Override
    public void completed(AsynchronousSocketChannel clientSocket,
            AsynchronousServerSocketChannel serverSocket) {
        serverSocket.accept(serverSocket, this);
        ByteBuffer inBuffer = ByteBuffer.allocate(BUFFER_SIZE);
        clientSocket.read(inBuffer, inBuffer, new ReadHandler(clientSocket));
    }    

    @Override
    public void failed(Throwable exception,
            AsynchronousServerSocketChannel serverSocket) {
        exception.printStackTrace();
        System.out.println("Fail to accept request");
    }

}
