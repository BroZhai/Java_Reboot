package Java_Reboot.Reflection.test_classes;

public class Advance_Player extends Player{
  private boolean isVip; // 子类特有属性
  
  public Advance_Player(String player_name, int life, boolean isVip){
    super(player_name,life); // 继承Player父类的构造方法
    this.isVip = isVip;
  }

}
