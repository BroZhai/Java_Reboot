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

    // 创建临时文件 (每次执行都会创建一个不同的空文件)

    File temp_file = File.createTempFile("temp_file", ".txt", output_path);  // 通过File.createTempFile创建一个'临时空文件', 通过传入'File路径对象'进行'输出目录指定'
    // temp_file.deleteOnExit(); // 设置该'临时文件'在JVM退出时删除

    // 注: 上面创建的'临时空文件'名称后面会一定跟一段'随机数字'! 表明每个生成的'临时文件'都是独立的 (执行完上一行后文件会直接被创建)
    // 一般来说, 我们不建议给'临时文件'重命名(一般文件还是ok的), 但是这里处于好奇, 踩了一大堆的雷最后实现了 '自定义重命名' (对之后正常的文件命名应该有个参考价值)
    System.out.println("临时文件已成功创建, 文件名: "+ temp_file.getName());

    // Deprecated, 这里自己的思路是用一个新的File对象 去指向 上面刚刚生成的文件, 但是给自己带到 new File()直接新建文件 的大坑里面去了 XD, 但是一些思路还是可以借鉴的
    // File rename_temp = new File(output_path, temp_file.getName()); // rename_temp指向刚刚创建的'临时文件' (直接'拼接', 省事)
    // // File rename_temp = new File(output_path.toString() + temp_file.getName());
    // System.out.println("rename尝试获取的名称: " + rename_temp.getName());
    
    // 获取 和 格式化时间, 准备赋在文件名上
    LocalDateTime datetime_now = LocalDateTime.now();
    DateTimeFormatter read_format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH点mm分ss秒"); // 读取LocalDateTime对象按照'该格式'进行输出 (自定义'关键字'位置)
    String formatted_time = read_format.format(datetime_now);
    System.out.println("当前的时间是: " + formatted_time);

    BufferedWriter temp_writer = new BufferedWriter(new FileWriter(temp_file)); // 啊哈, File对象也可以直接作为'输入流'
    temp_writer.write("该文件创建的时间为: " + formatted_time);
    // Thread.sleep(5000);
    temp_writer.flush();
    temp_writer.close();
    System.out.println("BufferedWriter 尝试往 空File对象中 写入了内容");
    // temp_file.renameTo(new File("临时文件" + formatted_time +".txt")); // 这里重命名成功了, 但因为忘记加'路径', 就输出到默认的'工作目录'去了 
    temp_file.renameTo(new File(output_path, "临时文件"+ formatted_time+".txt")); // 正解
    
    

  }

}
