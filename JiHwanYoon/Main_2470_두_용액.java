

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2470_두_용액 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] pHs = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			pHs[i] = Integer.parseInt(st.nextToken());
		}
		// 용액의 산성도를 오름차순으로 정렬
		Arrays.sort(pHs);
		// 투 포인터를 활용한다.
		// 왼쪽 끝과 오른쪽 끝의 값부터 시작해서,
		// 두 값의 합의 절댓값의 최솟값을 찾아나간다.
		// 만약 두 값의 합이 음수면 왼쪽 끝의 값의 절댓값이 오른쪽 끝의 값의 절댓값보다 크다는 뜻이므로
		// 왼쪽 끝에 해당하는 부분을 오른쪽으로 한 칸 이동시켜 두 값의 합의 절댓값을 줄여나간다.
		// 반대로 두 값의 합이 양수면 오른쪽 끝에 해당하는 부분을 오른쪽으로 한 칸 이동시킨다.
		int l = 0; // 현재 왼쪽 끝의 인덱스
		int r = N-1; // 현재 오른쪽 끝의 인덱스
		int minL = l; // 두 값의 합의 절댓값이 최소일 때 왼쪽 끝의 인덱스
		int minR = r; // 두 값의 합의 절댓값이 최소일 때 오른쪽 끝의 인덱스
		int min = Integer.MAX_VALUE; // 두 값의 합의 절댓값의 최솟값
		// l이 r보다 작을 동안 계속 반복
		while (l < r) {
			int sum = pHs[l] + pHs[r];
			// 두 값의 합의 절댓값이 min보다 더 작을 경우 최솟값 갱신
			if (min > Math.abs(sum)) {
				min = Math.abs(sum);
				minL = l;
				minR = r;
			}
			// 두 값의 합이 음수인 경우
			if (sum < 0) l++;
			// 두 값의 합이 양수인 경우
			else if (sum > 0) r--;
			// 두 값의 합이 0이면 이미 최솟값을 구한 것이나 다름없다.
			else break;
		}
		System.out.println(pHs[minL]+" "+pHs[minR]);
	}

}
