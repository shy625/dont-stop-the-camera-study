

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_18112_이진수_게임 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String startBit = br.readLine();
		String endBit = br.readLine();
		// 입력을 이진수로 변환
		int s = 0, e = 0;
		int temp = 1;
		for (int i = startBit.length()-1; i >= 0; i--) {
			if (startBit.charAt(i) == '1') s += temp;
			temp *= 2;
		}
		temp = 1;
		for (int i = endBit.length()-1; i >= 0; i--) {
			if (endBit.charAt(i) == '1') e += temp;
			temp *= 2;
		}
		// 너비 우선 탐색을 활용
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[1 << 11];
		q.offer(s);
		visited[s] = true;
		int time = 0; // 최소 동작 횟수
		while (!q.isEmpty()) {
			int qLen = q.size();
			while (qLen > 0) {
				int u = q.poll();
				// 목표하는 이진수를 만든 경우
				if (u == e) {
					System.out.println(time);
					return;
				}
				int l = Integer.toBinaryString(u).length();
				// 한 자리 숫자를 보수로 만들기
				for (int i = 0; i < l-1; i++) {
					int k = u & (1 << i);
					int v = 0;
					if (k == 0) {
						v = u | (1 << i);
					} else {
						v = u & ((1 << l) - 1 - (1 << i));
					}
					if (visited[v]) continue;
					visited[v] = true;
					q.offer(v);
				}
				// 1 더하기
				if (u < ((1 << 11) - 1) && !visited[u+1]) {
					visited[u+1] = true;
					q.offer(u+1);
				}
				// 1 빼기
				if (u > 0 && !visited[u-1]) {
					visited[u-1] = true;
					q.offer(u-1);
				}
				qLen--;
			}
			time++;
		}
		System.out.println(time);
	}

}
