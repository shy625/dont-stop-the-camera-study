

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_12908_텔레포트_3 {
	// 위치를 나타내는 클래스
	static class Location {
		long r, c;
		public Location(long r, long c) {
			this.r = r;
			this.c = c;
		}
	}
	static Location start, end; // 시작 위치와 끝 위치
	static Location[][] teleports; // 순간이동이 가능한 세 구간을 나타내는 배열
	static boolean[] visited; // 방문 배열
	static long min = Long.MAX_VALUE; // 집에 가는 가장 빠른 시간
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		start = new Location(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		st = new StringTokenizer(br.readLine());
		end = new Location(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		teleports = new Location[3][2];
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			teleports[i][0] = new Location(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			teleports[i][1] = new Location(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		visited = new boolean[3];
		// 브루트포스 알고리즘을 활용해 모든 경우를 고려한다.
		subset(start, 0l);
		System.out.println(min);
	}
	// 현재 위치 l, 현재 지난 시간이 time일 때 집에 가장 빨리 도착하는 시간을 구하는 함수
	private static void subset(Location l, long time) {
		// 집에 도착한 경우
		if (l.r == end.r && l.c == end.c) {
			min = Math.min(min, time);
			return;
		}
		// 바로 집으로 가는 경우
		subset(end, time + Math.abs(l.r - end.r) + Math.abs(l.c - end.c));
		// 순간이동 구간 3개 중 하나로 가는 경우
		for (int i = 0; i < 3; i++) {
			if (visited[i]) continue;
			visited[i] = true;
			// 순간이동 구간이 A <-> B일 때, A -> B로 가는 경우와 B -> A로 가는 경우 모두 시도한다.
			subset(teleports[i][1], time + Math.abs(l.r - teleports[i][0].r) + Math.abs(l.c - teleports[i][0].c) + 10l);
			subset(teleports[i][0], time + Math.abs(l.r - teleports[i][1].r) + Math.abs(l.c - teleports[i][1].c) + 10l);
			visited[i] = false;
		}
	}

}
