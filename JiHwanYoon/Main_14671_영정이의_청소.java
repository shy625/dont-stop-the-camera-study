

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14671_영정이의_청소 {
	static int N, M;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		// 체스판으로 비유할 때, 검정색 칸과 흰색 칸에 적어도 하나의 곰팡이가 있어야 검정색 칸과 흰색 칸에 곰팡이가 생길 수 있고,
		// 동일한 색의 칸을 모두 채우려면 곰팡이가 적어도 하나씩은 홀수번째 행과 짝수번째 행에 있어야 한다.
		boolean[] isPossible = new boolean[4];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if (r%2 == 0) {
				if ((r+c)%2 == 0) isPossible[0] = true;
				else isPossible[1] = true;
			} else {
				if ((r+c)%2 == 0) isPossible[2] = true;
				else isPossible[3] = true;
			}
		}
		if (isPossible[0] && isPossible[1] && isPossible[2] && isPossible[3]) System.out.println("YES");
		else System.out.println("NO");
	}

}
