package Java_Reboot.Network_Sockets.File_Upload;

// Java IO相关
import java.io.*;

// Java网络相关
import java.net.*;

public class Server {

  // 将输入的InputStream 转换为 字符串
  public static String inputStream_to_String(InputStream data) throws IOException{
    InputStreamReader reader = new InputStreamReader(data);
    BufferedReader filename_reader = new BufferedReader(reader);
    String filename = filename_reader.readLine(); // 一定要记得带个'回车', 这里'读一行'要用!

    return filename;
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
      InputStream stream_from_client = client_socket.getInputStream();
      String filename = inputStream_to_String(stream_from_client);
      System.out.println("读取到的文件名为:" + filename);
      // byte[] file_content
      // BufferedInputStream stream_reader = new BufferedInputStream(stream_from_client);

    }
    
    
  }
}
