import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_S1_27971_강아지는많을수록좋다 {
	static int N;
	static boolean [] v;
	static int [] dc;
	static int ans = -1;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		v = new boolean[N+1];
		// A마법, B마법을 저장할 배열
		dc = new int[2];
		dc[0] = Integer.parseInt(st.nextToken());
		dc[1] = Integer.parseInt(st.nextToken());

		// 닫힌 구간에 대해 방문 처리를 미리 해준다.
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int L = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			
			for (int j = L; j <= R; j++) {
				v[j] = true;
			}
		}
		
		bfs();
		
		System.out.println(ans);
		
	}

	private static void bfs() {
		Queue<Integer> q = new ArrayDeque<>();
		q.add(0);
		int cnt = 0;
		
		while(!q.isEmpty()) {
			int size = q.size();

			for (int i = 0; i < size; i++) {
				int cur = q.poll();
				
				for (int d = 0; d < 2; d++) {
					int next = cur+dc[d];
					
					// N마리에 도달하면 종료한다.
					if(next == N) {
						ans = cnt+1;
						return;
					}
					
					// N마리를 넘어가거나 이미 방문한 마릿수면 넘어간다.
					if(next > N || v[next]) continue;
					
					v[next] = true;
					q.add(next);
				}
			}
			
			cnt++;
			
		}
		
	}

}
