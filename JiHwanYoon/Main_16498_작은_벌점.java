

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_16498_작은_벌점 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int[] cardA = new int[A];
		int[] cardB = new int[B];
		int[] cardC = new int[C];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < A; i++) {
			cardA[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < B; i++) {
			cardB[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			cardC[i] = Integer.parseInt(st.nextToken());
		}
		// 이분 탐색을 활용한다.
		// A가 카드를 선택하는 모든 경우에 대해서,
		// B는 A가 선택한 카드와 가장 가까운 수가 적힌 카드를 선택한다.
		// C는 A가 선택한 카드와 가장 가까운 수, B가 선택한 카드와 가장 가까운 수가 적힌 카드를 하나씩 선택하고
		// 각 경우에 대해 구한 벌점 중 더 작은 값을 구한다.
		// 이분 탐색 적용을 위해 카드를 적힌 숫자의 오름차순으로 정렬
		Arrays.sort(cardB);
		Arrays.sort(cardC);
		int min = Integer.MAX_VALUE; // 벌점의 최솟값
		for (int i = 0; i < A; i++) {
			int a = cardA[i];
			int b = binarySearch(a, cardB);	// B의 카드에 적힌 숫자 중 a와 가장 가까운 숫자
			int ca = binarySearch(a, cardC); // C의 카드에 적힌 숫자 중 a와 가장 가까운 숫자
			int cb = binarySearch(b, cardC); // C의 카드에 적힌 숫자 중 b에 가장 가까운 숫자
			// 벌점의 최솟값을 구한다.
			min = Math.min(min, Math.max(a, Math.max(ca, b)) - Math.min(a, Math.min(ca, b)));
			min = Math.min(min, Math.max(a, Math.max(cb, b)) - Math.min(a, Math.min(cb, b)));
		}
		System.out.println(min);
	}
	// 이분 탐색을 활용해 card 배열에서 n과 가장 가까운 숫자를 찾는 함수
	private static int binarySearch(int n, int[] card) {
		int l = 0;
		int r = card.length-1;
		int nearest = card[l];
		while (l <= r) {
			int mid = (l+r)/2;
			if (card[mid] == n) nearest = card[mid];
			if (card[mid] >= n) r = mid-1;
			else l = mid+1;
			if (Math.abs(nearest - n) > Math.abs(card[mid] - n)) nearest = card[mid];
		}
		return nearest;
	}

}
