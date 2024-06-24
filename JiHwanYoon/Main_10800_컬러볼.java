

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_10800_컬러볼 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] balls = new int[N][3];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			balls[i][0] = i+1;
			balls[i][1] = Integer.parseInt(st.nextToken());
			balls[i][2] = Integer.parseInt(st.nextToken());
		}
		// 크기가 작은 공부터, 크기가 같다면 같은 색끼리 인접하도록 공들을 정렬
		// 단, 정렬하면서 index를 잃어버릴 위험이 있어 balls에 index도 따로 저장해 두었다.
		Arrays.sort(balls, (x, y) -> x[2] == y[2] ? Integer.compare(x[1], y[1]) : Integer.compare(x[2], y[2]));
		// 일반적으로 모든 공에 대해 각각 잡을 수 있는 공들의 크기의 합을 구하면 시간 초과가 난다.
		// 1. 모든 공이 다른 크기라 가정
		// 작은 공부터 크기의 합을 계속 누적해서 더해가면서, 그 공들의 크기의 총합에서 현재 공의 색과 같은 색의 공들의 크기의 합을 빼주면 된다.
		// 따라서 sums 배열을 만들어 지금까지 나온 공들 중 i번째 색인 공들의 크기의 총합을 sums[i]에 저장한다.
		// 2. 일반적인 경우
		// 크기가 같은 공이 있는 경우도 생각해야 한다.
		int[] sums = new int[N+1];
		int sum = 0; // 지금까지 나온 공의 크기의 누적 합
		int temp_color = 0; // 현재 공의 색과 같으면서 크기도 같은 공의 크기의 총합
		int temp_size = 0; // 현재 공의 크기와 같은 공의 크기의 총합
		int prev_color = 0; // 이전 공의 색
		int prev_size = 0; // 이전 공의 크기
		int[] res = new int[N+1]; // 각 공이 잡을 수 있는 공들의 크기의 총합
		for (int i = 0; i < N; i++) {
			// 공의 크기가 달라지거나, 공의 색이 달라지면 
			// color 부분을 sums에 update한다.
			if (prev_size != balls[i][2] || prev_color != balls[i][1]) {
				sums[prev_color] += temp_color;
				temp_color = 0;
				prev_color = balls[i][1];
			}
			// 공의 크기가 달라지면 size 부분을 sum에 update한다.
			if (prev_size != balls[i][2]) {
				sum += temp_size;
				temp_size = 0;
				prev_size = balls[i][2];
			}
			// 현재 공의 크기를 임시 변수에 저장해둔다.
			temp_color += balls[i][2];
			temp_size += balls[i][2];
			// 1번에서 제시한 아이디어대로 결과값 계산
			res[balls[i][0]] = sum - sums[balls[i][1]];
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			sb.append(res[i]).append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
