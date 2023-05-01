import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_G4_13019_A를B로 {
	// 1. 문자열의 각 알파벳 숫자를 비교해서 둘이 같지 않다면 -1을 출력.
	// 2. str1 문자열의 가장 마지막 인덱스부터 시작하여 한칸씩 앞으로 가면서 비교를 진행한다. str2 문자열도 맨 뒤부터 시작한다.
	// 3. c1과 c2가 같으면 cnt를 ++해주고, 다르다면 같은 문자가 나올 때 까지 str1의 인덱스를 앞으로 1칸씩 이동시키며 탐색해준다. (c2는 고정)
	// 4. 인덱스를 이동시키다가 c2와 맞는 c1을 찾는다면 cnt를 ++해주고, 3~4번 과정을 반복한다.
	// 5. str1의 인덱스가 마지막에서 0으로 갈 때 까지 같은 문자 외의 다른 문자는 모두 옮겨져야 한다.
	// 6. 문자열 바꾸는 총 횟수 = 문자열 전체 길이 - cnt
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cnt = 0;
		int [] check1 = new int[26];
		int [] check2 = new int[26];
		
		String str1 = br.readLine();
		String str2 = br.readLine();
		
		int length = str1.length();
		
		for (int i = 0; i < length; i++) {
			char c1 = str1.charAt(i);
			char c2 = str2.charAt(i);
			check1[c1-'A']++;
			check2[c2-'A']++;
		}
		
		for (int i = 0; i < 26; i++) {
			if(check1[i] != check2[i]) {
				System.out.println(-1);
				System.exit(0);
			}
		}
		
		for (int i = length-1; i >= 0; i--) {
			char c1 = str1.charAt(i);
			char c2 = str2.charAt(length-1-cnt);
			if(c1 == c2) {
				cnt++;
			}
		}
		
		System.out.println(length - cnt);

	}

}
