import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_S2_2232_지뢰 {
	
	// 1 2 3 4 3 2 1
	// 1 2 4 3 5 2 1
	// 1 2 3 2 5
	// 5 5 5 5 5 5

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int past = -1;
		
		// 배열의 상황이 올라가는 중이면 false, 내려가는 중이면 true
		boolean downward = false;
		
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(br.readLine());
			// 이전 수보다 같거나 커지면 downward를 false로 바꿔준다.
			if(downward) {
				if(past <= n) {
					downward = false;
				}
			// 이전 수보다 같거나 작아지면 해당 지뢰는 무조건 터트려야함.
			} else {
				if(past > n) {
					downward = true;
					sb.append(i+"\n");
				} else if(past == n) {
					sb.append(i+"\n");
				}
			}
			past = n;
			if(i == N-1) {
				if(downward) {
					continue;
				} else {
					sb.append(N);
				}
			}
		}
		
		System.out.println(sb.toString());

	}

}
