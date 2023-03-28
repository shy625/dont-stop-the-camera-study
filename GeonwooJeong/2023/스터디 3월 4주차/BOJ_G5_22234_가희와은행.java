import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Info implements Comparable<Info> {
	int id;
	int needTime;
	int arriveTime;
	
	public Info(int id, int needTime, int arriveTime) {
		this.id = id;
		this.needTime = needTime;
		this.arriveTime = arriveTime;
	}

	@Override
	public int compareTo(Info o) {
		return Integer.compare(this.arriveTime, o.arriveTime);
	}
	
}

public class BOJ_G5_22234_가희와은행 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		// 처음부터 은행에 있던 고객을 저장할 큐
		Queue<Info> q = new LinkedList<>();
		// 이후에 은행에 들어올 고객을 저장할 큐(도착 시간이 정렬되지 않아서 pq를 사용함)
		PriorityQueue<Info> pq = new PriorityQueue<>();
		
		// 처음 은행에 있던 고객들을 q에 넣어줌
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int id = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			q.offer(new Info(id, time, 0));
		}
		
		int custom = Integer.parseInt(br.readLine());
		
		// 이후에 은행에 들어올 고객을 pq에 넣어줌
		for (int i = 0; i < custom; i++) {
			st = new StringTokenizer(br.readLine());
			
			int id = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			int arriveTime = Integer.parseInt(st.nextToken());
			pq.offer(new Info(id, time, arriveTime));
		}
		
		int curTime = 0;
		
		while(!q.isEmpty()) {
			Info cur = q.poll();
			
			// 현재 고객의 처리하는데 필요한 시간이 T보다 같거나 작을 경우 끝까지 처리해준다.
			if(cur.needTime <= T) {
				// 은행이 최대 W초 까지만 일을 해서 따로 계산을 해줌
				int min = Math.min(curTime+cur.needTime, W);
				for (int i = curTime; i < min; i++) {
					sb.append(cur.id).append('\n');
				}
				curTime += cur.needTime;
			// 현재 고객의 처리하는데 필요한 시간이 T보다 오래 걸릴 경우 T초 까지만 처리한다.
			} else {
				// 마찬가지로 은행이 최대 W초 까지만 일을 해서 따로 계산해줌
				int min = Math.min(curTime+T, W);
				for (int i = curTime; i < min; i++) {
					sb.append(cur.id).append('\n');
				}
				curTime += T;
			}
			
			// W초까지 영업을 했다면 업무를 마친다.
			if(curTime > W) break;
			
			while(!pq.isEmpty()) {
				// 도착 시간이 현재 시간보다 같거나 클 경우 현재 은행 큐에 고객을 추가해준다. (대기열 줄세우기)
				int arriveTime = pq.peek().arriveTime;
				if(arriveTime <= curTime) {
					Info cur2 = pq.poll();
					q.offer(new Info(cur2.id, cur2.needTime, cur2.arriveTime));
				} else break;
			}
			
			// 이번 턴에 은행 업무를 처리했던 사람이 모든 업무를 끝마치지 못했을 경우 도착한 사람 뒤로 다시 줄을 세운다.
			if(cur.needTime > T) {
				q.offer(new Info(cur.id, cur.needTime-T, cur.arriveTime));
			}
			
		}
		
		System.out.println(sb.toString());
		
	}

}
