

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1107_리모컨 {
	static int N, M;
	static int[] nums;
	static int min;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		// 고장난 버튼 체크
		boolean[] check = new boolean[10];
		if (M > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < M; i++) {
				check[Integer.parseInt(st.nextToken())] = true;
			}
		}
		// 고장난 버튼을 제외한 나머지 버튼에 적힌 숫자를 기록
		nums = new int[10-M];
		int cnt = 0;
		for (int i = 0; i < 10; i++) {
			if (!check[i]) nums[cnt++] = i;
		}
		// 이제는 고장난 버튼의 개수가 아닌, 남아있는 버튼의 개수에 초점을 맞춤
		M = 10-M;
		// 현재 채널 번호가 100번
		// +, -만으로 갈 수 있는 방법을 먼저 생각
		min = Math.abs(N - 100);
		perm(0, 0);
		System.out.println(min);
	}
	// 중복순열을 활용
	private static void perm(int cnt, int channel) {
		if (cnt == 6) {
			return;
		}
		// 일의 자리부터 선택을 하면서 채널 이동을 했을 때 원하는 채널까지 +, - 버튼만을 이용해 가는 방법을 매번 생각 
		for (int i = 0; i < M; i++) {
			channel += nums[i]*Math.pow(10, cnt);
			min = Math.min(Math.abs(channel-N)+cnt+1, min);
			perm(cnt+1, channel);
			channel -= nums[i]*Math.pow(10, cnt);
		}
	}

}
