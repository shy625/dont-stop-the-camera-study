import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ_G5_14369_전화번호수수께끼 {
	static int N;
	static String [] strs = {"ZERO","ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE"};
	static int [] cnt;
	static boolean flag;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			String str = br.readLine();
			
			N = str.length();
			// 답을 찾았을 때 dfs를 빠져나오기 위한 flag
			flag = false;
			// 각 알파벳이 몇 번 등장했는지 저장할 배열
			cnt = new int[26];
			// 주어진 문자열의 알파벳들을 cnt에 추가한다.
			for (int i = 0; i < N; i++) {
				char c = str.charAt(i);
				cnt[c - 'A']++;
			}
			
			sb.append("Case #"+t+": ");
			
			find(new ArrayList<Integer>(), 0, 0);
			
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
		
	}

	private static void find(ArrayList<Integer> list, int depth, int start) {
		// 답을 찾았다면 dfs를 빠져나온다.
		if(flag) return;
		
		// 모든 숫자를 찾았다면 sb에 추가하고 종료한다.
		if(depth == N) {
			// 오름차순으로 정렬한다.
			Collections.sort(list);
			
			for(int n : list) {
				sb.append(n);
			}
			
			sb.append("\n");
			return;
		}
		
		for (int i = start; i <= 9; i++) {
			String str = strs[i];
			
			// 남아있는 str에 숫자 i가 존재하지 않으면 넘어간다.
			if(!check(str)) continue;
			
			// 숫자 i의 알파벳들만큼 cnt에서 빼준다.
			for (int j = 0; j < str.length(); j++) {
				int n = str.charAt(j) - 'A';
				cnt[n]--;
			}
			// 숫자 i를 리스트에 넣어준다.
			list.add(i);
			
			// 남은 str에 대해 나머지 숫자를 찾는다.
			find(list, depth+str.length(), i);
			
			// 다시 돌아와서 이전에 했던 작업들을 거꾸로 한다.(백트래킹)
			list.remove(list.size()-1);
			for (int j = 0; j < str.length(); j++) {
				int n = str.charAt(j) - 'A';
				cnt[n]++;
			}
		}
		
	}

	// 남아있는 문자열에서 현재 숫자 str이 존재하는지 확인하는 함수
	private static boolean check(String str) {
		for (int i = 0; i < str.length(); i++) {
			int n = str.charAt(i) - 'A';
			if(cnt[n] <= 0) return false;
		}
		
		return true;
		
	}

}
