package server;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;

public class AcceptHandler
        implements
        CompletionHandler<AsynchronousSocketChannel, AsynchronousServerSocketChannel> {

    @Override
    public void completed(AsynchronousSocketChannel clientSocket,
            AsynchronousServerSocketChannel serverSocket) {
        serverSocket.accept(serverSocket, this);
//        read(clientSocket);
        write(clientSocket, "hi, I'm server");
    }

    @Override
    public void failed(Throwable exception,
            AsynchronousServerSocketChannel serverSocket) {
        exception.printStackTrace();
    }

    public void read(AsynchronousSocketChannel clientSocket) {
        ByteBuffer in = ByteBuffer.allocate(1024);
        clientSocket.read(in);
        String inString;
        inString = getString(in);
        System.out.printf("Server Receive: " + inString);
    }
    
    public static String getString(ByteBuffer buffer) {
        Charset charset = null;
        try {
            charset = Charset.forName("UTF-8");
            return new String(buffer.array(), charset);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }
    
    public static void write(AsynchronousSocketChannel clientSocket, String string) {
        ByteBuffer out;
        try {
            out = ByteBuffer.wrap(string.getBytes("UTF-8"));
            clientSocket.write(out);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
