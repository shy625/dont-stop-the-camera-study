import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_16938_캠프준비 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		
		int [] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 비트마스킹 알고리즘을 사용하기 위해 탐색 범위 지정에 필요한 2^N을 계산한다.
		int M = (int) Math.pow(2, N);
		int ans = 0;
		
		// 1~M-1까지의 숫자를 탐색한다 (2진수로 사용)
		for (int i = 1; i < M; i++) {
			// 문제들의 난이도 총합을 구하기 위한 변수
			int sum = 0;
			// 문제의 최소, 최대 난이도를 구하기 위한 변수
			int min = 1000001;
			int max = 0;
			
			// 비트마스킹 비교를 통해 j번째 문제를 선택했는지 확인하고, 선택했다면 sum에 더하고 max와 min값을 갱신해준다.
			for (int j = 0; j < N; j++) {
				if((i & (1 << j)) != 0) {
					int n = arr[j];
					sum += n;
					min = Math.min(min, n);
					max = Math.max(max, n);
				}
			}
			
			// 문제 난이도의 총합은 L과 R 사이의 값이어야 한다.
			if(sum < L || sum > R) continue;
			// 제일 쉬운 문제와 어려운 문제의 난이도 차이는 X보다 크거나 같아야 한다.
			if(max - min < X) continue;
			ans++;
		}
		
		System.out.println(ans);
		
	}

}
