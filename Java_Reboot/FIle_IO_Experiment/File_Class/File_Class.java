package Java_Reboot.FIle_IO_Experiment.File_Class;
// import java.io.BufferedReader;
import java.io.File; // 导入File类

import java.io.FileWriter; // 写'字符文件'
import java.io.BufferedWriter; // '字符文件'硬盘缓冲区
import java.io.IOException; // 文件读写异常类 (涉及磁盘读取都会有)

import java.time.LocalDateTime; // 为每个'临时文件'提供 创建时间
import java.time.format.DateTimeFormatter; // 为创建的时间提供'格式化'读取

public class File_Class {
  public static void main(String[] args) throws IOException, InterruptedException{
    // File类实验区
    File[] system_roots = File.listRoots();
    System.out.println("使用File.listRoots()查到的根目录盘符有: ");
    for(File i: system_roots){
      System.out.println(i.getPath()); // 查根目录盘符得用getPath()
    }
    
    // System.out.println("当前的默认目录是" + System.getProperty("java.io.temdir")); 

    // 创建一个目录, 用于保存下面的'临时文件'
    File output_path = new File("Java_Reboot/File_IO_Experiment/File_Class/temp_files"); // 通过File创建一个'文件夹'(路径), 此处假设temp_files文件夹不存在
    if(output_path.mkdir()){
      System.out.println("文件夹 /temp_files 已成功创建!");
    }else{
      System.out.println("文件夹 /temp_files 已经被创建力...");
    }

    // 创建临时文件.createTempFile() (每次执行都会创建一个不同的空文件)
    File generated_temp = File.createTempFile("temp_file", ".txt", output_path); // temp_file随机数字.txt
    // 此时在/temp_files目录下就会有 temp_file随机数字.txt 创建

    // 先往临时文件中写内容, 最后再命名
    BufferedWriter temp_writer = new BufferedWriter(new FileWriter(generated_temp)); // FileWrier中可以直接传File对象, 其他类似类同理
    // 获取当前时间并进行格式化, 一会而作为内容输入'临时文件', 同时'更改的文件名'也会用
    LocalDateTime dateTime_now = LocalDateTime.now(); 
    DateTimeFormatter output_format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH时mm分ss秒"); // 自定义'输出模版'
    String time_str = output_format.format(dateTime_now); // 应用'输出模版', 返回字符串

    // 正式往临时文件中写内容
    temp_writer.write("本文件创建的时间为: " + time_str);
    temp_writer.flush();
    temp_writer.close();
    generated_temp.renameTo(new File(output_path,"重命名临时文件"+ time_str + ".txt")); // 最后对'临时文件'进行重命名, 别忘了带路径 + 文件后缀, 传入的是File对象

  }

}
