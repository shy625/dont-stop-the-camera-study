import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_G5_25427_DKSH를찾아라 {
	static int N;
	static String str;
	static long [][] sum;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		str = br.readLine();
		
		sum = new long[N+1][5];
		// 누적합 배열 초기화
		for (int i = 0; i <= N; i++) {
			sum[i][0] = 1;
		}
		
		check(1, 'D');
		check(2, 'K');
		check(3, 'S');
		check(4, 'H');
		
		System.out.println(sum[N][4]);

	}

	private static void check(int idx, char c) {
		for (int i = 1; i <= N; i++) {
			// 추가적으로 생기는 경우의수를 저장할 변수
			long add = 0;
			// 만약 현재 자릿수의 문자가 c와 같다면
			// c 이전의 문자(예를 들어 c가 K라면 D)가 i-1번 째 자릿수까지 몇 번 등장했는지 확인해서 더해준다.
			if(str.charAt(i-1) == c) {
				add = sum[i-1][idx-1];
			}
			// 기본적으로 누적합의 규칙을 따르고, add가 추가적으로 생길 경우에만 추가적으로 더해준다.
			sum[i][idx] = sum[i-1][idx] + add;
		}
		
	}

}
