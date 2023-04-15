import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_G4_2617_구슬찾기 {
	
	static int [][] dp;
	static boolean [] v;
	static List<Integer>[] list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int half = (N+1)/2;
		int ans = 0;
		
		// [x][0]은 x보다 가벼운 구슬의 개수, [x][1]은 x보다 무거운 구슬의 개수
		dp = new int[N+1][2];
		list = new ArrayList[N+1];
		
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			// a의 무게가 b보다 크다.
			list[a].add(b);
		}
		
		for (int i = 1; i <= N; i++) {
			v = new boolean[N+1];
			dfs(i, i);
		}
		
		// i번째 구슬보다 무거운 개수나 가벼운 개수가 (N+1)/2개 이상이면 i번째 구슬은 가운데 구슬이 될 수 없다.
		for (int i = 1; i <= N; i++) {
			if(dp[i][0] >= half || dp[i][1] >= half) ans++;
		}
		
		System.out.println(ans);
		
	}

	private static void dfs(int idx, int start) {
		v[idx] = true;
		
		for(int n : list[idx]) {
			if(!v[n]) {
				dp[start][0]++;
				dp[n][1]++;
				dfs(n, start);
			}
		}
	}

}
