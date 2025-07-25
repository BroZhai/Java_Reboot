package Java_Reboot.Lambda_Experiement.BuiltIn_Functional_Interfaces;

public class Person {
    // public static name; // static关键字一定要慎用, 不然就是'全局通用'变量了 XD (所有人'共享一个名字')
    public String name; 
    private int age;

    public Person(String name, int age){
      this.name = name;
      this.age = age;
    }

    public void intro(){
      System.out.println("你好, 我是" + this.name);
    }

    public String get_info(){
      return this.name + "的年龄为: " + this.age;
    }

    public boolean reset_name(String rename){ // 一会儿用 实例对象::方法 进行引用
      this.name = rename;
      System.out.println("已成功重命名为: " + this.name);
      intro();
      return true;
    }
}
