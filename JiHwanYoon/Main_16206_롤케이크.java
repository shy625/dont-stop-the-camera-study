

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_16206_롤케이크 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int cnt = 0;
		ArrayList<Integer> rollArr1 = new ArrayList<>(); // 길이가 10의 배수인 롤케이크 길이를 저장하는 ArrayList
		ArrayList<Integer> rollArr2 = new ArrayList<>(); // 길이가 10의 배수가 아닌 롤케이크 길이를 저장하는 ArrayList
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int roll = Integer.parseInt(st.nextToken());
			if (roll%10 == 0) rollArr1.add(roll);
			else rollArr2.add(roll);
		}
		// greedy algorithm을 활용
		// 길이가 10의 배수인 롤케이크의 경우, (롤케이크의 길이)/10 - 1번만 잘라도 (롤케이크의 길이)/10개만큼의 길이가 10인 롤케이크를 얻을 수 있다.
		// 따라서, 길이가 10의 배수인 롤케이크부터 먼저 자르는 게 좋다.
		// 또한, 위의 경우는 해당 롤케이크를 온전히 다 잘라야 1번 덜 잘라도 길이가 10인 롤케이크를 얻을 수 있기 때문에
		// 길이가 짧은 것을 먼저 자르는 것이 좋다.
		// 길이가 10의 배수가 아니면 자른 횟수만큼 길이가 10인 롤케이크를 얻을 수 있으므로 우선순위가 따로 존재하지 않는다.
		Collections.sort(rollArr1); // 길이가 10의 배수인 롤케이크는 짧은 길이의 롤케이크부터 먼저 자른다.
		int K = rollArr1.size();
		int idx = 0; // 길이가 10의 배수인 롤케이크를 몇 번째까지 봤는지를 나타내는 인덱스
		int idx2 = 0; // 길이가 10의 배수가 아닌 롤케이크를 몇 번째까지 봤는지를 나타내는 인덱스
		while (M > 0) {
			// 길이가 10의 배수인 롤케이크를 먼저 탐색
			if (idx < K) {
				int roll = rollArr1.get(idx);
				int q = roll/10-1;
				// 현재 자를 수 있는 횟수가 충분해 롤케이크를 온전히 자를 수 있는 경우
				if (M >= q) {
					cnt += q+1;
					M -= q;
				// 현재 자를 수 있는 횟수가 모자라 롤케이크를 온전히 자를 수 없는 경우
				} else {
					cnt += M;
					M = 0;
				}
				idx++;
			// 길이가 10의 배수가 아닌 롤케이크를 나중에 탐색
			} else {
				// 모든 롤케이크를 잘랐음에도 자를 수 있는 횟수가 남은 경우
				if (idx2 >= rollArr2.size()) break;
				int roll = rollArr2.get(idx2);
				int q = roll/10;
				cnt += Math.min(q, M);
				M -= Math.min(q, M);
				idx2++;
			}
		}
		System.out.println(cnt);
	}

}
