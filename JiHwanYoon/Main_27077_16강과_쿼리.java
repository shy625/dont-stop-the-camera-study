

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_27077_16강과_쿼리 {
	// 팀에 해당하는 국가와, 득점, 실점, 승점을 나타내는 클래스
	static class Team implements Comparable<Team> {
		String teamName;
		int get, lose, win;
		public Team(String teamName, int get, int lose, int win) {
			this.teamName = teamName;
			this.get = get;
			this.lose = lose;
			this.win = win;
		}
		// 순위를 매기기 위한 compareTo 함수
		public int compareTo(Team t) {
			return this.win == t.win ? 
						this.get - this.lose == t.get - t.lose ?
							this.get == t.get ? 
								// 재경기하는 경우 korea의 우선순위가 맨 뒤가 되도록 한다.
								Integer.compare(idxs.get(t.teamName), idxs.get(this.teamName))
							: Integer.compare(t.get, this.get)
						: Integer.compare(t.get - t.lose, this.get - this.lose)
					: Integer.compare(t.win, this.win);
		}
	}
	static Team[] teams;
	static String[] teamNames = {"korea", "uruguay", "ghana", "portugal"}; // 팀에 해당하는 국가
	static Map<String, Integer> idxs, enemies; // 각 팀의 인덱스 및 적 팀의 인덱스
	static int[] score; // 현재 점수
	static StringBuilder sb;
	static Team[] sortedTeams; // 순위대로 정렬된 팀들의 배열
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		teams = new Team[4];
		sortedTeams = new Team[4];
		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int get = Integer.parseInt(st.nextToken());
			int lose = Integer.parseInt(st.nextToken());
			int win = Integer.parseInt(st.nextToken());
			teams[i] = new Team(teamNames[i], get, lose, win);
		}
		sortedTeams = Arrays.copyOf(teams, 4);
		idxs = new HashMap<>();
		idxs.put("korea", 0); idxs.put("uruguay", 1); idxs.put("ghana", 2); idxs.put("portugal", 3);
		enemies = new HashMap<>();
		enemies.put("korea", 3); enemies.put("uruguay", 2); enemies.put("ghana", 1); enemies.put("portugal", 0);
		score = new int[4];
		sb = new StringBuilder();
		checkWin();
		int N = Integer.parseInt(br.readLine());
		// 골을 넣을 때마다 득점, 실점, 점수를 정리하고 순위를 매긴다.
		for (int i = 0; i < N; i++) {
			String getTeam = br.readLine();
			score[idxs.get(getTeam)]++;
			teams[idxs.get(getTeam)].get++;
			teams[enemies.get(getTeam)].lose++;
			checkWin();
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	// 현재 경기 상태로 모든 경기가 종료되었을 때 순위를 매기는 함수
	private static void checkWin() {
		// 한국과 포르투갈에 대한 승점을 매긴다.
		if (score[0] == score[3]) {
			teams[0].win += 1;
			teams[3].win += 1;
		} else if (score[0] > score[3]) {
			teams[0].win += 3;
		} else {
			teams[3].win += 3;
		}
		// 가나와 우루과이에 대한 승점을 매긴다.
		if (score[1] == score[2]) {
			teams[1].win += 1;
			teams[2].win += 1;
		} else if (score[1] > score[2]) {
			teams[1].win += 3;
		} else {
			teams[2].win += 3;
		}
		// 별도의 배열을 두고 정렬하여, teams 배열의 팀 순서에 영향이 가지 않도록 한다.
		Arrays.sort(sortedTeams);
		// 한국 16강 진출이 확실한 경우
		if (sortedTeams[0].teamName.equals("korea") || sortedTeams[1].teamName.equals("korea")) {
			sb.append("cry");
		// 한국 16강 진출이 불가능하거나 불확실한 경우
		} else {
			sb.append("unhappy");
		}
		sb.append("\n");
		// 승점을 맨 처음 상태로 되돌려서 함수를 재실행하는 경우를 대비
		if (score[0] == score[3]) {
			teams[0].win -= 1;
			teams[3].win -= 1;
		} else if (score[0] > score[3]) {
			teams[0].win -= 3;
		} else {
			teams[3].win -= 3;
		}
		if (score[1] == score[2]) {
			teams[1].win -= 1;
			teams[2].win -= 1;
		} else if (score[1] > score[2]) {
			teams[1].win -= 3;
		} else {
			teams[2].win -= 3;
		}
	}

}
