import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_G5_25391_특별상 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Map<Integer, Integer> map1 = new HashMap<>();
		Map<Integer, Integer> map2 = new HashMap<>();
		
		PriorityQueue<Integer> pq1 = new PriorityQueue<Integer>(Collections.reverseOrder());
		PriorityQueue<Integer> pq2 = new PriorityQueue<Integer>(Collections.reverseOrder());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			pq1.add(a);
			pq2.add(b);
			
			map1.put(a, b);
			map2.put(b, a);
			
		}
		
		long ans = 0;
		
		for (int i = 0; i < K; i++) {
			int n = pq2.poll();
			int score = map2.get(n);
			ans += score;
			map2.remove(n);
		}
		
		for (int i = 0; i < M; i++) {
			while(!pq1.isEmpty()) {
				int n = pq1.poll();
				int value = map1.get(n);
				
				if(!map2.containsKey(value)) continue;
				else {
					ans += n;
					break;
				}
			}
			
		}
		
		System.out.println(ans);
		
	}

}
