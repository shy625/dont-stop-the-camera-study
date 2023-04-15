import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17612_쇼핑몰 {

	//고객 리스트 순서대로 저장
	//계산대 리스트를 pq로 저장?
	//고객별로 대기시간 + 자신의 물건 시간, 계산한 계산대 번호(역순) pq 저장 후 꺼내면서 더하면 될 것 같다
	
	static class Counter implements Comparable<Counter>{
		int no;//카운터 번호
		int waitTime;//대기시간
		public Counter(int num, int waitTime) {
			super();
			this.no = num;
			this.waitTime = waitTime;
		}
		@Override
		public int compareTo(Counter o) {
			return Integer.compare(this.waitTime, o.waitTime)==0?Integer.compare(this.no, o.no):Integer.compare(this.waitTime, o.waitTime);
		}
	}
	
	static class Customer implements Comparable<Customer>{
		int no;//고객 번호
		int endTime;//대기시간
		int counterNo;
		
		public Customer(int no, int endTime, int counterNo) {
			super();
			this.no = no;
			this.endTime = endTime;
			this.counterNo = counterNo;
		}

		@Override
		public int compareTo(Customer o) {
			return Integer.compare(this.endTime, o.endTime)==0?Integer.compare(o.counterNo, this.counterNo):Integer.compare(this.endTime, o.endTime);
		}
	}
	
	static int N,k;
	static PriorityQueue<Counter> counterList;
	static PriorityQueue<Customer> customerList;
	static Queue<int []> waitList;
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		//입력 다 받기
		
		//N,k 입력
		N=Integer.parseInt(st.nextToken());
		k=Integer.parseInt(st.nextToken());
		
		//카운터 pq 만들기
		counterList=new PriorityQueue<>();
		for (int i = 1; i <=k; i++) {//처음엔 모두 대기
			counterList.add(new Counter(i, 0));
		}
		
		//N명의 정보 받기//줄 대기이므로 queue
		waitList=new LinkedList<int[]>();
		for (int i = 0; i < N; i++) {
			st=new StringTokenizer(br.readLine());
			int no=Integer.parseInt(st.nextToken());
			int w=Integer.parseInt(st.nextToken());
			waitList.offer(new int[] {no,w});
		}
		
		//고객 최종 리스트 pq 만들기
		customerList=new PriorityQueue<>();
		
		//대기 고객들 전부 처리해주기
		while(!waitList.isEmpty()) {
			int[] nowCustomer=waitList.poll();//현재 고객 받기
			
			Counter counter=counterList.poll();//현재 처리 가능한 카운터
			
			//고객이 처리 끝날 시간을 계산해서 customerList에 정리
			customerList.offer(new Customer(nowCustomer[0], counter.waitTime+nowCustomer[1], counter.no));
			//counterList도 현재 고객 처리시간을 더한 값을 다시 넣어주기
			counterList.offer(new Counter(counter.no, counter.waitTime+nowCustomer[1]));
		}
		
		//customerList 꺼내주면서 정답 계산//int 범위 넘어설 수 있다
		long answer=0;
		int index=1;
		while(!customerList.isEmpty()) {
			Customer cur=customerList.poll();
			answer +=(long) cur.no *index++;
		}
		
		System.out.println(answer);
	}
}
