import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_G4_16197_두동전 {
	
	// 2개의 동전 위치와 움직인 횟수를 저장할 클래스 Coins
	static class Coins {
		int r1;
		int c1;
		int r2;
		int c2;
		int time;
		
		public Coins(int r1, int c1, int r2, int c2, int time) {
			this.r1 = r1;
			this.c1 = c1;
			this.r2 = r2;
			this.c2 = c2;
			this.time = time;
		}
		
	}

	static int N, M, min;
	static char [][] map;
	static Set<String> v;
	static int [] dr = {-1, 0, 1, 0};
	static int [] dc = {0, 1, 0, -1};
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		// 동전 2개의 위치를 동시에 저장하기 위해 String을 담는 HashSet을 사용하여 방문 여부를 체크함
		v = new HashSet<>();
		// 10번 이상 움직이면 -1을 출력해야 하므로 초기 min을 11로 지정
		min = 11;
		
		// 맨 처음 동전 2개의 위치를 알아내기 위한 list
		List<int []> list = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				char c = str.charAt(j);
				if(c == 'o') {
					list.add(new int[] {i, j});
				}
				map[i][j] = c;
			}
		}
		
		// 처음 동전 위치를 확인
		int [] coin1 = list.get(0);
		int [] coin2 = list.get(1);
		
		// r1,c1,r2,c2를 이어붙여 String으로 만든 뒤, HashSet v에 저장하여 방문했음을 저장한다.
		sb.append(coin1[0]).append(coin1[1]).append(coin2[0]).append(coin2[1]);
		v.add(sb.toString());
		
		bfs(coin1[0], coin1[1], coin2[0], coin2[1]);
		
		// 10번 이상 움직인 경우 -1을 출력한다.
		if(min == 11) {
			min = -1;
		} 
		
		System.out.println(min);

	}

	private static void bfs(int r1, int c1, int r2, int c2) {
		Queue<Coins> q = new LinkedList<Coins>();
		q.add(new Coins(r1, c1, r2, c2, 0));
		
		while(!q.isEmpty()) {
			Coins cur = q.poll();
			
			// 10회 이상 움직이는 경우 더이상 진행하지 않는다.
			if(cur.time >= 10) return ;
			
			for (int d = 0; d < 4; d++) {
				// 동전 2개의 nr, nc를 각각 선언한다.
				int nr1 = cur.r1+dr[d];
				int nc1 = cur.c1+dc[d];
				int nr2 = cur.r2+dr[d];
				int nc2 = cur.c2+dc[d];
				
				// 두 개의 동전이 동시에 맵 밖으로 떨어지는 경우는 넘긴다.
				if(!check(nr1, nc1) && !check(nr2, nc2)) continue;
				// 두 개의 동전 중 1개의 동전만 맵 밖으로 떨어지는 경우가 답의 조건을 만족한다.
				else if((!check(nr1, nc1) && check(nr2, nc2)) || (check(nr1, nc1) && !check(nr2, nc2))) {
					// min 값을 갱신하고 bfs를 종료한다.
					min = cur.time + 1;
					return ;
				}
				
				// 두 개의 동전 모두 움직일 곳이 벽인 경우는 넘긴다.
				if(map[nr1][nc1] == '#' && map[nr2][nc2] == '#') continue;
				// 동전 1은 벽이지만, 동전 2는 움직일 수 있는 경우
				else if(map[nr1][nc1] == '#' && map[nr2][nc2] != '#') {
					// 동전 2개가 겹쳐지는 경우 답을 만족할 수 없기 때문에 넘긴다.
					if(cur.r1 == nr2 && cur.c1 == nc2) continue;
					// 동전 1은 벽에 부딪히기 때문에 nr, nc를 현재 위치로 바꿔준다.
					nr1 = cur.r1;
					nc1 = cur.c1;
				// 동전 2는 벽이지만 동전 1은 움직일 수 있는 경우
				} else if(map[nr1][nc1] != '#' && map[nr2][nc2] == '#') {
					// 동전 2개가 겹쳐지는 경우 답을 만족할 수 없기 때문에 넘긴다.
					if(cur.r2 == nr1 && cur.c2 == nc1) continue;
					// 동전 2는 벽에 부딪히기 때문에 nr, nc를 현재 위치로 바꿔준다.
					nr2 = cur.r2;
					nc2 = cur.c2;
				}
				
				sb.setLength(0);
				// nr1, nc1, nr2, nc2를 이어붙여 String을 만든 뒤, 이미 HashSet v안에 존재한다면 이미 방문한거기 때문에, 넘긴다.
				sb.append(nr1).append(nc1).append(nr2).append(nc2);
				if(v.contains(sb.toString())) continue;
				
				// 처음 방문하는 경우에는 v에 추가해준다.
				v.add(sb.toString());
				
				// time을 +1 해준 뒤 q에 다시 집어 넣는다.
				q.add(new Coins(nr1, nc1, nr2, nc2, cur.time+1));
				
			}
			
		}
		
	}

	private static boolean check(int r, int c) {
		return r>=0 && r<N && c>=0 && c<M;
	}

}
