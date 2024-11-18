import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S1_25683_행렬곱셈순서4 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int [][] arr = new int[N][2];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		// 연산은 크게 두 가지 방법으로 가능하다.
		// 첫번째는, 앞에서부터 계산하는 방법, 두번째는 뒤에서부터 계산하는 방법이다.
		// 전자의 경우는 제일 큰 숫자인 arr[0][0]을 계속해서 곱하기 때문에
		// 제일 작은 숫자인 arr[N-1][1]을 계속해서 곱하는 후자의 경우에 비해 항상 연산 횟수가 많다.
		// 그러므로, 뒤에서부터 계산하는 방법의 경우의 수를 구하면 된다.
		long ans = 0;
		int tail = arr[N-1][1];
		
		for (int i = N-1; i >= 1; i--) {
			// 10000^3이 int의 범위를 벗어날 수 있으므로 long으로 형변환을 해준 후 계산한다.
			ans += (long)arr[i-1][0] * arr[i-1][1] * tail;
		}
		
		System.out.println(ans);
		
	}

}
