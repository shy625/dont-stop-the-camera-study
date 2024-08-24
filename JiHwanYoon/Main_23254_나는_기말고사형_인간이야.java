

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_23254_나는_기말고사형_인간이야 {
	// 각 과목의 최초 점수와, 1시간 공부할 때 오르는 점수를 나타내는 클래스
	static class Subject implements Comparable<Subject> {
		int init, increase;
		public Subject(int init, int increase) {
			this.init = init;
			this.increase = increase;
		}
		public int compareTo(Subject s) {
			// 1시간 공부 시 오를 수 있는 점수 비교 시 100점 초과하여 오를 수 없음에 유의
			return Integer.compare(Math.min(100 - s.init, s.increase), Math.min(100 - this.init, this.increase));
		}
	}
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken())*24;
		int M = Integer.parseInt(st.nextToken());
		// greedy algorithm을 활용
		// 1시간 공부했을 때 점수가 가장 많이 오르는 과목부터 공부한다.
		// 단, 100점에 가까워짐에 따라 1시간 공부했을 때 오르는 점수가 변할 수 있음에 유의
		PriorityQueue<Subject> pq = new PriorityQueue<>();
		int[] inits = new int[M];
		int[] increases = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			inits[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			increases[i] = Integer.parseInt(st.nextToken());
		}
		long sum = 0;
		for (int i = 0; i < M; i++) {
			// 이미 100점이면 공부할 필요가 없다.
			if (inits[i] == 100) {
				sum += inits[i];
				continue;
			}
			pq.offer(new Subject(inits[i], increases[i]));
		}
		while (N > 0 && !pq.isEmpty()) {
			Subject s = pq.poll();
			N--;
			s.init = Math.min(s.init + s.increase, 100);
			// 1시간 공부할 때 오를 수 있는 점수를 바꿔준다.
			s.increase = Math.min(s.increase, 100 - s.init); 
			if (s.init < 100) pq.offer(s);
			else sum += s.init;
		}
		while (!pq.isEmpty()) {
			sum += pq.poll().init;
		}
		System.out.println(sum);
	}
}
