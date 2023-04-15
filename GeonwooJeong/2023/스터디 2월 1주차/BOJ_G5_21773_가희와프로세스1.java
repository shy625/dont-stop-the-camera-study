import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Pro implements Comparable<Pro> {
	int id;
	int time;
	int prior;
	
	public Pro(int id, int time, int prior) {
		this.id = id;
		this.time = time;
		this.prior = prior;
	}
	
	public int compareTo(Pro o) {
		if(this.prior != o.prior) {
			return o.prior - this.prior;
		} else {
			return Integer.compare(this.id, o.id);
		}
	}
	
}

public class BOJ_G5_21773_가희와프로세스1 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Pro> pq = new PriorityQueue<>();
		
		int T = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int id = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			int prior = Integer.parseInt(st.nextToken());
			pq.add(new Pro(id, time, prior));
		}
		
		for (int i = 0; i < T; i++) {
			if(pq.isEmpty()) break;
			
			Pro cur = pq.poll();
			sb.append(cur.id).append("\n");
			
			// 해당 프로세스가 1초 남았다면 큐에 다시 집어넣지 않음
			if(cur.time == 1) continue;
			
			// 다른 모든 프로세스의 우선 순위를 높이는 대신, 해당 프로세스의 우선 순위를 1 낮춤
			pq.add(new Pro(cur.id, cur.time-1, cur.prior-1));
		}
		
		System.out.println(sb.toString());

	}

}
