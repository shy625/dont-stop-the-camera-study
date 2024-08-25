import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_S1_1802_종이접기 {
	static String str;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			str = br.readLine();
			
			// 시작부터 끝까지 조건을 만족하는지 확인한다.
			// 조건을 만족하려면 가운데를 중심으로 대칭되는 숫자가 반대여야한다.
			if(fold(0, str.length()-1)) {
				sb.append("YES\n");
			} else {
				sb.append("NO\n");
			}
			
		}
		
		if(sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());

	}

	private static boolean fold(int start, int end) {
		// 재귀문의 끝에 도달한 것이므로 return true 해준다.
		if(start == end) return true;
		
		// 가운데를 중심으로 대칭점을 찾아야 하므로 가운데 점을 찾는다.
		int mid = (start + end) / 2;
		
		// 한 군데라도 조건을 만족하지 않으면 false를 리턴한다.
		for (int i = start; i < mid; i++) {
			if(str.charAt(i) == str.charAt(end-i)) {
				return false;
			}
		}
		
		// 만족한다면, 재귀 함수로 끝까지 확인해본다.
		return fold(start, mid-1) && fold(mid+1, end);
		
	}

}
