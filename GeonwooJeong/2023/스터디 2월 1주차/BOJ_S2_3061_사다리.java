import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S2_3061_사다리 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T;t++) {
			int N = Integer.parseInt(br.readLine());
			
			int cnt = 0;
			int idx = 1;
			
			int [] arr = new int[N+1];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if(arr[j] == i) {
						idx = j;
						
						while(arr[i] != i) {
							int tmp = arr[idx-1];
							arr[idx-1] = arr[idx];
							arr[idx--] = tmp;
							cnt++;
						}
					}
				}
			}
			
			System.out.println(cnt);
			
		}

	}

}
