import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_G5_20208_진우와민트초코우유 {
	static int [][] map;
	static boolean [] v;
	static int H, max, sr, sc, S;
	static List<int []> list;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		// 진우의 집 좌표(시작점)를 저장할 sr, sc
		sr = -1;
		sc = -1;
		// 민트초코우유의 좌표를 저장할 리스트
		list = new ArrayList<int []>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int n = Integer.parseInt(st.nextToken());
				if(n == 1) {
					sr = i;
					sc = j;
				} else if(n == 2) {
					list.add(new int[] {i, j});
				}
			}
		}
		
		// 민트초코우유의 개수만큼 S를 설정하고, 방문배열 v를 초기화해준다.
		S = list.size();
		v = new boolean[S];
		
		// sr, sc에서 출발한다.
		move(sr, sc, M, 0);
		
		System.out.println(max);
		
	}

	private static void move(int r, int c, int HP, int cnt) {
		// 현재 위치에서 집까지 갈 수 있을 경우에만 max를 갱신한다.
		int dist = Math.abs(r - sr) + Math.abs(c - sc);
		if(HP >= dist) {
			max = Math.max(max, cnt);
		}
		
		// 모든 민트초코우유에 대해 먹으러 갈지 판별한다.
		for (int i = 0; i < S; i++) {
			// i번째 민트초코우유를 아직 방문하지 않은 경우
			if(!v[i]) {
				
				// i번째 민트초코우유의 좌표를 가져온다.
				int [] tmp = list.get(i);
				int nr = tmp[0];
				int nc = tmp[1];
				// 현재 위치에서 i번째 민트초코우유 위치까지의 거리를 계산해본다.
				int tmpdist = (Math.abs(r - nr)+Math.abs(c - nc));
				// 현재의 체력으로 갈 수 있을 경우에만 민트초코우유를 먹으러 간다. (백트래킹 사용)
				if(HP < tmpdist) continue;
				
				v[i] = true;
					
				HP = HP - tmpdist + H;
				cnt++;
				move(nr, nc, HP, cnt);
				cnt--;
				HP = HP + tmpdist - H;
					
				v[i] = false;
			}
		}
		
	}

}
