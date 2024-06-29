

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_5186_파티를_열어라 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int K = Integer.parseInt(br.readLine());
		for (int k = 1; k <= K; k++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			int[] drunk = new int[l+1]; // 각 지역으로 가야하는 취한 사람의 수
			int[] sober = new int[l+1]; // 각 지역으로 가야하는 취하지 않은 사람의 수
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				char status = st.nextToken().charAt(0);
				if (status == 'I') {
					drunk[u]++;
				} else if (status == 'S') {
					sober[u]++;
				}
			}
			// 연정이 집에서 자는 사람의 수를 최소화하기 위해서는
			// 탑승할 수 있는 인원이 많은 차량을 우선적으로 활용해야 한다.
			// 각 지역으로 가는 차량을 탑승 인원이 많은 순서대로 뽑아서 최대치까지 탑승시킨다.
			ArrayList<PriorityQueue<Integer>> pqs = new ArrayList<>();
			for (int i = 0; i <= l; i++) {
				pqs.add(new PriorityQueue<>(Collections.reverseOrder()));
			}
			for (int i = 0; i < c; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int m = Integer.parseInt(st.nextToken());
				pqs.get(u).offer(m);
			}
			int cnt = 0; // 연정이 집에 자는 최소 인원 수
			for (int u = 1; u <= l; u++) {
				// 운전이 가능한 사람을 고려하기 위해, 우선 실제 이용 가능 차량의 수를 구한다.
				int carUsed = Math.min(sober[u], pqs.get(u).size());
				int cur = drunk[u] + sober[u]; // u 지역으로 가야하나, 차량 탑승을 하지 못하고 남은 인원의 수
				for (int i = 0; i < carUsed; i++) {
					int sizeOfCar = pqs.get(u).poll();
					cur = Math.max(0, cur - sizeOfCar);
				}
				// 차량 탑승을 하지 못한 인원은 연정이 집에서 잔다.
				cnt += cur;
			}
			sb.append("Data Set ").append(k).append(":").append("\n").append(cnt).append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
