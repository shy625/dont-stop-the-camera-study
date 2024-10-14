import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_G5_2565_전깃줄 {

   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      int N = Integer.parseInt(br.readLine());
      // 서로 연결된 전봇대의 번호를 저장할 2차원 배열
      int [][] line = new int[N][2];
      
      for (int i = 0; i < N; i++) {
         StringTokenizer st = new StringTokenizer(br.readLine());
         
         line[i][0] = Integer.parseInt(st.nextToken());
         line[i][1] = Integer.parseInt(st.nextToken());
      }
      
      // A 전봇대를 기준으로 정렬한다.
      Arrays.sort(line, (o1, o2) -> {
         return Integer.compare(o1[0], o2[0]);
      });
      
      // dp[i] : i번째 전깃줄을 연결했을 때, 교차하지 않는 전깃줄의 최대수
      int [] dp = new int[N];
      
      for (int i = 0; i < N; i++) {
         // 최소 1개를 연결할 수 있다.
         dp[i] = 1;
         
         for (int j = 0; j < i; j++) {
            // i번째 이전의 전깃줄에 대해서, 교차하는지 여부를 확인 후 dp 배열을 갱신한다.
            if(line[i][1] > line[j][1]) {
               dp[i] = Math.max(dp[i], dp[j]+1);
            }
         }
      }
      
      int max = 1;
      // 0~N-1번 째 dp 배열을 확인하며 최대 연결할 수 있는 전깃줄의 수를 갱신한다.
      for (int i = 0; i < N; i++) {
         max = Math.max(max, dp[i]);
      }
      
      // 없애야 하는 전깃줄의 수 = 총 전깃줄 수 - 최대 연결할 수 있는 전깃줄 수
      System.out.println(N-max);
      
   }

}
