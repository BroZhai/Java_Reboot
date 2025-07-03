package Java_Reboot.FIle_IO_Experiment;
import java.io.*; // Java对于二进制'数据流'的处理要用 java.io包中的工具

import java.util.Arrays; // 用于静态数组的展示
import java.text.DecimalFormat; // 用于'格式化'小数展示
import java.time.Duration; // 操作计时相关 (计算时间戳差值)
import java.time.Instant; // 操作计时相关 (获取时间戳)

public class Binary_and_Char_Input {

  public static void main(String[] args) throws IOException{

    System.out.println("System.getProperty()拿到的当前工作路径: " + System.getProperty("user.dir")); // 用这行命令查看当前Java的工作路径 Java_Reboot/
    
    /* 读取'字符'文件 Structured Data (用到FileReader类, 会抛出IOException异常) */ 
    FileReader file = new FileReader("Java_Reboot/File_IO_Experiment/char_lyrics.txt"); // 从上面查出来的路径开始'拼接'
    char[] file_content = new char[100]; // 小问题, 如果直接创建对应数组, 你并不知道 文件实际有多大 XD
    // System.out.println("下面开始逐个读取'字符'");
    // int value_examined;
    // while((value_examined = file.read()) != -1){ // FileReader对象.read(), 直接读取单个字符, 返回对应的int值, 读到结尾返回-1
    //   System.out.println("当前读取到的字符为: "+ (char) value_examined + "对应数字: " + value_examined);
    // };
    file.read(file_content); // 将读取到的'字符'文件 直接存到对应的char[] 数组中
    System.out.print("从文件存储到char[]数组中的数据为: ");
    for(char i : file_content){
      System.out.print(i);
    } 
    file.close();  // 注: 在FileReader对象被读到尾部之后, 其指针一直都会指向'-1', 如果需要'再利用'该文件的话, 只能关闭当前的 FileReader 并 重新创建对象 (还不能重名!)

    System.out.println("\n");
    System.out.println("接下来我们来看一下 字符缓冲区 BufferedReader 怎么用");
    FileReader my_file = new FileReader("Java_Reboot/File_IO_Experiment/char_lyrics.txt"); // 注意这里不要惯性思维, 有时不创建对象直接用 new FileReader也可以的 -w-
    // 我们发现直接读'固定大小'的文件不太现实, 所以我们就需要建立一个'读取缓冲区'
    // 这里我们要清楚数据在电脑中的读取流程: 磁盘 -> 内存 -> CPU寄存器, 其中 磁盘到内存 对应一个缓存, 内存 -> CPU 对应另一层缓存
    BufferedReader buffer_content = new BufferedReader(my_file, 1024); // 设置'硬盘缓冲区'大小为1024, 表示每次会从 硬盘中的'源文件'中读 1024Byte 到 '内存', 直至文件被完全载入内存
    
    StringBuilder sb = new StringBuilder(40); // StringBuilder随便初始大小都行, 反正会自动扩容 (可以理解成这里是CPU寄存器中的'存储空间')
    int value_examined; 
    char[] buffer_temp = new char[20]; // 这里才是真正的'内存缓冲区', 每次.read()时都会按照这里的 [指定大小] 存到CPU的寄存器中
    int read_counter = 1; // 读取计数器
    while( (value_examined = buffer_content.read(buffer_temp)) != -1){ //  不为-1时持续循环, 将读到的值填到上面设的 暂存char[]数组中, 这里的.read(数组[])方法会返回'从内存读的字符数'
      // 此时value_examined 就是从'内存缓冲区'中读取的长度
      System.out.println("当前从'内存缓冲区'中fetch到的大小为: " + value_examined + " Bytes, 已读入CPU... 这是第 " + read_counter +" 次内存读取");
      sb.append(buffer_temp, 0 ,value_examined); // .append(传入数据数组[], 读取偏移, 读取长度)
      // 小贴士: 这里的sb会在读完buffered的内容后, 会再通知BufferedReader'读下一段'到'内存缓冲区'中, 从而达成'每次读固定长度'

      // sb.append((char) value_examined); // 将当前读取到的 value_examined 转成 char 存到上面定好的StringBuilder中
      // 注意, 上面这样'逐字'读取的方式是错的, 作用就直接等同于用FileReader了, 并没有实际减少I/O的次数

      read_counter++; 
    }
    System.out.println("StringBuilder从'内存缓冲区'中读完的数据: "+sb.toString());
    System.out.println("当前StringBuilder的长度: " + sb.length());
    buffer_content.close(); // 在完成'流'操作后, 一定要'关闭流', 防止内存泄露
    my_file.close(); // 同上

    System.out.println();

    // 试一下readLine()
    BufferedReader line_reader = new BufferedReader(new FileReader("Java_Reboot/File_IO_Experiment/char_lyrics.txt"));
    // StringBuilder lines = new StringBuilder();
    System.out.println("BufferedReader的readLine()实例方法, 第一次read: "+line_reader.readLine());
    System.out.println("BufferedReader的readLine()实例方法, 第二次read: "+line_reader.readLine());
    System.out.println("BufferedReader的readLine()实例方法, 第三次read: "+line_reader.readLine());
    System.out.println("BufferedReader的readLine()实例方法, 第四次read: "+line_reader.readLine() + ", 可以看到到头了会返回null");
    line_reader.close();


    // 注意: readLine()返回的是String数据, 也就是说 文件中里面的 换行\n 和 回车\r 在返回时会被忽略
    System.out.println("\n我们再来用while循环过一遍");
    BufferedReader loop_reader = new BufferedReader(new FileReader("Java_Reboot/File_IO_Experiment/char_lyrics.txt"));
    StringBuilder loop_output = new StringBuilder(20);
    String current_line;
    while ((current_line = loop_reader.readLine()) != null) {
      loop_output.append(current_line);
    }
    System.out.println("loop_output中的数据为:  " + loop_output.toString());
    loop_reader.close();


    // 字符流输入 (Tips: Java中的控制台在 输入时是'字节流'byte stream, 但是后续会被 自动封装成'字符流'char stream 进行调用) 
    char c;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 从控制台读取输入
    System.out.print("\n请按下 e键 + 回车 继续: ");
    do{
      c = (char) br.read();
      System.out.println("你当前按下了: " + c);
    }while(c != 'e');
    // 在输入一个 非'e'的字符时, 控制台会进行3次打印 (至少
    /*
     * 第一次打印的是 '输入的字符'
     * 第二次是 '输入完毕的回车' \r,  (控制台不显示, 但是会换行)
     * 第三次是 'println()'的换行 \n (同上)
     * (小实验: 把println()改成print(), 控制台只会输出两次pwp)
     */

     /* 接下来我们来看一下字符流的'文件输出' .write() */
    System.out.println("欢迎来到'字符'写入区");
    FileWriter overwrite_writer = new FileWriter("Java_Reboot/File_IO_Experiment/writer_output.txt"); // 当文件不存在时, 会自动创建
    overwrite_writer.write("你好, 这里是来自Java中的FileWriter的字符输出\n"); // 这里的\n会被识别成'换行'
    overwrite_writer.write("默认我会覆盖文件的'原内容'哦qwq\\n"); // 这里输出的则会是 \\n
    overwrite_writer.flush(); // 一定要有这个flush(), 表示刷新并立即写入数据 
    overwrite_writer.close(); // 关闭操作流
    System.out.println("程序生成了一个writer_output.txt文件并往其中覆写了内容");

    // 追加写入, 在创建对象时 多传一个布尔参数
    FileWriter append_writer = new FileWriter("Java_Reboot/File_IO_Experiment/writer_output.txt",true); // 这里多加了一个布尔的参数, 表示'追加内容'而非覆写
    append_writer.write("\n\n诶嘿, 看见没, 我是新追加的内容, 不会覆盖该文件, 因为我多传了一个参awa");
    append_writer.flush();
    append_writer.close();
    System.out.println("程序又往刚刚生成的writer_output.txt中'追加'了新内容");

    // 当然, 读文件的时候有缓冲的BufferedReader, 那么写文件时也有BufferedWriter啦
    // 其使用过程和BufferedReader类似: BufferedWriter(输入流)
    BufferedWriter writer_with_buffer = new BufferedWriter(new FileWriter("Java_Reboot/File_IO_Experiment/bufferedWritter_output.txt")); // 这里的输入流传的是和上面一样的FileWriter, true追加写入
    writer_with_buffer.write("虽然直接用FileWriter更简单直接\n");
    writer_with_buffer.write("但是为了安全和高效读写, 我们一般还是用BufferedReader和Writer吧\n");
    writer_with_buffer.write("哦对了, BufferedWriter有个.newLine()直接换行方法来着?\n");
    writer_with_buffer.newLine();
    writer_with_buffer.write("诶嘿, 还真有用");
    char[] test_char_arr = {'\u6211','\u662f','\u742a','\u9732','\u8Bfa'}; // 我 是 琪 露 诺
    writer_with_buffer.write("我们再来尝试直接写入一个 字符数组[]: " + Arrays.toString(test_char_arr));
    writer_with_buffer.flush();
    writer_with_buffer.close();
    System.out.println("程序通过BufferedWriter往bufferedWritter.txt中安全的写入了数据");

    // 要想'追加写入'文件, 还是得在FileWriter输入流后边加一个布尔的参数, 然后才能用BufferedWriter.append()方法进行内容追加
    BufferedWriter writer_append = new BufferedWriter(new FileWriter("Java_Reboot/File_IO_Experiment/bufferedWritter_output.txt",true));
    writer_append.append("\n我是尝试追加的新内容");
    writer_append.flush();
    writer_append.close();
    System.out.println("程序通过BufferedWriter又往上面的文件中追加了新内容, 注意FileReader流后边要多传个boolean参");


    /* 字节Byte流实验区 Unstructred Data */
    String test_path = "Java_Reboot/File_IO_Experiment/"; // 快捷路径引用, 懒得再打了
    FileInputStream unstructured_file = new FileInputStream(test_path+"bin_picture.jpg");
    System.out.println("当前读到的文件大小为: "+ unstructured_file.available() + " Bytes"); // 使用.available()方法获取'文件大小', 查到为8846 Bytes (字节流特有的.available()方法)
    byte[] unstructured_storage = new byte[9000]; // 9000 > 8846, 设定一个byte数组的'存储空间'
    unstructured_file.read(unstructured_storage); // 将 字节文件 直接读入上面设的'存储空间' (暂未使用缓冲区, 一会儿下面搞)
    System.out.println("当前unstructured_storage[]数组的大小为: " + unstructured_storage.length + " Bytes"); 
    unstructured_file.close();
    System.out.println("尝试读取了 字节文件bin_picture.jpg 并存入了本地的byte[]数组中");

    // 我们假设读到的数据已经被存入到 byte[]中了, 下面进行'文件复制'输出验证
    String copied_path = "Java_Reboot/File_IO_Experiment/Copied_Files/";
    FileOutputStream output_picture = new FileOutputStream(copied_path+"copied_picture.jpg");
    output_picture.write(unstructured_storage);
    output_picture.flush();
    output_picture.close();
    System.out.println("尝试复制了 字节文件bin_picture.jpg 至 Copied_Files文件夹下, 能跑到这说明应该成功了"); // mission successful (Jolly并感
    
    System.out.println("\n接下来实践一下 字符文件的缓冲区");
    // 接下来我们来试一下使用 硬盘缓冲区BufferedInputStream 和 ArrayList<Byte> 配合 while循环来读取内容
    int buffer_size = 131072; // 统一定义硬盘 & 内存的 '缓存区大小' (小实验: 不同的 内存buffer_size将会影响'读取时间')
    BufferedInputStream unstructured_input_buffer = new BufferedInputStream(new FileInputStream(test_path+"bin_music.mp3"), buffer_size); // 自定义的'硬盘缓冲大小', 每次从硬盘读128KB
    System.out.println("从硬盘读取到bin_music.mp3的大小为: "+unstructured_input_buffer.available() + " Bytes"); // 字节流数据特有的.available()方法, 查看'已读取的文件大小'
    int file_size = unstructured_input_buffer.available(); // 读取的文件大小, 用于建立对应的存储数组用
    byte[] file_storage = new byte[file_size]; // 读取的文件大小
    byte[] ram_buffer = new byte[buffer_size]; // 内存缓冲区域, 每次读 131072 Byte (128KB)
    int read_bytes;
    read_counter = 1;
    double progress; // 实现一个'读取进度'百分比%
    DecimalFormat df = new DecimalFormat("#.00"); // 保留两位小数格式
    int total_readed = 0;
    Instant start = Instant.now(); // 记录开始时间的'时间戳'
    while((read_bytes = unstructured_input_buffer.read(ram_buffer)) != -1 ){ // BufferedInputStream.read()读到文件尾时也会返回 "-1"
      total_readed += read_bytes;
      progress = ((double) total_readed / file_size)*100;
      // progress = (double) progress * 100;

      System.out.println("当前从'内存缓冲区'中读了: " + read_bytes + " Bytes, 这是第 " + read_counter + " 次内存读取, 读取进度: " + df.format(progress) + "%");
      // dynamic_buffer.add((byte) current_byte_int);
      // 上面是错误的是写法, 逐字读I/O开销一定是大的

      // System.arraycopy(原数组, 原数组起始位置, 目标数组, 目标数组起始位置, 复制长度), 这是Java中用于高效复制'静态数组'的一个方法
      System.arraycopy(ram_buffer, 0, file_storage, (read_counter-1)*buffer_size, read_bytes); // Tips: 这个前后读取的'大小标准'一定要一致, 这里的'读取偏移'就用上了, 不一致将导致读到的数据'缺斤少两'XD (buffer_size=131072)
      read_counter++;
    }
    Instant end = Instant.now();
    Duration operation_time = Duration.between(start, end);
    System.out.println("数据在经过 " + (read_counter-1) +" 次内存读取后被完全存到了file_storage[]中, 本次读取操作耗时: " + operation_time.toMillis() + "毫秒");
    unstructured_input_buffer.close(); // 完成读入操作

    // 现在拿到储存好的数据file_storage[]了, 再用BufferedOutputStream进行Copy输出
    int output_buffer = 8192;
    BufferedOutputStream unstructured_output_buffer = new BufferedOutputStream(new FileOutputStream(copied_path+"double_music.mp3"), output_buffer); // 自定义输出缓存为8192Byte (这里的输出'硬盘缓存'相对来说没那么重要, 记着这玩意主要影响'内存缓冲区')
    Instant before = Instant.now(); // 尝试记录了操作耗时, 查看不同'缓冲区'大小对写出操作的影响, 结果发现没啥影响(?
    unstructured_output_buffer.write(file_storage);
    unstructured_output_buffer.write(file_storage);

    // 直接往文件中连续写入两段'音频数据' (music x 2, 发现输出的文件在5:14处成功衔接)
    
    unstructured_output_buffer.flush();
    unstructured_output_buffer.close();
    Instant after = Instant.now();
    Duration lastd_for = Duration.between(before, after);
    System.out.println("本次写出操作耗时: " + lastd_for.toMillis() + "毫秒");
    

  }
}