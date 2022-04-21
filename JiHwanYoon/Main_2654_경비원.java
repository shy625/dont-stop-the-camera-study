

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2654_경비원 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int cnt = Integer.parseInt(br.readLine());
		// 상점과 동근이의 좌표를 저장
		int[][] points = new int[cnt+1][2];
		for (int i = 0; i < cnt+1; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			if (d == 1) {
				points[i][0] = 0;
				points[i][1] = k;
			} else if (d == 2) {
				points[i][0] = N;
				points[i][1] = k;
			} else if (d == 3) {
				points[i][0] = k;
				points[i][1] = 0;
			} else if (d == 4) {
				points[i][0] = k;
				points[i][1] = M;
			}
		}
		int sum = 0;
		// 각 상점마다 동근이와의 최단거리 계산
		for (int i = 0; i < cnt; i++) {
			// 같은 행에 있을 때
			if ((points[i][0] == 0 && points[cnt][0] == 0) || (points[i][0] == N && points[cnt][0] == N)) {
				sum += Math.abs(points[i][1] - points[cnt][1]);
			// 같은 열에 있을 때
			} else if ((points[i][1] == 0 && points[cnt][1] == 0) || (points[i][1] == M && points[cnt][1] == M)) {
				sum += Math.abs(points[i][0] - points[cnt][0]);
			// 양 끝 행에 있을 때
			} else if ((points[i][0] == 0 && points[cnt][0] == N) || (points[i][0] == N && points[cnt][0] == 0)) {
				sum += Math.min(points[i][1] + points[cnt][1], 2*M - points[i][1] - points[cnt][1]) + N;
			// 양 끝 열에 있을 때
			} else if ((points[i][1] == 0 && points[cnt][1] == M) || (points[i][1] == M && points[cnt][1] == 0)) {
				sum += Math.min(points[i][0] + points[cnt][0], 2*N - points[i][0] - points[cnt][0]) + M;
			// 상점과 동근이가 위치한 변이 다르면서 서로 이웃할 때
			} else {
				sum += Math.abs(points[i][0] - points[cnt][0]) + Math.abs(points[i][1] - points[cnt][1]);
			}
		}
		System.out.println(sum);
	}

}

