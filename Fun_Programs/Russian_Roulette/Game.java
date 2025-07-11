package Fun_Programs.Russian_Roulette;

import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

import java.lang.Math;

import Fun_Programs.Self_Tools.Confirmer; // 自制'确认用户输入'工具类

public class Game {
  // 本程序打算实现一个简单的'俄罗斯轮盘赌', 仿制Russian_Roulette

  public static void start_Round() {
    System.out.print("\n请为蘸豆的玩家命名: ");
    Scanner user_input = new Scanner(System.in);
    String player_name = user_input.nextLine();

    int randon_life = (int) (Math.random() * 4) + 1; // 玩家生命值 2-4 范围随机

    // Player[] player_list = new Player[2];
    ArrayList<Player> player_list = new ArrayList<>(2);
    Player user = new Player(player_name, randon_life);
    System.out.println("\n成功创建玩家: " + user.get_name() + "血量: " + user.get_life());
    player_list.add(user);

    Player comp = new Player("Devil", randon_life);
    System.out.println("成功创建敌对玩家: " + comp.get_name() + "血量: " + comp.get_life());
    player_list.add(1, comp);

    int init_shells = (int) (Math.random() * 7) + 2; // 生成 2-8 颗首轮子弹数
    Gun gun = new Gun(init_shells);
    System.out.println();
    boolean keep_running = true;
    while (keep_running && player_list.size() != 1) {
      gun.check_chamber();
      for (int i = 0; i < player_list.size(); i++) {
        if (player_list.get(i).get_life() == 0) {
          player_list.remove(i);
          keep_running = false;
          break;
        } else if (player_list.get(i).get_name().equals("Devil")) { // 电脑回合
          System.out.println("\n当前为电脑 " + player_list.get(i).get_name() + " 的回合");
          keep_running = false;
          break;
        } else {
          System.out.println("\n当前是玩家 " + player_list.get(i).get_name() + " 的回合");
          System.out.println("\n1. 射爆对面!");
          System.out.println("2. 射自己...");
          System.out.print("\n请选择您的操作: ");
          String operation = user_input.nextLine();
          Pattern game_format = Pattern.compile("[1-2]{1}");
          boolean valid_input = game_format.matcher(operation).matches();
          while (!valid_input) {
            System.out.println("输入无效, 请重新选择 (1-2): ");
            operation = user_input.nextLine();
            valid_input = game_format.matcher(operation).matches();
          }
          if (operation.equals("1")) {
            System.out.println("玩家 " + player_list.get(i).get_name() + " 选择了射爆对面!!");
          } else {
            System.out.println("玩家 " + player_list.get(i).get_name() + " 选择了射自己...");
          }
        }
      }
    }
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
    while (!vaild_option) {
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