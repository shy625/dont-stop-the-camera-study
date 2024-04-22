import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class BOJ_G5_16500_문자열판별 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		
		String origin = br.readLine();
		int N = Integer.parseInt(br.readLine());
		
		// 뒤에서부터 계산해서, n번 째 글자까지 완성할 수 있는지 여부를 저장할 dp 배열
		int [] dp = new int[origin.length()];
		// 중복 제거, contains() 메소드를 사용하기 위해 set 사용
		Set<String> set = new HashSet<String>();
		
		for (int i = 0; i < N; i++) {
			set.add(br.readLine());
		}
		
		// 맨 뒷 글자부터 계산한다.
		// i는 확인할 부분문자열의 시작 글자이고, j는 확인할 부분문자열의 마지막 글자(+1)이다.
		for (int i = origin.length()-1; i >= 0; i--) {
			for (int j = i; j < origin.length(); j++) {
				// dp[j]가 0이면. 즉, (j~origin 끝 글자)까지의 부분 문자열이 체크되지 않았다면 넘어간다.
				if(dp[j] == 0) continue;
				String str = origin.substring(i, j);
				// (i~j)의 부분 문자열이 set에 존재한다면, (i~origin 끝 글자)까지 완성이 가능하기 때문에,
				// dp[i]를 1로 바꿔준다.
				if(set.contains(str)) dp[i] = 1;
			}
			
			// (i~origin 끝 글자)의 부분 문자열이 set에 존재한다면, dp[i]를 1로 바꿔준다.
			String str = origin.substring(i);
			if(set.contains(str)) dp[i] = 1;
		}
		
		// (첫번째~마지막 글자)까지의 완성 여부를 확인하기 위해 dp[0]을 확인한다.
		System.out.println(dp[0]);
		
	}

}
