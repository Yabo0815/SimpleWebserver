package server;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

import util.HttpWrap;

public class WriteHandler implements CompletionHandler<Integer, ByteBuffer>{
    AsynchronousSocketChannel clientSocket;
    public WriteHandler(AsynchronousSocketChannel clientSocket) {
        this.clientSocket = clientSocket;
    }
    
    public void write(AsynchronousSocketChannel clientSocket, String string) {
        ByteBuffer out;
        try {
            out = ByteBuffer.wrap(string.getBytes("UTF-8"));
            clientSocket.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void completed(Integer bufferSize, ByteBuffer outBuffer) {
        try {
            String outString = new HttpWrap().wrapCode();
            outBuffer = ByteBuffer.wrap(outString.getBytes("UTF-8"));
            clientSocket.write(outBuffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        exc.printStackTrace();
    }

}
