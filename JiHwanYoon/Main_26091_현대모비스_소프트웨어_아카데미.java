

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_26091_현대모비스_소프트웨어_아카데미 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		// greedy algorithm과 투 포인터를 활용
		// 우선, 능력치를 오름차순으로 정렬하고, 가장 능력치가 낮은 사람과 높은 사람을 짝지어본다.
		// 만약 능력치의 합이 최소 능력치 기준 이상이면 팀을 만든 뒤 다음으로 능력치가 낮은(높은) 사람으로 넘어가고,
		// 그렇지 않으면 가장 능력치가 낮은 사람 쪽을 다음으로 능력치가 낮은 사람으로 넘긴다.
		// 이를 능력치가 낮은 사람이 능력치가 높은 사람보다 능력치가 더 높아질 때까지(역전될 때까지) 반복한다.
		Arrays.sort(arr);
		int l = 0;
		int r = arr.length-1;
		int cnt = 0;
		while (l < r) {
			if (arr[l]+arr[r] >= M) {
				cnt++;
				r--;
			}
			l++;
		}
		System.out.println(cnt);
	}

}
