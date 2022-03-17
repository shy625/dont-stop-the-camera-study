/**
 * BJ 15787 기차가 어둠을 헤치고 은하수를
 * @ prob : https://www.acmicpc.net/problem/15787
 * @ level : S1
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Week6_BJ_15787_기차가어둠을헤치고은하수를 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] trains = new int[N+1];    // 기차번호 1번부터 시작
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            
            int command = Integer.parseInt(st.nextToken());
            int trainNum = Integer.parseInt(st.nextToken());

            // 오른쪽 0번째 자리가 좌석번호 1번
            if(command == 1) {
                int seatNum = Integer.parseInt(st.nextToken());
                trains[trainNum] = trains[trainNum] | 1 << (seatNum - 1);   // 좌석 위치를 1로 변경

            } else if(command == 2) {
                int seatNum = Integer.parseInt(st.nextToken());
                trains[trainNum] = trains[trainNum] & ~(1 << (seatNum - 1));    // 좌석 위치를 0으로 변경

            } else if(command == 3) {
                trains[trainNum] = trains[trainNum] << 1;   // 뒤로 1칸씩 이동
                trains[trainNum] = trains[trainNum] & ((int)Math.pow(2, 20) - 1);   // 19번째(좌석번호 20번) 이후 자리는 0으로 수정

            } else if(command == 4) {
                trains[trainNum] = trains[trainNum] >> 1;   // 앞으로 1칸씩 이동
            }
        }

        // 자신 이후의 기차 중 동일한 좌석 배열의 기차 확인 및 제거
        int count = 0;
        for(int i = 1; i < N+1; i++) {
            if(trains[i] == -1) {
                count++;    // 제거된 기차 수 세기
                continue;
            }
            for(int j = i+1; j < N+1; j++) {
                if(trains[i] == trains[j]) {
                    trains[j] = -1;
                }
            }
        }

        System.out.println(N - count);  // 전체 기차 수 - 제거된 기차 수

        br.close();
    }
}

// 풀이 1
// 비트마스킹을 이용해 각 명령 처리, 자신 뒤로 오는 기차 중 자신과 동일한 기차는 제거하고 제거된 기차 수를 세서 통과된 기차 수 출력
// https://www.acmicpc.net/source/40246520