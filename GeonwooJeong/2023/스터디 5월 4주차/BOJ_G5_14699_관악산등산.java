import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_G5_14699_관악산등산 {
	static int N, M;
	static ArrayList<ArrayList<Integer>> map;
	static int [] shelter;
	static int [] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		shelter = new int[N+1];
		dp = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			shelter[i] = Integer.parseInt(st.nextToken());
		}
		
		map = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			map.add(new ArrayList<>());
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map.get(a).add(b);
			map.get(b).add(a);
		}
		
		for (int i = 1; i <= N; i++) {
			int n = dfs(i);
			sb.append(n+"\n");
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

	private static int dfs(int n) {
		if(dp[n] != 0) {
			return dp[n];
		}
		
		dp[n] = 1;
		int cnt = 0;
		
		for(int a : map.get(n)) {
			if(shelter[a] < shelter[n]) continue;
			cnt = Math.max(cnt, dfs(a));
		}
		
		dp[n] += cnt;
		return dp[n];
	}

}
