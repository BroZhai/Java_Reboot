package Java_Reboot.Network_Sockets.File_Upload;

import java.io.*;
import java.net.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

import java.util.stream.*;
import java.util.List;
import java.util.Scanner;
import java.util.regex.*; // 正则表达式判断相关

public class Client {

  // 全局变量
  static Path base_path = Path.of(System.getProperty("user.dir"),"Java_Reboot","Network_Sockets","File_Upload");

  public static boolean validate_filename(String input_filename){
    Pattern filename_standard = Pattern.compile("^[\\w]+\\.[\\w]{2,}$"); 
    boolean match_result = filename_standard.matcher(input_filename).matches();
    if(!match_result){
      System.out.println("\n文件名不合法! 请重新输入");
      return false;
    }
    Path target_file = Path.of(base_path.toString(),"client_folder",input_filename);
    boolean file_exists = Files.exists(target_file) && !Files.isDirectory(target_file); // 文件存在且不是 '文件夹'
    if(!file_exists){
      System.out.println("\n文件名不存在! 请重新输入");
    }
    return match_result && file_exists;
  }

  public static void main(String[] args) throws IOException, UnknownHostException, InterruptedException{
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
    Path client_dir = Path.of(base_path.toString(),"client_folder");
    // System.out.println(Files.exists(client_dir)); 
    Stream<Path> path_stream = Files.list(client_dir); // 返回Stream<Path>对象, 欢迎来到兔子洞入口 :)))))))))))))))
    
    List<String> filename_list = path_stream.collect(
      Collectors.mapping((path_obj) -> {
        return path_obj.getFileName().toString(); // 返回流中'当前path'的纯文件名 String
      }, Collectors.toList()) // 下游收集器转成 List进行输出
    );
    Scanner user_input = new Scanner(System.in);
    String input_filename;
    boolean is_valid_input;
    do{
      System.out.println("\n客户端目录中有如下文件: \n" + filename_list.toString());
      System.out.print("\n请输入要上传的文件名: "); 
      input_filename = user_input.nextLine();
      is_valid_input = validate_filename(input_filename);
      if(!is_valid_input){
        // System.out.println("输入无效! 请重新输入\n");
        Thread.sleep(1000);
      }
    }while(!is_valid_input);

    Path target_file = Paths.get(client_dir.toString(), input_filename);
    byte[] file_content = Files.readAllBytes(target_file);
    // to_server_socket = new Socket(server_ip,13145); // 创建多个 同socket对象 会导致连接重置! 会让服务端'没反应过来' 从而丢数据

    OutputStream stream_to_server = to_server_socket.getOutputStream();
    OutputStreamWriter filename_writer = new OutputStreamWriter(stream_to_server);
    BufferedWriter writer = new BufferedWriter(filename_writer);
    writer.write(input_filename + "\n");
    writer.flush();
    



    // OutputStream stream_to_server = to_server_socket.getOutputStream();
    // BufferedOutputStream stream_writer  = new BufferedOutputStream(stream_to_server);
    // stream_writer.write(file_content);
    // stream_writer.flush();

    // stream_writer.write
  } // main函数结束
  
  
}
