package Java_Reboot.FIle_IO_Experiment;
import java.io.*; // Java对于二进制'数据流'的处理要用 java.io包中的工具
public class Binary_and_Char_Input {

  public static void main(String[] args) throws IOException{

    System.out.println(System.getProperty("user.dir")); // 用这行命令查看当前Java的工作路径 Java_Reboot/
    // 读取'字符'文件 (用到FileReader类, 会抛出IOException异常)
    
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
    } // 注: 在FileReader对象被读到尾部之后, 其指针一直都会指向'-1', 如果需要'再利用'该文件的话, 只能关闭当前的 FileReader 并 重新创建对象 (还不能重名!)
    file.close(); 

    FileReader my_file = new FileReader("Java_Reboot/File_IO_Experiment/char_lyrics.txt"); // 注意这里不要惯性思维, 有时不创建对象直接用 new FileReader都可以的 -w-
    System.out.println();
    // 我们发现直接读'固定大小'的文件不太现实, 所以我们就需要建立一个'读取缓冲区'
    BufferedReader buffer_content = new BufferedReader(my_file, 20); // 设置缓冲区大小为10
    StringBuilder sb = new StringBuilder(20); // 这里我们直接用StringBuilder, 初始大小为20, 内容爆了会'自动扩容', 里面实际存的是char[] 或 byte[]
    int current_char_value;
    while( (current_char_value = buffer_content.read()) != -1){ // current_char_value动态读取赋值, 不为-1时持续循环
      sb.append((char) current_char_value); // 将当前读取到的 current_char_value 转成 char 存到上面定好的StringBuilder中
    }
    System.out.println(sb.length());
    buffer_content.close();




    // 字符流输入 (Tips: Java中的控制台在 输入时是'字节流', 但是后续会被 自动封装成'字符流' 进行调用)
    /*
    
    char c;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 从控制台读取输入
    System.out.println("按下e键退出");
    do{
      c = (char) br.read();
      System.out.print("你当前按下了: " + c);
    }while(c != 'e');
    // 在输入一个 非'e'的字符时, 控制台会进行3次打印
    /*
     * 第一次打印的是 '输入的字符'
     * 第二次是 '输入完毕的回车' \r,  (控制台不显示, 但是会换行)
     * 第三次是 'println()'的换行 \n (同上)
     * (小实验: 把println()改成print(), 控制台只会输出两次pwp)
     */

  }
}