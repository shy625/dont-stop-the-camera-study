import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_15686_치킨배달 {

	static int N,M,C,H;
	static int [][] map;
	static ArrayList<int[]> h;//집들 리스트
	static ArrayList<int[]> c;//치킨집 리스트
	static boolean [] v;//체크된 치킨집 리스트
	static int min;
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine()," ");
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		map=new int [N][N];
		h=new ArrayList<>();
		c=new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st=new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				
				if(map[i][j]==1) {//집
					h.add(new int[]{i,j});
				}else if(map[i][j]==2) {//치킨집
					c.add(new int[]{i,j});
				}
			}
		}
		
		min=Integer.MAX_VALUE;
		C=c.size();//치킨집 갯수
		H=h.size();//사람집 갯수
		v=new boolean[C];
		ncr(0,0);
		System.out.println(min);
	}
	
	private static void ncr(int cnt, int start) {
		if(cnt==M) {
			ArrayList<int[]> cc=new ArrayList<>();
			for (int i = 0; i <C; i++) {
				if(!v[i])continue;
				cc.add(c.get(i));
			}
			//선택된 치킨집 리스트 선별 완료
			
			int total=0;
			
			for (int i = 0; i < H; i++) {//각 집에서 치킨집 까지의 최소 거리
				int minDis=Integer.MAX_VALUE;
				int r=h.get(i)[0];
				int c=h.get(i)[1];
				for (int j = 0; j < M; j++) {//M개의 치킨집과의 최소 거리 구하기
					int temp=Math.abs(r-cc.get(j)[0])+Math.abs(c-cc.get(j)[1]);
					minDis=Math.min(minDis, temp);
				}
				total+=minDis;
				
				if(total>min) return;//이미 최소 거리에서 벗어났으니 의미 없다
			}
			
			min=Math.min(min, total);
			return;
		}
		for (int i = start; i < C; i++) {
			v[i]=true;
			ncr(cnt+1,i+1);
			v[i]=false;
		}
	}

}
