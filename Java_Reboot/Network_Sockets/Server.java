package Java_Reboot.Network_Sockets;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {

  public static void main(String[] args) throws IOException, UnknownHostException{
    InetAddress server_ip = InetAddress.getByName("127.0.0.1");
    ServerSocket sv = new ServerSocket(11451, 20, server_ip);
    while(true){
      System.out.println("服务器 " + sv.getInetAddress().getHostAddress() + ":" + sv.getLocalPort() + " 已成功建立, 正在等待连接");
      Socket client_socket = sv.accept(); // 服务器接收到客户端连接, accept()并获取客户端连接socket对象
      System.out.println("接收到来自端口 " + client_socket.getPort() + " 的连接...");
    }
    
  }
  
}
