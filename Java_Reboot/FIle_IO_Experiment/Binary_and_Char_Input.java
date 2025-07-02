package Java_Reboot.FIle_IO_Experiment;
import java.io.*; // Java对于二进制'数据流'的处理要用 java.io包中的工具

public class Binary_and_Char_Input {

  public static void main(String[] args) throws IOException{

    System.out.println("System.getProperty()拿到的当前工作路径: " + System.getProperty("user.dir")); // 用这行命令查看当前Java的工作路径 Java_Reboot/
    
    /* 读取'字符'文件 (用到FileReader类, 会抛出IOException异常) */ 
    FileReader file = new FileReader("Java_Reboot/File_IO_Experiment/char_lyrics.txt"); // 从上面查出来的路径开始'拼接'
    char[] file_content = new char[100]; // 小问题, 如果直接创建对应数组, 你并不知道 文件实际有多大 XD
    // System.out.println("下面开始逐个读取'字符'");
    // int current_char_value;
    // while((current_char_value = file.read()) != -1){ // FileReader对象.read(), 直接读取单个字符, 返回对应的int值, 读到结尾返回-1
    //   System.out.println("当前读取到的字符为: "+ (char) current_char_value + "对应数字: " + current_char_value);
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
    BufferedReader buffer_content = new BufferedReader(my_file, 20); // 设置缓冲区大小为20, 表示每次只读20个byte/char
    StringBuilder sb = new StringBuilder(40); // StringBuilder随便初始大小都行, 反正会自动扩容
    int current_char_value;
    while( (current_char_value = buffer_content.read()) != -1){ // current_char_value动态读取赋值, 不为-1时持续循环
      sb.append((char) current_char_value); // 将当前读取到的 current_char_value 转成 char 存到上面定好的StringBuilder中
      // 小贴士: 这里的sb会在读完buffered的内容后, 再通知BufferedReader'读下一段', 从而达成'每次读固定长度'
    }
    System.out.println("StringBuilder从BufferedReader中'分段'存到的内容: "+sb.toString());
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


    /*字符流输入 (Tips: Java中的控制台在 输入时是'字节流'byte stream, 但是后续会被 自动封装成'字符流'char stream 进行调用) */ 
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
    FileWriter overwrite_writer = new FileWriter("Java_Reboot/File_IO_Experiment/writer_output.txt"); // 当文件不存在时, 会自动创建
    overwrite_writer.write("你好, 这里是来自Java中的FileWriter的字符输出\n"); // 这里的\n会被识别成'换行'
    overwrite_writer.write("默认我会覆盖文件的'原内容'哦qwq\\n"); // 这里输出的则会是 \\n
    overwrite_writer.flush(); // 一定要有这个flush(), 表示刷新并立即写入数据 
    overwrite_writer.close(); // 关闭操作流

    // 追加写入, 在创建对象时 多传一个布尔参数
    FileWriter append_writer = new FileWriter("Java_Reboot/File_IO_Experiment/writer_output.txt",true); // 这里多加了一个布尔的参数, 表示'追加内容'而非覆写
    append_writer.write("\n\n诶嘿, 看见没, 我是新追加的内容, 不会覆盖该文件, 因为我多传了一个参awa");
    append_writer.flush();
    append_writer.close();
  }
}