import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_G5_21278_호석이두마리치킨 {
	
	static int N, min;
	static int [][] map;
	static int [] picks;
	static boolean [] v;
	static String ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// 각 마을끼리의 거리를 저장할 2차원 배열
		map = new int[N][N];
		// 치킨집을 차릴 건물의 번호를 저장할 배열
		picks = new int[2];
		v = new boolean[N];
		// 왕복 시간의 최솟값을 저장할 변수
		min = Integer.MAX_VALUE;
		
		// 처음엔 도로가 없으므로 10001(INF)으로 채워준다. (자기 자신으로 가는 거리는 0)
		for (int i = 0; i < N; i++) {
			Arrays.fill(map[i], 10001);
			map[i][i] = 0;
		}
		
		// M개의 양방향 도로를 연결해준다.
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			
			map[a][b] = 1;
			map[b][a] = 1;
		}
		
		// 플로이드 워셜 알고리즘
		// N개의 건물에 대해 진행
		for (int k = 0; k < N; k++) {
			// i 건물부터 출발하여
			for (int i = 0; i < N; i++) {
				// j 건물까지 도착할 때,
				for (int j = 0; j < N; j++) {
					// i->k->j가 i->j보다 짧을 경우 갱신해준다.
					if(map[i][k] + map[k][j] < map[i][j]) {
						map[i][j] = map[i][k] + map[k][j];
					}
				}
			}
		}
		
		// 치킨 집을 어디에 지을 것인지 combination 사용
		combi(0, 0);
		
		System.out.println(ans+" "+min);
		
	}

	private static void combi(int start, int cnt) {
		if(cnt == 2) {
			int a = picks[0];
			int b = picks[1];
			int sum = 0;
			
			// 각각 건물에 대해 a치킨집과 b치킨집 중 가까운 거리를 선택하여 sum에 더해준다.
			for (int i = 0; i < N; i++) {
				sum += Math.min(map[a][i], map[b][i]);
			}
			
			// 왕복 거리가 min보다 적을 경우, 정답을 갱신해준다.
			if(sum*2 < min) {
				ans = (a+1)+" "+(b+1);
				min = sum*2;
			}
			
			return;
		}
		
		// N개중에 2개를 선택하는 combination
		for (int i = 0; i < N; i++) {
			if(!v[i]) {
				v[i] = true;
				picks[cnt] = i;
				combi(i+1, cnt+1);
				v[i] = false;
			}
		}
		
	}

}
