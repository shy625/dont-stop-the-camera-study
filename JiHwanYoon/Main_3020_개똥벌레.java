

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_3020_개똥벌레 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		int[] A = new int[N/2]; // 석순 길이
		int[] B = new int[N/2]; // 종유석 길이
		for (int i = 0; i < N/2; i++) {
			A[i] = Integer.parseInt(br.readLine());
			B[i] = Integer.parseInt(br.readLine());
		}
		// 누적합을 활용
		int[] sumA = new int[H+1]; // 각 높이로 지나갈 때 파괴하는 석순의 개수
		sumA[0] = N/2; // 가장 낮은 높이로 지나가면 모든 석순을 파괴한다.
		// 석순의 높이보다 높게 지나가면 해당 석순은 파괴하지 않는다는 것을 이용
		for (int i = 0; i < N/2; i++) {
			sumA[A[i]]--;
		}
		for (int h = 1; h <= H; h++) {
			sumA[h] += sumA[h-1];
		}
		int[] sumB = new int[H+1]; // 각 높이로 지나갈 때 파괴하는 종유석의 개수
		// (동굴의 높이 - 종유석의 높이)보다 높거나 같은 높이로 지나가면 해당 종유석은 파괴한다는 것을 이용
		for (int i = 0; i < N/2; i++) {
			sumB[H-B[i]]++;
		}
		for (int h = 1; h <= H; h++) {
			sumB[h] += sumB[h-1];
		}
		// 각 높이마다 종유석과 석순을 파괴하는 개수를 더해준다.
		for (int h = 0; h <= H; h++) {
			sumA[h] += sumB[h];
		}
		// 출력
		int min = Integer.MAX_VALUE;
		int cnt = 0;
		for (int h = 0; h < H; h++) {
			if (min > sumA[h]) {
				min = sumA[h];
				cnt = 1;
			} else if (min == sumA[h]) {
				cnt++;
			}
		}
		System.out.println(min + " " + cnt);
	}

}
