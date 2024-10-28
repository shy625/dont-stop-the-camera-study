package algoStudy_2024_10월_3주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_1682_돌리기 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String res = "";
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 8; i++) {
			res += st.nextToken();
		}
		// 너비 우선 탐색을 활용
		Queue<String> q = new LinkedList<>();
		Set<String> visited = new HashSet<>();
		String init = "12345678"; // 초기 상태
		q.offer(init);
		visited.add(init);
		int cnt = 0;
		while (!q.isEmpty()) {
			int qLen = q.size();
			while (qLen > 0) {
				String s = q.poll();
				// 지정한 상태의 매직 스퀘어를 만든 경우
				if (s.equals(res)) {
					System.out.println(cnt);
					System.exit(0);
				}
				// A 변환
				String A = new StringBuilder(s).reverse().toString();
				if (!visited.contains(A)) {
					q.offer(A);
					visited.add(A);
				}
				// B 변환
				String B = s.charAt(3) + s.substring(0, 3) + s.substring(5, 8) + s.charAt(4);
				if (!visited.contains(B)) {
					q.offer(B);
					visited.add(B);
				}
				// C 변환
				String C = "" + s.charAt(0) + s.charAt(2) + s.charAt(5) + s.charAt(3) + s.charAt(4) + s.charAt(6) + s.charAt(1) + s.charAt(7);
				if (!visited.contains(C)) {
					q.offer(C);
					visited.add(C);
				}
				// D 변환
				String D = s.charAt(4) + s.substring(1, 4) + s.charAt(0) + s.substring(5, 8);
				if (!visited.contains(D)) {
					q.offer(D);
					visited.add(D);
				}
				qLen--;
			}
			cnt++;
		}
	}

}
