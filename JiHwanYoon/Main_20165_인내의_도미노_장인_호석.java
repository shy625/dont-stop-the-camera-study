

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_20165_인내의_도미노_장인_호석 {
	static int N, M, R;
	static int[][] map;
	static boolean[][] states;
	static int score;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		// 각 도미노의 길이 저장
		map = new int[N][M];
		// 각 도미노가 서 있는 상태인지, 혹은 넘어진 상태인지를 저장
		// 만약 넘어진 상태라면 true
		states = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 구현을 보다 쉽게 하기 위해 방향을 나타내는 문자를 정수로 변환(dr, dc 배열 인덱스에 대응)
		Map<Character, Integer> direction = new HashMap<>();
		direction.put('N', 0); direction.put('E', 1); direction.put('S', 2); direction.put('W', 3);
		int r, c, d;
		// 공격과 수비 입력을 받고 이에 따른 states 및 score 기록
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken())-1;
			c = Integer.parseInt(st.nextToken())-1;
			d = direction.get(st.nextToken().charAt(0));
			attack(r, c, d);
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken())-1;
			c = Integer.parseInt(st.nextToken())-1;
			states[r][c] = false;
		}
		// 출력
		StringBuilder sb = new StringBuilder();
		// 점수
		sb.append(score).append("\n");
		// 현재 보드의 상태
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (states[i][j]) sb.append("F ");
				else sb.append("S ");
			}
			sb.setLength(sb.length()-1);
			sb.append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	// 공격하는 경우
	private static void attack(int r, int c, int d) {
		int nr = r;
		int nc = c;
		int l = map[r][c]; // 앞으로 쓰러뜨릴 수 있는 도미노의 개수
		// 다음 도미노를 쓰러뜨릴 수 있는 경우에 한해 while 문을 순회한다.
		while (0<=nr && nr<N && 0<=nc && nc<M && --l >= 0) {
			// 현재 위치의 도미노가 이미 쓰러진 경우
			if (states[nr][nc]) {
				nr += dr[d];
				nc += dc[d];
				continue;
			}
			// 쓰러뜨릴 수 있는 도미노 개수 수정
			// 만약 길이가 긴 도미노를 쓰러뜨리면 쓰러뜨릴 수 있는 도미노의 개수가 증가한다.
			l = Math.max(map[nr][nc]-1, l);
			// 쓰러뜨린 도미노 상태 수정
			states[nr][nc] = true;
			// 점수 증가
			score++;
			// 다음 도미노 탐색
			nr += dr[d];
			nc += dc[d];
		}
	}
}
