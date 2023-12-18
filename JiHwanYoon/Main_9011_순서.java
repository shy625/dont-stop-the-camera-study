

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_9011_순서 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		outer: for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			int[] R = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				R[j] = Integer.parseInt(st.nextToken());
			}
			// 해를 구성하기 위해, R 배열의 오른쪽부터 보면서
			// 지금까지 순서에 넣은 숫자를 제외하고, 1부터 숫자를 셌을 때 개수가 R[i]가 될 때의 숫자를 S[i]로 한다. 
			int[] S = new int[N];
			boolean[] visited = new boolean[N+1]; // 순서에 넣은 숫자를 표시하는 배열
			for (int i = N-1; i >= 0; i--) {
				int j = 1; // 1부터 세어나간다.
				int cnt = -1; // 현재까지 센 숫자의 개수
				while (j <= N && cnt != R[i]) {
					if (!visited[j++]) cnt++;
				}
				// 불가능한 경우
				if (cnt != R[i]) {
					sb.append("IMPOSSIBLE").append("\n");
					continue outer;
				}
				j--; // 숫자 보정
				S[i] = j;
				visited[j] = true;
			}
			for (int i = 0; i < N; i++) {
				sb.append(S[i]).append(" ");
			}
			sb.setLength(sb.length()-1);
			sb.append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
