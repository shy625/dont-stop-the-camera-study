import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_11497_통나무건너뛰기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			Integer [] nums = new Integer[N];
			int max = -1;
			// 가운데를 가장 큰 값으로 만들기 위해 앞, 뒤로 값을 넣기 편한 덱을 사용한다.
			ArrayDeque<Integer> dq = new ArrayDeque<>();
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			
			// 덱에 큰 수부터 하나씩 넣기 위해 역순으로 정렬해준다.
			Arrays.sort(nums, Collections.reverseOrder());
			
			// 가장 큰 수를 가운데에 넣어준다.
			dq.add(nums[0]);
			
			// 앞 쪽에 한 번, 뒷 쪽에 한 번 총 N/2번 넣어준다.
			for (int i = 1; i <= N/2; i++) {
				
				// 앞 쪽에 넣는 경우
				int nF = nums[i*2-1];
				max = Math.max(max, Math.abs(nF-dq.getFirst()));
				dq.addFirst(nF);
				
				// 뒷 쪽에 넣는 경우, N이 짝수일 경우 뒤에 넣지 않아도 된다.
				if(i*2 < N) {
					int nL = nums[i*2];
					max = Math.max(max, Math.abs(nL-dq.getLast()));
					dq.addLast(nL);
				}
			}
			
			// 맨 앞과 맨 뒤의 차이도 고려한다.
			max = Math.max(max, Math.abs(dq.getFirst()-dq.getLast()));
			
			System.out.println(max);

		}

	}

}
