package Java_Reboot.Builtin_Classes;
import java.io.*; // Java对于二进制'数据流'的处理要用 java.io包中的工具
public class Binary_IO {

  public static void main(String[] args) throws IOException{
    char c;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 从控制台读取输入
    System.out.println("按下e键退出");
    do{
      c = (char) br.read();
      System.out.println("你当前按下了: " + c);
    }while(c != 'e');
  }
}