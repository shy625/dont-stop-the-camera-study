import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_12731_열차시간표 {

	static class Train implements Comparable<Train>{
		int startTime;//
		int endTime;
		boolean A;//true: A역 출발 , false: B역 출발
		boolean ready;//회차가 완료된 열차인지
		
		public Train(int startTime, int endTime, boolean a, boolean ready) {
			super();
			this.startTime = startTime;
			this.endTime = endTime;
			A = a;
			this.ready = ready;
		}
		@Override
		public int compareTo(Train o) {
			if(this.startTime==o.startTime) {//시간이 같을 경우 회차 먼저
				int temp=0;
				if(ready) temp=-1;
				else temp=1;
				return Integer.compare(temp, 0);
			}
			return Integer.compare(this.startTime, o.startTime);
		}
		
		
	}
	static int N,T,NA,NB;
	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		for (int n = 1; n <= N; n++) {
			T=Integer.parseInt(br.readLine());

			StringTokenizer st=new StringTokenizer(br.readLine());
			NA=Integer.parseInt(st.nextToken());
			NB=Integer.parseInt(st.nextToken());
			
			PriorityQueue<Train> pq=new PriorityQueue<>();
			for (int i = 0; i < NA; i++) {
				st=new StringTokenizer(br.readLine());
				
				//출발 시간
				String [] start=st.nextToken().split(":");
				int startTime= Integer.parseInt(start[0])*60 + Integer.parseInt(start[1]);
				//도착 시간
				
				String [] end=st.nextToken().split(":");
				int endTime= Integer.parseInt(end[0])*60 + Integer.parseInt(end[1]);
				
				pq.offer(new Train(startTime, endTime, true, false));//A역 출발
			}
			
			for (int i = 0; i < NB; i++) {
				st=new StringTokenizer(br.readLine());
				
				//출발 시간
				String [] start=st.nextToken().split(":");
				int startTime= Integer.parseInt(start[0])*60 + Integer.parseInt(start[1]);
				//도착 시간
				
				String [] end=st.nextToken().split(":");
				int endTime= Integer.parseInt(end[0])*60 + Integer.parseInt(end[1]);
				
				pq.offer(new Train(startTime, endTime, false, false));//B역 출발
			}
			
			int answerA=0;
			int answerB=0;
			
			int remainA=0;
			int remainB=0;
			
			while(!pq.isEmpty()) {
				Train cur=pq.poll();
				
				if(cur.ready) {//회차가 완료된 기차 //각역에 남은 기차에 더해주기
					if(cur.A) {
						remainA++;
					}else {
						remainB++;
					}
					continue;
				}
				
				//기차 출발
				if(cur.A) {//A역 출발
					if(remainA>0) {//여분의 대기 기차가 있다면?
						remainA--;
					}else {
						answerA++;
					}
					pq.offer(new Train(cur.endTime+T, 0, false, true));//b역에서 회차 시간까지 대기
				}else {//B역 출발
					if(remainB>0) {//여분의 대기 기차가 있다면?
						remainB--;
					}else {
						answerB++;
					}
					pq.offer(new Train(cur.endTime+T, 0, true, true));
				}
			}
			System.out.println("Case #"+n+": "+answerA+" "+answerB);
		}

	}

}
