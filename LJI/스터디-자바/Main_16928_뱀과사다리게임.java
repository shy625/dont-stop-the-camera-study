import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_16928_뱀과사다리게임 {

	static int N,M;
	static int [] move;//이동할 위치 담을 배열
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		N=scann.nextInt();
		M=scann.nextInt();
		move=new int[101];
		
		int total=N+M;
		for (int i = 0; i < total; i++) {
			int from=scann.nextInt();
			int to=scann.nextInt();
			move[from]=to;
		}
		
		boolean [] v=new boolean[101];
		
		Queue<int[]> que=new LinkedList<>();
		
		que.offer(new int[] {1,0});
		v[1]=true;
		
		int answer=0;
		while(!que.isEmpty()) {
			int []cur=que.poll();
			int now=cur[0];
			int cnt=cur[1];
			
			if(now==100) {
				answer=cnt;
				break;
			}
			
			for (int d = 1; d <= 6; d++) {//주사위 눈
				int next=now+d;
				
				if(next>100) break;
				
				if(!v[next]) {
					if(move[next]!=0) {//이동할 곳 이 있다
						int moveNext=move[next];
						if(!v[moveNext]) {
							v[next]=true;
							v[moveNext]=true;
							que.offer(new int[] {moveNext,cnt+1});
						}
					}else {
						v[next]=true;
						que.offer(new int[] {next,cnt+1});
					}
				}
			}
		}
		
		System.out.println(answer);
	}
}
