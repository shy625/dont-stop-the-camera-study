import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_17503_맥주축제 {

	//도수 낮은 순으로 정렬// 일단 술 리스트에 들어갔으면 선호도 낮은 순으로 정렬?
	static class Drink {
		int v;
		int c;
		public Drink(int v, int c) {
			super();
			this.v = v;
			this.c = c;
		}
		
	}

	static int N, M, K;
	static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		answer = -1;

		//도수 낮은 순으로 정렬하여 술 리스트 생성
		PriorityQueue<Drink> drinkPQ=new PriorityQueue<>(new Comparator<Drink>() {
			@Override
			public int compare(Drink o1, Drink o2) {
				
				return o1.c==o2.c? Integer.compare(o2.v, o1.v):Integer.compare(o1.c, o2.c);
			}
			
		});
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			drinkPQ.offer(new Drink(v, c));
		}

		//N개의 술 고르기 //N개까지의 술은 넣고 하나씩 추가 빼기 반복
		int cnt=0;
		int alcohol=0;//허용 도수
		int sum=0;//선호도 총합
		PriorityQueue<Drink> pq=new PriorityQueue<>(new Comparator<Drink>() {
			@Override
			public int compare(Drink o1, Drink o2) {
				return Integer.compare(o1.v, o2.v);
			}
			
		});
		
		while(!drinkPQ.isEmpty()) {
			
			Drink cur=drinkPQ.poll();
			//술 개수가 모자랄 때 리스트 단순 추가
			if(cnt<N) {
				pq.offer(cur);
				cnt++;
				alcohol=cur.c;
				sum+=cur.v;
				continue;
			}
			
			//끝낼 조건인지 체크
			if(sum>=M)break;
			//술 교체
			
			//술 추가
			pq.offer(cur);
			alcohol=cur.c;
			sum+=cur.v;
			//술 제거
			Drink temp=pq.poll();
			sum-=temp.v;
			
		}

		if(cnt==N && sum>=M) answer=alcohol;
		
		System.out.println(answer);
	}
}
