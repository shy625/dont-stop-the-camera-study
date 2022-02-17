import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2606_바이러스 {
	
	static int com;
	static int cnt;
	static boolean [][] coms; // 연결관계를 나타낼 2차원 배열, true : 연결, false : 비연결
	static boolean [] visit; // 이미 방문했는지 확인할 배열, true : 방문, false : 미방문

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		com = Integer.parseInt(br.readLine());
		coms = new boolean[com+1][com+1];
		visit = new boolean[com+1];
		
		int p = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= p; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			coms[v][w] = true;  // v와 w 컴퓨터가 연결됨
			coms[w][v] = true;  // w와 v 컴퓨터가 연결됨
		}
		
		dfs(com, 1);
		
		System.out.println(cnt);

	}

	private static void dfs(int com, int v) {
		visit[v] = true; // v번째 컴퓨터를 방문했음을 표시
		
		for (int w = 1; w <= com; w++) {
			if (coms[v][w] && !visit[w]) { // 연결되어있고, 방문하지 않았으면
				cnt++;
				dfs(com, w);
			}
		}
		
	}

}
