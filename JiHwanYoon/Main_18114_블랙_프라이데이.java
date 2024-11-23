

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_18114_블랙_프라이데이 {
	static int N;
	static long C;
	static long[] goods;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Long.parseLong(st.nextToken());
		// 물건들을 최대 3개 고를 수 있으므로,
		// 무게가 0인 가상의 물건 2개를 추가한 뒤 3개의 물건을 고르는 방식으로 접근
		goods = new long[N+2]; 
		st = new StringTokenizer(br.readLine());
		for (int i = 2; i <= N+1; i++) {
			goods[i] = Long.parseLong(st.nextToken());
		}
		// 투 포인터 알고리즘 적용을 위한 정렬
		Arrays.sort(goods);
		// 우선, 고를 3개의 물건 중 무게가 가장 작은 물건 하나를 선택한다.
		for (int i = 0; i < N; i++) {
			// 고른 물건이 제시하는 무게보다 무거운 경우
			if (C < goods[i]) continue; 
			// 그리고 나머지 2개의 물건을 골라 무게의 합이 C가 되도록 한다.
			simulate(i+1, C - goods[i]);
		}
		// 어떻게 물건을 골라도 무게의 합이 C가 될 수 없는 경우
		System.out.println(0);
	}
	// start번째 물건부터 고려했을 때 2개의 물건의 합이 w가 되는 경우가 있는지 확인하는 함수
	private static void simulate(int start, long w) {
		// 투 포인터 알고리즘 활용
		int l = start;
		int r = N+1;
		while (l < r) {
			long cur = goods[l] + goods[r];
			// 2개의 물건의 합이 w가 되는 경우 
			// 즉 3개의 물건의 합이 C가 되는 경우
			if (cur == w) {
				System.out.println(1);
				System.exit(0);
			} else if (cur < w) {
				l++;
			} else {
				r--;
			}
		}
		
	}

}
