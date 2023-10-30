import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_S2_5002_도어맨 {
	static int X, diff;
	static String str;
	static List<Character> list; 

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		X = Integer.parseInt(br.readLine());
		str = br.readLine();
		// 줄에 있는 사람들을 한 명씩 저장하기 위한 list
		list = new ArrayList<>();
		int ans = 0;
		
		// 줄에 있는 사람들을 한 명씩 list에 넣어준다.
		for (int i = 0; i < str.length(); i++) {
			list.add(str.charAt(i));
		}
		
		// 줄에 있는 사람을 모두 입장시킬 때 까지
		while(!list.isEmpty()) {
			// 첫 번째 사람을 입장시킬 수 있는지 먼저 확인한다.
			if(check(0)) {
				ans++;
			// 첫 번째 사람을 입장 못시키는데, 뒤에 사람이 더 없을 경우 break 해준다.
			} else if(list.size() == 1) {
				break;
			// 첫 번째 사람을 입장 못시키는데, 두 번째 사람이 있을 경우
			// 두 번째 사람을 입장시킬 수 있는지 확인한다.
			} else if(check(1)) {
				ans++;
			// 두 번째 사람도 입장시킬 수 없다면 종료한다.
			} else {
				break;
			}
			
		}
		
		System.out.println(ans);
		
	}

	private static boolean check(int idx) {
		// idx번째 사람이 남성이고, diff가 X보다 작다면 출입이 가능하다.
		if(list.get(idx) == 'M' && diff < X) {
			// diff를 +1 해주고, idx번째 사람을 출입시킨다.
			diff++;
			list.remove(idx);
			return true;
		// idx번째 사람이 여성이고, diff가 -X보다 크다면 출입이 가능하다.
		} else if(list.get(idx) == 'W' && diff > -X) {
			// diff를 -1 해주고, idx번째 사람을 출입시킨다.
			diff--;
			list.remove(idx);
			return true;
		}
		
		// 그 외의 경우에는 출입이 불가능하다.
		return false;
	}

}
