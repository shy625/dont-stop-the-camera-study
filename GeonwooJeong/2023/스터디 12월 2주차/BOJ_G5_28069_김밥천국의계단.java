import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G5_28069_김밥천국의계단 {
	
	// 1, 2, 3, 4, 5, 6,  7,  8
	// 1, 3, 4, 6, 7, 9, 10, 12
	// 2 5 8 11 지팡이 불가 -> N % 3 == 2 -> 지팡이 불가
	// N % 3 == 0 -> N / 3 * 2
	// N % 1 == 1 -> N - (N/3)
	// 42 -> 28 -> 19 -> 13 -> 9 -> 6 -> 4 -> 3 -> 2 -> 1 -> 0
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int cnt = bfs(N);
		
		if(cnt <= K) {
			System.out.println("minigimbob");
		} else {
			System.out.println("water");
		}
		
	}

	private static int bfs(int start) {
		Queue<int []> q = new ArrayDeque<>();
		boolean [] v = new boolean[start+1];
		v[start] = true;
		q.add(new int[] {start, 0});
		
		while(!q.isEmpty()) {
			int [] cur = q.poll();
			int n = cur[0];
			
			if(n == 1) {
				return cur[1]+1;
			}
			
			if(n % 3 == 1) {
				int next = n - (n/3);
				if(!v[next]) {
					v[next] = true;
					q.add(new int[] {next, cur[1]+1});
				}
			} else if(n % 3 == 0) {
				int next = n / 3 * 2;
				if(!v[next]) {
					v[next] = true;
					q.add(new int[] {next, cur[1]+1});
				}
			}
			
			int next = n-1;
			if(!v[next]) {
				v[next] = true;
				q.add(new int[] {next, cur[1]+1});
			}
			
		}
		
		return 1000001;
	}

}
