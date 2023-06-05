import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main_1946_신입사원 {

	static int T,N;
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		
		T=scann.nextInt();
		for (int t = 0; t < T; t++) {
			
			N=scann.nextInt();
			
			//하나로 순위 정해서 정렬후 진행
			
			Comparator<Pri> comparator1 = new Comparator<Pri>() {
			    @Override
			    public int compare(Pri a, Pri b) {
			        return a.a-b.a;
			    }
			};
			
			PriorityQueue<Pri> pq=new PriorityQueue<>(comparator1);
			
			for (int i = 0; i < N; i++) {
				int a=scann.nextInt();
				int b=scann.nextInt();
				pq.offer(new Pri(a, b));
			}
			
			int max=Integer.MAX_VALUE;
			int cnt=0;
			for (int i = 0; i < N; i++) {
				Pri temp=pq.poll();
				if(temp.b<max) {//생존 가능
					max=temp.b;
					cnt++;
				}
			}
			
			System.out.println(cnt);
		}
	}

	static class Pri{//서류 심사로 1위부터 정렬
		int a;
		int b;
		public Pri(int a, int b) {
			super();
			this.a = a;
			this.b = b;
		}
	}
	
	
}
