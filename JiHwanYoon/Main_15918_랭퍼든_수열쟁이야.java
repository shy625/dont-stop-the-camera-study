

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15918_랭퍼든_수열쟁이야 {
	static int n, x, y, res;
	static int[] p;
	static boolean[] v;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		// 브루트포스 알고리즘을 이용해 모든 경우를 조사
		p = new int[2*n+1];
		v = new boolean[n+1];
		res = 0;
		perm(1, 0);
		System.out.println(res);
	}
	// 순열을 활용해 모든 경우를 조사하는 함수
	private static void perm(int cnt, int k) {
		// x번째 수와 y번째 수가 다른 경우
		if (p[x] != 0 && p[y] != 0 && p[x] != p[y]) return;
		// 수열을 완성시킨 경우
		if (cnt == 2*n+1) {
			// 단, 모든 숫자를 사용해야 한다.
			if (k == n) res++;
			return; 
		}
		// cnt번째 수에 이미 다른 수가 있는 경우
		if (p[cnt] != 0) {
			perm(cnt+1, k);
			return;
		}
		// 각 수에 대해 cnt번째 수와 (cnt+i+1)번째 수에 i를 집어넣는다.
		for (int i = 1; i <= n; i++) {
			if (v[i]) continue;
			if (cnt+i+1 > 2*n || p[cnt+i+1] != 0) continue;
			v[i] = true;
			p[cnt] = i;
			p[cnt+i+1] = i;
			perm(cnt+1, k+1);
			v[i] = false;
			p[cnt] = 0;
			p[cnt+i+1] = 0;
		}
	}

}
