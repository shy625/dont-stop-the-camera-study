

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2168_타일_위의_대각선 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        // x, y의 최대공약수가 1인 경우,
        // 대각선이 지나는 타일의 개수는 (x-1)+(y-1)+1이다.
        // 이때, x, y의 최대공약수가 1이 아니면, 
        // (x/gcd(x, y)) * (y/gcd(x, y)) 크기의 직사각형 gcd(x, y)개를 지나게 된다.
        // 따라서, 대각선이 지나는 타일의 개수는 gcd(x, y)*(x/gcd(x, y)-1+y/gcd(x, y)-1+1) 이고,
        // 이를 정리하면 x+y-gcd(x, y)가 된다.
        int gcd = gcd(x, y);
        System.out.println(x+y-gcd);
	}
	// 유클리드 호제법을 활용한, x와 y의 최대공약수를 구하는 함수
	private static int gcd(int x, int y) {
        return y == 0 ? x : gcd(y, x % y);
    }
}
