package server;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;

import util.HttpParse;

public class ReadHandler implements CompletionHandler<Integer, ByteBuffer> {

    private AsynchronousSocketChannel clientSocket;

    public ReadHandler(AsynchronousSocketChannel clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void completed(Integer bytesLength, ByteBuffer inBuffer) {
        if (bytesLength > 0) {
            inBuffer.flip();
            String inString;
            inString = getStringFromBuffer(inBuffer);
            inBuffer.compact();
            // FIXME deal with the header whose uri is too long
//            clientSocket.read(inBuffer, inBuffer, this);
            HttpParse parse = new HttpParse(inString);
            if (parse.extractMethod().equalsIgnoreCase("GET")) {
                Request request = new Request(clientSocket);
                request.doGet(parse.extractUri());
            }
//            System.out.println(inString);
        }
    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        exc.printStackTrace();
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
