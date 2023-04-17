import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 0. 지도(N==홀수): N*N, 상어: (N+1)/2, 구슬: 1,2,3
// 1. 블리자드 시전: 방향 d와 거리 s로 구슬 파괴
// 2. 구슬 이동: 빈자리 채워서 이동
// 3. 구슬 폭발: 4개 이상 연속된 구슬 폭발 (답계산!)
// 4. 구슬 이동: 빈자리 채워서 이동
// 5. 3번-4번 반복
// 6. 구슬 변화: 연속된 구슬인 그룹은 A와 B로 바뀌는데, A는 구슬의 개수, B는 구슬의 번호
// 답: 1×(폭발한 1번 구슬의 개수) + 2×(폭발한 2번 구슬의 개수) + 3×(폭발한 3번 구슬의 개수)
//  폭발 != 파괴

public class Main_21611_마법사상어와블리자드 {
    static int N, M;
    static int[][] map;

    static int[][] tornado; // [num][xy]
    static int[] dr = { 0, -1, 1, 0, 0 }; // 상하좌우
    static int[] dc = { 0, 0, 0, -1, 1 }; // 상하좌우
    static int shark;

    static int[] boom = { 0, 0, 0, 0 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        shark = N / 2; // ==(N + 1) / 2 - 1;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        tornado = new int[N * N][2]; // tornado[0]은 사용X
        setTornado();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            castBlizzard(d, s);
        }

        int ans = boom[1] + boom[2] * 2 + boom[3] * 3;
        System.out.println(ans);
    }

    public static void setTornado() {
        int r = shark;
        int c = shark;
        int num = 1; // tornado num

        // 좌-하-우-상
        int[] gr = { 0, 1, 0, -1 };
        int[] gc = { -1, 0, 1, 0 };
        int go = 0;
        // 왼1, 밑1, 오2, 위2, 왼3, 밑3, 오4, 위4 반복
        int move = 0; // move num

        tornado[0][0] = r;
        tornado[0][1] = c;
        boolean isBreak = false;
        while (!isBreak) {
            // 왼/오 일 때 move 개수 늘어남
            if (go % 2 == 0) {
                move++;
            }

            for (int i = 0; i < move; i++) {
                if (num >= N * N) {
                    isBreak = true;
                    break;
                }

                r += gr[go];
                c += gc[go];
                tornado[num][0] = r;
                tornado[num][1] = c;
                num++;
            }

            go = (go + 1) % 4;
        }
    }

    public static void castBlizzard(int d, int s) {

        // 1. 블리자드 시전: 방향 d와 거리 s로 구슬 파괴
        for (int i = 1; i <= s; i++) {
            int br = shark + dr[d] * i;
            int bc = shark + dc[d] * i;
            map[br][bc] = 0;
        }

        // 2. 구슬 이동: 빈자리 채워서 이동
        moveBead();

        // 5. 3번-4번 반복
        boolean isBoom = true;
        while (isBoom) {
            // 3. 구슬 폭발: 4개 이상 연속된 구슬 폭발 (답계산!)
            isBoom = boomBead();

            if (isBoom) {
                // 4. 구슬 이동: 빈자리 채워서 이동
                moveBead();
            }
        }

        // 6. 구슬 변화: 연속된 구슬인 그룹은 A와 B로 바뀌는데, A는 구슬의 개수, B는 구슬의 번호
        map = changeBead();
    }

    // 구슬 이동: 빈자리 채워서 이동
    public static void moveBead() {
        for (int i = 1; i < N * N; i++) {
            int nr = tornado[i][0];
            int nc = tornado[i][1];

            if (map[nr][nc] == 0) {
                int find = findBead(i);
                if (find == 0) { // 더이상 구슬은 없음
                    break;
                }

                int fr = tornado[find][0];
                int fc = tornado[find][1];

                map[nr][nc] = map[fr][fc];
                map[fr][fc] = 0;
            }
        }
    }

    public static int findBead(int tornadoNum) {
        int ans = 0;

        for (int i = tornadoNum + 1; i < tornado.length; i++) {
            int nr = tornado[i][0];
            int nc = tornado[i][1];

            if (map[nr][nc] != 0) {
                ans = i;
                break;
            }
        }

        return ans;
    }

    // 구슬 폭발: 4개 이상 연속된 구슬 폭발 (답계산!)
    public static boolean boomBead() {
        boolean isBoom = false;

        int bead = 0;
        int continuous = 1;

        for (int i = 1; i <= N * N; i++) {
            int nr = tornado[i][0];
            int nc = tornado[i][1];

            if (i == N * N || map[nr][nc] == 0) {
                if (continuous >= 4 && bead != 0) {
                    boom[bead] += continuous; // 답계산

                    // 구슬폭발
                    for (int c = 1; c <= continuous; c++) {
                        int br = tornado[i - c][0];
                        int bc = tornado[i - c][1];
                        map[br][bc] = 0;
                    }
                    isBoom = true; // 폭발함
                }
                break;
            }

            // beadNum을 현재 구슬번호로 변경
            if (continuous < 4 && map[nr][nc] != bead) {
                bead = map[nr][nc];
                continuous = 1;
            }
            // 구슬 폭발 후, beadNum을 현재 구슬번호로 변경
            else if (bead != 0 && continuous >= 4 && map[nr][nc] != bead) {
                boom[bead] += continuous; // 답계산

                // 구슬 폭발
                for (int c = 1; c <= continuous; c++) {
                    int br = tornado[i - c][0];
                    int bc = tornado[i - c][1];
                    map[br][bc] = 0;
                }

                isBoom = true; // 폭발함
                bead = map[nr][nc]; // bead 변경
                continuous = 1; // 연속된거 초기화
            }
            // 전의 구슬번호와 같으면 continuous 증가
            else {
                continuous++;
            }
        }

        return isBoom;
    }

    // 구슬 변화: 연속된 구슬인 그룹은 A와 B로 바뀌는데, A는 구슬의 개수, B는 구슬의 번호
    public static int[][] changeBead() {
        int[][] newMap = new int[N][N];
        int bead = 0;
        int continuous = 1;

        int A, B = 0;
        int newMapNum = 0;

        for (int i = 1; i <= N * N; i++) {
            int nr = tornado[i][0];
            int nc = tornado[i][1];

            if (map[nr][nc] == 0 || i == N * N) {
                A = continuous;
                B = bead;

                newMapNum++;
                int ar = tornado[newMapNum][0];
                int ac = tornado[newMapNum][1];
                newMap[ar][ac] = A;

                newMapNum++;
                int br = tornado[newMapNum][0];
                int bc = tornado[newMapNum][1];
                newMap[br][bc] = B;

                break;
            }

            if (bead == 0) {
                bead = map[nr][nc]; // bead 변경
                continuous = 1; // 연속된거 초기화
            }
            // A,B 계산
            else if (map[nr][nc] != bead) {
                A = continuous;
                B = bead;

                newMapNum++;
                int ar = tornado[newMapNum][0];
                int ac = tornado[newMapNum][1];
                newMap[ar][ac] = A;

                newMapNum++;
                int br = tornado[newMapNum][0];
                int bc = tornado[newMapNum][1];
                newMap[br][bc] = B;

                bead = map[nr][nc]; // bead 변경
                continuous = 1; // 연속된거 초기화

            }
            // 전의 구슬번호와 같으면 continuous 증가
            else {
                continuous++;
            }

            if (newMapNum == N * N - 1) {
                break;
            }
        }

        // System.out.println("new");
        // for (int k = 0; k < N; k++) {
        // for (int j = 0; j < N; j++) {
        // System.out.print(newMap[k][j] + " ");
        // }
        // System.out.println();
        // }
        // System.out.println();

        return newMap;
    }

}
