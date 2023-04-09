import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S1_14712_넴모넴모Easy {
	
	static int N, M, ans;
	static int [][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N+1][M+1];
		
		dfs(0);
		
		System.out.println(ans);

	}

	private static void dfs(int idx) {
		if(idx == N*M) {
			ans++;
			return;
		}
		
		int r = idx / M + 1;
		int c = idx % M + 1;
		
		// 현재 칸에 놓으면 네모가 완성되는 경우
		if(map[r-1][c-1] == 1 && map[r-1][c] == 1 && map[r][c-1] == 1) {
			// 현재 칸에 놓지 않고 다음 인덱스로 넘어간다.
			dfs(idx+1);
		// 현재 칸에 놓아도 되고 안놓아도 되는 경우엔 두 가지 경우 모두 가지를 뻗는다.
		} else {
			map[r][c] = 1;
			dfs(idx+1);
			map[r][c] = 0;
			dfs(idx+1);
			
		}
		
	}

}
