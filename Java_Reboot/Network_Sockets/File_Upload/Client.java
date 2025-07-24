package Java_Reboot.Network_Sockets.File_Upload;

import java.io.*;
import java.net.*;

public class Client {

  public static void main(String[] args) throws IOException, UnknownHostException{
    InetAddress server_ip = InetAddress.getByName("localhost");
    Socket to_server_socket = new Socket(server_ip,13145);
    // DataInputStream dis = new DataInputStream(to_server_socket.getInputStream());
  }
  
  
}
