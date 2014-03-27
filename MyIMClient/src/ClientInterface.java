import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class ClientInterface {

    public static void main(String[] args) {
        SocketChannel channelListener;
        try {
            channelListener = SocketChannel.open();
            if (channelListener
                    .connect(new InetSocketAddress(9000))) {
                System.out.println("client: connected");
                ClientInterface client = new ClientInterface();
//                client.write("Hi, I'm client", channelListener);
                client.read(channelListener);

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("client: end");

    }

    public void read(SocketChannel channelListener) {
        ByteBuffer in = ByteBuffer.allocate(1024);
        try {
            channelListener.read(in);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String inString;
        inString = getString(in);
        System.out.printf("Client Receive: " + inString);
    }

    public void write(String string, SocketChannel channelListener) {
        ByteBuffer out;
        try {
            out = ByteBuffer.wrap(string.getBytes("UTF-8"));
            channelListener.write(out);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    public static String getString(ByteBuffer buffer) {
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
