import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_G5_1419_등차수열의합 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int l = Integer.parseInt(br.readLine());
		int r = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());
		
		int ans = 0;
		int start = 0;
		
		// 2n + d, 1+2가 최소이므로 3이상인 모든 수
		if(k == 2) {
			start = Math.max(l, 3);
			ans = r - start + 1;
		// 3(n + d), 1+2+3이 최소이므로 6이상인 3의 배수
		} else if(k == 3) {
			start = Math.max(l, 6);
			for (int i = start; i <= r; i++) {
				if(i % 3 == 0) ans++;
			}
		// 2(2n + 3d), 1+2+3+4가 최소이므로 10이상인 짝수,
		// 12를 제외한 숫자는 식으로 모두 표현할 수 있다. 그러므로 12만 제외해준다.
		} else if(k == 4) {
			start = Math.max(l, 10);
			for (int i = start; i <= r; i++) {
				if(i % 2 == 0 && i != 12) ans++;
			}
		// 5(n + 2d), 1+2+3+4+5가 최소이므로 15이상인 5의 배수
		} else if(k == 5) {
			start = Math.max(l, 15);
			for (int i = start; i <= r; i++) {
				if(i % 5 == 0) ans++;
			}
		}

		System.out.println(ans);
		
	}

}
