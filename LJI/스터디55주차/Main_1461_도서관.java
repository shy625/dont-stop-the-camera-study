import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main_1461_도서관 {

	//책을 가져가기 위해 중간 과정에선 0을 거쳐야함
	//거리별로 m개씩 묶어 가져다 놓고 더 가져다놔야할 책이 있으면 0으로 귀환?
	//가장 먼 거리를 돌아오지 않는 것이 중요
	//-,+ 나눠서 한 뒤 가장 긴 거리를 한번만 더해주기
	static int N,M;
	static int answer;
	static PriorityQueue<Integer> pqM=new PriorityQueue<>(Collections.reverseOrder());
	static PriorityQueue<Integer> pqP=new PriorityQueue<>(Collections.reverseOrder());
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		N=scann.nextInt();
		M=scann.nextInt();
		
		for (int i = 0; i <N; i++) {
			int temp=scann.nextInt();
			if(temp<0) {
				pqM.offer(Math.abs(temp));
			}else {
				pqP.offer(temp);
			}
		}
		
		PriorityQueue<Integer> pq=new PriorityQueue<>();//거리 담을 pq
		
		int cnt=0;//현재 챙긴 책의 갯수 세기
		int dis=0;
		
		//마이너스 거리들 측정
		while(!pqM.isEmpty()) {
			
			int temp=pqM.poll();
			
			cnt++;
			if(cnt==1) dis=temp;
			
			if(cnt==M) {
				pq.offer(dis);
				cnt=0;
				dis=0;
			}
		}
		if(dis!=0) {
			pq.offer(dis);
			dis=0;
		}
		//플러스 거리들 측정
		dis=0;
		cnt=0;
		while(!pqP.isEmpty()) {
			int temp=pqP.poll();
			cnt++;
			
			
			if(cnt==1) dis=temp;
			
			if(cnt==M) {
				pq.offer(dis);
				cnt=0;
				dis=0;
			}
		}
		if(dis!=0) {
			pq.offer(dis);
			dis=0;
		}
		
		//거리 더하기
		answer=0;
		dis=0;
		while(!pq.isEmpty()) {
			answer+=dis*2;
			dis=pq.poll();
		};
		
		answer+=dis;//가장 긴 거리는 가서 끝낸다
		
		System.out.println(answer);
	}

}
