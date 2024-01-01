import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BOJ_S1_12101_123더하기2 {
	static List<String> list;
	static int N;

	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in);
		N = scann.nextInt();
		int K = scann.nextInt();
		list = new ArrayList<>();
		
		// 백트래킹을 이용하여 모든 경우를 구해본다.
		dfs(0, "");
		
		// 사전 순으로 정렬한다.
		Collections.sort(list);
		
		// k번째 오는 식이 있는 경우
		if(list.size() >= K) {
			System.out.println(list.get(K-1));
		// k번째 오는 식이 없는 경우
		} else {
			System.out.println(-1);
		}
		
	}

	private static void dfs(int sum, String str) {
		// sum이 N보다 크거 같을 경우 더 이상 진행하지 않는다.
		if(sum >= N) {
			// 그 중에서, sum이 N과 같을 경우에는 list에 해당 식을 추가해준다.
			if(sum == N) {
				list.add(str.substring(0, str.length()-1));
			}
			return;
		}
		
		// 숫자는 1, 2, 3만 사용할 수 있다.
		for (int i = 1; i <= 3; i++) {
			// 사용한 숫자와 '+'를 str에 더해준다.
			str += i+"+";
			// 사용한 숫자만큼 sum에 더해준다.
			sum += i;
			// 식을 계속해서 쌓아나간다.
			dfs(sum, str);
			// 돌아온 뒤, 사용한 숫자만큼 sum에서 다시 빼준다.
			sum -= i;
			// 사용한 숫자와 '+' 만큼 str에서 다시 빼준다.
			str = str.substring(0, str.length()-2);
		}
		
	}

}
