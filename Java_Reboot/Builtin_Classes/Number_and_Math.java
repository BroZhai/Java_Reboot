package Java_Reboot.Builtin_Classes;
import java.lang.Number; // 导入 Number类
import java.lang.Math; // 导入 Math类

public class Number_and_Math {
  public static void main(String[] args) {
    /* Number类实验区 */
    Number num1 = Double.valueOf(133.14);
    System.out.println("num当前的值为: " + num1);
    double sussy_number = 150.17;
    // num1.compareTo(); // 抽象父类Number不包含compareTo()方法, 下面具体的子类才有, 用于直接比较两个'数字对象'的值
    Double num2 = (Double) num1; // 强制类型转换, 变回'具体的子类'
    switch(num2.compareTo(sussy_number)){
      case 1:
        System.out.println("数字对象值num > " + sussy_number);
        break;
      case 0:
        System.out.println("数字对象值num = " + sussy_number);
        break;
      case -1:
        System.out.println("数字对象值num < " + sussy_number);
        break;
    }

    Double d = 11.0; // 自动装箱, 右边的字面量11.0会自动变成 Double.valueOf(11.0);
    Integer ia = 11; // 同理
    Integer ib = 12;
    Integer ia_copy = 11;
    System.out.println(d.equals(ia)); // .equals 用于比较两个'数据对象', 返回布尔, Double 11.0 != Integer 11, false
    System.out.println(ia.equals(ib)); // false, Integer 11 != Integer 12;
    System.out.println(ia.equals(ia_copy)); // true, Integer 11 = Integer 11;
    if(ia.intValue()==d.intValue()){ // ia, d 数字对象均'取整', 用==进行'数值比较' (统一数值后再进行比较)
      System.out.println("ia.intValue()的值 " + ia.intValue() + " 和d.intValue()的值\s" + d.intValue() +" 一致!");
    }
    
    /* Math类实验区 */
    System.out.println("\n以下开始为Cirno's Perfect 'Math Class'");
    // random()默认是[0.0, 1.0)的随机double小数, 可自行灵活转换 + 扩展
    int random_value = (int) (Math.random()*100) + 1; // [0+1, 99+1] 范围内取值 (1-100)
    System.out.println("本轮的random_value为: " + random_value);

    double test_value = 4.5;
    System.out.println("4.5取floor()的值为: " + Math.floor(test_value)); // 4.0
    System.out.println("4.5取ceil()的值为: " + Math.ceil(test_value)); // 5.0
    System.out.println("4.5取rint()的值为: " + Math.ceil(test_value)); // rint()小数的四舍五入, 仍返回小数 5.0
    System.out.println(Math.ceil(test_value) > Math.floor(test_value) ? "4.5的ceil > floor大" : "4.5的ceil < floor (?waht?");
    System.out.println("返回了上面的比较中'大'的值: "+ Math.max(Math.ceil(test_value), Math.ceil(test_value)));
    System.out.println(Math.pow(test_value, 2)); // 计算4.5^2, 始终返回Double;

    int cirno = 9;
    System.out.println("cirno被根号制裁惹: " + Math.sqrt(cirno)); // 3.0, double

    System.err.println("3^2 + 4^2 开根号 = "+ Math.hypot(3.0, 4.0));
  }

}
