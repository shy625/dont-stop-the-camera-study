import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_S2_5464_주차장 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int ans = 0;
		
		// 주차 공간을 앞에서부터 사용하기 위해 사용할 PriorityQueue
		PriorityQueue<Integer> parkingQueue = new PriorityQueue<>();
		// 주차 공간이 없을 경우에 사용할 주차 대기 Queue
		Queue<Integer> waitingQueue = new LinkedList<>();
		
		// n번째 차가 몇 번째 주차 공간에 주차 중인지를 저장할 배열
		int [] parking = new int[M+1];
		// 주차 공간의 가격 정보를 저장할 배열
		int [] price = new int[N+1];
		// 자동차의 무게를 저장할 배열
		int [] weight = new int[M+1];
		
		// 처음에는 주차공간이 모두 비어있으므로 pq에 전부 추가해준다.
		for (int i = 1; i <= N; i++) {
			parkingQueue.add(i);
		}
		
		// 주차 공간 가격 정보 받기
		for (int i = 1; i <= N; i++) {
			price[i] = Integer.parseInt(br.readLine());
		}
		
		// 자동차 무게 정보 받기
		for (int i = 1; i <= M; i++) {
			weight[i] = Integer.parseInt(br.readLine());
		}
		
		for (int i = 0; i < 2*M; i++) {
			int n = Integer.parseInt(br.readLine());
			// 주차를 하는 경우
			if(n > 0) {
				// 주차 공간을 모두 사용중이라면 대기열 큐에 추가한다.
				if(parkingQueue.isEmpty()) {
					waitingQueue.add(n);
				// 주차 공간이 있는 경우에는 주차를 하고, 어디에 주차했는지를 parking 배열에 저장한다.
				} else {
					int space = parkingQueue.poll();
					parking[n] = space;
				}
			// 출차를 하는 경우
			} else {
				n = Math.abs(n);
				int space = parking[n];
				// 출차를 하면서 ans에 총액을 추가한다.
				ans += (price[space] * weight[n]);
				// 대기열에 차가 대기중인 경우에는 해당 자리에 바로 주차시켜준다.
				if(!waitingQueue.isEmpty()) {
					int nextCar = waitingQueue.poll();
					parking[nextCar] = space;
				// 대기열에 차가 없는 경우에는 주차 공간을 다시 반납한다.
				} else {
					parkingQueue.add(space);
				}
			}
		}
		
		System.out.println(ans);
		
	}

}
