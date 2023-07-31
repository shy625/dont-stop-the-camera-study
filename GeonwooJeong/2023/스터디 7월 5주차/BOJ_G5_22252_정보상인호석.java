import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_G5_22252_정보상인호석 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Map<String, PriorityQueue<Integer>> map = new HashMap<>();
		
		long ans = 0;
		
		int Q = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < Q; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			
			if(N == 1) {
				String Name = st.nextToken();
				PriorityQueue<Integer> pq;
				if(map.containsKey(Name)) {
					pq = map.get(Name);
				} else {
					pq = new PriorityQueue<>(Collections.reverseOrder());
				}
				
				int K = Integer.parseInt(st.nextToken());
				
				for (int j = 0; j < K; j++) {
					pq.add(Integer.parseInt(st.nextToken()));
				}
				
				map.put(Name, pq);
				
			} else if(N == 2) {
				String Name = st.nextToken();
				int B = Integer.parseInt(st.nextToken());
				
				if(!map.containsKey(Name)) continue;
				
				PriorityQueue<Integer> pq = map.get(Name);
				int size = Math.min(pq.size(), B);
				
				for (int j = 0; j < size; j++) {
					ans += pq.poll();
				}
				
				map.put(Name, pq);
				
			}
		}
		
		System.out.println(ans);
		
		
		
	}

}
