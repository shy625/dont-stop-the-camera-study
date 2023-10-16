import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_S3_12018_YonseiTOTO {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int ans = 0;
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int P = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			
			int [] arr = new int[P];
			
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < P; j++) {
				arr[j] = Integer.parseInt(st.nextToken());
			}
			
			if(P < L) {
				pq.add(1);
			} else {
				Arrays.sort(arr);
				pq.add(arr[P-L]);
			}
		}
		
		while(!pq.isEmpty()) {
			int n = pq.poll();
			if(M - n < 0) {
				break;
			} else {
				M -= n;
				ans++;
			}
		}
		
		System.out.println(ans);
		
	}

}
