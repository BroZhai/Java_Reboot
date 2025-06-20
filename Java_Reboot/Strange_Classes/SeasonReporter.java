package Java_Reboot.Strange_Classes; // 告诉文件对应的'本包'在哪

public class SeasonReporter{

  enum Season{ // 这里的枚举是一个 SeansonReporter的'内部类'
    // 必须先定义好'有哪些枚举'对象, 同时想这些枚举对象会有'哪些属性' (每一个枚举对象都是Season类型)
    SPRING("春天", 20),
    SUMMER("夏天", 30),
    AUTUMN("秋天", 24),
    WINTER("冬天", 8);
    
    // 然后定义好'枚举对象'所涉及的'属性'(成员变量)
    private final String s_name; //因为枚举的对象都是 私有定好的'常量', 所以需要final修饰
    private final int s_temp;

    // 随后依据上面定好的'成员变量'创建构造函数(enum的构造函数只能是private, 可以不写)
    private Season(String season_name, int season_temperature){
      this.s_name=season_name;
      this.s_temp=season_temperature;
    }
    public String report(){
      return "当前的季节是: " + s_name +", 温度是: " + s_temp + "°C";
    }

  }

  public static void main(String[] args) {
    Season currentSeason = Season.AUTUMN;
    System.out.println(currentSeason.report() + "\n"); // 简单输出Season枚举类里面的AUTUMN常量

    // 玩转一下枚举遍历
    System.out.println("接下来是枚举测试: ");
    Season[] seasons = Season.values(); // 枚举有个.values()函数, 用于提取枚举里面的所有值, 并返回一个数组[]
    for(Season s: seasons){
      System.out.println(s.report());
    }
  }
}