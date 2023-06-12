import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G4_13397_구간나누기2 {
	
	static int N, M;
	static int [] arr;
	static int max;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, arr[i]);
		}
		
		System.out.println(binary());
		
	}

	private static int binary() {
		int left = 0;
		int right = max;
		int ans = right;
		
		while(left <= right) {
			int mid = (left + right) / 2;
			if(check(mid)) {
				ans = Math.min(ans, mid);
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}

		return ans;
	}

	private static boolean check(int mid) {
		int cnt = 1;
		int min2 = 10001;
		int max2 = 0;
		
		for (int i = 0; i < N; i++) {
			if(arr[i] < min2) min2 = arr[i];	
			if(arr[i] > max2) max2 = arr[i];
			
			if(max2 - min2 > mid) {
				cnt++;
				min2 = arr[i];
				max2 = arr[i];
			}
			
		}
		
		return cnt <= M;
	}

}
