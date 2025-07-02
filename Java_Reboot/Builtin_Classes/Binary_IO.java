package Java_Reboot.Builtin_Classes;
import java.io.*; // Java对于二进制'数据流'的处理要用 java.io包中的工具
public class Binary_IO {

  public static void main(String[] args) throws IOException{
    // 字符流输入 (Tips: Java中的控制台在 输入时是'字节流', 但是后续会被 自动封装成'字符流' 进行调用)
    char c;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 从控制台读取输入 ()
    System.out.println("按下e键退出");
    do{
      c = (char) br.read();
      System.out.print("你当前按下了: " + c);
    }while(c != 'e');
    // 在输入一个 非'e'的字符时, 控制台会进行3次打印
    /*
     * 第一次打印的是 '输入的字符'
     * 第二次是 '输入完毕的回车' \r (控制台不显示, 但是会换行)
     * 第三次是 'println()'的换行 \n (同上)
     * (小实验: 把println()改成print(), 控制台只会输出两次pwp)
     */

  }
}