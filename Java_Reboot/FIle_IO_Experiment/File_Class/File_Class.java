package Java_Reboot.FIle_IO_Experiment.File_Class;
// import java.io.BufferedReader;
import java.io.File; // 导入File类
import java.io.FileWriter; // 写'字符文件'
import java.io.BufferedWriter; // '字符文件'硬盘缓冲区
import java.io.IOException; // 文件读写异常类 (涉及磁盘读取都会有)

import java.io.FileFilter;
import java.io.FilenameFilter;


import java.time.LocalDateTime; // 为每个'临时文件'提供 创建时间
import java.time.Instant; // 毫秒 / 秒 时间戳
import java.time.ZoneId; // 时区设置 (解析'时间戳'要用)
import java.time.format.DateTimeFormatter; // 为创建的时间提供'格式化'读取
import java.util.ArrayList; // 动态数组展示&操作用
import java.util.Arrays; // 静态数组展示&操作用

public class File_Class {
  public static void main(String[] args) throws IOException, InterruptedException{
    // File类实验区
    File[] system_roots = File.listRoots();
    System.out.println("使用File.listRoots()查到的根目录盘符有: ");
    for(File i: system_roots){
      System.out.println(i.getPath()); // 查根目录盘符得用getPath()
    }
    System.out.println("当前系统的'分隔符为': " + File.separator);
    System.out.println("多路径'集合'的之间的分隔符为: " + File.pathSeparator);
    
    // System.out.println("当前的默认目录是" + System.getProperty("user.dir")); 

    // 创建一个目录, 用于保存下面的'临时文件'
    File output_path = new File("Java_Reboot/File_IO_Experiment/File_Class/temp_files"); // 通过File创建一个'文件夹'(路径), 此处假设temp_files文件夹不存在
    if(output_path.mkdir()){
      System.out.println("文件夹 /temp_files 成功创建!");
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
    DateTimeFormatter time_format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH时mm分ss秒"); // 自定义'输出模版'
    String time_str = time_format.format(dateTime_now); // 应用'输出模版', 返回字符串

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

    /* 其他的实例方法实验区, 还有很多呢! */
    System.out.println("\n我们再来看点其他的示例方法");
    // 文件 / 目录状态判断 & 查询
    System.out.println(output_path + "是路径吗? " + output_path.isDirectory()); // true
    System.out.println("那它是绝对路径吗? " + output_path.isAbsolute()); // false
    File target_file = new File("Java_Reboot/File_IO_Experiment/File_Class/Am_I_a_file.txt"); // 将指定外部文件作为一个 File对象 (如有
    System.out.println("读取的target_file '"+ target_file.getName()+"' 是一个文件吗? " + target_file.isFile()); // true
    System.out.println("那该文件是隐藏文件吗? " + target_file.isHidden()); // false
    if(target_file.isFile()){
      System.out.println("那么是文件, 就一定有个大小, 该文件的大小为: " + target_file.length() + " Bytes");
      Instant ms_till_now = Instant.ofEpochMilli(target_file.lastModified()); // .lastModifed返回的是'毫秒时间戳', 默认的长毫秒不带'时区'的, 要对其解析的话, 我们就需要一个'带时区'的DateTimeFormatter
      DateTimeFormatter ms_with_zone = DateTimeFormatter.ofPattern("yyyy-MM-dd HH时:mm分:ss秒").withZone(ZoneId.systemDefault()); // 使用系统的'时区'
      System.out.println("该文件最后一次修改于: " + ms_with_zone.format(ms_till_now));  // 毫秒时间戳 转 正常日期
    }
    
    System.out.println();
    // 下面做了一个小实验, 在正常的路径中突然加入'/..'来尝试返回上一级目录在语法上是可行的, 但是.exist()并不会'灵活变通', 还是会照着路径'死查'
    // File test = new File(target_file.getPath() + "/../char_lyrics.txt");
    // String file_returned_path = target_file.getPath() + "/..";
    // System.out.println("test 取得的路径为: " + test.getPath());
    // System.out.println("这个路径取得的文件存在吗? " + test.exists());

    // 如果要取得'上一级', 建议通过.getParent()方法
    File last_folder = new File(target_file.getParent());
    System.out.println("last_folder通过target_file为参考取得的路径为: " + last_folder.getPath()); // target_file 位于 /Java_Reboot/File_IO_Experiment/File_Class/Am_I_a_file.txt, 返回'上一级'就是到/File_Class(文件也算'一级')
    System.out.println("last_folder再往上一级是: " + last_folder.getParent());
    
    // 文件 / 目录操作
    System.out.println("\n接下来我们来看看针对文件/目录的操作");
    File new_folder = new File("Java_Reboot/File_IO_Experiment/File_Class/mkdir_folder"); // 指定新建的'mkdir_folder'位于'File_Class'目录下
    if(new_folder.mkdir()){
      System.out.println("新文件夹 " + new_folder.getName() + "成功创建");
    }else{
      System.out.println("文件夹 " + new_folder.getName() + "已经被创建了...");
    }
    File generate_file = new File(new_folder.getPath() + "/My_newfile.txt"); // 往新文件夹中写入新文件 My_newfile.txt;
    generate_file.deleteOnExit(); // 设定该文件在JVM退出时被删除
    System.out.println("现在是在.createNewFile()之前, 文件是否存在: " + generate_file.exists());  // false
    generate_file.createNewFile();
    System.out.println("现在是.createNewFile()之后, 现在文件在不在? " + generate_file.exists()); // true

    File multi_folder = new File("Java_Reboot/File_IO_Experiment/File_Class/multi_folder/folder_1/folder_2/last_folder"); // 试一下创建多级目录
    if(multi_folder.mkdirs()){
      System.out.println("成功在File_Class下创建了多级目录/multi_folder/folder_1/folder_2/last_foler");
    }else{
      System.out.println("该多级目录已存在...");
    }

    System.out.println();
    // 目录遍历
    File folder_list = new File("Java_Reboot/File_IO_Experiment"); // temp_files的上一级就到 File_IO_Experiment
    System.out.println("folder_list当前的目录为: " + folder_list.getPath());
    String[] files_in_folder_list = folder_list.list(); // 使用list方法取得当前目录下的 所有文件/文件夹, 返回一个String[]
    System.out.println("当前目录下有: " + Arrays.toString(files_in_folder_list));

    // 突然蹦出来两个新的接口, FileFilter 和 FileNameFilter, 主要在这里面就是根据 'File类的各种不同方法' 返回的boolean进行文件判断和过滤
    // FileFilter: 传入的File类可能会被解析成 文件 / 文件夹
    class TxtFilter implements FileFilter{
      public TxtFilter(){} // 空的构造函数
      public boolean accept(File current_file){
        String file_name = current_file.getName();
        return current_file.isFile() && file_name.endsWith(".txt"); // 判断当前'文件' 是否为一个 .txt 文件
      }
    }
    TxtFilter txt_filter = new TxtFilter();

    // FilenameFilter: 传入的File类会被'自动分开', 前面是所处路径, 后面是文件(夹)名
    class Mp3Filter implements FilenameFilter{
      public Mp3Filter(){}
      public boolean accept(File path, String filename){
        // 注: 这里前面的File是'目录对象'
        boolean suffix_judge = filename.endsWith(".mp3");
        return suffix_judge;
      }
    }
    Mp3Filter mp3_filter = new Mp3Filter();
    
    File[] txtfile_arr = folder_list.listFiles(txt_filter); // .listFiles返回File[]数组, 传入FileFilter的实现类, 这里过滤出所有的.txt文件
    File[] mp3file_arr = folder_list.listFiles(mp3_filter); // 同上, 但是这里传入的是一个FilenameFilter的实现类, 滤出所有的.mp3

    // StringBuilder[] sb_arr = new StringBuilder[10]; // 不用ArrayList, 哥们想办法另辟蹊径
    // for(int i=0; i<file_arr.length; i++){
    //   sb_arr[i] = new StringBuilder(); // StringBuilder数组, 每一个元素都是 单独的'StringBuilder对象'
    //   sb_arr[i].append(file_arr[i].getName()); // 为每一个单独的StringBuilder对象元素 写入 File[]数组中的文件名
    // }
    // 上面的StringBuilder[]本质上还是'静态数组', 并不会因为是个StringBuilder既可以'动态变大小', IMSB

    ArrayList<String> txtFiles_arraylist = new ArrayList<>(); // txt的动态数组
    ArrayList<String> mp3Files_arraylist = new ArrayList<>(); // mp3的动态数组

    for(File i : txtfile_arr){
      txtFiles_arraylist.add(i.getName());
    }
    for(File i : mp3file_arr){
      mp3Files_arraylist.add(i.getName());
    }

    System.out.println("txtFiles_arraylist中的内容: " + txtFiles_arraylist.toString());
    System.out.println("mp3Files_arraylist中的内容: " + mp3Files_arraylist.toString());
    System.out.println("JVM环境现已退出");
  }

}
