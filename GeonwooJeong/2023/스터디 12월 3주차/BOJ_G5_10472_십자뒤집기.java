import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ_G5_10472_십자뒤집기 {
	static int target, ans;
	static int [] dr = {0, -1, 0, 1, 0};
	static int [] dc = {0, 0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int P = Integer.parseInt(br.readLine());
		
		for (int p = 1; p <= P; p++) {
			// 2진수를 저장하기 위한 변수
			int sum = 0;
			
			for (int i = 0; i < 3; i++) {
				String str = br.readLine();
				for (int j = 0; j < 3; j++) {
					char c = str.charAt(j);
					if(c == '*') {
						sum += 1 << (i*3 + j);
					}
				}
			}
			
			target = sum;
			
			bfs();
			
			sb.append(ans).append("\n");
			
		}
		
		if(sb.length() > 0) {
			sb.setLength(sb.length()-1);
		}
		
		System.out.println(sb.toString());
		
	}

	private static void bfs() {
		Queue<Integer> q = new ArrayDeque<>();
		// 비트마스킹을 사용, 3x3 = 9칸이므로 범위는 0 ~ 2^9-1이다.
		boolean [] v = new boolean[1 << 9];
		v[0] = true;
		q.add(0);
		int cnt = 0;
		
		while(!q.isEmpty()) {
			int size = q.size();
			for (int t = 0; t < size; t++) {
				int cur = q.poll();
				
				if(cur == target) {
					ans = cnt;
					return;
				}
				
				for (int r = 0; r < 3; r++) {
					for (int c = 0; c < 3; c++) {
						int next = reverse(cur, r, c);
						
						// 이미 방문한 적이 있다면 continue
						if(v[next]) continue;
						
						v[next] = true;
						q.add(next);
						
					}
				}
				
			}
			
			cnt++;
			
		}
		
	}

	private static int reverse(int now, int r, int c) {
		// next에 일단 현재 2진수 정보를 저장
		int next = now;
		
		// 자신, 상하좌우 5곳에 대해 reverse 실행
		for (int d = 0; d < 5; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			// 맵 밖을 벗어나면 continue
			if(!check(nr, nc)) continue;
			
			// next가 0 이었다면 1이 되고, 1이었다면 0이 된다.
			next ^= (1 << (nr*3 + nc));
		}
		
		return next;
	}

	private static boolean check(int r, int c) {
		return r>=0 && r<3 && c>=0 && c<3;
	}

}
