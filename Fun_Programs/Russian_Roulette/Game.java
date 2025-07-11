package Fun_Programs.Russian_Roulette;

import java.util.regex.Pattern;
import java.util.Scanner;
import java.util.regex.Matcher;

import java.lang.Math;

import Fun_Programs.Self_Tools.Confirmer; // 自制'确认用户输入'工具类

public class Game{
  // 本程序打算实现一个简单的'俄罗斯轮盘赌', 仿制Russian_Roulette

  public static void start_Round(){
    System.out.print("\n请为蘸豆的玩家命名: ");
    Scanner user_input = new Scanner(System.in);
    String player_name = user_input.nextLine();
    
    int randon_life = (int)(Math.random()*4) + 1; // 玩家生命值 1-4 范围随机 

    Player[] player_list = new Player[2];
    Player user = new Player(player_name, randon_life);
    System.out.println("成功创建玩家: " + user.get_name());
    player_list[0]=user;

    Player comp = new Player("Devil", randon_life);
    System.out.println("成功创建敌对玩家: " + comp.get_name());
    player_list[1]=comp;
    
    
    // boolean keep_running = true;
    // while(keep_running){
      
    // }
  }

  public static void main(String[] args) {
    System.out.println("您好, 欢迎来到山寨Russian_Reoulette");
    Pattern menu_format = Pattern.compile("[1-2]{1}"); // 限定输入为 1, 2
    System.out.println("\n1. 开始游戏");
    System.out.println("2. 退出游戏");
    System.out.print("\n请选择游戏选项: ");
    Scanner user_input = new Scanner(System.in);
    String input_option = user_input.nextLine();
    boolean vaild_option = menu_format.matcher(input_option).matches();
    while(!vaild_option){
      System.out.print("输入无效, 请重新输入选项 (1-2): ");
      input_option = user_input.nextLine();
      vaild_option = menu_format.matcher(input_option).matches();
    }
    switch (input_option) {
      case "1":
        start_Round();
        break;

      case "2":
        System.out.println("喜欢您来~~");
        System.exit(0);
        break;
    
      default:
        System.out.println("程序异常退出!!");
        break;
    }
  }
}