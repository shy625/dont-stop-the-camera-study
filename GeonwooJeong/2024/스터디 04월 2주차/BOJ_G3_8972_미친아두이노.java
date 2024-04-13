import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G3_8972_미친아두이노 {
	static class Direction implements Comparable<Direction> {
		int nr;
		int nc;
		int dist;
		
		public Direction(int nr, int nc, int dist) {
			this.nr = nr;
			this.nc = nc;
			this.dist = dist;
		}
		
		@Override
		public int compareTo(Direction o) {
			return Integer.compare(this.dist, o.dist);
		}
	}
	static int R, C, jr, jc;
	static int [] dr = {1, 1, 1, 0, 0, 0, -1, -1, -1};
	static int [] dc = {-1, 0, 1, -1, 0, 1, -1, 0, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		// 미친 아두이노를 저장할 큐
		Queue<int []> q = new ArrayDeque<>();
		
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				char c = str.charAt(j);
				// 미친 아두이노는 큐에 넣는다.
				if(c == 'R') {
					q.add(new int[] {i, j});
				// 종수 아두이노는 시작지점을 기록한다.
				} else if(c == 'I') {
					jr = i;
					jc = j;
				}
			}
		}
		
		// 중간에 게임이 끝날 경우에 사용할 변수들
		boolean failed = false;
		int cnt = 0;
		
		String move = br.readLine();
		outer:
			for (int i = 0; i < move.length(); i++) {				
				int d = (move.charAt(i) - '0')-1;
				
				int nr = jr+dr[d];
				int nc = jc+dc[d];
				
				// 정해진 방향으로 종수 아두이노를 움직일 수 있으면 움직인다.
				if(check(nr, nc)) {
					jr = nr;
					jc = nc;
				}
				
				// 같은 좌표에 미친 아두이노의 개수를 체크할 map
				Map<String, Integer> map = new HashMap<>();
				
				// 미친 아두이노를 모두 종수 아두이노와 가장 가까운 방향으로 움직인다.
				while(!q.isEmpty()) {
					int [] cur = q.poll();
					int curR = cur[0];
					int curC = cur[1];
					
					// 만약 종수 아두이노가 이동한 곳에 이미 미친 아두이노가 있으면 게임이 끝난다.
					if(curR == jr && curC == jc) {
						failed = true;
						cnt = i+1;
						break outer;
					}
					
					// 9가지 방향 중 가장 가까운 방향을 고르기 위한 pq
					PriorityQueue<Direction> pq = new PriorityQueue<>();
					for (int d2 = 0; d2 < 9; d2++) {
						int nr2 = curR+dr[d2];
						int nc2 = curC+dc[d2];
						
						if(!check(nr2, nc2)) continue;
						
						int dist = Math.abs(nr2 - jr) + Math.abs(nc2 - jc);
						pq.add(new Direction(nr2, nc2, dist));
					}
					
					// 종수 아두이노와 가장 가까운 방향
					Direction next = pq.poll();
					
					// 만약 움직여서 종수 아두이노와 만난다면 게임이 끝난다.
					if(next.nr == jr && next.nc == jc) {
						failed = true;
						cnt = i+1;
						break outer;
					}
					
					// 미친 아두이노가 움직일 좌표를 String 형태로 바꾼다.
					String key = next.nr+" "+next.nc;
					// map에 이미 존재한다면 +1 해준다.
					if(map.containsKey(key)) {
						map.put(key, map.get(key)+1);
					// 없다면 1로 넣어준다.
					} else {
						map.put(key, 1);
					}
					
				}
				
				// 맵을 전체 순위하면서 value가 1을 초과하는 경우(한 곳에 미친 아두이노가 여러개 인 경우)는 폭파시킨다.
				for(Map.Entry<String, Integer> entry : map.entrySet()) {
					if(entry.getValue() > 1) continue;
					
					// 그 외의 미친 아두이노는 다시 q에 넣어준다.
					String [] arr = entry.getKey().split(" ");
					int r = Integer.parseInt(arr[0]);
					int c = Integer.parseInt(arr[1]);
					q.add(new int[] {r, c});
				}

			}
		
		// 중간에 게임이 끝난 경우
		if(failed) {
			System.out.println("kraj "+cnt);
			System.exit(0);
		}
		
		// 정답 출력을 위한 2차원 배열 map
		char [][] map = new char[R][C];
		for (int j = 0; j < R; j++) {
			Arrays.fill(map[j], '.');
		}
		
		// 마지막까지 살아남은 경우는 종수 아두이노의 위치를 기록하고,
		map[jr][jc] = 'I';
		
		// 큐에서 모든 미친 아두이노를 꺼내어 맵에 기록해준다.
		for(int [] arr : q) {
			map[arr[0]][arr[1]] = 'R';
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				sb.append(map[i][j]);
			}
			sb.append('\n');
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
		
	}

	private static boolean check(int r, int c) {
		return r>=0 && r<R && c>=0 && c<C;
	}

}
