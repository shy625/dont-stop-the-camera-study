

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_3190_뱀 {
	static int[][] map; // 보드
	static int[] turn = new int[10001]; // 방향 전환 기록을 위한 배열
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		map = new int[N][N];
		map[0][0] = -1; // 뱀이 있는 구역은 -1로 표시
		StringTokenizer st;
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[r-1][c-1] = 1; // 사과가 있는 구역은 1로 표시
		} // 여기까지 하면 뱀이 있는 구역은 -1, 빈 구역은 0, 사과가 있는 구역은 1이 된다.
		int L = Integer.parseInt(br.readLine());
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			String s = st.nextToken();
			// 방향 변환 정보를 -1 또는 1로 표시해 dr과 dc에서 이용한다.
			// -1인 경우 왼쪽으로 회전, 즉 반시계 방향으로 회전하고, 1인 경우 오른쪽으로 회전, 즉 시계 방향으로 회전한다.
			if (s.equals("L")) {
				turn[t] = -1;
			} else if (s.equals("D")) {
				turn[t] = 1;
			}
		}
		int sec = 0; // 흐른 시간
		int d = 0; // 현재 방향((dr[0], dc[0]) = (0, 1), 즉 오른쪽 방향)
 		int r = 0; // 뱀의 머리 행 번호
		int c = 0; // 뱀의 머리 열 번호
		Queue<int[]> snake = new LinkedList<>(); // 뱀이 있는 구역을 머리부터 꼬리까지 모아둔 큐
		snake.offer(new int[] {0, 0}); // 초기값 설정
		while (true) {
			sec++;
			// 뱀 이동
			r += dr[d]; 
			c += dc[d];
			// 만약 경계를 벗어나거나 뱀의 몸 일부와 부딪치면 게임이 끝난다.
			if (r < 0 || r >= N || c < 0 || c >= N || map[r][c] == -1) {
				break;
			} else if (map[r][c] == 0) { // 뱀이 빈 공간에 접근하면 뱀의 꼬리 부분을 비워줘야 한다.
				int[] arr = snake.poll();
				map[arr[0]][arr[1]] = 0;
			}
			// 뱀의 머리가 진행하였으므로 새로운 뱀의 머리 위치를 저장한다.
			// 여기서 사과가 있는 경우는 고려하지 않았는데, 
			// 이는 사과가 있는 경우에는 꼬리 부분을 굳이 고려할 필요가 없기 떄문이다.
			snake.offer(new int[] {r, c});
			map[r][c] = -1;
			d = (d+turn[sec]+4)%4; // 방향 변환이 있는 경우에는 방향 변환을 해준다.
		}
		System.out.println(sec);
	}

}
