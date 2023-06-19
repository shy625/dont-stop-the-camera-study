import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_G4_2643_색종이올려놓기 {
	
	static class Paper implements Comparable<Paper> {
		int r;
		int c;
		
		public Paper(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public int compareTo(Paper o) {
			if(this.r != o.r) {
				return Integer.compare(this.r, o.r);
			} else {
				return Integer.compare(this.c, o.c);
			}
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		Paper [] arr = new Paper[N];
		int [] dp = new int[N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			// c가 더 큰 경우 색종이를 90도 돌려서 저장
			if(r < c) {
				arr[i] = new Paper(c, r);
			} else {
				arr[i] = new Paper(r, c);
			}
		}
		
		// 가장 큰 색종이가 앞으로 오도록 정렬
		Arrays.sort(arr, Collections.reverseOrder());
		
		// 아무 색종이를 올려놓지 않아도 기본적으로 자기 자신은 1장으로 취급한다.
		for (int i = 0; i < N; i++) {
			dp[i] = 1;
		}
		
		for (int i = 0; i < N; i++) {
			// i번째 색종이까지 최대 몇장이 올라갈 수 있는지 계속해서 계산하여 dp 배열에 저장한다.
			for (int j = 0; j < i; j++) {
				if(arr[i].r <= arr[j].r && arr[i].c <= arr[j].c) {
					dp[i] = Math.max(dp[i], dp[j]+1);
				}
			}
		}
		
		int ans = 0;
		for (int i = 0; i < N; i++) {
			ans = Math.max(ans, dp[i]);
		}
		
		System.out.println(ans);
		
	}

}
