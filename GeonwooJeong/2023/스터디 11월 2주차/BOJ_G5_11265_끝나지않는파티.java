import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_11265_끝나지않는파티 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int [][] map = new int[N][N];
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 플로이드 워셜 알고리즘 사용
		// 0~N번 노드를 모두 확인해본다.
		for (int k = 0; k < N; k++) {
			// 노드 i에서 부터 시작하여
			for (int i = 0; i < N; i++) {
				// 노드 j로 가는 경우
				for (int j = 0; j < N; j++) {
					// i->k->j 시간이 i->j 시간보다 적을 경우 시간을 새로 갱신해준다.
					if(map[i][k] + map[k][j] < map[i][j]) {
						map[i][j] = map[i][k] + map[k][j];
					}
				}
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken())-1;
			int B = Integer.parseInt(st.nextToken())-1;
			int C = Integer.parseInt(st.nextToken());
			
			// A->B로 가는 데 C시간 이하로 걸릴 경우 정답 출력
			if(map[A][B] <= C) {
				sb.append("Enjoy other party\n");
			// C시간 초과로 걸릴 경우 오답 출력
			} else {
				sb.append("Stay here\n");
			}
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
		
	}

}
