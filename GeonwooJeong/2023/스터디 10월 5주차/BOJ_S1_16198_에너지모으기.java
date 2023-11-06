import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_S1_16198_에너지모으기 {
	static int N, max;
	
	// N의 범위가 3~10밖에 되지 않으므로 dfs + 백트래킹을 사용할 수 있다.
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		List<Integer> list = new ArrayList<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		
		dfs(list, 0);
		
		System.out.println(max);
		
	}

	private static void dfs(List<Integer> list, int sum) {
		// 첫 번째와 마지막 구슬만 남았을 때, sum 값을 가지고 max를 갱신해준다.
		if(list.size() == 2) {
			max = Math.max(max, sum);
			return;
		}
		
		// 첫 번째 구슬과 마지막 구슬은 선택할 수 없으므로 for문의 범위는 i ~ list.size()-1이 된다.
		for (int i = 1; i < list.size()-1; i++) {
			// 백트래킹을 하기 위해 i번째 구슬의 무게를 저장해놓는다.
			int n = list.get(i);
			// i-1번째 구슬과 i+1번째 구슬을 mul이라는 변수에 저장해놓는다.
			int mul = list.get(i-1) * list.get(i+1);
			// i번째 구슬을 제거한다.
			list.remove(i);
			// sum에 mul을 더해주고, 계속해서 에너지를 모은다.
			dfs(list, sum + mul);
			// 돌아오면, i번째 구슬을 원래 있던 자리에 집어넣는다.
			list.add(i, n);
		}
		
	}

}
