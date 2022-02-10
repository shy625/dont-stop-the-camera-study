import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
public class Main_1966 {
	static int T,N,index;
	static int [] pri;
	public static void main(String[] args) {
		Queue<Integer> queue=new LinkedList<>();
		Scanner scann=new Scanner(System.in);
		int now=0;
		T=scann.nextInt();
		for (int t = 1; t <= T; t++) {
			queue.clear();
			boolean canExit=false;
			int cnt=0;//팝 될 때마다 ++
			pri=new int[10];//1~9까지의 우선 순위 담을 그릇
			N=scann.nextInt();//문서의 개수
			index=scann.nextInt();//몇번쨰 위치를 찾을건지//이만큼 반복해서 pop을 못하고 push하면 q 크기만큼 반복해야한다
			for (int i = 0; i < N; i++) {//우선순위 배열 삽입, 큐 삽입
				now=scann.nextInt();
				queue.offer(now);
				pri[now]++;
			}
			while(!(canExit)) {
				for(int i=0;i<=index;i++) {
					now=queue.poll();
					if(canPrint(now)) {//프린팅 가능//요소를 팝한다
						pri[now]--;
						cnt++;
						if(i==index) {
							canExit=true;
						}
					}else {//프린팅 불가//다시 큐에 넣어야한다
						queue.offer(now);
					}
				}
				index=queue.size()-1;
			}
			System.out.println(cnt);
		}
	}
	private static boolean canPrint(int now) {
		for(int i=now+1;i<10;i++) {
			if(pri[i]>0) return false;
		}
		return true;
	}
}
