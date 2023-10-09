import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Task implements Comparable<Task> {
	int time;
	int limit;

	public Task(int time, int limit) {
		this.time = time;
		this.limit = limit;
	}

	@Override
	public int compareTo(Task o) {
		if(this.limit != o.limit) {
			return Integer.compare(o.limit, this.limit);
		} else {
			return Integer.compare(this.time, o.time);
		}
	}
	
}

public class BOJ_G5_1263_시간관리 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Task> pq = new PriorityQueue<>();
		
		int N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			int limit = Integer.parseInt(st.nextToken());
			
			pq.add(new Task(time, limit));
		}
		
		int ans = Integer.MAX_VALUE;
		
		while(!pq.isEmpty()) {
			Task now = pq.poll();
			
			if(now.limit < ans) {
				ans = now.limit;
			}
			
			ans -= now.time;
			
		}
		
		if(ans < 0) {
			System.out.println(-1);
		} else {
			System.out.println(ans);
		}
		
		
	}

}
