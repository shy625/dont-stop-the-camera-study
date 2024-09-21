

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_11067_모노톤길 {
	// 각 지점의 x,y좌표를 저장하는 클래스
	public static class Point implements Comparable<Point> {
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public int compareTo(Point p) {
			return this.x == p.x ? Integer.compare(this.y, p.y) : Integer.compare(this.x, p.x);
		}
	}
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			ArrayList<Point> points = new ArrayList<>();
			// 가장 초기 지점을 임시로 (-1, 0)으로 지정
			// x좌표가 0일 때 어느 방향으로 이동할지 결정해야 하기 때문이다.
			points.add(new Point(-1, 0)); 
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				points.add(new Point(x, y));
			}
			Collections.sort(points);
			int curIdx = 1; // 현재 몇 번째 카페를 방문하는지를 나타내는 변수
			while (curIdx <= N) {
				// x좌표가 이전 카페와 같은 경우
				if (points.get(curIdx).x == points.get(curIdx-1).x) {
					curIdx++;
				// y좌표가 이전 카페와 같은 경우
				} else if (points.get(curIdx).y == points.get(curIdx-1).y) {
					curIdx++;
				// x좌표, y좌표가 이전 카페와 모두 다른 경우 
				// 현재 카페와 동일한 x좌표를 가지는 모든 카페를 반대 순서대로 방문해야 한다.
				} else {
					int temp = curIdx;
					int curX = points.get(curIdx).x;
					while (curIdx <= N && curX == points.get(curIdx).x) {
						curIdx++;
					}
					List<Point> subList = points.subList(temp, curIdx);
					Collections.reverse(subList);
				}
			}
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			for (int m = 0; m < M; m++) {
				int idx = Integer.parseInt(st.nextToken());
				sb.append(points.get(idx).x).append(" ").append(points.get(idx).y).append("\n");
			}
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
