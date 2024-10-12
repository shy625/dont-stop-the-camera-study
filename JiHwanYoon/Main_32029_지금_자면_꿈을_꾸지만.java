

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_32029_지금_자면_꿈을_꾸지만 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int[] homeworks = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
        	homeworks[i] = Integer.parseInt(st.nextToken());
        }
        // 과제를 기한이 빠른 순서대로 정려
        Arrays.sort(homeworks);
        int max = 0; // 기한 내에 완료할 수 있는 과제 수의 최댓값
        // 모든 경우를 고려한다.
        // X가 0 ~ A-1일 때, 잠을 0 ~ N-1번째 과제를 고려하기 전 잘 때를 모두 고려
        for (int X = 0; X < A; X++) {
            for (int i = 0; i < N; i++) {
            	int cnt = 0; // 기한 내 완료할 수 있는 과제 수
                int time = 0; // 경과한 시간
                // 잠을 자기 전 i-1번째 과제까지 기한 내에 완료할 수 있는 과제를 차례대로 완료한다.
                for (int j = 0; j < i; j++) {
                    if (time + A <= homeworks[j]) {
                    	time += A;
                    	cnt++;
                    }
                }
                // 잠을 잔다.
                time += B*X;
                // 잠을 잔 후 i번째 과제부터 N-1번째 과제까지 기한 내에 완료할 수 있는 과제를 차례대로 완료한다.
                for (int j = i; j < N; j++) {
                    if (time + (A - X) <= homeworks[j]) {
                    	time += A - X;
                    	cnt++;
                    }
                }
                max = Math.max(max, cnt);
            }
        }
        System.out.println(max);
	}
}
