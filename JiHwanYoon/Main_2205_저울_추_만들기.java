

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2205_저울_추_만들기 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		// 저울 추를 이용해 만들 수 있는 무게의 최댓값을 구한다.
		int limit = 1;
		while (limit <= n) {
			limit *= 2;
		}
		// greedy algorithm을 활용
		// 가장 무거운 납덩어리부터, 가장 큰 2의 거듭제곱 무게를 만들 수 있는 주석덩어리를 찾는다.
		boolean[] visited = new boolean[n+1]; // 이미 납덩어리와 합친 주석덩어리들의 무게를 방문 표시하는 배열
		int[] res = new int[n];
		for (int i = n; i >= 1; i--) {
			// 가능한 2의 거듭제곱 무게를 최댓값부터 시도
			for (int j = limit; j >= 1; j /= 2) {
				// 납덩어리와 주석덩어리를 합쳐 2의 거듭제곱 무게를 만들 수 있는 경우
				if (j-i <= n && !visited[j-i]) {
					res[i-1] = j-i;
					visited[j-i] = true;
					break;
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append(res[i]).append("\n");
		}
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
}
