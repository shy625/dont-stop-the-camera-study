
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1535_안녕 {

	public static void main(String[] args) throws IOException{
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] health = new int[N+1];
		int[] happy = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			health[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			happy[i] = Integer.parseInt(st.nextToken());
		}
		// 베낭 문제와 유사하게 각 사람을 만날 때마다, 사용할 수 있는 체력의 한계마다 얻을 수 있는 행복의 최대치를 기록한다.
		int[][] dp = new int[N+1][101];
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j <= 100; j++) {
				if (j < health[i]) dp[i][j] = dp[i-1][j];
				else dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-health[i]] + happy[i]);
			}
		}
		// 체력 100을 모두 쓰면 죽으므로 99까지를 한계치로 봤을 때 얻을 수 있는 행복의 최대치를 출력한다.
		System.out.println(dp[N][99]);
	}

}
