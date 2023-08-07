import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G3_1082_방번호 {
	
	// 숫자가 가장 크려면 길이가 가장 길어야 한다.
	// 길이가 같다면, 앞에서부터 큰 수가 와야 큰 수이다.
	// 가장 싼 숫자가 0일 떄는 2번째로 싼 숫자를 맨 앞에 두어야 한다.
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		// 최종 방번호를 숫자 한 개씩 저장할 배열
		int [] ans = new int[51];
		// 방번호의 몇 번째 숫자인지를 나타낼 변수
		int idx = 0;
		// 각 번호의 비용을 저장할 배열
		int [] cost = new int[N];
		// 지불해야 하는 비용을 저장할 변수
		int sum = 0;
		
		// 가장 싼 번호를 찾기 위한 변수들
		int minIdx = 0;
		int min = Integer.MAX_VALUE;

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(st.nextToken());
			cost[i] = n;
			if(n <= min && i > 0) {
				minIdx = i;
				min = n;
			}
		}
		
		int M = Integer.parseInt(br.readLine());
		
		if(cost[0] < min) {
			// 첫 자리에 두 번째로 싼 숫자를 쓸 수 있을 경우에 해당 숫자를 사용하고 비용을 지불한다.
			if(cost[minIdx] <= M) {
				ans[idx++] = minIdx;
				sum += cost[minIdx];
				minIdx = 0;
			// 두 번째로 싼 숫자를 1개로 쓸 수 없을 때에는 0이 답이 된다.
			} else {
				System.out.println(0);
				System.exit(0);
			}
		}
		
		// 비용을 다 지불할 때 까지 최대한 길게 방번호를 만든다.
		while(cost[minIdx] + sum <= M) {
			ans[idx++] = minIdx;
			sum += cost[minIdx];
		}
		
		// 가장 앞의 숫자부터 최대한 큰 숫자로 바꾼다.
		for (int i = 0; i < idx; i++) {
			for (int j = N-1; j > minIdx; j--) {
				// i번째 숫자를 j와 교체할 수 있으면 그만큼 비용을 지불하고 교체한다.
				if(sum - cost[ans[i]] + cost[j] <= M) {
					sum = sum - cost[ans[i]] + cost[j];
					ans[i] = j;
					break;
				}
			}
		}
		
		for (int i = 0; i < idx; i++) {
			sb.append(ans[i]);
		}
		
		System.out.println(sb.toString());
		
	}

}
