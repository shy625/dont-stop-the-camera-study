import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_G5_30646_최대합순서쌍의개수 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		// 원소들을 저장할 1차원 배열
		int [] nums = new int[N];
		// 누적 합을 저장할 1차원 배열
		long [] sum = new long[N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		// 누적합을 계산해준다.
		for (int i = 1; i <= N; i++) {
			sum[i] = sum[i-1] + nums[i-1];
		}
		
		long max = 0;
		int ans = 0;
		
		// 원소 N가 가장 처음에 나온 인덱스를 저장할 HashMap
		Map<Integer, Integer> map = new HashMap<>();
		
		for (int i = 0; i < N; i++) {
			int n = nums[i];
			
			// 원소 n이 이전에 이미 나왔다면 누적 합을 이용해 구간 합을 계산해준다.
			if(map.containsKey(n)) {
				int idx = map.get(n);
				long total = sum[i+1] - sum[idx];
				
				// 구간 합이 max보다 클 경우 갱신해준다.
				if(total > max) {
					max = total;
					ans = 1;
				// 같을 경우 ans만 +1 해준다.
				} else if(total == max) {
					ans++;
				}
			// 원소 n이 처음 발견한 것이라면
			} else {
				// map에 n의 인덱스를 저장해준다.
				map.put(n, i);
				
				// n 한개만 가지고 순서쌍을 만들 수 있으므로 max를 갱신하는 작업을 해준다.
				if(n > max) {
					max = n;
					ans = 1;
				} else if(n == max) {
					ans++;
				}
			}
		}
		
		System.out.println(max+" "+ans);
		
		
	}

}
