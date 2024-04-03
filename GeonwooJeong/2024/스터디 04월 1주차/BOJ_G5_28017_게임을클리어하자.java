import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_G5_28017_게임을클리어하자 {
	static class Weapon implements Comparable<Weapon> {
		int idx;
		int value;

		public Weapon(int idx, int value) {
			this.idx = idx;
			this.value = value;
		}

		@Override
		public int compareTo(Weapon o) {
			return Integer.compare(this.value, o.value);
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		// i번째 날에 M번 무기를 선택했을 때의 최솟값을 저장할 2차원 배열
		int[][] dp = new int[N + 1][M];
		// 무기 정보를 담을 2차원 배열
		int[][] weapons = new int[N + 1][M];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				weapons[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 1; i <= N; i++) {
			// i번째 날 이전날(i-1번째 날)의 무기 최솟값들을 정렬하기 위한 list
			List<Weapon> list = new ArrayList<Weapon>();
			for (int j = 0; j < M; j++) {
				list.add(new Weapon(j, dp[i-1][j]));
			}
			// value가 작은 순서로 정렬한다.
			Collections.sort(list);
			
			for (int j = 0; j < M; j++) {
				int value = Integer.MAX_VALUE;
				// 만약 가장 value가 작은 무기를 이전 스테이지에서 사용하지 않았다면 value가 가장 작은 무기를 사용한다.
				if(list.get(0).idx != j) {
					value = list.get(0).value;
				// 사용했다면, 그 다음으로 작은 무기를 선택한다.
				} else {
					value = list.get(1).value;
				}
				// i번째 날에 j 무기를 선택했을 때의 최소값을 저장한다.
				dp[i][j] = value + weapons[i][j];
			}
		}
		
		int min = Integer.MAX_VALUE;
		// N번째 날에 대해 모든 무기의 최솟값을 구한다.
		for (int i = 0; i < M; i++) {
			min = Math.min(min, dp[N][i]);
		}
		
		System.out.println(min);

	}

}
