package Java_Reboot.Network_Sockets.File_Upload;

import java.io.*;
import java.net.*;
import java.nio.file.Path;
import java.nio.file.Files;

public class Client {

  public static void main(String[] args) throws IOException, UnknownHostException{
    // 连上服务器
    InetAddress server_ip = InetAddress.getByName("localhost");
    Socket to_server_socket;
    try{
        to_server_socket = new Socket(server_ip,13145);
        // DataInputStream dis = new DataInputStream(to_server_socket.getInputStream());
        System.out.println("已成功连接至服务器!");
    } catch(Exception e){
      System.out.println("Connection Refuesd, 可能是服务器不存在...");
    }
    
    // 客户端选择文件并上传
    // System.out.println(System.getProperty("user.dir"));
    Path client_dir = Path.of(System.getProperty("user.dir"),"Java_Reboot","Network_Sockets","File_Upload","client_folder");
    // System.out.println(Files.exists(client_dir)); 
    

  }
  
  
}
