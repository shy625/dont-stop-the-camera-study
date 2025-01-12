

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1931_회의실_배정 {
	// 회의의 시작 시간과 종료 시간을 저장하는 클래스
	static class Discussion implements Comparable<Discussion> {
		int start, end;
		public Discussion (int start, int end) {
			this.start = start;
			this.end = end;
		}
		// 일찍 끝나는 순서대로, 만약 종료 시간이 같다면 시작 시간이 빠른 순서대로 정렬
		// (시작 시간과 종료 시간이 동일한 경우를 고려하기 위함)
		public int compareTo(Discussion d) {
			return this.end == d.end ? Integer.compare(this.start, d.start) : Integer.compare(this.end, d.end);
		}
	}
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// 그리디 알고리즘 활용
		// 종료 시간이 빠른 순서대로 정렬한 뒤
		// 각 회의에 대해 회의실을 잡을지 결정
		PriorityQueue<Discussion> pq = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			pq.offer(new Discussion(start, end));
		}
		int cur = -1; // 이전 회의가 끝난 시간
		int cnt = 0; // 진행 가능한 최대 회의 개수
		while (!pq.isEmpty()) {
			Discussion d = pq.poll();
			if (cur <= d.start) {
				cnt++;
				cur = d.end;
			}
		}
		System.out.println(cnt);
	}

}
