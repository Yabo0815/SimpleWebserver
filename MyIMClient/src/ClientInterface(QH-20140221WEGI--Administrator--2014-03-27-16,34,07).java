import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ClientInterface {
    
    public static void main(String[] args) {
        SocketChannel channelListener;
        try {
            channelListener = SocketChannel.open();
            if (channelListener.connect(new InetSocketAddress("127.0.0.1", 8080))) {
                ClientInterface client = new ClientInterface();
                ByteBuffer in = ByteBuffer.allocate(1024);
                channelListener.read(ByteBuffer);
                client.read(in);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    public void read(ByteBuffer in) {
        try {
            if (in.available() != 0) {
                int size = in.available();
                byte[] inBytes = new byte[size];
                in.read(inBytes, 0, size);
                String inString = new String(inBytes);
                System.out.printf("Client Receive: " + inString);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void write() {

    }
}
