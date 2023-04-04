import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

class Address{
    public static void main(String[] args) throws UnknownHostException {
        printAddressDetails("www.google.com");
        printAddressDetails(null); // ipv4 loopback address
        printAddressDetails("::1"); //ipv6 loopback address 

    }

    public static void printAddressDetails(String host){
        System.out.println("Host name:"+host);
        try {
            InetAddress address = InetAddress.getByName(host);
            System.out.println("Host IP address:"+address.getHostAddress());
            System.out.println("Cononical Host name:"+address.getHostName());
            int timeOutinMillis = 10000;
            System.out.println("isReachable(): " + address.isReachable(timeOutinMillis));
            System.out.println("isLoopBackAddress(): " + address.isLoopbackAddress());
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("---------------------------------------\n");
        }
    }
}