package server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;

public class ReadHandler implements CompletionHandler<Integer, ByteBuffer> {
    // the call back of read function
    private AsynchronousSocketChannel clientSocket;

    // Constructor
    public ReadHandler(AsynchronousSocketChannel clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void completed(Integer bytesLength, ByteBuffer inBuffer)  {
        String inString = null;
        if (bytesLength > 0) {
            inBuffer.flip();
            // Buffer to String
            inString = getStringFromBuffer(inBuffer);
            inBuffer.compact();
            Processor processor = new Processor(clientSocket, inString);
            try {
                processor.process();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Request Process Error");
            }
        } else {
            System.out.println("Empty info received");
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        exc.printStackTrace();
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String getStringFromBuffer(ByteBuffer buffer) {
        Charset charset = null;
        try {
            charset = Charset.forName("UTF-8");
            return new String(buffer.array(), charset);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

}
