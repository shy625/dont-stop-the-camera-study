import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_17088_등차수열변환 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		
		int N = Integer.parseInt(br.readLine());
		
		// N이 1일 경우, 아래의 코드를 이용할 수 없고, 답도 0으로 고정이기 때문에 출력 후 종료한다.
		if(N == 1) {
			System.out.println(0);
			System.exit(0);
		}
		
		// 수열을 저장할 배열
		int [] arr = new int[N];
		// 최소 연산 횟수를 저장할 변수
		int ans = Integer.MAX_VALUE;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 맨 앞과 맨 뒤의 수에 대해 +1 할 것인지, -1 할 것인지, 아무것도 안할건지 3가지 경우에 대해 브루트포스 사용
		// i : 맨 앞의 수에 연산, j : 맨 뒤의 수에 연산. 총 경우의 수 6가지
		for (int i = -1; i <= 1; i++) {
			outer:
				for (int j = -1; j <= 1; j++) {
					int first = arr[0] + i;
					int last = arr[N-1] + j;
					// 등차수열의 등차를 구한다.
					int d = (last - first) / (N-1);
					
					// 등차를 (N-1)로 나누었을 때, 딱 나누어 떨어지지 않으면 등차수열이 될 수 없다.
					if(d * (N-1) != (last - first)) continue;
					
					// 연산 횟수를 저장할 변수
					int cnt = Math.abs(i) + Math.abs(j);
					
					// 1번 째 숫자부터 N-2번 째 숫자까지 확인해본다.
					for (int k = 1; k < N-1; k++) {
						// 입력으로 받은 k번째 숫자이다.
						int n = arr[k];
						// 등차수열을 만족하기 위해 되어야 할 숫자이다.
						int target = first + (k * d);
						// 연산을 하지 않아도 만족할 경우 넘어간다.
						if(n == target) continue;
						// n에 +1이나, -1을 했을 때 만족하는 경우 연산 횟수를 +1 해주고 넘어간다.
						else if(n+1 == target || n-1 == target) {
							cnt++;
						// 그 외의 경우. 즉, 연산을 해도 등차수열을 만족하지 못하는 경우에는 확인을 중단하고 outer로 돌아간다.
						} else {
							continue outer;
						}
					}
					
					// 최소 연산 횟수를 갱신한다.
					ans = Math.min(ans, cnt);
					
				}
			
		}
		
		if(ans == Integer.MAX_VALUE) {
			ans = -1;
		}
		
		System.out.println(ans);
		
		
	}

}
