

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_14222_배열과_연산 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		// 그리디 알고리즘을 활용
		// 우선, 입력으로 주어진 배열을 정렬한 뒤에
		// 앞 숫자부터 방문 배열에 대한 다음과 같은 절차를 거친다.
		// 1. 만약 해당 숫자를 방문한 적이 없으면 방문 처리
		// 2. 만약 해당 숫자를 방문한 적이 있으면 N을 넘어설 때까지 K를 더하면서 방문한 적이 없는지 확인
		//    방문한 적이 없는 숫자를 발견하면 방문 처리
		// 3. 1 혹은 2에서 N을 넘어가는 숫자가 있으면 실패
		// 모든 숫자에 대해 방문 처리가 가능하면 성공
		Arrays.sort(arr);
		boolean[] visited = new boolean[N+1];
		for (int n : arr) {
			if (n <= N && !visited[n]) {
				visited[n] = true;
			} else {
				int temp = n;
				do {
					temp += K;
				} while (temp <= N && visited[temp]);
				if (temp <= N) {
					visited[temp] = true;
				} else {
					System.out.println(0);
					System.exit(0);
				}				
			}
		}
		System.out.println(1);		
	}

}
