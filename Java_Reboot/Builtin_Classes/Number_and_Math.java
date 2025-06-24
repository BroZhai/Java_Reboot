package Java_Reboot.Builtin_Classes;
import java.lang.Number; // 导入 Number类
import java.lang.Math; // 导入 Math类

public class Number_and_Math {
  public static void main(String[] args) {
    // Number类实验区
    Number num1 = Double.valueOf(133.14);
    System.out.println("num当 前的值为: " + num1);
    double sussy_number = 150.17;
    // num1.compareTo(); // 抽象父类Number不包含compareTo()方法, 下面具体的子类才有, 用于直接比较两个'数字对象'的值
    Double num2 = (Double) num1; // 强制类型转换, 变回'具体的子类'
    switch(num2.compareTo(sussy_number)){
      case 1:
        System.out.println("数字对象值 > " + sussy_number);
        break;
      case 0:
        System.out.println("数字对象值 = " + sussy_number);
        break;
      case -1:
        System.out.println("数字对象值 < " + sussy_number);
        break;
    }

    Double d = 11.0; // 自动装箱, 右边的字面量11.0会自动变成 Double.valueOf(11.0);
    Integer ia = 11; // 同理
    Integer ib = 12;
    Integer ia_copy = 11;
    System.out.println(d.equals(ia)); // .equals 用于比较两个'数据对象', 返回布尔, Double 11.0 != Integer 11, false
    System.out.println(ia.equals(ib)); // false, Integer 11 != Integer 12;
    System.out.println(ia.equals(ia_copy)); // true, Integer 11 = Integer 11;
    if(ia.intValue()==d.intValue()){ // ia, d 数字对象均'取整'
      System.out.println("ia.intValue()的值 " + ia.intValue() + " 和d.intValue()的值\s" + d.intValue() +" 一致!");
    }
    
  }
}
