

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_28286_재채점을_기다리는_중 {
	static int N, K;
	static int[] answer;
	static int[] p;
	static int max;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		answer = new int[N]; // 실제 문제의 답 
		p = new int[N]; // 민규 답안지
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			answer[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			p[i] = Integer.parseInt(st.nextToken());
		}
		check(p); // 초기 상태에서 몇 문제가 정답인지 확인
		simulate(0); // 최대 K번 밀고 당기기를 진행했을 때 맞힐 수 있는 문제의 최대 개수를 구한다.
		System.out.println(max);
	}
	// 브루트포스 알고리즘을 통해 (k+1)번 밀고 당기기를 진행했을 때 맞힐 수 있는 문제의 최대 개수를 구하는 함수
	private static void simulate(int k) {
		if (k == K) return; // K번 밀고 당기기를 진행한 경우
		int[] start = Arrays.copyOf(p, N); // 초기 상태 저장
		for (int i = 0; i < N; i++) {
			// 당기기
			for (int j = 0; j < i; j++) {
				p[j] = start[j];
			}
			for (int j = i; j < N-1; j++) {
				p[j] = start[j+1];
			}
			p[N-1] = 0;
			// 당기기 결과 확인 및 다음 스텝 진행
			check(p);
			simulate(k+1);
			// 밀기
			for (int j = 0; j < i; j++) {
				p[j] = start[j];
			}
			p[i] = 0;
			for (int j = i; j < N-1; j++) {
				p[j+1] = start[j];
			}
			// 밀기 결과 확인 및 다음 스텝 진행
			check(p);
			simulate(k+1);
		}
	}
	// 현 상태(arr)에서 몇 문제를 맞췄는지 확인하는 함수
	private static void check(int[] arr) {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			if (answer[i] == arr[i]) cnt++;
		}
		max = Math.max(max, cnt);
	}
	
}
