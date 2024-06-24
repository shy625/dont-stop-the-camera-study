

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_3980_선발_명단 {
	static int N = 11;
	static int[][] abilities;
	static boolean[] v;
	static int max;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int C = Integer.parseInt(br.readLine());
		abilities = new int[N][N];
		v = new boolean[N];
		StringBuilder sb = new StringBuilder();
		for (int c = 0; c < C; c++) {
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					abilities[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// 브루트포스 알고리즘 활용
			// 가능한 모든 경우를 고려해, 그 중 능력치의 합이 최대인 경우를 선택
			max = 0;
			Arrays.fill(v, false);
			perm(0, 0);
			sb.append(max).append("\n");
		}
		// 출력
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	// 브루트포스 알고리즘을 활용해 능력치의 합의 최댓값을 구하는 재귀 함수
	private static void perm(int cnt, int sum) {
		// 모든 선수의 포지션을 정하는데 성공한 경우
		if (cnt == N) {
			max = Math.max(max, sum);
			return;
		}
		// cnt번째 선수의 포지션 결정
		for (int i = 0; i < N; i++) {
			// 이미 정한 포지션이거나, 해당 선수를 i번째 포지션에 배치할 수 없는 경우
			if (v[i] || abilities[cnt][i] == 0) continue;
			v[i] = true;
			perm(cnt+1, sum+abilities[cnt][i]);
			// 백트래킹
			v[i] = false;
		}
	}
}
