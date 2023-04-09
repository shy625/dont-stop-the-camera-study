import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_13164_행복유치원 {

	static int N,K;
	static int [] children;
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		
		children=new int[N];
		st=new StringTokenizer(br.readLine());
		PriorityQueue<Integer> pq=new PriorityQueue<>((x,y)-> -Integer.compare(x, y));
		
		for (int i = 0; i < N; i++) {
			children[i]=Integer.parseInt(st.nextToken());
			if(i==0) continue;
			pq.offer(children[i]-children[i-1]);
		}

		int total=children[N-1]-children[0];//가장 큰 아이-가장 작은 아이=전체 길이
		//여기서 끊어줄 아이만 끊음면 된다
		
		for (int i = 0; i < K-1; i++) {//조가 2개일때부터 끊을 수있다
			total-=pq.poll();
		}
		
		System.out.println(total);
	}

}
