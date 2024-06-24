import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_19238_스타트택시 {

	static int N, M, fuel;
	static int[][] map;
	static int r, c;// 택시 현재 위치
	static ArrayList<Passenger> pList;

	// 승객 위치 기록
	static class Passenger {
		int startR;
		int startC;
		int endR;
		int endC;

		public Passenger(int startR, int startC, int endR, int endC) {
			super();
			this.startR = startR;
			this.startC = startC;
			this.endR = endR;
			this.endC = endC;
		}

	}

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());

		// 맵 읽기
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 택시 처음 위치
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken()) - 1;
		c = Integer.parseInt(st.nextToken()) - 1;
		// 승객 위치 받기
		pList = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int startR = Integer.parseInt(st.nextToken()) - 1;
			int startC = Integer.parseInt(st.nextToken()) - 1;
			int endR = Integer.parseInt(st.nextToken()) - 1;
			int endC = Integer.parseInt(st.nextToken()) - 1;
			map[startR][startC] = 2;// 승객들 위치는 2 기록
			// map[endR][endC]=3;
			pList.add(new Passenger(startR, startC, endR, endC));
		}

		
		// 끝날 때까지 반복
		// 이동할 때마다 연료량 부족하면 종료하고 -1출력
		// 1. 최단거리 승객 찾기 (같은 거리일 경우 왼쪽 위 위치 우선):BFS
		// 2. 승객까지 이동 후 연료 감소
		// 3. 승객 목적지까지 이동 후 연료 감소 : BFS
		// 4. 이동시 소진된 연료량 * 2 를 연료에 충전
		while (!pList.isEmpty()) {
			Passenger now = null;
			// 최단 거리 승객 찾기
			int dist = 0;// 거리
			boolean v[][] = new boolean[N][N];
			PriorityQueue<int[]> pq = new PriorityQueue<>((x,
					y) -> Integer.compare(x[0], y[0]) == 0 ? Integer.compare(x[1], y[1]) : Integer.compare(x[0], y[0]));
			pq.offer(new int[] { r, c });
			v[r][c] = true;
			boolean find = false;// 찾았는지 표시해줄 변수
			PriorityQueue<int[]> pq2 = new PriorityQueue<>((x,
					y) -> Integer.compare(x[0], y[0]) == 0 ? Integer.compare(x[1], y[1]) : Integer.compare(x[0], y[0]));
			
			while (!pq.isEmpty()) {
				if (dist > fuel)
					break;
				int size = pq.size();
				
				//System.out.println(size);
				for (int i = 0; i < size; i++) {
					int[] cur = pq.poll();
					int nowR = cur[0];
					int nowC = cur[1];
					//System.out.println(nowR + " " + nowC);
					// 승객이 현재 위치에 존재한다
					if (map[nowR][nowC] == 2) {
						//System.out.println("r : " + nowR + " c : " + nowC + " dist : " + dist);
						find = true;
						for (Passenger curP : pList) {
							if (curP.startR == nowR && curP.startC == nowC) {
								now = curP;
								break;
							}
						}
					}
					if (find)
						break;
					// bfs
					for (int d = 0; d < 4; d++) {
						int nr = nowR + dr[d];
						int nc = nowC + dc[d];
						if (!check(nr, nc))
							continue;
						if (map[nr][nc] == 1)
							continue;// 벽이여서 못감
						if (v[nr][nc])
							continue;// 이미 갔던 곳
						
						v[nr][nc] = true;
						pq2.offer(new int[] { nr, nc });
					}
				}
				if (find)
					break;
				pq=pq2;
				pq2=new PriorityQueue<>((x,
						y) -> Integer.compare(x[0], y[0]) == 0 ? Integer.compare(x[1], y[1]) : Integer.compare(x[0], y[0]));
				dist++;
			}

			if (!find)
				break;// 찾지 못했으면 종료
			
			// 찾으면 승객위치로 이동해서 태우기
			fuel -= dist; // 이동거리만큼 연료 제거
			r = now.startR;
			c = now.startC;
			//System.out.println(r+ " "+ c);
			// 목적지까지 이동
			dist = 0;
			v = new boolean[N][N];
			Queue<int[]> que = new LinkedList<int[]>();
			que.offer(new int[] { r, c });
			v[r][c] = true;
			boolean go = false;

			while (!que.isEmpty()) {
				if (dist > fuel)
					break;
				int size = que.size();
				for (int i = 0; i < size; i++) {
					int[] cur = que.poll();
					int nowR = cur[0];
					int nowC = cur[1];

					// 목표한 위치 도착
					// 위치로 r,c 좌표 수정하고 연료 충전, 승객 리스트 제거//맵에서 표시 제거
					if (nowR == now.endR && nowC == now.endC) {
						go = true;
						fuel += dist; // fuel-dist + dist*2;
						r = nowR;
						c = nowC;
						map[now.startR][now.startC] = 0;
						// map[now.endR][now.endC]=0;
						pList.remove(now);
						M--;
						break;
					}

					if (go)
						break;
					// bfs
					for (int d = 0; d < 4; d++) {
						int nr = nowR + dr[d];
						int nc = nowC + dc[d];
						if (!check(nr, nc))
							continue;
						if (map[nr][nc] == 1)
							continue;// 벽이여서 못감
						if (v[nr][nc])
							continue;// 이미 갔던 곳

						v[nr][nc] = true;
						que.offer(new int[] { nr, nc });
					}
				}
				if (go)
					break;
				dist++;
			}
			if (!go)
				break;
		}
		// 성공여부에 따라서 -1 혹은 연료량 출력
		if (pList.size() == 0)
			System.out.println(fuel);
		else
			System.out.println(-1);
	}

	private static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}
