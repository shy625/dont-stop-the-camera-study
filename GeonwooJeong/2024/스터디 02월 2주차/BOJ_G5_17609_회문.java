import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_G5_17609_회문 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());

		testcase:
			for (int t = 1; t <= T; t++) {
				String str = br.readLine();
				
				// 투포인터를 사용하기 위해 startIdx, lastIdx를 사용한다.
				int startIdx = 0;
				int lastIdx = str.length()-1;
				
				while(startIdx < lastIdx) {
					char c1 = str.charAt(startIdx);
					char c2 = str.charAt(lastIdx);
					
					if(c1 == c2) {
						startIdx++;
						lastIdx--;
					} else {
						// sb1는 str에서 c1을 제거, sb2는 str에서 c2을 제거해서 담는다.
						StringBuilder sb1 = new StringBuilder();
						StringBuilder sb2 = new StringBuilder();
						sb1.append(str.substring(0, startIdx)).append(str.substring(startIdx+1));
						sb2.append(str.substring(0, lastIdx)).append(str.substring(lastIdx+1));
						
						// 만약 sb1과 sb1의 reverse가 같다면(문자 하나를 제거했을 때 회문이라면)
						// 또는 sb2와 sb2의 reverse가 같다면(문자 하나를 제거했을 때 회문이라면)
						// 1을 출력한다.
						if(sb1.toString().equals(sb1.reverse().toString()) || sb2.toString().equals(sb2.reverse().toString())) {
							sb.append(1).append('\n');
							continue testcase;
						// 만약 문자 1개를 제거했음에도 회문이 안된다면 2를 출력한다.
						} else {
							sb.append(2).append('\n');
							continue testcase;
						}
					}
				
				}
				
				// 문자를 제거하지 않아도 회문이라면 0을 출력한다.
				sb.append(0).append('\n');
			}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
		
	}

}
