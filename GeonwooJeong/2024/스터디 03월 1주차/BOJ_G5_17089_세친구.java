import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_G5_17089_세친구 {
	static int N, min;
	static List<ArrayList<Integer>> list;
	static boolean [] v;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		// 친구 관계를 저장하기 위해 이중 ArrayList를 사용함
		list = new ArrayList<ArrayList<Integer>>();
		v = new boolean[N+1];
		
		// list 초기화 작업
		for (int i = 0; i <= N; i++) {
			list.add(new ArrayList<Integer>());
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list.get(a).add(b);
			list.get(b).add(a);
		}
		
		// 최솟값을 구하는 것이므로 초기 값을 MAX_VALUE로 해준다.
		min = Integer.MAX_VALUE;
		
		// 1번째 사람부터 N번째 사람까지 모두 체크한다.
		for (int i = 1; i <= N; i++) {
			find(i);
		}
		
		if(min == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(min);
		}
		
	}

	private static void find(int start) {
		// start의 친구인 mid의 친구들에 대해 모두 확인한다.
		for (int mid : list.get(start)) {
			if(v[mid]) continue;
			// mid의 친구인 end에 대해 확인한다.
			for (int end : list.get(mid)) {
				if(v[end]) continue;
				// 만약 end가 start와 동일인물이라면 넘어간다.
				if(start == end) continue;
				// 만약 end와 start가 친구가 아니라면 넘어간다. (문제 조건 만족하지 못함)
				if(list.get(end).indexOf(start) == -1) continue;
						
				// A의 친구 수, B의 친구 수, C의 친구 수를 더하고,
				// 6을 빼준 값을 min과 비교하여 갱신한다.
				int size = list.get(start).size() + list.get(mid).size() + list.get(end).size() - 6;
				min = Math.min(min, size);
						
			}
			
		}
		
		v[start] = true;
		
	}

}
