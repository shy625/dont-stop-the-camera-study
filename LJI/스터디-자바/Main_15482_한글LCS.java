import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_15482_한글LCS {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    String a = br.readLine();
	    String b = br.readLine();
	    
	    int aLen = a.length();
	    int bLen = b.length();
	    
	    int[][] dp = new int[aLen+1][bLen+1];
	    for (int i = 0; i < aLen; i++) {
	      for (int j = 0; j < bLen; j++) {
	        if (a.charAt(i) == b.charAt(j)) {
	          dp[i+1][j+1] = dp[i][j] + 1;
	        } else {
	        	dp[i+1][j+1] = Math.max(dp[i+1][j], dp[i][j+1]);
	        }
	      }
	    }
	    System.out.print(dp[aLen][bLen]);

	}

}
