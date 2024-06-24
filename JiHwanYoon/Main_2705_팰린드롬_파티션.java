

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2705_팰린드롬_파티션 {
	static long[] cnt; 
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		// dynamic programming을 활용
		// 재귀적인 팰린드롬 파티션의 개수를 저장하는 배열
		cnt = new long[1001];
		// 초기화
		cnt[0] = 1;
		cnt[1] = 1;
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			sb.append(recur(Integer.parseInt(br.readLine()))).append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	// 재귀적으로, 재귀적인 팰린드롬 파티션의 개수를 구하는 함수
	private static long recur(int n) {
		// 이미 구한 경우
		if (cnt[n] > 0) return cnt[n];
		// n에 대한 개수를 구할 때, 0 + n + 0, 1 + (n-2) + 1, ... , (n/2) + (1 +) (n/2) 꼴이 가능하다.
		// 따라서, 0, ... , n/2에 대한 재귀적인 팰린드롬 파티션의 개수를 구하고, 이를 다 더한다.
		for (int i = 0; i <= n/2; i++) {
			recur(i);
			cnt[n] += cnt[i];
		}
		return cnt[n];
	}

}
