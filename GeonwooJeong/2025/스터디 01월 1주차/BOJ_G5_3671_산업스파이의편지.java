import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class BOJ_G5_3671_산업스파이의편지 {
	static String str;
	static Set<Integer> set;
	static char [] picks;
	static boolean [] v;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			str = br.readLine();
			set = new HashSet<>();
			// 숫자 1개 사용 ~ 숫자 모두 사용까지 모든 경우의 수를 구해본다.
			for (int i = 1; i <= str.length(); i++) {
				// 선택한 숫자를 저장할 배열
				picks = new char[i];
				// 숫자를 이미 선택했는지 여부를 저장할 배열
				v = new boolean[str.length()];
				// 순열을 사용해 모든 경우의 수를 구한다. 주어진 숫자들을 이용하여 i자리를 구한다.
				perm(i, 0);
			}
			
			int ans = 0;
			
			// 방금 구한 모든 수들을 소수 판별 해본다.
			for(int n : set) {
				if(isPrime(n)) ans++;
			}
			
			sb.append(ans+"\n");
			
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
		
	}
	private static boolean isPrime(int n) {
		if(n < 2) return false;
		
		for (int i = 2; i*i <= n; i++) {
			if(n % i == 0) return false;
		}
		
		return true;
	}
	
	private static void perm(int r, int depth) {
		if(r == depth) {
			String num = String.valueOf(picks);
			
			set.add(Integer.parseInt(num));
			
			return;
		}
		
		for (int i = 0; i < str.length(); i++) {
			if(v[i]) continue;
			
			// 백트래킹 사용
			v[i] = true;
			picks[depth] = str.charAt(i);
			perm(r, depth+1);
			
			v[i] = false;
		}
		
	}

}
