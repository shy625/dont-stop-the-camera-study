import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_S1_1389_케빈베이컨의6단계법칙 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int [][] map = new int[N][N];
		
		// map 초기화 과정
		for (int i = 0; i < N; i++) {
			Arrays.fill(map[i], 100000);
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			
			map[a][b] = 1;
			map[b][a] = 1;
		}
		
		// 플로이드 워셜
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					if(i == j || j == k || i == k) continue;
					
					if(map[j][k] > map[j][i] + map[i][k]) {
						map[j][k] = map[j][i] + map[i][k];
					}
				}
			}
		}
		
		int idx = -1;
		int min = Integer.MAX_VALUE;
		
		// 케빈 베이컨 수 구하기
		for (int i = 0; i < N; i++) {
			int tmp = 0;
			
			for (int j = 0; j < N; j++) {
				if(i == j) continue;
				
				if(map[i][j] < 100000) tmp += map[i][j];
			}
			
			if(tmp < min) {
				idx = i+1;
				min = tmp;
			}
		}
		
		System.out.println(idx);

	}

}
