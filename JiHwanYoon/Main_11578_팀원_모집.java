

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_11578_팀원_모집 {
	static int N, M;
	static int[] problems;
	static int min;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// 각 학생마다 풀 수 있는 문제들을 나타내기 위해 비트마스킹을 활용
		problems = new int[M];
		// 골라야 하는 학생 수의 최솟값
		min = Integer.MAX_VALUE;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			for (int j = 0; j < k; j++) {
				int l = Integer.parseInt(st.nextToken())-1;
				problems[i] += (1 << l);
			}
		}
		// 각 학생을 뽑는 모든 경우를 고려하기 위해 부분집합을 활용
		subset(0, 0, 0);
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}
	// cnt : 현재 고려 중인 학생 인덱스, solved : 현재 고른 학생들로 풀 수 있는 문제들을 비트마스킹으로 나타낸 정수
	// num : 현재 고른 총 학생 수
	private static void subset(int cnt, int solved, int num) {
		// 지금까지 고른 학생들로 모든 문제를 다 풀 수 있는 경우
		if (solved == (1 << N) - 1) {
			min = Math.min(num, min);
			return;
		}
		// M명을 모두 고려해도 풀 수 없는 경우
		if (cnt == M) {
			return;
		}
		subset(cnt+1, solved, num); // 현재 고려 중인 학생을 선택하지 않는 경우
		subset(cnt+1, solved | problems[cnt], num+1); // 현재 고려 중인 학생을 선택하는 경우
	}

}
