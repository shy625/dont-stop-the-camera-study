import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S1_11660_구간합구하기5 {
	
	static int [][] dp;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		dp = new int[N+1][N+1];
		
		// 가로 배열 1줄씩 누적합을 계산해서 저장(dp??)
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				int n = Integer.parseInt(st.nextToken());
				dp[i][j] = dp[i][j-1] + n;
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			cal(x1, y1, x2, y2);
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
		
	}

	private static void cal(int x1, int y1, int x2, int y2) {
		int sum = 0;
		
		// 이전에 누적합을 계산한 것 같이 y2 - (y1-1)을 해서 가로 1줄의 값을 구하고, x1부터 x2까지 계산한다.
		for (int i = x1; i <= x2; i++) {
			int start = dp[i][y1-1];
			int end = dp[i][y2];
			sum += (end - start);
		}
		
		sb.append(sum+"\n");
		
	}

}
