import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_S2_17829_222풀링 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int [][] map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(N >= 2) {
			int [][] newMap = new int[N/2][N/2];
			
			for (int i = 0; i < N; i += 2) {
				for (int j = 0; j < N; j += 2) {
					int [] arr = new int[4];
					int idx = 0;
					for (int r = i; r < i+2; r++) {
						for (int c = j; c < j+2; c++) {
							arr[idx++] = map[r][c];
						}
					}
					Arrays.sort(arr);
					newMap[i/2][j/2] = arr[2];
				}
			}
			
			map = new int[N/2][N/2];
			for (int i = 0; i < N/2; i++) {
				map[i] = Arrays.copyOf(newMap[i], N/2);
			}
			
			N /= 2;
		}
		
		System.out.println(map[0][0]);
		
	}

}
