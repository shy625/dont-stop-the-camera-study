

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1711_직각삼각형 {
	// 각 점의 좌표를 저장하는 클래스
	static class Point {
		long x, y;
		public Point(long x, long y) {
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Point[] points = new Point[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			points[i] = new Point(x, y);
		}
		int cnt = 0; // 직각삼각형의 개수
		// 모든 경우에 대해 직각삼각형이 만들어지는지 확인
		// 직각삼각형 확인은 피타고라스 정리를 활용해 확인
		for (int i = 0; i < N; i++) {
			Point p1 = points[i];
			for (int j = i+1; j < N; j++) {
				Point p2 = points[j];
				long dis1 = (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
				for (int k = j+1; k < N; k++) {
					Point p3 = points[k];
					long dis2 = (p1.x - p3.x) * (p1.x - p3.x) + (p1.y - p3.y) * (p1.y - p3.y);
					long dis3 = (p2.x - p3.x) * (p2.x - p3.x) + (p2.y - p3.y) * (p2.y - p3.y);
					if (dis1 == dis2 + dis3 || dis2 == dis1 + dis3 || dis3 == dis1 + dis2) cnt++;
				}
			}
		}
		System.out.println(cnt);
	}

}
