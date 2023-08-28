import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_20168_골목대장호석 {
	static int N, M, A, B, C;
	static int ans = -1;
	static int [][] arr;
	static boolean [] v;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new int[N+1][N+1];
		v = new boolean[N+1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			arr[a][b] = c;
			arr[b][a] = c;
		}
		
		dfs(A, C, 0);
		
		System.out.println(ans);
		
	}

	private static void dfs(int P, int money, int max) {
		// 도착지에 도달한 경우
		if(P == B) {
			// 한 번도 방문하지 않았거나, 이전의 최댓값이 현재 최댓값보다 클 경우 갱신해준다.
			if(ans == -1 || ans > max) {
				ans = max;
			}
			return;
		}
		
		for (int i = 1; i <= N; i++) {
			// 방문한 적이 없고, 골목길이 존재하고, 현재 가진 돈이 요금보다 많다면 다음 목적지로 이동한다.
			if(!v[i] && arr[P][i] > 0 && money >= arr[P][i]) {
				v[i] = true;
				dfs(i, money - arr[P][i], Math.max(max, arr[P][i]));
				v[i] = false;
			}
			
		}
		
	}

}
