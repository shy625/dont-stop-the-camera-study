import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S1_25708_만남의광장 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int max = Integer.MIN_VALUE;
		
		int [][] map = new int[N][M];
		// 가로 줄들의 누적합을 저장할 배열
		int [] sum1 = new int[N];
		// 세로 줄들의 누적합을 저장할 배열
		int [] sum2 = new int[M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int n = Integer.parseInt(st.nextToken());
				// 광장 정보를 입력받는다.
				map[i][j] = n;
				// 가로 줄들의 누적합을 계산한다.
				sum1[i] += n;
			}
		}
		
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				// 세로 줄들의 누적합을 계산한다.
				sum2[i] += map[j][i];
			}
		}
		
		// 가로 2줄, 세로 2줄을 선택하기 위해 4중 for문 사용
		// combination을 사용해도 됐지만 성능상 차이가 없고 직관적이기에 4중 for문 사용함.
		for (int i = 0; i < N; i++) {
			for (int j = i+1; j < N; j++) {
				for (int k = 0; k < M; k++) {
					for (int l = k+1; l < M; l++) {
						int sum = 0;
						// 녹지인 칸의 개수를 더해준다.
						sum += (j-i-1) * (l-k-1);
						// 선택한 도로 4줄을 sum에 더해준다. 미리 구했던 누적합을 이용함.
						sum += sum1[i] + sum1[j] + sum2[k] + sum2[l];
						// 위의 과정에서 도로가 서로 겹치는 4곳을 다시 빼준다.
						sum -= (map[i][k] + map[i][l] + map[j][k] + map[j][l]);
						
						// 최댓값 갱신
						max = Math.max(max, sum);
					}
				}
			}
		}
		
		System.out.println(max);
		
	}

}
