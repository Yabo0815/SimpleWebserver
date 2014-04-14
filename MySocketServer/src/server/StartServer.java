package server;

import java.io.IOException;

public class StartServer {
    
    public static void main(String[] args) throws IOException {
        
        int port = 8080;
        int threadSize = 20;
        
        TcpServer server = new TcpServer(port, threadSize);
        Thread t = new Thread(server);
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Socket server is closed");
    }

}
