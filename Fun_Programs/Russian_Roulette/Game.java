package Fun_Programs.Russian_Roulette;

import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher; // 导了但是没用 XD
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.Math;

// import Fun_Programs.Self_Tools.Confirmer; // 自制'确认用户输入'工具类

public class Game {
  // 本程序打算实现一个简单的'俄罗斯轮盘赌', 仿制Russian_Roulette

  // 封装好的'开火视觉效果'
  public static void show_boom(File boom_effect){
    try {
      Scanner file_reader = new Scanner(boom_effect);
      while(file_reader.hasNextLine()){
        System.out.println(file_reader.nextLine());
      }
    } catch (Exception e) {
      System.out.println("检测到boom_effect文件不存在!");
    }
    
  }

  public static void start_Round() throws InterruptedException, FileNotFoundException{
    
    String current_directory = "Fun_Programs/Russian_Roulette";
    // System.out.println("当前工作路径位于: " + System.getProperty("user.dir"));
    File boom_effect = new File(current_directory,"Boom.txt");
    if(!boom_effect.exists()){
      System.out.println("检测到文件缺失了必要视觉文件Boom.txt! 程序已自行退出...");
      return;
    }
    
    // show_boom(boom_effect);

    System.out.print("\n请为蘸豆的玩家命名: ");
    Scanner user_input = new Scanner(System.in);
    String player_name = user_input.nextLine();

    int randon_life = (int) (Math.random() * 4) + 1; // 玩家生命值 2-4 范围随机

    ArrayList<Player> player_list = new ArrayList<>(2); // 玩家队列
    Player user = new Player(player_name, randon_life);
    System.out.println("\n成功创建玩家: " + user.get_name());
    player_list.add(user);

    Player comp = new Player("Devil", randon_life);
    System.out.println("成功创建敌对玩家: " + comp.get_name());
    player_list.add(1, comp); // 加在'第一个玩家'后面

    int init_shells = (int) (Math.random() * 7) + 2; // 生成 2-8 颗首轮子弹数
    Gun gun = new Gun(init_shells);
    int i = 0; // 玩家序数, 用于下方'特殊情况'发生时用, 玩家朝自己开枪, 但是是空弹, 下一回合仍是该玩家

    while (player_list.size() != 1) { // 双方玩家都存活
      System.out.println("\n当前玩家血量: ");
      for (Player p : player_list) {
        System.out.println(p.get_name() + ": " + p.get_life() + " 点生命");
        if (p.get_life() <= 0) { // 有人生命为0
          player_list.remove(p); // 移除队列生命为0的玩家
          break;
        }
      }
      if (player_list.size() == 1) { // 及时判断上面'有没有玩家没移除'
        break;
      }
      // Thread.sleep(1500);

      if (gun.isChamberEmpty()) { // 弹药清空, 自动重装
        int reload_shells = (int) (Math.random() * 7) + 2;
        System.out.println("\n检测到弹夹清空, 正在自动换弹...");
        gun.reload(reload_shells);
        Thread.sleep(1500);
      }

      gun.check_chamber();
      Thread.sleep(1500);
      
      if (player_list.get(i % 2).get_name().equals("Devil")) { // 电脑回合
        System.out.println("\n当前为电脑 " + comp.get_name() + " 的回合");
        Thread.sleep(1500);
        // 50%随机选择 (一会儿再做复杂的)
        int action = (int) (Math.random() * 2) + 1; // 取值范围[1-2] 1射玩家, 2射自己
        if (action == 1) { // AI选择干玩家
          System.out.println("\n" + comp.get_name() + " 将枪口对准了 " + user.get_name() + "...");
          Thread.sleep(1500);
          if (gun.shoot(player_list.get(0))) {
            show_boom(boom_effect); // 炸裂的视觉效果
            Thread.sleep(1200);
            System.out.println(user.get_name() + "生命值-1!!");
            Thread.sleep(1500);
          } else {
            System.out.println("\n咔! 哦呀?! 枪没响...");
            Thread.sleep(1500);
          }

        } else { // AI选择干自己
          System.out.println("\n" + comp.get_name() + "将枪口对准了自己...");
          Thread.sleep(1500);
          if (!gun.shoot(player_list.get(i % 2))) { // 枪没响
            System.out.println("\n咔! 哦豁?! 枪没响!");
            Thread.sleep(1500);
            System.out.println("\n现在仍是" + comp.get_name() + "的回合...");
            Thread.sleep(1500);
            i++; // 提前 + 1, 循环结束时还会来个+1, 通过取余刚好让'下个回合' 仍是自己
          } else {
            show_boom(boom_effect);
            Thread.sleep(1200);
            System.out.println(comp.get_name() + " 生命值-1!!");
            Thread.sleep(1500);
          }

        }

      } else { // 玩家回合
        System.out.println("\n当前是玩家 " + user.get_name() + " 的回合");
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
          System.out.println("玩家 " + user.get_name() + " 将枪口对准了 " + comp.get_name() +"...");
          Thread.sleep(1500);
          if (gun.shoot(player_list.get((i % 2) + 1))) { // 玩家只有可能是0, + 1 爆对面
            show_boom(boom_effect);
            Thread.sleep(1200);
            System.out.println(comp.get_name() + " 生命值-1!!");
            Thread.sleep(1500);
          } else {
            System.out.println("\n咔! 噔噔咚, 枪没响...");
            Thread.sleep(1500);
          }
        } else {
          System.out.println("玩家 " + user.get_name() + " 将枪口对准了自己...");
          Thread.sleep(1500);
          
          if (!gun.shoot(player_list.get(i % 2))) { // 打的是空弹
            System.out.println("\n咔! 哦豁?! 枪没响!");
            Thread.sleep(1500);
            System.out.println("\n现在仍是" + user.get_name() + "的回合...");
            Thread.sleep(1000);
            i++; // 下一个回合仍是'玩家''
          } else { // 玩家朝自己开了实弹
            show_boom(boom_effect);
            Thread.sleep(1200);
            System.out.println("噔噔咚, " + player_list.get(i % 2).get_name() + "生命值-1...");
            Thread.sleep(1500);
          }
        }

      }
      i++; // 交个'下个玩家'
    } // while循环结束

    System.out.println("\n游戏结束, 赢家是: " + player_list.get(0).get_name() + "\n");
  }

  // 主函数运行区
  public static void main(String[] args) throws InterruptedException, FileNotFoundException{
    System.out.println("您好, 欢迎来到山寨Russian_Roulette");
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
        start_Round(); // 开始游戏
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