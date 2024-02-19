import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_G5_9251_LCS {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str1 = br.readLine();
		String str2 = br.readLine();
		
		int l1 = str1.length();
		int l2 = str2.length();
		
		int [][] dp = new int[l1+1][l2+1];
		
		// i는 str1의 글자수를 의미, j는 str2의 글자수를 의미
		// dp[i][j]는 str1(i글자)와 str2(j글자)의 LCS를 의미한다.
		for (int i = 1; i <= l1; i++) {
			for (int j = 1; j <= l2 ; j++) {
				char c1 = str1.charAt(i-1);
				char c2 = str2.charAt(j-1);
				// str1의 i번째 문자와 str2의 j번째 문자가 같을 때에는 +1 해준다.
				if(c1 == c2) {
					dp[i][j] = dp[i-1][j-1] + 1;
				// 그 외의 경우에는 왼쪽과 위쪽 중 더 높은 값을 가져온다.
				} else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		
		System.out.println(dp[l1][l2]);
		
	}

}
