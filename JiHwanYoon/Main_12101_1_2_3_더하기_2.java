

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_12101_1_2_3_더하기_2 {
	static ArrayList<String> arr;
	static int n;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		// 더해서 n이 되는 식을 저장하는 ArrayList
		arr = new ArrayList<>();
		// 브루트포스 알고리즘을 활용한다.
		perm(0, 0, "");
		// 정렬을 통해 k번째 식을 구한다.
		Collections.sort(arr); 
		if (k > arr.size()) {
			System.out.println(-1);
		} else {
			String nums = arr.get(k-1);
			StringBuilder sb = new StringBuilder();
			sb.append(nums.charAt(0));
			for (int i = 1; i < nums.length(); i++) {
				sb.append('+').append(nums.charAt(i));
			}
			System.out.println(sb.toString());
		}
	}
	// 브루트포스 알고리즘을 통해 합이 n이 되는 모든 식을 구하는 함수
	private static void perm(int cnt, int sum, String s) {
		if (sum == n) {
			arr.add(s);
			return;
		}
		for (int i = 1; i <= 3; i++) {
			if (sum + i <= n) perm(cnt+1, sum+i, s+i);
		}
	}
}
