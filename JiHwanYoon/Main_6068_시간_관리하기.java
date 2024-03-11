

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_6068_시간_관리하기 {
	// 일을 끝마치는데 걸리는 시간과 끝내야 하는 시간을 나타내는 클래스
	static class Work implements Comparable<Work> {
		int duration, endTime;
		public Work(int duration, int endTime) {
			this.duration = duration;
			this.endTime = endTime;
		}
		public int compareTo(Work w) {
			return Integer.compare(w.endTime, this.endTime);
		}
	}
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// greedy algorithm을 활용
		// 끝내야 하는 시간이 늦은 일부터 역순으로 탐색하면서, 
		// 이전까지 탐색한 일을 모두 처리하는데 가장 늦게 시작할 수 있는 시간과, 현재 탐색 중인 일을 끝내야 하는 시간 중
		// 더 이른 시간을, 현재 탐색 중인 일을 끝내는 시간으로 한다.
		PriorityQueue<Work> pq = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int duration = Integer.parseInt(st.nextToken());
			int endTime = Integer.parseInt(st.nextToken());
			pq.offer(new Work(duration, endTime));
		}
		int cur = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			Work w = pq.poll();
			cur = Math.min(cur, w.endTime) - w.duration;
		}
		System.out.println(cur < 0 ? -1 : cur);
	}

}
