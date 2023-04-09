import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_G5_15482_한글LCS {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str1 = br.readLine();
		int leng1 = str1.length();
		
		String str2 = br.readLine();
		int leng2 = str2.length();
		
		int [][] dp = new int[leng1+1][leng2+1];
		
		for (int i = 0; i < leng1; i++) {
			for (int j = 0; j < leng2; j++) {
				if (str1.charAt(i) == str2.charAt(j)) {
					dp[i+1][j+1] = dp[i][j] + 1;
				} else {
					dp[i+1][j+1] = Math.max(dp[i+1][j], dp[i][j+1]);
				}
			}
		}
		
		System.out.println(dp[leng1][leng2]);

	}

}
