import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_G5_24230_트리색칠하기 {
	static List<ArrayList<Integer>> list;
	static int [] colors;
	static boolean [] v;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		// 트리의 정보를 저장할 2차원 리스트
		list = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			list.add(new ArrayList<>());
		}
		
		// 각 정점의 색을 저장할 1차원 배열
		colors = new int[N+1];
		// 각 정점을 확인(방문)했는지 여부를 저장할 1차원 배열
		v = new boolean[N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			colors[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
		
			list.get(a).add(b);
			list.get(b).add(a);
		}
		
		// 루트는 항상 1번 정점이어서 1번부터 dfs를 시작하는데,
		// 1번 정점이 흰색이 아니라면 색을 칠해야 하므로 ans를 +1 해주고 시작한다.
		if(colors[1] != 0) ans++;
		dfs(1, colors[1]);
		
		System.out.println(ans);
		
	}

	private static void dfs(int idx, int color) {
		// 확인한 정점은 방문처리를 해준다.
		v[idx] = true;
		
		// 현재 정점의 자식 정점들을 모두 확인한다.
		for(int next : list.get(idx)) {
			// 이미 확인한 정점이라면 넘어간다.
			if(v[next]) continue;
			
			// 현재 정점과 자식 정점의 색이 다르면
			// 자식 정점부터 새로운 색을 칠하는 것이므로 ans를 +1 해준다.
			if(color != colors[next]) ans++;
			
			// 자식 정점부터 이어서 확인해본다 (dfs)
			dfs(next, colors[next]);
		}
		
	}

}
