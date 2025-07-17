package Java_Reboot.Serializable_Experiment;

import java.io.Serializable;

// Gun类
public class Gun implements Serializable{
    private String gun_name;
    private String ammo_type;
    private Boolean handGun;
    private transient Integer gun_id; // 这个transient关键字表示'该属性'将不会被序列化(出于保护目的)

    public Gun(String gun_name, String ammo_type, Boolean is_handGun, int gun_id){
      this.gun_name = gun_name;
      this.ammo_type = ammo_type;
      this.handGun = is_handGun; 
      this.gun_id = gun_id;
    }
    
    public String get_name(){
      return this.gun_name;
    }

    public String get_ammo_type(){
      return this.ammo_type;
    }

    public boolean is_handGun(){
      return this.handGun;
    }

    public int get_gun_id(){
      return this.gun_id;
    }

}
