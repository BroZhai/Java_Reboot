package Java_Reboot.FIle_IO_Experiment.File_Class;
// import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File; // 导入File类
import java.io.FileWriter;
import java.io.IOException; // 文件读写异常类 (涉及磁盘读取都会有)

public class File_Class {
  public static void main(String[] args) throws IOException{
    // File类实验区
    File[] system_roots = File.listRoots();
    System.out.println("使用File.listRoots()查到的根目录盘符有: ");
    for(File i: system_roots){
      System.out.println(i.getPath()); // 查根目录盘符得用getPath()
    }
    
    // 创建临时文件
    File temp_file = File.createTempFile("temp_File", ".txt"); // 可以理解成 File对象的'构造函数'
    BufferedWriter temp_writer = new BufferedWriter(new FileWriter(temp_file)); // 啊哈, File对象也可以直接作为'输入流'


  }

}
