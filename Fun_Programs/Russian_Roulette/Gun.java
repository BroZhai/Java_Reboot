package Fun_Programs.Russian_Roulette;

import java.lang.Math;
import java.util.ArrayList;

import Fun_Programs.Russian_Roulette.Shell;

public class Gun {

  private int total_shells;
  private int blank_shells = 0;
  private int real_shells = 0;
  private ArrayList<Shell> chamber;

  public Gun(int total_shells){
    this.total_shells = total_shells;
    reload(total_shells);
  }

  public void reload(int total_shells){
    this.total_shells = total_shells;
    chamber = new ArrayList<Shell>(total_shells);
    for(int i=0; i<total_shells; i++){
      Shell current_shell = new Shell();
      if(current_shell.get_shell()){ // 是实弹
        this.real_shells++;
      }else{
        this.blank_shells++;
      }
      chamber.add(current_shell);
    }
  }
  
  public void shoot(Player target){
    boolean real_shell = chamber.get(0).get_shell(); // 读取第一个弹药
    if (real_shell) {
      target.deduce_life();
      System.out.println("BOOM!! " + target.get_name() + "生命值-1!!");
    }else{
      System.out.println("咔! 枪没响...");
    }
    chamber.remove(0); // 移除首发弹药
  }

  
  public void check_chamber(){
    System.out.println("当前Gun中共有 "+ this.total_shells +" 颗子弹");
    System.out.println("其中有 " + this.real_shells +" 发实弹");
    System.out.println(this.blank_shells + " 发空弹");

  }

}
