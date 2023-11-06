

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_1911_흙길_보수하기 {
	// 웅덩이의 위치를 나타내는 클래스
	static class Pool implements Comparable<Pool> {
		int start, end;
		public Pool(int start, int end) {
			this.start = start;
			this.end = end;
		}
		public int compareTo(Pool p) {
			return Integer.compare(this.start, p.start);
		}
	}
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		ArrayList<Pool> pools = new ArrayList<>(); // 웅덩이들을 저장하는 ArrayList
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			pools.add(new Pool(start, end));
		}
		// 웅덩이를 위치에 따라 정렬한 뒤, 널빤지를 차례대로 놓는다.
		Collections.sort(pools);
		int curEnd = -1; // 현재 가장 오른쪽 널빤지의 끝 부분의 위치
		int sum = 0; // 사용한 널빤지 개수
		for (Pool p : pools) {
			// 현재 가장 오른쪽 널빤지가 여러 개의 웅덩이를 가로질러 덮을 수 있음에 유의
			int curStart = Math.max(curEnd, p.start);
			// 현재 웅덩이를 덮기 위해 필요한 널빤지 개수 계산
			int cnt = (p.end - curStart + L-1)/L;
			sum += cnt;
			// 가장 오른쪽 널빤지의 끝 부분의 위치 계산
			curEnd = curStart + cnt*L;
		}
		System.out.println(sum);
	}

}
