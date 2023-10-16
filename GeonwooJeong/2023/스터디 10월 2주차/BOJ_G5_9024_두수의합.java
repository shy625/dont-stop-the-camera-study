import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_G5_9024_두수의합 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			int [] arr = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(arr);
			
			// 투포인터 알고리즘을 사용하기 위해 start, end 변수 초기화
			int start = 0;
			int end = N-1;
			// K에 가장 가까운 수를 저장할 min 변수
			int min = Integer.MAX_VALUE;
			// K에 가장 가까운 조합이 몇개인지를 저장할 ans 변수
			int ans = 0;
			
			while(start < end) {
				int sum = arr[start] + arr[end];
				// 두 수와 K의 차이가 얼마인지 계산한다.
				int gap = Math.abs(sum - K);
				// 이전의 min보다 더 차이가 적다면, ans를 1로 초기화하고 min 값을 갱신해준다.
				if(gap < min) {
					ans = 1;
					min = gap;
				// 이전의 min과 차이 같다면, ans를 1 더해준다.
				} else if(gap == min) {
					ans++;
				}
				
				// start를 +할지 end를 -할지 조정해준다.
				if(sum <= K) {
					start++;
				} else {
					end--;
				}
				
			}
			
			sb.append(ans+"\n");
			
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
		
	}

}
