

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_24954_물약_구매 {
	// 할인되는 물약과 할인값을 저장하는 클래스
	static class Discount {
		int a, d;
		public Discount(int a, int d) {
			this.a = a;
			this.d = d;
		}
	}
	static int N;
	static int[] prices;
	static ArrayList<ArrayList<Discount>> discounts;
	static boolean[] v;
	static int min;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		prices = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			prices[i] = Integer.parseInt(st.nextToken());
		}
		discounts = new ArrayList<>(); 
		for (int i = 0; i < N; i++) {
			discounts.add(new ArrayList<>());
		}
		for (int i = 0; i < N; i++) {
			int p = Integer.parseInt(br.readLine());
			for (int j = 0; j < p; j++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken())-1;
				int d = Integer.parseInt(st.nextToken());
				discounts.get(i).add(new Discount(a, d));
			}
		}
		// 브루트포스 알고리즘을 활용
		v = new boolean[N];
		min = Integer.MAX_VALUE;
		perm(0, 0);
		System.out.println(min);
	}
	// 브루트포스 알고리즘을 활용해 물약을 사는데 드는 최소 비용을 구하는 함수
	private static void perm(int cnt, int sum) {
		// 모든 물약을 구매한 경우
		if (cnt == N) {
			min = Math.min(min, sum);
			return;
		}
		for (int i = 0; i < N; i++) {
			if (v[i]) continue;
			v[i] = true;
			// 해당 물약을 사면서 할인되는 물약의 비용을 감소시킨다.
			for (Discount dis : discounts.get(i)) {
				prices[dis.a] = prices[dis.a] - dis.d;
			}
			// 물약 구매 비용이 0원 이하로 줄어들지 않음에 유의
			perm(cnt+1, sum+Math.max(prices[i], 1));
			// 백트래킹
			v[i] = false;
			for (Discount dis : discounts.get(i)) {
				prices[dis.a] = prices[dis.a] + dis.d;
			}
		}
	}

}
