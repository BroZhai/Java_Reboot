package Java_Reboot.Network_Sockets;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Server {
  public static String inputStream_to_String(InputStream data){
    System.out.println("测试: " + data);
    StringBuilder sb = new StringBuilder(5);
    InputStreamReader reader = new InputStreamReader(data); // InputStreamReader, 将 输入的'字符流' 转换为 '字节流'
    char[] string_buffer = new char[1024]; // 设置读取缓存 (InputStream是'字节'流)
    int read_value;
    try {
      while ((read_value = reader.read(string_buffer)) != -1) {
        String current_content = Arrays.toString(string_buffer);
        sb.append(current_content);
      }
    } catch (IOException e) {
      System.out.println("发生了IO异常!");
    }
   
    return sb.toString();
  }

  public static void main(String[] args) throws IOException, UnknownHostException{
    InetAddress server_ip = InetAddress.getByName("127.0.0.1");
    ServerSocket sv = new ServerSocket(11451, 20, server_ip);
    while(true){
      System.out.println("服务器 " + sv.getInetAddress().getHostAddress() + ":" + sv.getLocalPort() + " 已成功建立, 正在等待连接");
      Socket client_socket = sv.accept(); // 服务器接收到客户端连接, accept()并获取客户端连接socket对象
      System.out.println("接收到来自端口 " + client_socket.getPort() + " 的连接...");
      // 成功获取到客户端的Socket后, 取得该 客户端Socket的'输入流'.getInputStream获取对应信息 (客户端Socket的'输出流' 对应 服务端Socket的'输入流')
      InputStream client_data = client_socket.getInputStream();
      String data_content = Server.inputStream_to_String(client_data);
      System.out.println("尝试取得的数据为: " + data_content);
    }
    
  }
  
}
