import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_19638_센티와마법의뿅망치 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

		for (int i = 0; i < N; i++) {
			pq.add(Integer.parseInt(br.readLine()));
		}
		
		for (int i = 0; i < T; i++) {
			int giant = pq.peek();
			
			if(giant < H) {
				System.out.println("YES");
				System.out.println(i);
				System.exit(0);
			} else if(giant > 1) {
				pq.poll();
				pq.add(giant/2);
			}
		}
		
		if(pq.peek()<H) {
			System.out.println("YES");
			System.out.println(T);
		} else {
			System.out.println("NO");
			System.out.println(pq.peek());
		}
		
	}

}
