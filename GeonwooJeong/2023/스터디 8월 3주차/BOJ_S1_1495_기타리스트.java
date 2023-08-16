import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S1_1495_기타리스트 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// 볼륨들을 저장할 배열
		int [] V = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			V[i] = Integer.parseInt(st.nextToken());
		}
		
		// N번째 곡을 볼륨 M으로 연주할 수 있는지 여부를 저장할 dp 배열
		boolean [][] dp = new boolean[M+1][N+1];
		// 맨 처음에는 볼륨 S로 시작한다.
		dp[S][0] = true;
		
		// 각 곡을 연주할 때 마다 dp배열을 갱신한다.
		for (int i = 1; i <= N; i++) {
			// 조절 가능한 볼륨의 범위는 0~M이므로, 모든 범위에 대해 계산을 해준다.
			for (int j = 0; j <= M; j++) {
				// i-1번째 곡에서 볼륨 j로 연주할 수 없으면 넘긴다.
				if(!dp[j][i-1]) continue;
				
				// 볼륨을 높이는 경우
				int p = j+V[i-1];
				// 볼륨을 낮추는 경우
				int m = j-V[i-1];
				
				// M보다 크면 안된다.
				if(p <= M) {
					dp[p][i] = true;
				}
				
				// 0보다 작으면 안된다.
				if(m >= 0) {
					dp[m][i] = true;
				}
			}
		}
		
		int ans = -1;
		
		// 마지막 곡의 볼륨 중 가장 큰 값을 찾아낸다.
		for (int i = M; i >= 0; i--) {
			if(dp[i][N]) {
				ans = i;
				break;
			}
		}
		
		System.out.println(ans);
		
	}

}
