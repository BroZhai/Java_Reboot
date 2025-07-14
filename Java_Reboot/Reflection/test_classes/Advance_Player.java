package Java_Reboot.Reflection.test_classes;

// 测试getSuperClass()用
public class Advance_Player extends Player{
  private boolean isVip; // 子类特有属性
  
  public Advance_Player(String player_name, int life, boolean isVip){
    super(player_name,life); // 继承Player父类的构造方法
    this.isVip = isVip;
  }

  public void verify_vip(){
    if (this.isVip) {
      System.out.println("我是Vip玩家: " + this.get_name());
    }
    else{
      System.out.println("玩家 " + this.get_name() + " 不是Vip");
    }
  }

}
