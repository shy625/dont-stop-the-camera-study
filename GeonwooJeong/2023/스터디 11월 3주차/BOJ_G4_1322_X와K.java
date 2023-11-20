import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G4_1322_X와K {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int X = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		// X를 이진수로 변환한 String
		String biX = Long.toBinaryString(X);
		// K를 이진수로 변환한 String
		String biK = Long.toBinaryString(K);
		
		long ans = 0;
		// 2를 몇번 제곱할건지 저장하는 변수
		int cnt = 0;
		// biK를 뒤에서부터 계산하기 위해 length 변수를 따로 선언한다.
		int length = biK.length()-1;
		
		// biX도 마찬가지로 뒤에서부터 계산한다.
		for (int i = biX.length()-1; i >= 0; i--) {
			char c1 = biX.charAt(i);
						
			// biX가 0일 때에만 계산한다.
			if(c1 == '0') {	
				char c2 = biK.charAt(length);
				
				// biK가 1이라면, 2^cnt 수 만큼 ans에 더해준다.
				if(c2 == '1') {
					ans += (long) Math.pow(2, cnt);
				}
				
				// biK를 1번 사용했으므로 length--
				length--;
			}
			
			// 자릿수를 하나 옮겨주기 위해 cnt++
			cnt++;
			
			// biK를 끝까지 사용했다면 종료한다.
			if(length == -1) break;
			
		}
		
		// biX를 다 탐색하고도 biK가 남았다면, 이후에도 계속 계산해준다.
		while(length >= 0) {
			char c = biK.charAt(length);
			
			if(c == '1') {
				ans += (long) Math.pow(2, cnt);
			}
			
			length--;
			cnt++;
		}
		
		System.out.println(ans);
		
	}

}
