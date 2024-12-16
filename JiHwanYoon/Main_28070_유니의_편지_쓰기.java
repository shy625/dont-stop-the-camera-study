

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_28070_유니의_편지_쓰기 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// 누적합을 활용
		// 배열 내 입대 일자와 전역 일자에 1, -1을 더해주고, 왼쪽부터 누적합을 순차적으로 구해준다.
		int[] arr = new int[(9999-2000+1)*12+1]; // 각 일자마다 군대에 있는 친구들의 수를 나타내는 배열
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			StringTokenizer start = new StringTokenizer(st.nextToken(), "-");
			int startyyyy = Integer.parseInt(start.nextToken());
			int startmm = Integer.parseInt(start.nextToken());
			arr[(startyyyy-2000)*12+startmm-1] += 1;
			StringTokenizer end = new StringTokenizer(st.nextToken(), "-");
			int endyyyy = Integer.parseInt(end.nextToken());
			int endmm = Integer.parseInt(end.nextToken());
			arr[(endyyyy-2000)*12+endmm] += -1;
		}
		int M = arr.length;
		for (int i = 1; i < M; i++) {
			arr[i] += arr[i-1];
		}
		int max = 0; // 군대에 있는 친구들 수의 최댓값
		int maxDate = 0; // 군대에 있는 친구들 수가 가장 많은 일자
		for (int i = 0; i < M; i++) {
			if (max < arr[i]) {
				max = arr[i];
				maxDate = i;
			}
		}
		System.out.println((maxDate/12+2000)+"-"+((maxDate%12+1) < 10 ? "0" + (maxDate%12+1) : (maxDate%12+1)));
	}

}
