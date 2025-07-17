package Java_Reboot.FIle_IO_Experiment.Nio_Files;

// 相关异常类
import java.io.IOException; // 硬盘IO错误
import java.nio.file.DirectoryNotEmptyException; // 文件夹非空

import java.nio.file.Files; // 导入 Files类
import java.nio.file.Path; // 导入 Path类
import java.nio.file.Paths; // 只有 Paths类的静态方法.get / .of 才能创建Path类对象
import java.nio.file.StandardOpenOption; // Files类文件'打开'操作相关枚举常量 (定义不同的'打开方式')

// 其他类
import java.util.ArrayList; // ArrayList类
import java.util.Scanner; // Scanner 获取 System.in作为输入流

public class Files_and_Path {
  // 我们来实践一下这个Java 7+之后专门出现的对'文件/目录'操作的类, 据说有更多的功能, 现在实际项目开发常用这个, 操作还内置了'缓存空间'
  public static void main(String[] args) throws IOException{
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
      System.out.println("该文件取得'第2级'的目录为: " + test_file_path.getName(2)); // index从0开始数
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
      System.out.println("该目录是否以'Java_Reboot'开头? " + inner_folder.startsWith("Java_Reboot") + ", 是否以'folder_poop'结尾: " + inner_folder.endsWith("folder_poop")); // true false
    }

    System.out.println();
    // File类实验区 (注: Files并没有对象, 只用静态的'操作Path'方法)
    Path new_folder = Paths.get("Java_Reboot","File_IO_Experiment","Nio_Files","new_folder");
    boolean new_folder_exist = Files.exists(new_folder);
    if(!new_folder_exist){
      System.out.println("检测到Path对象 new_folder不存在, 现直接创建");
      new_folder = Files.createDirectory(new_folder); // 传入new_folder规定的路径进行'新文件夹'的创建, 返回一个新Path对象
      System.out.println("成功在 " + new_folder + " 创建了目录");
    }else{
      System.out.println("检测到 " + new_folder + " 已存在同名目录, 将不会重复创建...");
    }
    Path delete_path = Paths.get("Java_Reboot","File_IO_Experiment","Nio_Files","new_folder","..","test_folder","folder_loop");  // 使用 ".."在相对路径中拼接, 灵活返回上一级 (玩点花的
    // System.out.println(delete_path);
    // System.out.println(Files.exists(delete_path));
    try {
      boolean delete_result = Files.deleteIfExists(delete_path);
      if(delete_result){
        System.out.println("成功删除了文件夹: " + delete_path.getFileName());
      }else{
        System.out.println("未能成功删除文件夹, 可能是文件夹不存在");
      }
    } catch (DirectoryNotEmptyException e) {
      System.out.println("删除失败, 目标文件夹仍有嵌套内容...");
    }
    
    ArrayList<String> file_content = (ArrayList<String>) Files.readAllLines(test_file_path); // 这里返回的是个List<String>, 可以进一步转成ArrayList
    int counter = 1;
    for(String content:file_content){
      System.out.println("第"+counter+"行的内容为: " + content);
      counter++;
    }

    System.out.println();
    Path byte_mp3 = Paths.get("Java_Reboot/File_IO_Experiment/Nio_Files","test_music.mp3"); // 玩点花式路径拼接
    // System.out.println(Files.exists(byte_mp3));
    boolean is_mp3_exist = Files.exists(byte_mp3);
    if(is_mp3_exist){
      // 手动复制 (实验readAllBytes() 和 write())
      System.out.println("检测到 " + byte_mp3.getFileName() + "存在! 正在读取byte信息至数组...");
      byte[] byte_data = Files.readAllBytes(byte_mp3); // 这个操作内置了'操作缓存'
      System.out.println("完成了对 " + byte_mp3.getFileName() + " 的读取!");
      Scanner filename_input = new Scanner(System.in);
      System.out.print("请输入新mp3的文件名(仅名称即可)"); // 这里为了简单做实验, 就不做校验了 :3
      String new_mp3_name = filename_input.nextLine();
      Path output_mp3 = Paths.get("Java_Reboot/File_IO_Experiment/Nio_Files", new_mp3_name+".mp3"); // 这里补充后缀
      // System.out.println("新建的创建的 " + output_mp3.getFileName() + " 现在存在吗? " + Files.exists(output_mp3)); // 第一次为false, 往后为true
      output_mp3 = Files.write(output_mp3, byte_data,StandardOpenOption.CREATE,StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING); // '复制的文件'不存在即创建, 否则自动打开, 打开后'清空原内容', 重新写入新byte[]数据
      // System.out.println("现在往其中写入了数据, "+ output_mp3.getFileName() + " 现在存在吗? " + Files.exists(output_mp3) ); // true
    }else{
      System.out.println("没有找到文件 " + byte_mp3.getFileName() + ", byte复制操作已跳过");
    }


  } // main结束
  

 

}
