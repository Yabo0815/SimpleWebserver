package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.Executors;

public class TcpServer implements Runnable {

    private AsynchronousChannelGroup channelGroup;
    private AsynchronousServerSocketChannel serverSocket;
    private int threadPoolSize;
    private int port;

    public TcpServer (int port, int threadPoolSize) throws IOException {
        this.threadPoolSize = threadPoolSize;
        this.port = port;
            channelGroup = AsynchronousChannelGroup.withThreadPool(Executors
                    .newFixedThreadPool(threadPoolSize));
            serverSocket = AsynchronousServerSocketChannel.open(channelGroup);
            serverSocket.bind(new InetSocketAddress(port));
            System.out.println("Socket server is listening on port " + port);
    }
    
    @Override
    public void run() {
        serverSocket.accept(serverSocket, new AcceptHandler());
        while (true) {
                try {
                    Thread.sleep(360000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("Thread Error, please restart");
                    break;
                }
        }
    }
  
}
