package tech.zlia.interest.lottery;

/**
 * 抽奖小项目
 * @version 1.0.0 2018-12-16
 * @author zlia
 */
public class LotteryArray {

    public static void main(String[] args) {
        final int NMAX =10;

        //初始化数据结构为二维数组
        int[][] odds = new int[NMAX + 1][];
        for(int n = 0 ; n <= NMAX ; n++){
            odds[n] = new int[n+1];
        }

        //为二维数组填充数据
        for(int n = 0 ; n < odds.length ; n ++){
            for(int k = 0 ; k < odds[n].length ; k++){
                //主要依赖算法是 n*(n-1)*(n-2)*...*(n-k+1)/1*2*3*...*k
                int lotteryOdds = 1;
                for(int i = 1 ; i <= k ; i ++){
                    lotteryOdds = lotteryOdds * (n - i +1)/i;
                }
                odds[n][k] = lotteryOdds;
            }
        }

        //打印结果
        for(int[] row : odds){
            for(int odd : row){
                System.out.printf("%4d",odd);
            }
            System.out.println();
        }
    }
}
/* 结果展示

1
1   1
1   2   1
1   3   3   1
1   4   6   4   1
1   5  10  10   5   1
1   6  15  20  15   6   1
1   7  21  35  35  21   7   1
1   8  28  56  70  56  28   8   1
1   9  36  84 126 126  84  36   9   1
1  10  45 120 210 252 210 120  45  10   1

*/
