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

    System.out.println();

    // 创建临时文件.createTempFile() (每次执行都会创建一个不同的空文件)
    // 注意: 要更改文件名, 一定要等到所有数据的'读写'操作后, 最后再重命名 (.renameTo()本身原子性问题, 不会动更新'原File对象'的路径)
    File generated_temp = File.createTempFile("temp_file", ".txt", output_path); // temp_file随机数字.txt
    // 正常来说, 此时在/temp_files目录下就会有 temp_file随机数字.txt 创建, 我们来做个验证
    if(generated_temp.exists()){
      System.out.println("临时文件创建成功, 当前文件名: " + generated_temp.getName());
    }else{
      System.out.println("临时文件创建失败...程序已自行退出");
      System.exit(1);
    }
    
    // 获取当前时间并进行格式化, 一会而作为内容输入'临时文件', 同时'更改的文件名'也会用
    LocalDateTime dateTime_now = LocalDateTime.now(); 
    DateTimeFormatter output_format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH时mm分ss秒"); // 自定义'输出模版'
    String time_str = output_format.format(dateTime_now); // 应用'输出模版', 返回字符串

    // BufferedWriter一旦打开File对象时, 就会'一直占用'该对象, 从而导致'其他的修改'失效(如.renameTo())
    BufferedWriter temp_writer = new BufferedWriter(new FileWriter(generated_temp)); // FileWrier中可以直接传File对象, 其他类似类同理

    // 正式往临时文件中写内容
    temp_writer.write("本文件创建的时间为: " + time_str);
    temp_writer.flush();
    temp_writer.close();

    // 在对临时文件的'读写操作'.close()完成后, 此时才能进行文件名的更改
    if(generated_temp.renameTo(new File(output_path,"重命名临时文件"+ time_str + ".txt"))){ // 别忘了带路径 + 文件后缀, 传入的是File对象
      System.out.println("临时文件在写入数据后前被成功重命名, 当前文件名: " + generated_temp.getName());
      System.out.println("诶? 为什么这里更改显示的名字和之前一样? 哦! 原来是.renameTo()在成功更新后, 并不会更新'原File对象'的引用啊, 所以才没读到正确更新后的名字捏..."); // 这个大坑耗了老子3个小时, 去死吧 --
      //
    }else{
      System.out.println("临时文件重命名失败...程序已自行退出");
      System.exit(1);
    }

  }

}
