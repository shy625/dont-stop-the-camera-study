import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16986_인싸들의가위바위보 {

	// 진행 순서가 고정된 것이 뒷사람이 승리라는 뜻이 아니라 진짜 진행하는 사람들의 순서를 고정했다는 뜻
	static int N, K;
	static int[][] map;
	static int[] kh;// 경희
	static int[] mh;// 민호
	static boolean[] jw;// 지우
	static int answer;
	static int max;
	static String[] player = { "jw", "kh", "mh" };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 경희 입력
		kh = new int[20];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 20; i++) {
			kh[i] = Integer.parseInt(st.nextToken());
		}

		// 민호 입력
		mh = new int[20];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 20; i++) {
			mh[i] = Integer.parseInt(st.nextToken());
		}

		// 지우가 어떤걸 냈는지 체크하면서 진행?
		jw = new boolean[N + 1];
		answer = 0;
		max = 3 * (K - 1) + 1;
		Play(new boolean[] { true, true, false }, 0, 0, 0, 0, 0);// 플레이어 정보,지우승,경희승,민호승,경희순서,민호순서
		System.out.println(answer);
	}

	private static void Play(boolean[] player, int jwWin, int khWin, int mhWin, int khT, int mhT) {
		// 끝내는 경우들
		// System.out.println(p1+" vs "+p2+" "+jwWin+","+khWin+","+mhWin);
		if (answer == 1)
			return;

		if (jwWin == K) {
			answer = 1;
			return;
		}
		if (khWin == K || mhWin == K)
			return;

		// 가위바위보
		// 다음 게임 참가할 사람 한명 구해놓기
		int other = 0;
		for (int i = 0; i < 3; i++) {
			if (!player[i])
				other = i;
		}
		// 플레이어 두명 알아내기
		if (player[0]) {// 지우 참전
			// 지우가 낼 가지수 구하기
			for (int i = 1; i <= N; i++) {
				if (jw[i])
					continue;// 낼 수 없음

				jw[i] = true;

				int p1Num = i;// 지우가 낼 종류
				int p2Num = 0;// 상대가 낼 종류
				if (player[1]) {// vs 경희
					p2Num = kh[khT];

					if (map[p1Num][p2Num] == 0 || map[p1Num][p2Num] == 1) {// 경희 승
						Play(new boolean[] { false, true, true }, jwWin, khWin + 1, mhWin, khT + 1, mhT);
					} else {// 지우 승
						Play(new boolean[] { true, false, true }, jwWin + 1, khWin, mhWin, khT + 1, mhT);
					}
				} else {// vs 민호
					p2Num = mh[mhT];

					if (map[p1Num][p2Num] == 0 || map[p1Num][p2Num] == 1) {// 민호 승
						Play(new boolean[] { false, true, true }, jwWin, khWin, mhWin + 1, khT, mhT + 1);
					} else {// 지우 승
						Play(new boolean[] { true, true, false }, jwWin + 1, khWin, mhWin, khT, mhT + 1);
					}
				}

				jw[i] = false;
			}
		} else {// 경희 vs 민호
			int p1Num = kh[khT];
			int p2Num = mh[mhT];
			if (map[p1Num][p2Num] == 0 || map[p1Num][p2Num] == 1) {// 민호 승
				Play(new boolean[] { true, false, true }, jwWin, khWin, mhWin + 1, khT + 1, mhT + 1);
			} else {// 경희 승
				Play(new boolean[] { true, true, false }, jwWin, khWin + 1, mhWin, khT + 1, mhT + 1);
			}
		}
	}

}
