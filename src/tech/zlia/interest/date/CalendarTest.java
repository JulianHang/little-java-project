package tech.zlia.interest.date;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * 打印当月的日历并标记当天
 * @version 1.0.0 2018-12-17
 * @author  zlia
 */
public class CalendarTest {

    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        int today = date.getDayOfMonth();
        int month = date.getMonthValue();
        date = date.minusDays(today - 1); //设置到每个月的1号
        DayOfWeek weekday = date.getDayOfWeek();
        int value = weekday.getValue(); // 1 = Monday ...7 = Sunday

        System.out.println("Mon  Tue  Wed  Thu  Fri  Sat  Sun");
        for(int i = 1 ; i < value ; i ++){
            System.out.print("     ");
        }

        while(date.getMonthValue() == month){
            System.out.printf("%3d",date.getDayOfMonth());
            if(date.getDayOfMonth() == today){
                System.out.print("* ");
            }else{
                System.out.print("  ");
            }
            date = date.plusDays(1);
            if(date.getDayOfWeek().getValue() == 1){
                System.out.println();
            }
        }
    }
}
/*结果展示

Mon  Tue  Wed  Thu  Fri  Sat  Sun
                           1    2
  3    4    5    6    7    8    9
 10   11   12   13   14   15   16
 17*  18   19   20   21   22   23
 24   25   26   27   28   29   30
 31

*/
