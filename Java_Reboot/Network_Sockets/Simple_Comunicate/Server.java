package Java_Reboot.Network_Sockets.Simple_Comunicate;

// Java 网络相关
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

// Java '流处理'相关 (网络传的是Byte)
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;



public class Server {
  public static String inputStream_to_String(InputStream data) throws IOException{
    System.out.println("测试: " + data);
    StringBuilder sb = new StringBuilder(5); // 动态接收'字符串'内容
    InputStreamReader reader = new InputStreamReader(data); // InputStreamReader, 将 输入的'字符流' 转换为 '字节流'
    char[] string_buffer = new char[20]; // 设置读取缓存 (InputStream是'字节'流), 刻意设的很小, 用于测试, 正常是1024
    int read_value; // 本次读取大小
    int test_counter = 1;
    try {
      while ((read_value = reader.read(string_buffer)) != -1) { // 这里的.read()方法会阻塞I/O, 直到收到客户端回应才会继续
          sb.append(string_buffer,0,read_value);
          System.out.println("已从InputStream中读取了 " + read_value + " Bytes, 这是第 " + test_counter + " 次");
          test_counter++;
          if(sb.toString().contains("\n")) break; // '手动结束'标记, 告知read读到'换行\n'时 停止read, 不然read会'一直读'导致阻塞...
        // String current_content = Arrays.toString(string_buffer);
        
      }
    } catch (IOException e) {
      System.out.println("发生了IO异常!");
    }
    System.out.println("服务器读取完成");
    return sb.toString();
  }

  public static void main(String[] args) throws IOException, UnknownHostException, InterruptedException{
    InetAddress server_ip = InetAddress.getByName("127.0.0.1");
    ServerSocket sv = new ServerSocket(11451, 20, server_ip);
    while(true){
      System.out.println("服务器 " + sv.getInetAddress().getHostAddress() + ":" + sv.getLocalPort() + " 已成功建立, 正在等待连接");
      Socket client_socket = sv.accept(); // 服务器接收到客户端连接, accept()并获取客户端连接socket对象
      System.out.println("接收到来自端口 " + client_socket.getPort() + " 的连接...");
      // 成功获取到客户端的Socket后, 取得该 客户端Socket的'输入流'.getInputStream获取对应信息 (客户端Socket的'输出流' 对应 服务端Socket的'输入流')
      InputStream client_data = client_socket.getInputStream();
      String data_content = Server.inputStream_to_String(client_data); // 这个方法中的.read就会阻塞I/O, 直到收到来自客户端的消息
      System.out.println("\n客户端Socket发来的信息为: " + data_content);
      
      // 要想给客户端进行'回复', 就用到服务器Socket的'输出流', 对应客户端那边Socket的输入流
      System.out.println("\n服务器正在准备回应消息, 3秒后发送...");
      // Thread.sleep(3000);
      String server_reply = "你好! 端口 " + client_socket.getPort() + ", 我收到了你的消息: " + data_content + "\n";
      System.out.println(server_reply);
      OutputStream server_output_stream = client_socket.getOutputStream();
      OutputStreamWriter output_writer = new OutputStreamWriter(server_output_stream);
      output_writer.write(server_reply);
      output_writer.flush();
      // output_writer.close();
      


    }
    
  }
  
}
