import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_10253_헨리 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int x = 0;
			
			// a: 5, b: 7, x1: 2 -> a: 3, b: 14, x2: 5 -> a: 1, b: 70, x3: 70 a가 1이 될 때 while문을 종료하면 된다.
			
			while(a != 1) {
				// x 값 갱신
				if(b % a == 0) {
					x = b/a;
				} else {
					x = b/a + 1;
				}
				
				// a 값 갱신(분모)
				a = a*x - b;
				// b 값 갱신(분자)
				b *= x;
				
				int tmp = gcd(a, b);
				a /= tmp;
				b /= tmp;
			}
			
			System.out.println(b);

		}
	}

	private static int gcd(int a, int b) {
		if(b == 0) {
			return a;
		} 
		
		return gcd(b, a % b);
	}

}
