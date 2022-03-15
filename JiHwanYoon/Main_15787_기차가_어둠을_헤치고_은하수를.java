

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15787_기차가_어둠을_헤치고_은하수를 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		// 각 기차에 0~2^20-1까지의 범위를 가지는 정수 생성
		// 이 수는 2진수로 나타냈을 때 (i-1)번째 자리의 값이 i번째 자리에 사람이 탔는지를 나타낸다.
		int[] train = new int[N+1];
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine(), " ");
			// 명령 번호
			int command = Integer.parseInt(st.nextToken());
			// 명령을 수행할 기차
			int i = Integer.parseInt(st.nextToken());
			// 적절한 비트 연산을 통해 명령을 수행
			if (command == 1) {
				int x = Integer.parseInt(st.nextToken());
				train[i] |= (1 << (x-1));
			} else if (command == 2) {
				int x = Integer.parseInt(st.nextToken());
				// x-1번째 자리에 사람이 있는 경우에만 x-1번째 자리를 비운다.
				if ((train[i] & (1 << (x-1))) != 0) train[i] -= (1 << (x-1));
			} else if (command == 3) {
				train[i] *= 2;
				// 20번째 자리 이후로는 존재하지 않음에 유의
				train[i] %= 1 << 20;
			} else if (command == 4) {
				train[i] /= 2;
			}
		}
		// 각 기차마다 승객이 앉은 상태가 이미 나온 상태인지를 확인
		boolean[] visited = new boolean[1 << 20];
		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			train[i] %= 1 << 20;
			if (!visited[train[i]]) {
				cnt++;
				visited[train[i]] = true;
			}
		}
		System.out.println(cnt);
	}

}
