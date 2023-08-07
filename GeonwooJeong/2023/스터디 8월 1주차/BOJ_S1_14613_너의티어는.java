import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S1_14613_너의티어는 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		double W = Double.parseDouble(st.nextToken());
		double L = Double.parseDouble(st.nextToken());
		double D = Double.parseDouble(st.nextToken());
		
		// 판수, 점수
		// 점수를 50으로 나누어 사용
		double [][] dp = new double[21][61];
		dp[0][40] = 1.0;
		
		for (int i = 1; i <= 20; i++) {
			for (int j = 20; j <= 60; j++) {
				if(dp[i-1][j] == 0.0) continue;
				
				// 이길 경우
				dp[i][j+1] += dp[i-1][j] * W;
				// 질 경우
				dp[i][j-1] += dp[i-1][j] * L;
				// 비길 경우
				dp[i][j] += dp[i-1][j] * D;
			}
		}
		
		double b, s, g, p, d;
		b = s = g = p = d = 0.0;
		
		for (int i = 20; i <= 60; i++) {
			if(i < 30) {
				b += dp[20][i];
			} else if(i >= 30 && i < 40) {
				s += dp[20][i];
			} else if(i >= 40 && i < 50) {
				g += dp[20][i];
			} else if(i >= 50 && i < 60) {
				p += dp[20][i];
			} else {
				d += dp[20][i];
			}
		}
		
		System.out.printf("%.8f\n", b);
		System.out.printf("%.8f\n", s);
		System.out.printf("%.8f\n", g);
		System.out.printf("%.8f\n", p);
		System.out.printf("%.8f\n", d);
		
	}

}
