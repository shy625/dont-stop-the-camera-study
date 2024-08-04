

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1821_수들의_합_6 {
	static int N, F;
	static int[] p;
	static boolean[] visited;
	static int[][] arr;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		F = Integer.parseInt(st.nextToken());
		// 브루트포스 알고리즘을 활용
		p = new int[N];
		visited = new boolean[N+1];
		arr = new int[N][N]; // 파스칼 삼각형을 구현하기 위한 배열
		perm(0);
	}
	// 브루트포스 알고리즘을 활용해 F를 만들 수 있는 파스칼 삼각형을 구하는 함수
	private static void perm(int cnt) {
		if (cnt == N) {
			// p 배열을 통해 F를 만들 수 있는 경우
			if (check()) {
				// 가장 사전 순으로 앞에 오는 배열을 출력
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < N; i++) {
					sb.append(p[i]).append(" ");
				}
				sb.setLength(sb.length()-1);
				System.out.println(sb.toString());
				System.exit(0);
			}
		}
		for (int i = 1; i <= N; i++) {
			if (visited[i]) continue;
			visited[i] = true;
			p[cnt] = i;
			perm(cnt+1);
			visited[i] = false;
		}	
	}
	// 파스칼 삼각형을 p 배열을 이용해 만들었을 때 맨 밑 숫자가 F가 나오는지 확인하는 함수
	private static boolean check() {
		for (int i = 0; i < N; i++) {
			arr[0][i] = p[i];
		}
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < N - i; j++) {
				arr[i][j] = arr[i-1][j] + arr[i-1][j+1];
			}
		}
		return arr[N-1][0] == F;
	}

}
