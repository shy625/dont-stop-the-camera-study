import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_G5_2195_문자열복사 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String S = br.readLine();
		String P = br.readLine();
		int ans = 0;
		// P가 총 몇글자인지 저장할 변수
		int l = P.length();
		// 몇 번째 글자부터 확인을 할지 저장할 변수
		int s = 0;
		
		while(s < l) {
			StringBuilder sb = new StringBuilder();
			// P 문자열의 s번째부터 마지막 글자까지 sb에 넣고 비교한다.
			sb.append(P.substring(s, l));
			
			for (int i = 0; i < l-s; i++) {
				// P 문자열의 s번째부터 (l-s-i)번째 글자까지의 문자열이 S에 포함되어 있는지 확인한다.
				if(S.indexOf(sb.toString()) != -1) {
					// 포함되어 있다면 다음 탐색을 위해 s의 값을 변경하고, for문을 탈출하여 다음 탐색을 준비한다.
					s += (l-s-i);
					break;
				}
				// 포함되어있지 않다면 탐색 범위를 뒤에서부터 1씩 줄여나간다.
				sb.setLength(sb.length()-1);
			}
			
			ans++;
			
		}
		
		System.out.println(ans);
		
	}

}
