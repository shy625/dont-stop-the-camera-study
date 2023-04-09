package temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S3_14501_퇴사 {
	
	static int N;
	static int max = -1;
	static int [] arr, value;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N+1];
		value = new int[N+1];
		
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i] = Integer.parseInt(st.nextToken());
			value[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(1, 0);
		
		System.out.println(max);

	}

	private static void dfs(int day, int sum) {
		if(day > N) {
			max = Math.max(max, sum);
			return;
		}
		
		int now = arr[day];
		int val = value[day];
		
		if(now + day <= N+1) {
			dfs(day+now, sum+val);
		}
		
		dfs(day+1, sum);
		
	}

}
