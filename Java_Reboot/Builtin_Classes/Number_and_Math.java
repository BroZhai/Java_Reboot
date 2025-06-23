package Java_Reboot.Builtin_Classes;
import java.lang.Number; // 导入 Number类
import java.lang.Math; // 导入 Math类

public class Number_and_Math {
  public static void main(String[] args) {
    Number num1 = Double.valueOf(133.14);
    System.out.println("num当 前的值为: " + num1);
    double sussy_number = 150.17;
    // num1.compareTo(); // 抽象父类Number不包含compareTo()方法, 下面具体的子类才有
    Double num2 = (Double) num1; // 强制类型转换, 变成'具体的子类'
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

  }
}
