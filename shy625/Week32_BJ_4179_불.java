/**
 * BJ 4179 불!
 * @ prob : https://www.acmicpc.net/problem/4179
 * @ level : G4
 */

import java.io.*;
import java.util.*;

public class Week32_BJ_4179_불 {

    private static final int[] dr = { -1, 0, 1, 0 };
    private static final int[] dc = { 0, 1, 0, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        char[][] maze = new char[R][C];
        int[] startPos = new int[2];
        List<int[]> firePosList = new ArrayList<>();

        for (int r = 0; r < R; r++) {
            maze[r] = br.readLine().toCharArray();
            for (int c = 0; c < C; c++) {
                if (maze[r][c] == 'J') {
                    startPos[0] = r;
                    startPos[1] = c;
                    maze[r][c] = '.';
                } else if (maze[r][c] == 'F') {
                    firePosList.add(new int[] { r, c });
                }
            }
        }

        int result = escapeMaze(R, C, maze, startPos, firePosList);

        System.out.println(result > 0 ? result : "IMPOSSIBLE");

        br.close();
    }
    
    // BFS
    private static int escapeMaze(int R, int C, char[][] maze, int[] startPos, List<int[]> firePosList) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] isVisited = new boolean[R][C];
        int time = 0;

        // 초기 상태에서 탈출 가능 여부 먼저 체크
        if (isBorder(startPos[0], startPos[1], R, C)) {
            return time + 1;
        }

        q.offer(startPos);
        isVisited[startPos[0]][startPos[1]] = true;

        while (!q.isEmpty()) {
            time++;

            // 불 확산
            List<int[]> newFirePosList = new ArrayList<>();
            for (int[] firePos : firePosList) {
                for (int d = 0; d < 4; d++) {
                    int nr = firePos[0] + dr[d];
                    int nc = firePos[1] + dc[d];
                    if (isInOfBound(nr, nc, R, C) && maze[nr][nc] == '.') {
                        maze[nr][nc] = 'F';
                        newFirePosList.add(new int[] { nr, nc });
                    }
                }
            }
            firePosList = newFirePosList;   // 다음에는 새로 확산된 불만 체크

            // 지훈 이동
            // 직전 시간에 추가된 것만 체크
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();

                for (int d = 0; d < 4; d++) {
                    int nr = cur[0] + dr[d];
                    int nc = cur[1] + dc[d];
                    if (isInOfBound(nr, nc, R, C) && !isVisited[nr][nc] && maze[nr][nc] == '.') {
                        isVisited[nr][nc] = true;
                        q.offer(new int[] { nr, nc });
                        // 탈출
                        if (isBorder(nr, nc, R, C)) {
                            return time + 1;
                        }
                    }
                }
            }
        }
        return -1;
    }

    private static boolean isInOfBound(int r, int c, int R, int C) {
        return 0 <= r && r < R && 0 <= c && c < C;
    }

    private static boolean isBorder(int r, int c, int R, int C) {
        return r == 0 || r == R - 1 || c == 0 || c == C - 1;
    }
}

// 풀이 1 - 시간초과
// 실제로는 지훈의 이동과 불의 확산이 동시에 이루어짐
// 지훈이 이번 시간에 불이 확산될 곳으로 이동하면 안되기 때문에 "불의 확산 -> 지훈의 이동" 로직으로 동작
// 가장 빠르게 탈출하는 시간을 구하기 때문에 지훈의 위치에 대해 BFS 사용
// 큐에 지훈의 위치와 이동한 시간을 같이 넣고, 큐에서 뺄 때마다 직전에 이동한 것인지 확인 후 빼기
// 매번 미로 전체를 돌면서 불을 찾고 확산
// https://www.acmicpc.net/source/49265082

// 풀이 2 - 시간초과
// 불의 위치를 따로 리스트에 저장하여 미로 전체를 돌지 않고 불의 위치만 확인하여 확산
// 그 외는 풀이 1과 동일
// https://www.acmicpc.net/source/49266001

// 풀이 3
// 이미 이전에 확산된 불은 더 이상 확인할 필요 X
// 리스트에 모든 불의 위치를 저장하지 않고, 새로 확산된 불의 위치만 저장
// 처음 초기 상태에서 바로 탈출 가능한지 여부를 미리 체크
// 그 외는 풀이 2와 동일
// https://www.acmicpc.net/source/49266979

// 풀이 4
// 큐에 이동한 시간을 같이 넣지 않고 지훈의 위치만 넣기
// 직전 시간에 이동했는지 여부는 큐를 돌기 전에 큐에 있는 원소의 개수를 확인하여 원소의 개수만큼만 확인하는 것으로 변경
// 그 외는 풀이 3과 동일

// 다른 풀이 1
// 지훈인지 불인지 구분하는 상태값을 추가하여 지훈의 위치와 불의 위치를 모두 같은 큐에 넣고 돌림
// 큐에 불의 위치를 먼저 다 넣고, 그 다음에 지훈의 위치를 넣기 때문에 "불의 확산 -> 지훈의 이동" 이라는 로직은 동일
// 단, 불의 확산이 끝나면 자연스럽게 해당 시간대의 지훈의 이동이 이뤄지고,
// 지훈이 이동이 끝나면 다음 시간대의 불의 확산이 큐의 구조 상 자연스럽게 이뤄지기 때문에
// 불과 지훈의 위치를 분리하여 동작하는 경우처럼 직전 시간에 큐에 추가된 것인지 따로 체크할 필요 X
// https://www.acmicpc.net/source/41666467
// https://www.acmicpc.net/source/17905237