

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2661_좋은_수열 {
	static int N;	
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		// 브루트포스 알고리즘을 통해 N자리 좋은 수열 중 가장 작은 수를 찾는다.
		simulate("");
	}

	private static void simulate(String str) {
		// 최초로 찾은 N자리 좋은 수열 반환
		if (str.length() == N) {
			System.out.println(str);
			System.exit(0);
		}
		// 1부터 3 중 하나의 숫자를 뒤에 붙여보고, 좋은 수열인 경우 뒤에 숫자를 더 붙인다.
		for (int i = 1; i <= 3; i++) {
            if (check(str+i)) simulate(str+i);
        }
	}
	// 좋은 수열인지 확인하는 함수
	// 맨 뒤에 숫자가 붙었을 때 그 숫자로 인해 나쁜 수열이 되는지만 확인
	private static boolean check(String str) {
		for (int i = 1; i <= str.length()/2; i++) {
            String front = str.substring(str.length()-i*2, str.length()-i);
            String back = str.substring(str.length()-i, str.length());
            if(front.equals(back)) return false;
        }
        return true;
	}

}
