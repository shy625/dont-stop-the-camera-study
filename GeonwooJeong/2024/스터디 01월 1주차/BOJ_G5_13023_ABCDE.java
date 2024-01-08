import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_G5_13023_ABCDE {
	// 친구 관계를 저장할 인접 리스트
	static ArrayList<ArrayList<Integer>> list;
	// 방문 체크를 위한 boolean 배열
	static boolean [] v;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			list.add(new ArrayList<>());
		}
		v = new boolean[N];
		
		// 친구 관계를 인접 리스트에 정리해준다.
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list.get(a).add(b);
			list.get(b).add(a);
		}
		
		// 0번부터 N-1번까지 모두 확인해본다.
		for (int i = 0; i < N; i++) {
			dfs(i, 0);
		}
		
		// 모두 확인했음에도 불구하고 지문과 같은 친구 관계가 존재하지 않으면 0을 출력한다.
		System.out.println(0);
		
	}

	private static void dfs(int idx, int cnt) {
		if(cnt == 4) {
			// 지문과 같이 5명의 친구 관계가 확인되면 1을 출력하고 종료한다.
			System.out.println(1);
			System.exit(0);
		}
		
		// idx번째 사람을 방문 처리 해준다.
		v[idx] = true;
		// idx번째 사람의 친구 관계를 모두 확인해봐서, 하나씩 체크해본다.
		for(int n : list.get(idx)) {
			// 이미 방문한 친구는 넘어간다.
			if(!v[n]) {
				// 방문하지 않았다면 n번째 친구부터 다시 체크를 해본다.
				dfs(n, cnt+1);
			}
		}
		// 모두 확인한 뒤에, idx번째 사람의 방문 처리를 취소한다.
		v[idx] = false;
		
	}

}
