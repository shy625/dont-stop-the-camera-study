import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_G5_17953_디저트 {
	static class Dessert implements Comparable<Dessert> {
		int idx;
		int value;
		
		public Dessert(int idx, int value) {
			this.idx = idx;
			this.value = value;
		}

		@Override
		public int compareTo(Dessert o) {
			return Integer.compare(o.value, this.value);
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int [][] taste = new int[N+1][M+1];
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				taste[j][i] = Integer.parseInt(st.nextToken());
			}
		}
		
		int [][] dp = new int[N+1][M+1];
		for (int i = 1; i <= M; i++) {
			dp[1][i] = taste[1][i];
		}
		
		for (int i = 2; i <= N; i++) {
			List<Dessert> list = new ArrayList<Dessert>();
			for (int j = 1; j <= M; j++) {
				list.add(new Dessert(j, dp[i-1][j]));
			}
			
			Collections.sort(list);
			Dessert best = list.get(0);
			
			for (int j = 1; j <= M; j++) {
				int now = taste[i][j];
				if(best.idx == j && M != 1) {
					dp[i][j] = Math.max(best.value + now/2, list.get(1).value + now);
				} else if(best.idx != j){
					dp[i][j] = best.value + now;
				} else {
					dp[i][j] = best.value + now/2;
				}
			}
		}
		
		int max = -1;
		for (int i = 1; i <= M; i++) {
			max = Math.max(max, dp[N][i]);
		}
		
		System.out.println(max);
		
	}

}
