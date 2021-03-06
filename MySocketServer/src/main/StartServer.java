package main;

import java.io.IOException;
import java.util.Scanner;

import server.TcpServer;

public class StartServer {

    public static void main(String[] args) throws IOException {
        int port = 8080; // default port
        int threadSize = 20; // default thread pool size
        String index = "index.html";
        int i = 0;
        
        // Set the parameters
        while (i < args.length) {
            if (!verifyCmdFormat(args[i])) {
                System.out
                        .println("Command Format Error, please start the server again");
                return;
            }
            int j = args[i].indexOf("-", 1);
            String cmdStr = args[i].substring(1, j);
            if (cmdStr.equals("port")) {
                port = Integer.parseInt(args[i].substring(j+1));
            } else if (cmdStr.equals("thread")) {
                threadSize = Integer.parseInt(args[i].substring(j+1));
            } else if (cmdStr.equals("index")) {
                index = args[i].substring(j+1);
            } else {
                System.out.println("Invalid Command, please start the server again");
                return;
            }
            i++;
        }
        
        // start the server
        TcpServer server = new TcpServer(port, threadSize, index);
        Thread thread = new Thread(server);
        thread.start();
        
        // listen to the pressing and stop the server accordingly
        Scanner input = new Scanner(System.in);
        while (!input.next().toString().equals("close")) {
            System.out.println("Invalid stop commander");
        }
        input.close();
        server.stop();
        System.out.println("The Socket server is closed");
    }

    public static boolean verifyCmdFormat(String cmd) {
        boolean isQualified = false;
        if (cmd.matches("-[a-z]+-[1-9a-zA-Z][0-9a-zA-Z\\.]*"))
            isQualified = true;
        return isQualified;
    }

}
