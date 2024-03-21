import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_G4_21940_가운데에서만나기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int [][] dist = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			// dist 배열을 INF로 채워준다.
			Arrays.fill(dist[i], 200001);
			// 자기 자신으로 가는 거리는 0이다.
			dist[i][i] = 0;
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			int cost = Integer.parseInt(st.nextToken());
			
			dist[a][b] = cost;
		}
		
		int K = Integer.parseInt(br.readLine());
		int [] cities = new int[K];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			cities[i] = Integer.parseInt(st.nextToken())-1;
		}
		
		// 플로이드 워셜 알고리즘
		// 경유지
		for (int k = 0; k < N; k++) {
			// 출발지
			for (int i = 0; i < N; i++) {
				// 도착지
				for (int j = 0; j < N; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}
		
		// 왕복거리의 최소거리를 저장할 변수
		int minDist = Integer.MAX_VALUE;
		// 왕복거리가 같을 경우 여러개를 저장하기 위한 리스트
		List<Integer> list = new ArrayList<Integer>();
		
		// 왕복거리 최소 계산
		for (int i = 0; i < N; i++) {
			int max = -1;
			for (int j = 0; j < K; j++) {
				int k = cities[j];
				int distance = dist[i][k] + dist[k][i];
				max = Math.max(max, distance);
			}
			// 왕복거리가 최대가 더 작으면 list를 비워주고 값을 갱신해준다.
			if(max < minDist) {
				minDist = max;
				list.clear();
				list.add(i+1);
			// 왕복거리가 같으면 list에 해당 도시를 추가해준다.
			} else if(max == minDist) {
				list.add(i+1);
			}
			
		}
		
		StringBuilder sb = new StringBuilder();
		
		// 0번부터 N-1번 도시까지 순서대로 계산했기 때문에 자동으로 오름차순이다.
		for(int n : list) {
			sb.append(n+" ");
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
		
	}

}
