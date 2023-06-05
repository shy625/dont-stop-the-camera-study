import java.util.PriorityQueue;
import java.util.Scanner;

public class Main_2217_로프 {

	//가장 적은 무게를 견딜 수 있는 로프가 견디는 무게* 병렬로 연결된 로프의 갯수= 최대 중량 무게 
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		
		int N=scann.nextInt();
		PriorityQueue<Integer> pq=new PriorityQueue<>();
		for (int i = 0; i <N; i++) {
			pq.offer(scann.nextInt());
		}
		
		int max=-1;
		
		for (int i = 0; i < N; i++) {
			int temp=pq.poll();
			int maxWeight=temp*(N-i);
			max=Math.max(max, maxWeight);
		}
		System.out.println(max);
	}
}
