import java.util.Scanner;

public class BOJ_G4_1563_개근상 {

   public static void main(String[] args) {
      Scanner scann = new Scanner(System.in);
      
      int N = scann.nextInt();
      int [][][] dp = new int[N+1][2][3]; // 날짜, 지각, 결석
      dp[1][0][0] = 1;
      dp[1][1][0] = 1;
      dp[1][0][1] = 1;
      
      int div = 1000000;
      
      for (int i = 2; i <= N; i++) {
    	 // 지각횟수 0회일 때
    	 // 오늘이 출석인 경우는 전날이 출석이든, 1일 결석이든, 2일 연속 결석이든지 결석 횟수가 0회로 초기화된다.
         dp[i][0][0] = (dp[i-1][0][0] + dp[i-1][0][1] + dp[i-1][0][2]) % div;
         // 오늘이 연속 결석 1번인 경우는 전날에는 출석을 했어야 한다.
         dp[i][0][1] = dp[i-1][0][0] % div;
         // 오늘이 연속 결석 2번인 경우는 전날에는 결석을 1번 했어야 한다.
         dp[i][0][2] = dp[i-1][0][1] % div;
         
         // 지각횟수 1회일 때
         // 오늘 지각을 새로 쌓는거라면 이전까지는 지각이 1번도 없어야 한다.
         dp[i][1][0] = (dp[i-1][0][0] + dp[i-1][0][1] + dp[i-1][0][2] +
         // 오늘 출석을 했다 -> 이전에 지각을 1회 했으므로 지각을 1번 했었던 dp 배열을 더해준다.
        		 dp[i-1][1][0] + dp[i-1][1][1] + dp[i-1][1][2]) % div;
         // 오늘이 연속 결석 1번인 경우는 전날에는 출석을 했어야 한다.
         dp[i][1][1] = dp[i-1][1][0] % div;
         // 오늘이 연속 결석 2번인 경우는 전날에는 결석을 1번 했어야 한다.
         dp[i][1][2] = dp[i-1][1][1] % div;
      }
      
      System.out.println((dp[N][0][0] + dp[N][0][1] + dp[N][0][2] + dp[N][1][0] + dp[N][1][1] + dp[N][1][2]) % div);

   }

}
