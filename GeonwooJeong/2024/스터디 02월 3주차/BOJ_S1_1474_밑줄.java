import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S1_1474_밑줄 {
	
	// 사전 먼저오는 순서가 대문자 -> '_' -> 소문자 이므로,
	// 대문자면 앞에 밑줄을 적게 붙이는게 유리, 소문자면 많이 붙이는게 유리
	// ex) a_AB__abc > a__AB_abc
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		String [] arr = new String[N];
		
		// 문자열 길이의 합 저장할 변수
		int len = 0;
		// 문자열 중 소문자로 시작하는 문자열의 수
		int small = 0;
		// 문자열 중 대문자로 시작하는 문자열의 수
		int capital = 0;
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			arr[i] = str;
			len += str.length();
			
			// 맨 앞의 문자열 앞에는 밑줄을 긋지 않으므로 따로 카운팅하지 않는다.
			if(i == 0) continue;
			
			if(checkFirstLetter(str)) {
				small++;
			} else {
				capital++;
			}
			
		}
		
		// 몇개의 밑줄을 추가해야 하는지 계산한다.
		M -= len;
		
		// 모든 문자열 앞에 몇 개의 밑줄을 추가해야 하는지 계산한다.
		int cycle = M/(N-1);
		// 더 붙여야 하는 밑줄의 개수를 구한다.
		int remain = M%(N-1);
		
		// 소문자로 시작하는 문자열 앞에 붙일 밑줄의 개수
		int smallCnt = 0;
		
		// 더 붙여야 하는 밑줄의 개수가 소문자로 시작하는 문자열의 개수보다 크다면,
		// 소문자로 시작하는 모든 문자열 앞에 밑줄을 붙여야 한다.
		// 그리고 나서, 추가로 더 붙여야 하는(대문자 문자열 앞에) 밑줄의 개수를 구한다.
		if(remain >= small) {
			smallCnt = small;
			remain -= small;
		} else {
			smallCnt = remain;
			remain = 0;
		}
		
		// 대문자로 시작하는 문자열 앞에 밑줄을 붙이지 않고 skip 할 개수
		// 예를 들어, 대문자 문자열이 5개인데, 2개의 밑줄을 추가로 붙여야 한다면,
		// 가장 뒤에 나오는 2개의 대문자 문자열 앞에 밑줄을 붙이는게 유리하다
		// -> 앞의 3개의 문자열은 스킵한다.
		int capitalCnt = capital - remain;
		
		// 첫 번째 문자열 앞에는 밑줄이 필요 없으므로 sb에 담고 시작한다.
		sb.append(arr[0]);
		
		for (int i = 1; i < N; i++) {
			// 모든 문자열 앞에 cycle 만큼의 밑줄을 붙인다.
			for (int j = 0; j < cycle; j++) {
				sb.append('_');
			}
			
			String str = arr[i];
			boolean res = checkFirstLetter(str);
			// 소문자로 시작하고, smallCnt가 남아있다면 하나를 소비하고 밑줄을 추가한다.
			if(res && smallCnt > 0) {
				sb.append('_');
				smallCnt--;
			// 대문자로 시작하고,
			} else if(!res) {
				// capitalCnt가 남아있다면 밑줄을 추가하지 않는다.
				if(capitalCnt > 0) {
					capitalCnt--;	
				// 모두 사용했다면, 밑줄을 추가한다.
				} else {
					sb.append('_');
				}
			}
			
			// 문자열을 담는다.
			sb.append(str);
			
		}
		
		System.out.println(sb.toString());
		
	}

	private static boolean checkFirstLetter(String str) {
		char c = str.charAt(0);
		return c >= 'a' && c <= 'z';
	}

}
