import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_G5_22968_균형 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		// 높이를 만족하는 최소 정점의 개수를 저장할 배열
		int [] dp = new int[44];
		
		// 초기값 설정
		dp[1] = 1;
		dp[2] = 2;
		
		// 3~42층까지 점화식을 통해 각 높이의 최소 정점 개수를 구한다.
		for (int i = 3; i <= 42; i++) {
			dp[i] = dp[i-2] + dp[i-1] + 1;
		}
		
		// 입력의 마지막 숫자인 1,000,000,000가 42층이므로, 43층은 임의로 +1해서 설정해준다.
		dp[43] = 1000000001;
		
		for (int t = 0; t < T; t++) {
			int V = Integer.parseInt(br.readLine());
			
			for (int i = 2; i <= 43; i++) {
				// 정점 V개가 i층의 최소 정점 개수보다 작으면 i-1층까지만 만들 수 있다.
				if(dp[i] > V) {
					sb.append((i-1)+"\n");
					break;
				}
			}
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
		
	}

}
