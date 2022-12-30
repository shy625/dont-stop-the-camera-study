import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_S2_1058_친구 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int max = 0;
		int N = Integer.parseInt(br.readLine());
		int [][] map = new int[N][N];
		
		// map 초기화 과정
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				if(i == j) {
					map[i][j] = 100000;
					continue;
				}
				
				char c = str.charAt(j);
				if(c == 'Y') map[i][j] = 1;
				else map[i][j] = 100000;
			}
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
		
		// 친구 수 구하기
		for (int i = 0; i < N; i++) {
			int tmp = 0;
			
			for (int j = 0; j < N; j++) {
				if(map[i][j] <= 2) tmp++;
			}
			
			max = Math.max(max, tmp);
		}
		
		System.out.println(max);

	}

}
