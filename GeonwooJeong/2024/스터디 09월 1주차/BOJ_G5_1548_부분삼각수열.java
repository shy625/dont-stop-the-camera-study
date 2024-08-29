import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_G5_1548_부분삼각수열 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		// N이 2보다 작으면 N이 정답이 된다.
		if(N <= 2) {
			System.out.println(N);
			System.exit(0);
		}
		
		int [] arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 숫자들을 정렬한다.
		Arrays.sort(arr);
		
		// 가장 긴 부분 삼각 수열의 길이를 저장할 변수
		int max = 0;
		// 0번부터 N-3번까지 기준점을 잡는다.
		for (int i = 0; i < N-2; i++) {
			// 해당 i번째 수와 i+1번째 수를 선택한다.
			int sum = arr[i] + arr[i+1];
			for (int j = N-1; j > i; j--) {
				// N-1~i+1번째 숫자 중에서 조건을 만족하는 숫자를 찾는다.
				if(sum > arr[j]) {
					// 조건을 만족하면 해당 길이를 max에 저장하고 i+1을 기준점으로 잡고 다시 탐색한다.
					max = Math.max(max, j-i+1);
					break;
				}
			}
		}
		
		System.out.println(max);	
		
	}

}
