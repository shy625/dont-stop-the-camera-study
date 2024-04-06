

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2317_결혼식 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] lionHeights = new int[K];
		int minL = Integer.MAX_VALUE;
		int maxL = 0;
		int[] heights = new int[N-K];
		int minH = Integer.MAX_VALUE;
		int maxH = 0;
		for (int i = 0; i < K; i++) {
			lionHeights[i] = Integer.parseInt(br.readLine());
			minL = Math.min(minL, lionHeights[i]);
			maxL = Math.max(maxL, lionHeights[i]);
		}
		for (int i = 0; i < N-K; i++) {
			heights[i] = Integer.parseInt(br.readLine());
			minH = Math.min(minH, heights[i]);
			maxH = Math.max(maxH, heights[i]);
		}
		// greedy algorithm을 활용
		// 사자 가족에 대해 키의 최댓값, 최솟값을 구하고, 나머지 사람들의 키가 최댓값과 최솟값 사이인 경우 그 사이에 적절히 끼워넣으면
		// 키 차이의 합의 최솟값은 사자 가족의 인접한 키 차이의 합과 같다.
		// 다만, 나머지 사람들 중 키 중 사자 가족의 키의 최솟값 ~ 최댓값 범위를 벗어나는 경우가 있으면
		// 해당 사람들 중 가장 크거나 작은 사람의 경우 앞쪽에 세울지, 중간에 세울지, 뒤쪽에 세울지 결정한다.
		int res = 0;
		for (int i = 0; i < K-1; i++) {
			res += Math.abs(lionHeights[i+1] - lionHeights[i]);
		}
		if (minH < minL) {
			int min = 2*(minL - minH);
			min = Math.min(min, Math.abs(lionHeights[0] - minH));
			min = Math.min(min, Math.abs(lionHeights[K-1] - minH));
			res += min;
		}
		if (maxH > maxL) {
			int min = 2*(maxH - maxL);
			min = Math.min(min, Math.abs(lionHeights[0] - maxH));
			min = Math.min(min, Math.abs(lionHeights[K-1] - maxH));
			res += min;
		}
		System.out.println(res);
	}

}
