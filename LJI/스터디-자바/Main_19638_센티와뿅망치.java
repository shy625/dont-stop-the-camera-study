import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_19638_센티와뿅망치 {

	static int N,H,T;
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		H=Integer.parseInt(st.nextToken());
		T=Integer.parseInt(st.nextToken());
		
		PriorityQueue<Integer> pq=new PriorityQueue<>(Collections.reverseOrder());//내림차순 정렬
		
		for (int i = 0; i < N; i++) {
			pq.offer(Integer.parseInt(br.readLine()));
		}
		
		int cnt=0;
		
		while(cnt<T) {//회수가 한계 회수 보다 작을때
			int cur=pq.poll();
			if(cur<H){//모든 키가 H보다 작다
				pq.offer(cur);
				break;
			}
			cnt++;
			if(cur==1) {
				pq.offer(1);
				break;
				}
			else pq.offer(cur>>1);
		}
		
		int tall=pq.poll();//가장 큰 사람
		if( tall>=H) {
			System.out.println("NO");
			System.out.println(tall);
		}else {
			System.out.println("YES");
			System.out.println(cnt);
		}
	}

}
