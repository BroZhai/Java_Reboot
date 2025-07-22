package Java_Reboot.Lambda_Experiement;

public class Main {

  public static void main(String[] args) {
    // 定义了一个'计算器' 接口'对象'
    Calculator add = (a,b) -> a+b; // 利用'接口对象'对其内部方法实现'花样定义' (lamda实现)
    Calculator sub = (c,d) -> c-d;
    Calculator multiply = (num1, num2) -> num1*num2;
    Calculator devide = (int1, int2) -> {
      double decimal_value = (double)int1/int2;
      System.out.println("取得的精准小数为: " + decimal_value);
      return int1/int2; // {}多行复杂操作需 手动return
    };
    
    System.out.println("加法结果: "+add.calculate(5, 3)); // 8
    System.out.println("减法结果: "+sub.calculate(5, 3)); // 2
    System.out.println("乘法结果: "+multiply.calculate(5, 3)); // 15
    System.out.println("除法结果: "+devide.calculate(5, 3)); // 1 (1.66666...7)

  } // main结束

}