import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G3_17182_우주탐사선 {
	
	static int N, K;
	static int [][] dist;
	static boolean [] v;
	static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		dist = new int[N][N];
		v = new boolean[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				dist[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(i == j) continue;
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}
		
		v[K] = true;
		dfs(0, K, 0);
		
		System.out.println(ans);
		
	}

	private static void dfs(int cnt, int start, int sum) {
		if(cnt == N-1) {
			ans = Math.min(ans, sum);
			return ;
		}
		
		for (int i = 0; i < N; i++) {
			if(!v[i]) {
				v[i] = true;
				dfs(cnt+1, i, sum + dist[start][i]);
				v[i] = false;
			}
		}
		
	}

}
