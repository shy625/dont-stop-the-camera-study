/**
 * BJ 7576 토마토
 * @ prob : https://www.acmicpc.net/problem/7576
 * @ level : G5
 * @ blog : 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Week3_BJ_7576_토마토 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine(), " ");
        int M = Integer.parseInt(st.nextToken());   // 가로
        int N = Integer.parseInt(st.nextToken());   // 세로
        
        int[][] tomatoes = new int[N][M];
        Queue<int[]> q = new LinkedList<>();
        int unripeCount = M * N;    // 덜익은 토마토 개수 = 전체 상자 칸 수로 초기화

        // 토마토 입력 처리
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < M; j++) {
                tomatoes[i][j] = Integer.parseInt(st.nextToken());
                if(tomatoes[i][j] == 1) {   // 익은 토마토
                    q.offer(new int[]{i, j});   // BFS 큐에 넣음
                } else if(tomatoes[i][j] == -1) {   // 토마토 없는 칸
                    unripeCount--;      // 빈 칸 제거
                }
            }
        }

        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        int day = 1;
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            unripeCount--;      // 익은 토마토 제거
            for(int d = 0; d < 4; d++) {    // 익은 토마토 주위 4방향 체크
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                if((0 <= nr && nr < N && 0 <= nc && nc < M) && tomatoes[nr][nc] == 0) {     // 배열 범위 내에 있으면서 익지 않은 토마토인 경우
                    tomatoes[nr][nc] = tomatoes[cur[0]][cur[1]] + 1;    // 현재 토마토가 익은 날짜 + 1
                    day = tomatoes[nr][nc];     // 가장 마지막에 나온 날짜가 가장 최대값
                    q.offer(new int[]{nr, nc});     // 큐에 넣기
                }
            }
        }

        System.out.println(unripeCount > 0 ? -1 : day - 1);     // 덜 익은 토마토가 남아 있으면 -1, 아니면 day - 1

        br.close();
    }
}

// 풀이 1
// BFS를 이용해 모든 칸을 한번 순회하면서 날짜 업데이트 후 전체 칸을 돌아보면서 덜 익은 토마토가 남아있는지 확인 및 날짜 최댓값 체크하여 출력
// https://www.acmicpc.net/source/39598234
// 732ms

// 풀이 2
// 풀이 1에 덜 익은 토마토 개수 변수와 날짜 변수를 추가해 BFS 이후 출력값 확인을 위한 반복문 제거
// 위 코드
// 616ms