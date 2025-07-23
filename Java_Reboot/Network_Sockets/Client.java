package Java_Reboot.Network_Sockets;

// Java 网络相关
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

// Java '流处理'相关
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Client {
  
  public static String inputStream_to_String(InputStream data){
    System.out.println();
    StringBuilder sb = new StringBuilder(5); // 动态接收'字符串'内容
    InputStreamReader reader = new InputStreamReader(data); // InputStreamReader, 将 输入的'字符流' 转换为 '字节流'
    char[] string_buffer = new char[1024]; 
    int read_value; // 本次读取大小
    int test_counter = 1;
    try {
      while ((read_value = reader.read(string_buffer)) != -1) { // 这里的.read()方法会阻塞I/O, 直到收到客户端回应才会继续
          sb.append(string_buffer,0,read_value);
          System.out.println("已从InputStream中读取了 " + read_value + " Bytes, 这是第 " + test_counter + " 次");
          test_counter++;
          if(sb.toString().contains("\n")) break;
      }
    } catch (IOException e) {
      System.out.println("发生了IO异常!");
    }
   
    return sb.toString();
  }

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
      user_input.close();
      
      OutputStream byte_stream = to_server_socket.getOutputStream(); // 取得客户端'输出流', 拿到后用OutputStreamWriter直接写入'字符数据'
      OutputStreamWriter writer = new OutputStreamWriter(byte_stream); // OutputStreamWriter会自动将其转换为'字节'Byte数据进行网络传输
      writer.write(input_content + "\n");
      writer.flush();
      // writer.close();

      // 一会儿服务器还会有个回应, 这里用个和服务器同款的阻塞I/O耗着
      System.out.println("正在等待服务器进行回应...");

      InputStream socket_receive_stream = to_server_socket.getInputStream(); // 从服务器socket过来的'输入'
      String server_reply = inputStream_to_String(socket_receive_stream); // 这里阻塞
      System.out.println("\n从服务器接收到的回应: " + server_reply);

      // System.out.println("\n客户端Socket已关闭");
      // to_server_socket.close(); // 完成通信, 关闭Socket
    } catch (Exception e) {
      System.out.println("Connection Refused, 可能是没找到对应的服务器");
    }
    
    

  } // main函数结束
}
