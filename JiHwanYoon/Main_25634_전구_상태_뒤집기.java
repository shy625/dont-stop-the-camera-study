

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_25634_전구_상태_뒤집기 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] lights = new int[N];
		boolean[] isOn = new boolean[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		int min = Integer.MAX_VALUE; // 가장 어두운 전구의 밝기
		for (int i = 0; i < N; i++) {
			lights[i] = Integer.parseInt(st.nextToken());
			min = Math.min(min, lights[i]);
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			isOn[i] = Integer.parseInt(st.nextToken()) == 1;
		}
		// dynamic programming을 활용
		// 일단 처음 전구부터 상태를 뒤집어가면서, 밝기의 변화를 계산하고 이의 최댓값을 지속적으로 갱신한다.
		// 만약 밝기의 변화가 0보다 작아지면, 처음 전구부터 i번째 전구까지 상태를 뒤집으면 밝기의 합에서 손해를 보기 때문에,
		// 해당 전구들을 제외하고 (i+1)번째 전구부터 상태를 뒤집는 것을 다시 시도한다.
		// 이를 N번째 전구까지 반복한다.
		int sum = 0; // 총 밝기의 변화
		int max = 0; // 총 밝기의 변화의 최댓값
		int curSum = 0; // 현재 상태의 전구 밝기의 합
		for (int i = 0; i < N; i++) {
			if (isOn[i]) {
				sum -= lights[i];
				curSum += lights[i];
			}
			else sum += lights[i];
			// 밝기의 변화가 0보다 작아지면 초기화
			if (sum < 0) sum = 0;
			max = Math.max(max, sum);
		}
		// 만약 총 밝기의 변화의 최댓값이 0인 경우, 즉 모든 전구가 켜져있는 경우 가장 어두운 전구만 끈다.
		if (max == 0) System.out.println(curSum - min);
		else System.out.println(curSum + max);
	}
}
