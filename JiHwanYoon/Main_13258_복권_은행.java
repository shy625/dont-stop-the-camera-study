

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_13258_복권_은행 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken()); // 강호의 잔고
		int sum = A; // 모든 사람의 잔고의 합
		for(int i = 1; i < N; i++) {
			sum += Integer.parseInt(st.nextToken());
		}
		double J = Integer.parseInt(br.readLine());
		double C = Integer.parseInt(br.readLine());
		// 강호의 잔고의 기댓값은 강호의 잔고 + (강호의 잔고 * 당첨될 때마다 받는 돈 * 기간) / 모든 사람의 잔고의 합 이다.
		double ans = A + (A * J * C) / sum; 
		System.out.print(ans);
	}
}
