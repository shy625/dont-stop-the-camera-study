

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_25602_캔_주기 {
	static int N, K, max;
	static int[] A;
	static int[][] R;
	static int[][] M;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		A = new int[N];
		R = new int[K][N];
		M = new int[K][N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				R[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				M[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		max = -1; // 만족도 합의 최댓값
		perm(0, 0);
		System.out.println(max);
	}
	// 순열을 이용해 만족도 합의 최댓값을 구하는 함수
	// cnt번째 날 만족도 합이 k인 상태
	private static void perm(int cnt, int k) {
		// K번쨰 날이 되면 만족도 합의 최댓값을 갱신
		if (cnt == K) {
			max = Math.max(max, k);
			return;
		}
		// 량이와 메리에게 i번째 캔과 j번째 캔을 먹인다.
		for (int i = 0; i < N; i++) {
			if (A[i] == 0) continue;
			A[i]--;
			for (int j = 0; j < N; j++) {
				if (A[j] == 0) continue;
				A[j]--;
				perm(cnt+1, k+R[cnt][i]+M[cnt][j]);
				A[j]++;
			}
			A[i]++;
		}
	}

}
