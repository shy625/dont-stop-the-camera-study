

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_16500_문자열_판별 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		int N = Integer.parseInt(br.readLine());
		String[] arr = new String[N];
		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine();
		}
		int L = s.length();
		// dynamic programming을 활용
		// dp[i]는 i번째 문자까지의 부분문자열에 대해 동일한 문제에 대한 답을 나타낸다.
		int[] dp = new int[L+1];
		dp[0] = 1; // 초기화
		for (int i = 0; i < L; i++) {
			// 주어진 문자열을 활용해서 i번째 문자까지의 부분문자열을 만들 수 없는 경우 
			if (i > 0 && dp[i] == 0) continue;
			// 주어진 문자열을 활용해서 i번째 문자까지의 부분문자열을 만들 수 있다면,
			// 주어진 문자열을 더 붙여서 다음 부분문자열을 만들 수 있는지를 확인
			outer: for (int j = 0; j < N; j++) {
				String cand = arr[j]; // 후보 문자열
				int k = cand.length();
				if (L - i < k) continue; // 후보 문자열의 길이가 너무 긴 경우 
				for (int l = 0; l < k; l++) {
					if (s.charAt(i+l) != cand.charAt(l)) continue outer;
				}
				// 후보 문자열을 붙일 수 있는 경우
				dp[i+k] = dp[i];
			}
		}
		System.out.println(dp[L]);
	}

}
