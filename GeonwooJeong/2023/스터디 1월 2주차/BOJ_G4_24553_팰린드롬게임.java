import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_G4_24553_팰린드롬게임 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());

		// N이 1~9일 경우 무조건 상윤이 승리
		// N이 10일 경우 무조건 승우 승리
		// N이 11일 경우 상윤이가 1개를 가져가면 10개가 남아 상윤이 승리
		// N이 19일 경우 상윤이가 9개를 가져가면 10개가 남아 상윤이 승리
		// N이 20일 경우 몇 개를 가져가더라도 승우 승리
		// N이 10의 배수라면 승우 승리, 아니라면 상윤이 승리
		for (int t = 1; t <= T; t++) {
			Long N = Long.parseLong(br.readLine());
			
			if(N % 10 == 0) {
				System.out.println(1);
			} else {
				System.out.println(0);
			}
			
		}
		
	}

}
