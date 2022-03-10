import java.util.PriorityQueue;
import java.util.Scanner;

public class Main_10971_외판원순회2 {

	//방향이 있기에 최소 신장은 어렵다 //nPr에서 백트래킹을 잘 쓰면 될 것 같다
	static int N;
	static int  map[][];
	static int Min;
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		N=scann.nextInt();
		
		map=new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j]=scann.nextInt();
			}
		}
		Min=Integer.MAX_VALUE;
		find(0,0,-1,new boolean [N],0);//cnt, distance;
		
		System.out.println(Min);
	}
	
	private static void find(int cnt, int dis,int preNode, boolean [] v,int start) {
		if(cnt==N-1) {
			if(map[preNode][start]==0) return;
			
			int d=map[preNode][start]+dis;
			Min=Math.min(Min, d);
			return;
		}
		
		if(cnt==0) {//처음 시작 //전 노드가 정해지지 않음
			for (int i = 0; i < N; i++) {//i가 출발지
				v[i]=true;
				for (int j = 0; j < N; j++) {//j가 도착지
					if(map[i][j]==0)continue;//갈수 없거나 제자리
					
					int d=map[i][j];
					if(d>Min)continue;//갈필요 없다
					v[j]=true;
					find(cnt+1,d,j,v,i);
					v[j]=false;
				}
				v[i]=false;
			}
		}else {
			for (int i = 0; i <N; i++) {
				if(v[i] || map[preNode][i]==0 )continue;
				
				int d=dis+map[preNode][i];
				if(d>Min) continue;
				
				v[i]=true;
				find(cnt+1,d,i,v,start);
				v[i]=false;
			}
		}
	}
}
