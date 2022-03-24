

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14889_스타트와_링크 {
	static int N;
	static int[][] abilities;
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		abilities = new int[N][N];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				abilities[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		combi(0, 0, 0);
		System.out.println(min);
	}
	// 조합 + 비트를 이용해 두 팀으로 나눔
	private static void combi(int cnt, int start, int team) {
		// 두 팀으로 나눴을 때
		if (cnt == N/2) {
			// 각 팀에 대해 능력치 합을 구한다.
			int sum1 = 0;
			int sum2 = 0;
			for (int i = 0; i < N; i++) {
				if ((team & (1 << i)) != 0) {
					for (int j = i+1; j < N; j++) {
						if ((team & (1 << j)) != 0) sum1 += abilities[i][j] + abilities[j][i];
					}
				} else {
					for (int j = i+1; j < N; j++) {
						if ((team & (1 << j)) == 0) sum2 += abilities[i][j] + abilities[j][i];
					}
				}
			}
			min = Math.min(min, Math.abs(sum1 - sum2));
			return;
		}
		for (int i = start; i < N; i++) {
			combi(cnt+1, i+1, team | (1 << i));
		}
	}

}
