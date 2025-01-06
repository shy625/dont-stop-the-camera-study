import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S1_1421_나무꾼이다솜 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		
		// 나무의 길이를 저장할 배열
		int [] arr = new int[N];
		// 가장 긴 나무를 저장할 변수
		int max = -1;
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			max = Math.max(max, arr[i]);
		}
		
		long ans = -1;
		
		// 단위를 1일 때부터 max일 때 까지 모두 계산해본다.
		for (int i = 1; i <= max; i++) {
			// 단위가 i일 때 얻을 수 있는 돈
			long sum = 0;
			for (int j = 0; j < N; j++) {
				// 잘라야하는 횟수를 저장할 변수
				int cnt = 0;
				
				// j번째 나무가 i보다 클 경우에만 계산해본다.
				if(arr[j] >= i) {
					cnt = arr[j]/i;
					// 나무를 잘랐을 때 딱 나누어 떨어지면 1번 덜 잘라도 된다.
					if(arr[j] % i == 0) cnt--;
					
					// j번째 나무를 잘랐을 때 비용을 계산하고, 0보다 클 때에만 sum에 더해준다.
					int cost = W*i * (arr[j]/i) - cnt*C;
					
					if(cost > 0) sum += cost;
				}
			}
			
			// 단위 i일 때 얻을 수 있는 돈을 갱신한다.
			ans = Math.max(ans, sum);
			
		}
		
		System.out.println(ans);
		
	}

}
