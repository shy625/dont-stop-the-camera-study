import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_G5_11985_오렌지출하 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		// 오렌지를 포장했을 때 최소 비용을 저장할 배열
		long [] dp = new long[N+1];
		// 오렌지의 크기를 저장할 배열
		int [] orange = new int[N+1];
		
		// 최소 비용을 갱신해나가기 위해 처음엔 MAX 값으로 설정해놓는다.
		Arrays.fill(dp, Long.MAX_VALUE);
		// 오렌지 0개를 포장할 때에는 0원이 든다. (초기값 설정)
		dp[0] = 0;
		
		for (int i = 1; i <= N; i++) {
			orange[i] = Integer.parseInt(br.readLine());
		}
		
		for (int i = 1; i <= N; i++) {
			// 포장에 가장 큰 오렌지 크기(max), 가장 작은 오렌지 크기(min)의 정보가 필요하므로
			// 1~M개 단위로 포장했을 때 마다 max와 min을 갱신해가며 포장 비용을 계산해보고,
			// 그 과정에서 가장 비용이 적은 값으로 dp[i]를 갱신해준다.
			int max = orange[i];
			int min = orange[i];
			for (int j = 1; j <= M; j++) {
				// 지금까지 포장한 오렌지 개수보다 한 상자에 넣으려는 오렌지 값이 크다면 넘어간다.
				if(i < j) break;
				// 가장 큰 오렌지 크기 갱신
				max = Math.max(max, orange[i-j+1]);
				// 가장 작은 오렌지 크기 갱신
				min = Math.min(min, orange[i-j+1]);
				// i번째 오렌지까지 포장했을 때의 최소 비용 갱신
				dp[i] = Math.min(dp[i], dp[i-j] + K + (long) j * (max - min));
			}
		}
		
		System.out.println(dp[N]);

	}

}
