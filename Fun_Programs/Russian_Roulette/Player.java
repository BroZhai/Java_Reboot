package Fun_Programs.Russian_Roulette;

import java.lang.Math;

public class Player { // 玩家类
  private String player_name;
  private int life;

  public Player(String player_name, int life){
    this.player_name = player_name;
    this.life = life;
  }
  
  public String get_name(){
    return this.player_name;
  }

  public int get_life(){
    return this.life;
  }

  public void deduce_life(){
    this.life = this.life-1;
  }


}
