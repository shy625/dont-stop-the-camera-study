

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_11497_통나무_건너뛰기 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int[] arr = new int[N];
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			/*
			 * 전체적인 아이디어
			 * 통나무들을 마치 하나의 언덕 모양으로 배치한다(ㅅ 모양)
			 * 이를 위해 배열을 정렬한 뒤 작은 값부터 왼쪽 끝과 오른쪽 끝에 하나씩 배치한다.
			 */
			Arrays.sort(arr);
			int l = 0, r = N-1;
			int[] logs = new int[N];
			int k = 0;
			// N의 값이 홀수냐 짝수냐에 따라 while 문을 벗어나는 위치가 다를 수 있으므로 주의해야 한다.
			while (l <= r) {
				logs[l] = arr[k++];
				if (k >= N) break;
				logs[r] = arr[k++];
				if (k >= N) break;
				l++; r--;
			}
			// 양끝 통나무의 높이차를 먼저 고려한다.
			int max = Math.abs(logs[0] - logs[N-1]);
			for (int i = 0; i < N-1; i++) {
				max = Math.max(max, Math.abs(logs[i+1] - logs[i]));
			}
			System.out.println(max);
		}
	}
}
