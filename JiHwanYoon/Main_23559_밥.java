

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_23559_밥 {
	// 며칠째 메뉴인지와 5000원 메뉴와 1000원 메뉴의 맛의 차이를 나타내는 클래스
	static class Menu implements Comparable<Menu> {
		int idx, taste;
		public Menu(int idx, int taste) {
			this.idx = idx;
			this.taste = taste;
		}
		public int compareTo(Menu m) {
			return Integer.compare(m.taste, this.taste);
		}
	}
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		long X = Long.parseLong(st.nextToken());
		// greedy algorithm을 활용
		// 5000원 메뉴와 1000원 메뉴의 맛이 차이가 큰 순서대로 정렬하고,
		// 하나씩 탐색하면서 만약 남은 금액으로 5000원 메뉴를 고를 수 없거나 1000원 메뉴가 더 맛있는 경우
		// 1000원 메뉴를 선택하고, 그렇지 않으면 5000원 메뉴를 선택한다.
		PriorityQueue<Menu> pq = new PriorityQueue<>();
		int[] A = new int[N]; // 5000원 메뉴의 맛
		int[] B = new int[N]; // 1000원 메뉴의 맛
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			A[i] = Integer.parseInt(st.nextToken());
			B[i] = Integer.parseInt(st.nextToken());
			pq.offer(new Menu(i, A[i] - B[i]));
		}
		long sum = 0; // 선택한 메뉴의 맛의 합
		for (int i = 0; i < N; i++) {
			Menu m = pq.poll();
			// 1000원 메뉴를 고르는 경우
			if (5000 + 1000 * (N - i - 1) > X || m.taste <= 0) {
				sum += B[m.idx];
				X -= 1000;
			// 5000원 메뉴를 고르는 경우
			} else {
				sum += A[m.idx];
				X -= 5000;
			}
		}
		System.out.println(sum);
	}

}
