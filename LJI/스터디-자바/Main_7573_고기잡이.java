import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_7573_고기잡이 {

	static int N,I, M;
	static boolean [][] map;
	static int max;
	static ArrayList<int[]> fish;
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		I=Integer.parseInt(st.nextToken())/2;
		M=Integer.parseInt(st.nextToken());

		map=new boolean [N][N];
		
		fish=new ArrayList<>();
		for (int i = 0; i <M; i++) {
			st=new StringTokenizer(br.readLine());
			int r=Integer.parseInt(st.nextToken())-1;
			int c=Integer.parseInt(st.nextToken())-1;
			map[r][c]=true;//물고기 있으면 1
			fish.add(new int[] {r,c});
		}
		
		max=0;
		//물고기마다 그물 쳐보기
		for (int i = 0; i < M; i++) {
			int[] cur=fish.get(i);
			getFish(cur[0],cur[1]);
		}
		
		System.out.println(max);
	}
	private static void getFish(int fishR, int fishC) {//물고기 위치
		for (int r = 1; r < I; r++) {
			int c=I-r;
			//r,c는 그물의 길이
			int netR,netC;//그물 시작 위치
			//그물에 이 물고기가 걸리게 하려면?
			//가로선에 물고기 있을 때
			//위쪽
			if(fishR+r<N) {
				netR=fishR;
				for (int j = fishC-c; j <= fishC; j++) {
					cntFish(r,c,netR,j);
				}
			}
			//물고기가아래쪽
			if(fishR-r>=0) {
				netR=fishR-r;
				for (int j = fishC-c; j <= fishC; j++) {
					cntFish(r,c,netR,j);
				}
			}
			//세로 선에 물고기 있을 때
			if(fishC+c<N) {
				netC=fishC;
				for (int i = fishR-r; i <= fishR; i++) {
					cntFish(r, c, i, netC);
				}
			}
			if(fishC-c>=0) {
				netC=fishC-c;
				for (int i = fishR-r; i <= fishR; i++) {
					cntFish(r, c, i, netC);
				}
			}
		}
		
	}
	private static void cntFish(int r, int c, int netR, int netC) {
		int fishCnt=0;
		for (int i = netR; i <= netR+r; i++) {
			for (int j = netC; j <= netC+c; j++) {
				if(!check(i,j)) return;
				if(map[i][j])fishCnt++;
			}
		}
		max=Math.max(max, fishCnt);
	}
	private static boolean check(int i, int j) {
		return i>=0&&i<N&&j>=0&&j<N;
		
	}

}
