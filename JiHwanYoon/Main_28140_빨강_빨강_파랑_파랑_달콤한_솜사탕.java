

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_28140_빨강_빨강_파랑_파랑_달콤한_솜사탕 {
	static int[] idxR;
	static int[] idxB;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		String s = br.readLine();
		// R, B가 현재 위치에서 다음으로 나오는 인덱스를 저장하는 배열
		idxR = new int[1_000_004];
		idxB = new int[1_000_004];
		for (int i = 0; i < N; i++) {
			char c = s.charAt(i);
			if (c != 'R') idxR[i] = i+1;
			else idxR[i] = i;
			if (c != 'B') idxB[i] = i+1;
			else idxB[i] = i;
		}
		for (int i = 0; i < 4; i++) {
			idxR[N+i] = idxB[N+i] = N+i;
		}
		StringBuilder sb = new StringBuilder();
		for (int q = 1; q <= Q; q++) {
			st = new StringTokenizer(br.readLine());
			int L = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			int idx1 = findR(L); // 범위 내 가장 왼쪽 R을 찾는다.
			int idx2 = findR(idx1+1); // 범위 내 두 번째로 왼쪽에 있는 R을 찾는다.
			int idx3 = findB(idx2+1); // 범위 내 두 번째 R 이후 가장 왼쪽 B를 찾는다.
			int idx4 = findB(idx3+1); // 범위 내 B 이후 가장 왼쪽 B를 찾는다.
			if (idx4 > R) sb.append(-1).append("\n"); // 조건을 만족하는 R, B를 못 찾은 경우
			else sb.append(idx1).append(" ").append(idx2).append(" ")
				   .append(idx3).append(" ").append(idx4).append("\n");
		}
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	// i번째 인덱스 이후 가장 왼쪽에 있는 R을 재귀함수를 이용해 찾는 함수
	private static int findR(int i) {
		if (idxR[i] == i) return i;
		else return idxR[i] = findR(idxR[i]);
	}
	// i번째 인덱스 이후 가장 왼쪽에 있는 B를 재귀함수를 이용해 찾는 함수
	private static int findB(int i) {
		if (idxB[i] == i) return i;
		else return idxB[i] = findB(idxB[i]);
	}
}
