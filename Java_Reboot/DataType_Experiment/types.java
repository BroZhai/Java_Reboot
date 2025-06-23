package Java_Reboot.DataType_Experiment;

public class types {

  public static void main(String[] args) {
    byte a = 100;
    // byte a = 200; 超出表示范围
    int test = 123;
    byte aa = (byte) test; // '高数据' 转 '低数据', 涉及 强制类型转换
    System.out.println("byte: "+ a + ", int强制类型转换byte: "+ aa);

    short b = 32767;
    // short b = 114514; 超出表示范围
    System.out.println("short: "+ b);

    int c = 0144; // 0开头为8进制表示, (1x8^2 + 4 x 8 + 4) = 100
    long d = 0xFF; // 0x为16进制, 这里的FF为0x00FF, (15 x 16^1 + 15) = 255
    System.out.println("八进制int: "+ c + ", 十六进制long: " + d);

    /*float, double, boolean此处跳过 */
    
    char fl = 'A';
    char sl = '\u0042'; // B 的Unicode
    System.out.println("第一个字母: " + fl + "\n第二个字母: "+ sl);

    System.out.println("短属性:\tTekon\t一个超级无敌巨巨巨巨巨巨长的属性:\tbaka"); // 利用'制表符'规范&格式化输出
  }
}
