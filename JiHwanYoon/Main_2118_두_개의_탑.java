

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2118_두_개의_탑 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] distances = new int[N];
		int sum = 0;
		for (int i = 0; i < N; i++) {
			distances[i] = Integer.parseInt(br.readLine());
			sum += distances[i];
		}
		// 투 포인터를 이용
		// 두 지점
		int l = 0; 
		int r = 0;
		// 반시계 방향 거리
		int d1 = sum;
		// 시계 방향 거리
		int d2 = 0;
		int max = 0; // 두 탑 사이 거리의 최댓값
		while (l <= r && l < N) {
			max = Math.max(max, Math.min(d1, d2));
			// 시계 방향 거리가 더 길면 r을 옮긴다.
			if (r < N && d1 > d2) {
				d2 += distances[r];
				d1 -= distances[r];
				r++;
			// 그렇지 않으면 l을 옮긴다.
			} else {
				d2 -= distances[l];
				d1 += distances[l];
				l++;
			}
		}
		System.out.println(max);
	}

}
