package Java_Reboot.Network_Sockets;

import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
  
  public static void main(String[] args) throws IOException, UnknownHostException {
    InetAddress server_ip = InetAddress.getByName("localhost");
    try {
      Socket to_server_socket = new Socket(server_ip,11451); // 这一行在建立完Socket对象后会'直接自行connect' (无需再手动connect)
      System.out.println("本地的连接端口为: " + to_server_socket.getLocalPort());
      // to_server_socket.connect(new InetSocketAddress("localhost", 11451), 5000);
      OutputStream send_msg = to_server_socket.getOutputStream();
      Scanner user_input = new Scanner(System.in);
      System.out.print("请输入发送内容: "); // 取得用户输入信息
      String input_content = user_input.nextLine();

      
      OutputStream byte_stream = to_server_socket.getOutputStream(); // 取得客户端'输出流', 拿到后用OutputStreamWriter直接写入'字符数据'
      OutputStreamWriter writer = new OutputStreamWriter(byte_stream); // OutputStreamWriter会自动将其转换为'字节'Byte数据进行网络传输
      writer.write(input_content);
      writer.flush();
      writer.close();
      
    } catch (Exception e) {
      System.out.println("Connection Refused, 可能是没找到对应的服务器");
    }
    
    

  } // main函数结束
}
