package Java_Reboot.Network_Sockets;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
  
  public static void main(String[] args) throws IOException, UnknownHostException {
    InetAddress server_ip = InetAddress.getByName("localhost");
    Socket to_server_socket = new Socket(server_ip,11451); // 这一行在建立完Socket对象后会'直接自行connect' (无需再手动connect)
    System.out.println("本地的连接端口为: " + to_server_socket.getLocalPort());
    // to_server_socket.connect(new InetSocketAddress("localhost", 11451), 5000);
  }
}
