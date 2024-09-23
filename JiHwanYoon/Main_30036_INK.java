

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_30036_INK {
	static int[] dr = {-1, 1};
	static int[] dc = {1, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int I = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		// int K = Integer.parseInt(st.nextToken());
		String inkStr = br.readLine();
		int[] box = new int[2]; // 박스의 위치
		char[][] map = new char[N][N];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = s.charAt(j);
				// 박스가 있던 곳은 빈 칸으로 초기화
				if (map[i][j] == '@') {
					box = new int[] {i, j};
					map[i][j] = '.';
				}
			}
		}
		int curInk = 0; // 현재 박스가 가지고 있는 잉크의 양
		int jumpCnt = 0; // 박스가 점프한 횟수
		String command = br.readLine();
		for (char c : command.toCharArray()) {
			if (c == 'U') { // 위로 이동
				if (box[0] > 0 && map[box[0]-1][box[1]] == '.') box[0]--;
			} else if (c == 'D') { // 아래로 이동
				if (box[0] < N-1 && map[box[0]+1][box[1]] == '.') box[0]++;
			} else if (c == 'L') { // 왼쪽으로 이동
				if (box[1] > 0 && map[box[0]][box[1]-1] == '.') box[1]--;
			} else if (c == 'R') { // 오른쪽으로 이동
				if (box[1] < N-1 && map[box[0]][box[1]+1] == '.') box[1]++;
			} else if (c == 'j') { // 잉크 충전
				curInk++;
			} else if (c == 'J') { // 점프
				// curIdx의 거리만큼 떨어진 모든 장애물의 색을 inkStr의 jumpCnt%I번째 색으로 칠한다.
				for (int l = 1; l <= curInk; l++) {
					for (int i = 0; i <= l; i++) {
						int j = l-i;
						for (int d1 = 0; d1 < 2; d1++) {
							for (int d2 = 0; d2 < 2; d2++) {
								int nr = box[0] + dr[d1]*i;
								int nc = box[1] + dc[d2]*j;
								if (0<=nr && nr<N && 0<=nc && nc<N && map[nr][nc] != '.') {
									map[nr][nc] = inkStr.charAt(jumpCnt%I);
								}
							}
						}
					}
				}
				curInk = 0;
				jumpCnt++;
			}
		}
		// 출력
		map[box[0]][box[1]] = '@';
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
