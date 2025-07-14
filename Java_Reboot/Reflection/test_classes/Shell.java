package Java_Reboot.Reflection.test_classes;

import java.lang.Math;

public class Shell {

  private boolean is_real;

  public Shell(){
    int random_num = (int)Math.round(Math.random()); // 0 - 0.9 , 四舍五入, 0空弹, 1实弹
    if(random_num==0){
      this.is_real=false;
    }else{
      this.is_real=true;
    }
  }

  public Shell(boolean is_real){ // 传入自定义子弹, 补充用
    this.is_real=is_real;
  }

  public boolean get_shell(){
    return this.is_real;
  }


}
