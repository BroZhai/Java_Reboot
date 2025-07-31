package Java_Reboot.Network_Sockets.File_Upload;

// Java IO相关
import java.io.*;

// Java网络相关
import java.net.*;

// 文件处理相关
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.Files;

public class Server {

  static Path base_path = Path.of(System.getProperty("user.dir"),"Java_Reboot","Network_Sockets","File_Upload");

  // 将输入的InputStream 转换为 字符串 (接收文件名)
  public static String inputStream_to_String(InputStream data) throws IOException{
    InputStreamReader reader = new InputStreamReader(data);
    BufferedReader filename_reader = new BufferedReader(reader);
    String filename = filename_reader.readLine(); // 一定要记得带个'回车', 这里'读一行'要用!

    return filename;
  }

  // 将输入的InputStream 转换为 byte[] (接收文件数据)
  public static byte[] input_stream_to_File(InputStream data) throws IOException{
    DataInputStream din = new DataInputStream(data);
    int fileSize = din.readInt();
    System.out.println("接收到的文件大小为: " + fileSize);
    byte[] file_content = new byte[fileSize];
    din.readFully(file_content);
    return file_content;
  }
  
  public static void main(String[] args) throws IOException{
    InetAddress server_ip = InetAddress.getByName("localhost");
    ServerSocket server = new ServerSocket(13145,30,server_ip);
    while (true) {
      Socket client_socket = server.accept();
      System.out.println("收到来自端口 " + client_socket.getPort() + " 的连接请求");
      InputStream stream_from_client = client_socket.getInputStream();
      String filename = inputStream_to_String(stream_from_client); // 接收文件名
      System.out.println("读取到的文件名为:" + filename);
      byte[] file_content = input_stream_to_File(stream_from_client); // 接收文件数据

      Path save_file = Paths.get(base_path.toString(),"server_database",filename);
      Files.write(save_file, file_content, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE); // 文件不存在创建, 存在则清空再覆写
      if(Files.exists(save_file)){
        System.out.println("成功接收文件 " + filename);
      }else{
        System.out.println("接收失败...");
      }
      
    }
    
    
  }
}
