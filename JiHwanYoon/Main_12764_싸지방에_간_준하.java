

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_12764_싸지방에_간_준하 {
	// 컴퓨터 사용 시작 시간, 종료 시간, 번호를 나타내는 클래스
	static class Computer implements Comparable<Computer> {
		int startTime, endTime, idx;
		public Computer(int startTime, int endTime) {
			this.startTime = startTime;
			this.endTime = endTime;
		}
		public int compareTo(Computer p) {
			return Integer.compare(this.startTime, p.startTime);
		}
	}
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Computer> people = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int startTime = Integer.parseInt(st.nextToken());
			int endTime = Integer.parseInt(st.nextToken());
			people.offer(new Computer(startTime, endTime));
		}
		int[] counts = new int[N];
		int cnt = 0; // 최소 사용해야 하는 컴퓨터 수
		// 현재 사용 중인 컴퓨터들을 종료 시간 순으로 정렬한 우선 순위 큐
		PriorityQueue<Computer> usingComputers = new PriorityQueue<>((x, y) -> Integer.compare(x.endTime, y.endTime));
		// 사용이 종료된 컴퓨터들을 번호 순서대로 정렬한 우선 순위 큐
		PriorityQueue<Computer> candidates = new PriorityQueue<>((x, y) -> Integer.compare(x.idx, y.idx));
		for (int i = 0; i < N; i++) {
			Computer c = people.poll();
			// 현재 사용자가 사용 시작하는 시간보다 종료 시간이 빠른 컴퓨터를 후보로 지정
			while (!usingComputers.isEmpty() && c.startTime > usingComputers.peek().endTime) {					
				candidates.offer(usingComputers.poll());
			}
			// 사용할 컴퓨터를 정한다.
			// 후보가 없는 경우(사용을 한 컴퓨터가 없거나 종료된 컴퓨터가 없는 경우) 새 컴퓨터를 사용한다.
			int idxToUse = candidates.isEmpty() ? cnt++ : candidates.poll().idx;
            counts[idxToUse]++;
            c.idx = idxToUse;
            usingComputers.add(c);
		}
		// 출력
		StringBuilder sb = new StringBuilder();
		sb.append(cnt).append("\n");
		for (int i = 0; i < cnt; i++) {
			sb.append(counts[i]).append(" ");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
