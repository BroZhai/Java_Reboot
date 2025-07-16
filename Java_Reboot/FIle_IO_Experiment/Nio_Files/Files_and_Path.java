package Java_Reboot.FIle_IO_Experiment.Nio_Files;

import java.nio.file.Files; // 导入 Files类
import java.nio.file.Path; // 导入 Path类
import java.nio.file.Paths; // 只有 Paths类的静态方法.get / .of 才能创建Path类对象

public class Files_and_Path {
  // 我们来实践一下这个Java 7+之后专门出现的对'文件/目录'操作的类, 据说有更多的功能, 现在实际项目开发常用这个, 操作还内置了'缓存空间'
  public static void main(String[] args) {
    // Path类实验区 (对应File类的'纯路径/文件')
    String current_path = System.getProperty("user.dir");
    System.out.println("当前取得的默认工作目录user.dir为: " + current_path);

    // Paths.get()声明路径, 这里直接用了'相对路径' + 逗号(,)拼接
    Path test_file_path = Paths.get("Java_Reboot","File_IO_Experiment","Nio_Files","test.TXT"); // 我嘞个日, 原来路径大小写没关系的, 只要对上就行
    System.out.println("构建的Path文件对象路径为: "+test_file_path.toFile());
    boolean file_exist = Files.exists(test_file_path);
    System.out.println("test_file存在吗? " + file_exist);
    System.out.println();
    if(file_exist){
      System.out.println("取得的文件名为: " + test_file_path.getFileName());
      System.out.println("该文件'所处目录'的上一级目录为: " + test_file_path.getParent().getParent()); // 文件位于 Java_Reboot\File_IO_Experiment\Nio_Files\test.txt
      System.out.println("该文件共有 " + test_file_path.getNameCount() + " 级目录(文件自身也算一级)");
      System.out.println("该文件取得'第2级'的目录为: " + test_file_path.getName(2));
      Path test_abs_path = test_file_path.toAbsolutePath();
      System.out.println("尝试转成的绝对路径为: " + test_abs_path); // 成功
      System.out.println("从绝对路径中取得的'根目录'为: " + test_abs_path.getRoot());
    }

    System.out.println("\n现在创建另一个Path对象folder_path, 定位到当前目录下的 test_folder目录");
    Path folder_path = Paths.get("Java_Reboot/File_IO_Experiment/Nio_Files/test_folder");
    boolean folder_exist = Files.exists(folder_path);
    System.out.println("folder_path取得的目录存在吗? " + folder_exist);
    if(folder_exist){
      System.out.println("folder_path取得的目录为: " + folder_path);
      System.out.println("该目录到test.txt的路径为: " + folder_path.relativize(test_file_path));
      Path inner_folder = folder_path.resolve("folder_loop"); // 在当前目录的基础上, 直接'追加一段新路径' 并 返回一个'新Path对象'
      System.out.println("拿到的'新目录'为: " + inner_folder.toString());
      System.out.println("该目录到test.txt的路径为: " + inner_folder.relativize(test_file_path));
      System.out.println("该目录当前是'绝对路径'吗? " + inner_folder.isAbsolute());  // false
      System.out.println("该目录是否以'Java_Reboot'开头? " + inner_folder.startsWith("Java_Reboot") + ", 是否以'poop'结尾: " + inner_folder.endsWith("inner_folder")); // true false
    }


  }



}
