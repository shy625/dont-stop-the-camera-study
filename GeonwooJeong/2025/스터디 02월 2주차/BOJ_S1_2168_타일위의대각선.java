import java.util.Scanner;

public class BOJ_S1_2168_타일위의대각선 {

	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in);
		int x = scann.nextInt();
		int y = scann.nextInt();
		
		// x가 항상 큰 수가 되도록 유지한다. (유클리드 호제법 사용 위함)
		if(x < y) {
			int tmp = x;
			x = y;
			y = tmp;
		}
		
		// x*y 직사각형에서 반복되는 패턴이 몇개 나오는지 계산한다. (두 수의 최대 공약수만큼 등장)
		int pattern = getGCD(x, y);
		// 한 패턴에서의 x길이를 구한다.
		int px = x/pattern;
		// 한 패턴에서의 y길이를 구한다.
		int py = y/pattern;
		
		// 한 패턴에서 대각선이 그어지는 정사각형의 수를 구한다.
		int cnt = px+py-1;
		int ans = cnt*pattern;
		
		System.out.println(ans);

	}
	
	// 유클리드 호제법을 이용한 최대공약수 구하기
	private static int getGCD(int x, int y) {
		if(y == 0) return x;
		return getGCD(y, x % y);
	}

}
