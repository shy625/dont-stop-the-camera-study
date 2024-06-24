import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1389_케빈베이컨 {

	//전부의 상대 거리니까 플로이드 와셜?
	static int N;
	static int M;
	static int [][] dis;
	static int INF=1000000;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		dis=new int[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			Arrays.fill(dis[i], INF);
			dis[i][i]=0;
		}
		for (int i = 0; i < M; i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			dis[a][b]=1;
			dis[b][a]=1;
		}
		
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <=N; i++) {
				if(k==i) continue;
				for (int j = 1; j <=N; j++) {
					if(j==i || j==k) continue;
					
					if(dis[i][k]+dis[k][j]<dis[i][j]) {
						dis[i][j]=dis[i][k]+dis[k][j];
					}
				}
			}
		}
		
		int min=1000000;
		int answer=0;
		for (int i = 1; i <=N; i++) {
			int kevin=0;
			for (int j = 1; j <=N; j++) {
				kevin+=dis[i][j];
			}
			if(min>kevin) {
				answer=i;
				min=kevin;
			}
		}
		
		System.out.println(answer);
	}

}
