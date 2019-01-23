package tech.zlia.interest.example;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * 大数值的使用
 * @version - 1.0.0 2018-12-16
 * @author zlia
 */
public class BigTntegerTest {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.println("How many numbers do you need to draw ?");
        int k = in.nextInt();
        System.out.println("What is the highest number you can draw ?");
        int n = in.nextInt();

        BigInteger lotteryOdds = BigInteger.valueOf(1);

        for (int i = 1 ; i <= k ; i++){
            lotteryOdds = lotteryOdds.multiply(BigInteger.valueOf(n - i + 1)).divide(BigInteger.valueOf(i));
        }
        System.out.println("You oadds are 1 in " + lotteryOdds + ". Good Luck !");
    }
}
