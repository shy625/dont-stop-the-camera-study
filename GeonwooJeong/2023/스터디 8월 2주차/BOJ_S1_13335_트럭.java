import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_S1_13335_트럭 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		Queue<Integer> waiting = new LinkedList<>();
		Queue<Integer> bridge = new LinkedList<>();
		int time = 0;
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			waiting.add(Integer.parseInt(st.nextToken()));
		}
		
		for (int i = 0; i < W; i++) {
			bridge.add(0);
		}
		
		int weight = 0;
		while(!bridge.isEmpty()) {
			time++;
			weight -= bridge.poll();
			
			if(!waiting.isEmpty()) {
				if(waiting.peek() + weight <= L) {
					int n = waiting.poll();
					weight += n;
					bridge.add(n);
				} else {
					bridge.add(0);
				}
			}
			
		}
		
		System.out.println(time);

	}

}
