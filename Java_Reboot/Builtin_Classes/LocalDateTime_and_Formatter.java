package Java_Reboot.Builtin_Classes;
import java.time.LocalDate; // 导入LocalDate 日期类
import java.time.LocalTime; // 导入LocalTime 时间类
import java.time.LocalDateTime; // 导入LocalDateTime 日期时间类
import java.time.format.DateTimeFormatter; // 导入DateTimeFormatter 时间日期格式化工具
public class LocalDateTime_and_Formatter {
  
  public static void main(String[] args) {
    /* LocalDate实验区 */
    LocalDate todays_date = LocalDate.now(); // 使用静态方法.now()取得今天的日期, 默认以'-'进行分隔
    System.out.println("今天的日期是: " + todays_date + ", todays_date的类型是: " + todays_date.getClass().getName());
    LocalDate trance_shock = LocalDate.of(2025, 7, 14); // 静态方法.of() 设置特定日期
    LocalDate next_shock = trance_shock.plusDays(1); // 7月15
    System.out.println("来势汹汹的Trance电音, " + trance_shock.getMonthValue() + "月" + trance_shock.getDayOfMonth() + "日" 
                      + ", " + next_shock.getMonthValue() + "月" + next_shock.getDayOfMonth() + "日, 天地人间完全放电");
                      // 注: getMonth()返回的是一个Month的枚举(如JULY), 要获取月份数值用getMonthValue()
    System.out.println("7月14日在7月15日前面吗? " + trance_shock.isBefore(next_shock));
    System.out.println(todays_date.getYear() + "是闰年吗? " + todays_date.isLeapYear());
    LocalDate baka_wing = LocalDate.parse("2024-12-28"); // 默认解析格式yyyy-MM-dd
    System.out.println("从String中.parse提取到baka_wing的信息: " + baka_wing);

    System.out.println();

    /* LocalTime实验区 */
    LocalTime time_now = LocalTime.now();
    System.out.println("现在的时间是: " + time_now);
    System.out.println("单独取出秒数: "+ time_now.getSecond()); // 单独取秒数时为整
    System.out.println("一小时后是: "+ time_now.plusHours(1));
    System.out.println("看我超时空TP到23点: " + time_now.withHour(23));
    
    System.out.println();

    /* LocalDateTime实验区 */
    LocalDateTime date_time = LocalDateTime.now();
    System.out.println("今天的时间+日期是: " + date_time);
    System.out.println("单独摘出日期: " + date_time.toLocalDate());
    System.out.println("单独摘出时间: " + date_time.toLocalTime());
    LocalDateTime portal_opening_days = LocalDateTime.of(2025, 6, 14, 16, 14, 0);
    System.out.println("Hey Vergil! Your " + portal_opening_days + " are over, give me the Yamato...");

    System.out.println();

    /* DateTimeFormatter实验区 */
    // 简单来说, 这个玩意就教上面那些类 如何从一个'有规律'的字符串中'摘出'时间信息
    // 注意点: 单写 和 双写字符的意义不一样! (如 20时05分 和 20时5分, 对应的解析格式分别是 HH是mm分 和 HH是m分)
    DateTimeFormatter chn_date_format = DateTimeFormatter.ofPattern("yyyy年MM月dd日"); // LocalDate的解析模版
    DateTimeFormatter chn_time_format = DateTimeFormatter.ofPattern("HH时m分s秒"); // LocalTime的解析模版
    DateTimeFormatter chn_datetime_format = DateTimeFormatter.ofPattern("yyyy年MM月dd号H点mm分ss秒"); // LocalDateTime的解析模版
    String chn_date = "2024年10月17日";
    System.out.println("给定一个日期字符串: " + chn_date);
    LocalDate parsed_chn_date = LocalDate.parse(chn_date, chn_date_format); // .parse(LocalDate对象, 要用的模版) 下面的同理
    System.out.println("从字符串chn_date用chn_date_format解析的LocalDate结果为: " + parsed_chn_date); // 2024-10-17
    
    String chn_time = "20时5分7秒"; // 一般时间都会'补0',这里的'怪异'的时间仅做测试
    System.out.println("给定一个时间字符串: " + chn_time);
    LocalTime parsed_chn_time = LocalTime.parse(chn_time, chn_time_format);
    System.out.println("从字符串chn_time用chn_time_format解析的LocalDate结果为: " + parsed_chn_time); // 20:05:07 (成功解析后自动补0了)

    String chn_datetime = "2017年11月31号9点45分17秒";
    System.out.println("给定一个日期+时间字符串: " + chn_date);
    LocalDateTime parsed_chn_datetime = LocalDateTime.parse(chn_datetime, chn_datetime_format);
    System.out.println("从字符串chn_datetime用chn_datetime_format解析的LocalDate结果为: " + parsed_chn_datetime); // 2017-11-30T09:45:17


    


  }

}
