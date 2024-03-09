

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1790_수_이어_쓰기_2 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int sum = 0; // 자릿수가 바뀔 때마다 총 숫자 개수의 누적합
        int base = 1;
        // 1~9, 10~99, ... 에 대해서는 일정한 길이 규칙이 있으므로, 
        // K가 sum + (해당 자릿수를 가지는 모든 숫자를 나열했을 때 숫자 개수의 합)보다 클 때 동안 다음 자릿수로 넘어간다.
        for (int i = 1; i <= 8; i++, base *= 10) {
            int cnt = base*9;
            if (sum + cnt*i < K) {
                sum += cnt*i;
                continue;
            }
            // 여기로 왔다는 것은 K번째 숫자가 들어있는 자릿수까지 왔다는 의미이다.
            int num = base + (K-sum-1)/i; // K번째 숫자가 들어있는 숫자를 구한다.
            if (num > N) System.out.println(-1); // num이 N을 넘어간다는 것은 k번째 숫자가 1~N까지의 숫자를 나열한 숫자에 없음을 의미
            else System.out.println(String.valueOf(num).charAt((K-sum-1)%i));
            return;
        }
        // N이 100,000,000일 때 고려
        System.out.println(K-sum <= 9 ? (K-sum == 0 ? 1 : 0) : -1);
	}

}
