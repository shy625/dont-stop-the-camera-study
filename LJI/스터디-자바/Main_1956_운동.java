import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1956_운동 {

	//플로이드 와샬 후 출발->도착->출발 거리가 가장 짧은 운동 거리 찾자
	
	static int V,E;
	static int [][] floyd;
	static int MAX=Integer.MAX_VALUE/100;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		st=new StringTokenizer(br.readLine());
		V=Integer.parseInt(st.nextToken());
		E=Integer.parseInt(st.nextToken());
		
		floyd=new int[V][V];
		for (int i = 0; i < V; i++) {
			Arrays.fill(floyd[i], MAX);
			floyd[i][i]=0;
		}
		for (int i = 0; i < E; i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken())-1;
			int b=Integer.parseInt(st.nextToken())-1;
			int w=Integer.parseInt(st.nextToken());
			
			floyd[a][b]=w;
		}
		
		for (int k = 0; k < V; k++) {//경유지
			for (int i = 0; i < V; i++) {//출발지
				if(k==i)continue;
				for (int j = 0; j < V; j++) {//도착지
					if(k==j || i==j)continue;
					
					if(floyd[i][j]>floyd[i][k]+floyd[k][j])
						floyd[i][j]=floyd[i][k]+floyd[k][j];
				}
			}
		}
		
		int min=MAX;
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++) {
				if(i==j)continue;
				if(floyd[i][j]!=MAX&&floyd[j][i]!=MAX) {
					min=Integer.min(min, floyd[i][j]+floyd[j][i]);
				}
			}
		}
		
		System.out.println(min==MAX?-1:min);
	}
}
