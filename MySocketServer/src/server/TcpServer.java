package server;

import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TcpServer implements Runnable {

    private final int PORT = 9000;

    private AsynchronousChannelGroup channelGroup;
    private AsynchronousServerSocketChannel listener;

    public static void main(String[] args) throws Exception {
        // TODO Complete for real deployment
        // now design for test
        TcpServer server = new TcpServer();
        new Thread(server).start();
        
    }

    public TcpServer() throws Exception {
        // Create a thread pool with max threads number 20
        ExecutorService executor = Executors.newFixedThreadPool(20);
        channelGroup = AsynchronousChannelGroup.withThreadPool(executor);
        // Create a socket server
        listener = AsynchronousServerSocketChannel.open(channelGroup).bind(
                new InetSocketAddress(PORT));
    }

    public void run() {
        try {
            listener.accept(listener, new AcceptHandler());
            Thread.sleep(400000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Server Closed");
        }
    }

}
