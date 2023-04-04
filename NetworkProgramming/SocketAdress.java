import java.net.InetSocketAddress;

public class SocketAdress {
    public static void main(String[] args) {
        InetSocketAddress socketAddress = new InetSocketAddress("::1", 12889);
        printSocketAddress(socketAddress);
      
        printSocketAddress(InetSocketAddress.createUnresolved("::1", 12881));
    }

    public static void printSocketAddress(InetSocketAddress  socketAddress){
        System.out.println("Socket Address:"+socketAddress.getAddress());
        System.out.println("Socket Host Name:"+socketAddress.getHostName());
        System.out.println("Socket Port:"+socketAddress.getPort());
        System.out.println("Socket isUnresolved():"+socketAddress.isUnresolved());
        System.out.println("==================================================\n");
    }
}
