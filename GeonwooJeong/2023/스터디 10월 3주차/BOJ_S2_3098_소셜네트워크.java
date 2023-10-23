import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_S2_3098_소셜네트워크 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int [][] arr = new int[N][N];
		boolean [][] v = new boolean[N][N];
		int cnt = (N*N-N) - M*2;
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			
			arr[a][b] = 1;
			arr[b][a] = 1;
			v[a][b] = true;
			v[b][a] = true;
		}
		
		int day = 1;
		StringBuilder sb = new StringBuilder();
		
		while(cnt > 0) {
			int tmp = 0;
			List<int []> list = new ArrayList<>();
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(i == j) continue;
					
					if(arr[i][j] > 0) {
						for (int k = 0; k < N; k++) {
							if(k == i) continue;
							
							if(arr[j][k] > 0 && !v[i][k]) {
								list.add(new int[] {i, k});
								v[i][k] = true;
								cnt--;
								tmp++;
							}
						}
					}
					
				}
			}
			
			for(int [] now : list) {
				arr[now[0]][now[1]] = day+1;
			}
			
			sb.append((tmp/2)+"\n");
			day++;
		}
		
		System.out.println(day-1);
		System.out.println(sb.toString());
		
	}

}
