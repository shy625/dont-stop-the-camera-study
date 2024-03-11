import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G4_12851_숨바꼭질2 {
	
	static int K, min, ans, time = 1;
	static Map<Integer, Integer> map;
	static boolean [] v;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		// 1->2->4->8->9->10와 1->2->3->4->5->10은 둘다 5번만에 10을 가지만 서로 다른 경우의 수로 쳐야한다.
		// 그러므로 위치 X에 같은 횟수만에 올 수 있는 경우의수를 저장하기 위해 map을 따로 사용했다.
		map = new HashMap<Integer, Integer>();
		
		// 수빈이와 동생의 위치가 같으면 출력하고 끝낸다.
		if(N == K) {
			System.out.println(0);
			System.out.println(1);
			System.exit(0);
		} else {
		// 이외의 경우에는 BFS를 사용하여 구한다.
			bfs(N);			
		}
		
		// 최단시간 min 출력
		System.out.println(min);
		// 최단 시간의 경우의 수 ans 출력
		System.out.println(ans);
		
	}

	private static void bfs(int start) {
		// bfs 이므로 기본적으로 visited 배열과 Queue를 사용한다.
		v = new boolean[100001];
		Queue<int []> q = new ArrayDeque<int []>();
		
		v[start] = true;
		q.add(new int[] {start, 1});
		
		while(!q.isEmpty()) {
			// size를 계산해서 1초 마다 큐를 돌려준다.
			int size = q.size();
			
			for (int i = 0; i < size; i++) {
				// 0번 idx : 위치, 1번 idx : 해당 위치까지 오는데의 경우의 수
				int [] cur = q.poll();
				
				// X+1 하는 경우
				int nr1 = cur[0]+1;
				verifyingNr(nr1, cur[1]);
				
				// X-1 하는 경우
				int nr2 = cur[0]-1;
				verifyingNr(nr2, cur[1]);
				
				// X*2 하는 경우
				int nr3 = cur[0]*2;
				verifyingNr(nr3, cur[1]);
			}
			
			// 맵에서 하나씩 꺼내서 큐로 옮기는 작업
			for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
				// 해당 위치의 방문을 처리해준다.
				v[entry.getKey()] = true;
				// map에 있던 정보를 q로 옮겨 담아준다.
				q.add(new int[] {entry.getKey(), entry.getValue()});
			}
			
			// map에 저장된 위치 X는 방문 처리 되었으므로
			// 다시는 사용되지 않을 값이기 때문에 clear 해준다.
			map.clear();
			
			// 만약에 최단 시간을 찾았다면 종료한다.
			if(min > 0) break;
			
			// 1초 증가시켜준다.
			time++;
			
		}
		
		
	}

	private static void verifyingNr(int nr, int cnt) {
		// 범위를 벗어났거나 이미 방문한 위치라면 리턴한다.
		if(!check(nr) || v[nr]) return;
		
		// nr에 동생이 있는지 확인하고, 있으면 처리를 해준다.
		if(nr == K) {
			if(min > 0) {
				ans += cnt;
			} else {
				min = time;
				ans += cnt;
			}
		// nr에 동생이 없다면, map에 갱신하는 작업을 진행한다.
		} else {
			if(map.containsKey(nr) ) {
				map.put(nr, map.get(nr)+cnt);
			} else {
				map.put(nr, cnt);
			}
		}
		
	}

	private static boolean check(int n) {
		return n >= 0 && n <= 100000;
	}

}
