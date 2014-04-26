package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import util.HttpHelper;

public class TcpServer implements Runnable {

    private AsynchronousChannelGroup channelGroup;
    private AsynchronousServerSocketChannel serverSocket;
    private volatile Thread blinker; // the flag to stop the thread of server
    ExecutorService threadPool;
    private final int INTERVAL = 10000; // 10 seconds
    private final int SERVER_BUFFER_SIZE = 1024 *1024;

    public TcpServer(int port, int threadPoolSize, String index) throws IOException {
        threadPool = Executors.newFixedThreadPool(threadPoolSize);
        channelGroup = AsynchronousChannelGroup.withThreadPool(threadPool);
        serverSocket = AsynchronousServerSocketChannel.open(channelGroup);
        serverSocket.bind(new InetSocketAddress(port));
        // Allow the reuse of port 8080, and set the buffer size
        serverSocket.setOption(StandardSocketOptions.SO_REUSEADDR, true);
        serverSocket.setOption(StandardSocketOptions.SO_RCVBUF, SERVER_BUFFER_SIZE);
        HttpHelper.setIndex(index);
        System.out.println("Socket server is listening on port " + port);
        System.out.println("With " + threadPoolSize + " threads");
        System.out.println("Index Page is " + index);
        System.out.println("Please enter \"close\" to stop the server");
    }

    @Override
    public void run() {
        serverSocket.accept(serverSocket, new AcceptHandler());
        blinker = Thread.currentThread();
        while (blinker == Thread.currentThread()) {
            try {
                Thread.sleep(INTERVAL);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("Thread Error, please restart");
                break;
            }
        }
    }

    public void stop() {
        blinker = null;
        threadPool.shutdown();
    }
}
