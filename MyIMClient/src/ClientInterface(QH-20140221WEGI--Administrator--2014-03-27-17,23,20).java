import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ClientInterface {

    public static void main(String[] args) {
        SocketChannel channelListener;
        try {
            channelListener = SocketChannel.open();
            if (channelListener
                    .connect(new InetSocketAddress("127.0.0.1", 8080))) {
                ClientInterface client = new ClientInterface();
                client.read(channelListener);

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void read(SocketChannel channelListener) {
        ByteBuffer in = ByteBuffer.allocate(1024);
        try {
            channelListener.read(in);
            byte[] inBytes = null;
            in.get(inBytes);
            String inString = new String(inBytes);
            System.out.printf("Client Receive: " + inString);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void write() {

    }
}
