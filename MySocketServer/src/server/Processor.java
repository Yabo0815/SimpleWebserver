package server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;

import util.HttpHelper;

public class Processor {
    AsynchronousSocketChannel clientSocket;
    String requestStr;
    HttpHelper httpHelper;

    public Processor(AsynchronousSocketChannel clientSocket, String requestStr) {
        this.clientSocket = clientSocket;
        this.requestStr = requestStr;
        httpHelper = new HttpHelper(requestStr);
    }

    public void process() throws IOException {
        if (requestStr != null) {
            httpHelper.parse();
            switch (httpHelper.getMethod()) {
            case get:
                doGet();
                break;
            case post:
                doPost();
                break;
            default:
                System.out.println("the operation is not supported yet");
                clientSocket.close();
            }
        } else {
            System.out.println("ByteBuffer transfered error, request is null");
            clientSocket.close();
        }
    }

    public void doGet() {
        ByteBuffer outBuffer;
        try {
            outBuffer = httpHelper.wrap();
            clientSocket.write(outBuffer, outBuffer, new WriteHandler(clientSocket));
        } catch (Exception e) {
            System.out.println("Write operation error");
            e.printStackTrace();
            try {
                clientSocket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

    }

    public void doPost() {

    }
}
