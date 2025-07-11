package Fun_Programs.Russian_Roulette;

import java.lang.Math;
import java.util.ArrayList;

import Fun_Programs.Russian_Roulette.Shell;

public class Gun {

  private int total_shells;
  private int blank_shells = 0;
  private int real_shells = 0;
  private static ArrayList<Shell> chamber;

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
    if(this.real_shells == 0){ // 捏毛毛一颗实弹都没有, 手动在弹夹'随机位置'加两颗
      // System.out.println("本轮未能生成实弹!");
      Shell real_shell = new Shell(true);
      // 假设长度为4(个空的)
      // int random_index = (int)Math.random()*this.total_shells;
      chamber.add((int)Math.random()*this.total_shells, real_shell);
      chamber.add((int)Math.random()*this.total_shells, real_shell);
      this.total_shells+=2;
      this.real_shells+=2;
    }
  }
  
  public boolean shoot(Player target){
    boolean real_shell = chamber.get(0).get_shell(); // 读取第一个弹药
    if (real_shell) {
      target.deduce_life();
      this.total_shells = this.total_shells-1;
      this.real_shells = this.real_shells-1;
      chamber.remove(0); // 移除枪膛中的弹药对象
      return true; // 实弹
    }else{
      this.total_shells = this.total_shells-1;
      this.blank_shells = this.blank_shells-1;
      chamber.remove(0); 
      return false; // 空弹
    }
  }

  public boolean isChamberEmpty(){
    if(this.total_shells==0){
      return true;
    }
    return false;
  }
  
  public void check_chamber(){
    System.out.println("\n当前Gun中共有 "+ this.total_shells +" 颗子弹");
    System.out.println("实弹: " + this.real_shells +" 发");
    System.out.println("空弹: " + this.blank_shells + " 发");
  }

}
