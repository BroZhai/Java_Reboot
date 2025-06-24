package Java_Reboot.Builtin_Classes;
import java.lang.StringBuffer;
import java.lang.StringBuilder;

public class StringBuffer_and_Builder {

  public static void main(String[] args) {
    StringBuilder builder = new StringBuilder(10); // 指定'容量大小'为10
    StringBuffer buffer = new StringBuffer("wow"); // 大小为 3 + 16
    System.out.println("builder当前的容量大小: " + builder.capacity() + ", buffer的容量为: "+ buffer.capacity());
    builder.append("泥豪, 我是冰精");
    System.out.println("builder的内容长度为: " + builder.length()); // 8 (空格也算)
    System.out.println("builder当前的内容为: " + builder);

    builder.insert(6,"雾之湖的大"); // 插入到'是'(下标为5)后边, 填6 (默认插入'下标字符-1'处, 左侧) , 可以看到是"直接应用更改"的 (无需新变量再存)
    System.out.println("在第5个的'右侧'(6) 处插入了新东西: " + builder); 
    builder.delete(4, 6); // delete范围[4,6) , 删除 4 5 下标的字符
    System.out.println("删除了下标4-5的字符: " + builder);
    builder.reverse(); // 反转字符序列
    System.out.println("别急, 有反转: " + builder + "\n");

  }
}
