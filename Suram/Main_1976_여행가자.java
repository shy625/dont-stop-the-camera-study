import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1976_여행가자 {
	static int N;
	static int M;
	static int[] parent;

	public static int find(int x) {
		if (parent[x] == x)
			return parent[x];
		return parent[x] = find(parent[x]);
	}

	public static void union(int x, int y) {
		int rootx = find(x);
		int rooty = find(y);
		if(rootx > rooty) {
			parent[rootx] = rooty;
		}
		else {
			parent[rooty] = rootx;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		StringTokenizer st = null;

		parent = new int[N+1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				int input = Integer.parseInt(st.nextToken());
				if (input == 1) {
					union(i,j);
				}
			}
		}

		st = new StringTokenizer(br.readLine());
		boolean possible = true;
		int start = find(Integer.parseInt(st.nextToken()));
		for (int i = 1; i < M; i++) {
			int now = Integer.parseInt(st.nextToken());

			if (start != find(now)) {
				possible = false;
				break;
			}
		}

		System.out.println(possible ? "YES" : "NO");
	}
}
