package Java_Reboot.Network_Sockets.File_Upload;

// Java IO相关
import java.io.*;

// Java网络相关
import java.net.*;

public class Server {

  // 将输入的InputStream 转换为 字符串
  public static String input_stream_to_String(InputStream data){
    
    return "";
  }

  // 将输入的InputStream 转换为...
  public static void input_stream_to_File(InputStream data){

  }
  
  public static void main(String[] args) throws IOException{
    InetAddress server_ip = InetAddress.getByName("localhost");
    ServerSocket server = new ServerSocket(13145,30,server_ip);
    while (true) {
      Socket client_socket = server.accept();
      System.out.println("收到来自端口 " + client_socket.getPort() + " 的连接请求");
    }
    
  }
}
