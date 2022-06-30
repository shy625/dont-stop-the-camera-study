/**
 * BJ 2594 놀이공원
 * @ prob : https://www.acmicpc.net/problem/2594
 * @ level : S2
 */

import java.io.*;
import java.util.*;

public class Week20_BJ_2594_놀이공원 {

    // 놀이기구 클래스
    static class Ride implements Comparable<Ride> {
        int start;  // 시작시간
        int end;    // 종료시간

        Ride(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Ride o) {
            return Integer.compare(start, o.start);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());
        Ride[] rides = new Ride[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = stringToMinute(st.nextToken());
            int end = stringToMinute(st.nextToken());
            rides[i] = new Ride(start, end);
        }

        Arrays.sort(rides);     // 시작시간이 빠른 순으로 정렬

        int maxRestTime = rides[0].start - 610;     // 놀이공원 시작시간과 첫번째 놀이기구 시작시간 사이의 쉬는 시간
        int restStart = rides[0].end, restEnd = 1320;
        for (int i = 1; i < rides.length; i++) {
            // 쉬는 시간의 시작시간보다 놀이기구의 시작시간이 늦는 경우, 그 사이의 쉬는 시간을 계산
            if (restStart < rides[i].start) {
                int restTime = rides[i].start - restStart - 20;
                maxRestTime = Math.max(maxRestTime, restTime);
            }
            restStart = Math.max(restStart, rides[i].end);  // 이전 종료시간과 현재 놀이기구의 종료시간 중 더 늦게 끝나는 값으로 설정
        }
        if (restStart < restEnd) {  // 최종적으로 모든 놀이기구가 종료되는 시간과 놀이공원 종료시간 사이의 쉬는 시간
            maxRestTime = Math.max(maxRestTime, restEnd - restStart - 10);
        }

        System.out.println(maxRestTime > 0 ? maxRestTime : 0);

        br.close();
    }

    // 입력된 HHmm 형식의 시간 문자열을 분으로 변환
    private static int stringToMinute(String str) {
        int hour = Integer.parseInt(str.substring(0, 2));
        int minute = Integer.parseInt(str.substring(2));
        return hour * 60 + minute;
    }
}

// 풀이 1
// 놀이기구 클래스를 만들어 기구의 시작시간으로 오름차순 정렬
// 놀이기구의 시작시간이 빠른 순으로 해당 놀이기구의 시작시간 이전의 쉬는 시간을 체크하면서 max 값 확인
// 현재 체크한 놀이기구보다 더 빨리 시작하는 놀이기구는 없으므로 그 다음에는 현재 놀이기구가 끝나는 시간 이후만 확인하면 됨
// 단, 이전 놀이기구가 종료되는 시간이 현재 놀이기구보다 늦을 수 있으므로 둘 중 더 늦게 끝나는 놀이기구 시간 이후로 체크
// 입력되는 시간 포맷은 분 단위로 바꿔서 계산

// 다른 풀이 1
// 10시 ~ 22시 구간의 모든 분(720분)을 배열로 선언
// boolean[] rest = new boolean[720];
// 입력되는 놀이기구 운영시간에 해당하는 시간 구간을 false로 처리하고
// 이후 배열에서 연속으로 true인 길이를 체크하여 그 중 max값 확인
// https://github.com/shy625/dont-stop-the-camera-study/blob/main/JiHwanYoon/Main_2594_놀이공원.java