import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_14550_마리오파티 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			
			if(N == 0) break;
			
			int S = Integer.parseInt(st.nextToken());
			int T = Integer.parseInt(st.nextToken());
			
			// 칸에 적힌 점수들을 저장할 배열
			int [] scores = new int[N];
			
			// 배열에 점수를 저장하는 로직
			int cnt = 0;
			while (cnt < N) {
				st = new StringTokenizer(br.readLine());
				while(st.hasMoreTokens()) {
					scores[cnt++] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 2차원 DP 배열 사용, 보드 칸수 / 턴 수
			int [][] dp = new int[N+1][T+1];
			// 1턴에서 얻을 수 있는 값들을 미리 초기화해준다.
			for (int i = 1; i <= S; i++) {
				dp[i][1] = scores[i-1];
			}
			
			// 2턴부터 마지막-1턴 까지 계산한다.
			for (int i = 2; i < T; i++) {
				// N번째 칸부터 1번 칸까지 내림차순으로 계산한다.
				for (int j = N; j > 0; j--) {
					// 해당 칸에 적힌 숫자를 확인한다.
					int now = scores[j-1];
					// 해당 dp 칸을 초기화해준다. (Integer.MIN_VALUE로 초기화 시 오버플로우 발생)
					dp[j][i] = Integer.MIN_VALUE/10;
					// S부터 1번까지 주사위 눈을 굴려 dp 값을 갱신한다.
					for (int k = S; k >= 1; k--) {
						// j-k 칸을 조사할건데, j-k가 0보다 작아지면 해당 주사위는 굴릴 수 없다.
						if(j < k) continue;
						// 아직 방문하지 못한 칸이면 넘어간다.
						if(dp[j-k][i-1] == 0) continue;
						
						// dp 배열에 있던 값과 이전 값 + 이번 칸에 적힌 값을 더한 값 중 더 큰 값을 dp에 저장한다.
						dp[j][i] = Math.max(dp[j][i], dp[j-k][i-1] + now);
						
					}
					
					// 해당 칸을 모든 주사위를 굴려봐도 도달할 수 없으면 0으로 초기화한다.
					if(dp[j][i] == Integer.MIN_VALUE) dp[j][i] = 0;
					
				}
			}
			
			int ans = Integer.MIN_VALUE;
			
			// 가장 마지막 턴에 주사위를 (1~S) 굴렸을 때 도달할 수 있는 칸들을 조사한다.
			for (int i = N-S+1; i <= N; i++) {
				ans = Math.max(dp[i][T-1], ans);
			}
			
			sb.append(ans+"\n");
			
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
		
		
	}

}
