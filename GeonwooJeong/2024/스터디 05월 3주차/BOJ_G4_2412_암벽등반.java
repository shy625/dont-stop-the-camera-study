import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_G4_2412_암벽등반 {
	static int T, ans = -1;	
	static Map<Integer, ArrayList<Integer>> map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		// 같은 x 좌표에 있는 y 좌표들을 저장하기 위한 map
		map = new HashMap<Integer, ArrayList<Integer>>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			// x 좌표가 한 번도 등장하지 않았으면 추가해준다.
			if(map.get(x) == null) map.put(x, new ArrayList<Integer>());
			
			// x 좌표에 y 좌표를 넣어준다.
			map.get(x).add(y);
		}
		
		bfs();
		
		System.out.println(ans);
		
	}

	private static void bfs() {
		Queue<int []> q = new ArrayDeque<int[]>();
		// 이미 밤문한 좌표인지 확인하기 위한 set
		Set<String> set = new HashSet<String>();
		q.add(new int[] {0, 0});
		
		int turn = 0;
		while(!q.isEmpty()) {
			// 큐 사이즈 만큼만 진행한다.
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int [] cur = q.poll();
				int curX = cur[0];
				int curY = cur[1];
				
				// x 좌표는 상관 없이 y 좌표가 T에 도달하면 끝낸다.
				if(curY == T) {
					ans = turn;
					return;
				}
				
				// curX-2, curY-2가 0보다 작아질 수 있으므로 범위를 지정한다.
				int sx = Math.max(0, curX-2);
				int sy = Math.max(0, curY-2);
				
				// |a-x|<=2, |b-y|<=2 이면 이동할 수 있으므로 해당 범위의 값들을 체크한다.
				for (int x = sx; x <= curX+2; x++) {
					for (int y = sy; y <= curY+2; y++) {
						// 이미 방문한 지점이라면 넘어간다.
						if(set.contains(x+" "+y)) continue;
						
						// map에서 x 좌표에 y 좌표가 들어있다면 해당 지점을 방문처리하고 q에 집어 넣는다.
						if(map.get(x) != null && map.get(x).contains(y)) {
							set.add(x+" "+y);
							q.add(new int[] {x, y});
						}
						
					}
				}
				
			}
			
			turn++;
		}
		
	}

}
