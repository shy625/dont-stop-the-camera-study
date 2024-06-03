

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_27896_특별한_서핑 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] x = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			x[i] = Integer.parseInt(st.nextToken());
		}
		// 그리디 알고리즘을 활용
		// 앞에서부터 피묻튀를 주다가, 불만도가 M을 넘어서면 가장 불만도가 높았던 사람에게 가지를 준다.
		// 불만도를 높은 순서대로 나열하는 우선순위 큐
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); 
		int cnt = 0; // 가지를 준 횟수
		int cur = 0; // 현재 불만도
		for (int i = 0; i < N; i++) {
			pq.offer(x[i]);
			cur += x[i];
			if (cur >= M) {
				int max = pq.poll();
				cur -= 2*max; // 피묻튀 대신 가지를 주면서 불만도가 2*x_i만큼 내려간다.
				cnt++;
			}
		}
		System.out.println(cnt);
	}

}
