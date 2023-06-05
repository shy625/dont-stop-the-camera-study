import java.util.PriorityQueue;
import java.util.Scanner;

public class Main_2594_놀이공원 {

	static class Ride implements Comparable<Ride>{
		int startTime;
		int endTime;
		public Ride(int startTime, int endTime) {
			super();
			this.startTime = startTime;
			this.endTime = endTime;
		}
		
		@Override
		public int compareTo(Ride o) {
			return Integer.compare(this.startTime, o.startTime);
		}
	}
	
	static int N;
	static PriorityQueue<Ride> pq;
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		
		N=scann.nextInt();
		pq=new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			int A=scann.nextInt();
			int B=scann.nextInt();
			
			int startT= A/100*60 + A%100 -10;
			int endT= B/100*60 + B%100 +10;
			
			pq.offer(new Ride(startT, endT));
		}
		
		PriorityQueue<Ride> pq2=new PriorityQueue<>();
		while(!pq.isEmpty()) {
			Ride A=pq.poll();
			if(pq.isEmpty()) {//마지막 한개 남음
				pq2.offer(A);
				break;
			}
			Ride B=pq.poll();
			
			if(A.endTime>=B.startTime) {//둘 사이의 쉬는 시간이 없다
				
				pq.offer(new Ride(A.startTime, B.endTime>A.endTime? B.endTime : A.endTime));//A의 시작 시간부터 둘 중 늦게 끝나는 시간까지 결합
			}else {
				pq2.offer(A);
				pq.offer(B);
			}
		}
		
		int preTime=600;//10시 분으로 환산
		int max=0;
		
		while(!pq2.isEmpty()) {
			Ride cur=pq2.poll();
			//System.out.println("start : "+cur.startTime+" end : "+cur.endTime);
			int breakTime=cur.startTime-preTime;
			
			max=Math.max(max, breakTime);
			
			preTime=cur.endTime;
		}
		
		//영업 종료 시간까지의 차이 구해야한다
		max=Math.max(max, 1320-preTime);//22*60=1320
		
		System.out.println(max);
	}

}
