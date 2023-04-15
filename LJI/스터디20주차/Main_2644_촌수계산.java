import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_2644_촌수계산 {

	static int N;
	static int A,B;//목표하는 사람들
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		N=scann.nextInt();
		ArrayList<ArrayList<Integer>> graph=new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		A=scann.nextInt();
		B=scann.nextInt();
		
		//graph 제작
		int m=scann.nextInt();
		for (int i = 0; i < m; i++) {
			int parent=scann.nextInt();
			int child=scann.nextInt();
			
			//양방향 해야 촌수 알 수 있다
			graph.get(parent).add(child);
			graph.get(child).add(parent);
		}
		
		int answer=-1;
		Queue<Integer> que=new LinkedList<Integer>();
		que.offer(A);
		boolean v[]= new boolean[N+1];
		v[A]=true;
		
		int cnt=0;//촌수 계산
		
		while(!que.isEmpty()) {
			int size=que.size();//현재 같은 촌수의 사람들 알아내기
			for (int i = 0; i < size; i++) {
				int cur=que.poll();
				
				if(cur==B) {
					System.out.println(cnt);
					return;
				}
				for(int PorC:graph.get(cur)) {
					if(!v[PorC]) {
						v[PorC]=true;
						que.offer(PorC);
					}
				}
			}
			cnt++;
		}
		System.out.println("-1");
	}

}
