package server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class WriteHandler implements CompletionHandler<Integer, ByteBuffer>{
    private AsynchronousSocketChannel clientSocket;
    // the callback of write function
    public WriteHandler(AsynchronousSocketChannel clientSocket) {
        this.clientSocket = clientSocket;
    }
    
    @Override
    public void completed(Integer bufferSize, ByteBuffer outBuffer) {
        try {
            clientSocket.shutdownOutput();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        exc.printStackTrace();
    }

}
