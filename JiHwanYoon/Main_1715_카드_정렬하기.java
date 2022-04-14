

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_1715_카드_정렬하기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			pq.offer(Integer.parseInt(br.readLine()));
		}
		long sum = 0;
		// 가장 개수가 적은 카드 묶음 두 개를 꺼내서 합친 뒤 다시 카드 묶음들 사이에 넣는 방식을 반복
		// 먼저 꺼낸 카드 묶음의 카드 개수가 합친 카드 묶음에서 계속 고려되기 때문
		while (pq.size() >= 2) {
			int a = pq.poll();
			int b = pq.poll();
			sum += a + b;
			pq.offer(a + b);
		}
		System.out.println(sum);
	}

}
