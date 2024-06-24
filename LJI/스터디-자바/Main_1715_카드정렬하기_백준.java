import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_1715_카드정렬하기_백준 {

	//적은 수부터 두개씩 더하고 다시 우선순위큐에 넣고 꺼내서 더하고 넣고 반복..
	//10만개인데 scanner랑 buffered read차이가 많이 난다
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> pq=new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			pq.offer(Integer.parseInt(br.readLine()));
		}

		int answer=0;
		while(pq.size()!=1) {
			int sum=pq.poll()+pq.poll();
			
			answer+=sum;
			pq.offer(sum);
		}
		
		System.out.println(answer);
	}

}
