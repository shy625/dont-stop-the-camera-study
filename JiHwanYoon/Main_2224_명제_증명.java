

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main_2224_명제_증명 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// 플로이드-와샬 알고리즘 활용
		int[][] map = new int[52][52];
		for (int i = 0; i < 52; i++) {
			Arrays.fill(map[i], Integer.MAX_VALUE/10);
		}
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			char c1 = s.charAt(0);
			char c2 = s.charAt(s.length()-1);
			int n1 = 0;
			int n2 = 0;
			if ('A' <= c1 && c1 <= 'Z') n1 = c1 - 'A';
			else n1 = c1 - 'a' + 26;
			if ('A' <= c2 && c2 <= 'Z') n2 = c2 - 'A';
			else n2 = c2 - 'a' + 26;
			map[n1][n2] = 1;
		}
		for (int k = 0; k < 52; k++) {
			for (int i = 0; i < 52; i++) {
				if (i == k) continue;
				for (int j = 0; j < 52; j++) {
					if (j == k || j == i) continue;
					if (map[i][j] > map[i][k] + map[k][j]) map[i][j] = map[i][k] + map[k][j];
				}
			}
		}
		// 출력
		ArrayList<String> res = new ArrayList<>(); // 가능한 명제들을 모아놓는 ArrayList
		for (int i = 0; i < 52; i++) {
			for (int j = 0; j < 52; j++) {
				if (i == j) continue; // 자명한 명제는 생략
				if (map[i][j] < Integer.MAX_VALUE/10) {
					char c1 = 'a';
					char c2 = 'a';
					if (0 <= i && i < 26) c1 = (char)(i + 'A');
					else c1 = (char)(i - 26 + 'a');
					if (0 <= j && j < 26) c2 = (char)(j + 'A');
					else c2 = (char)(j - 26 + 'a');
					res.add(c1 + " => " + c2);
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(res.size()).append("\n");
		for (int i = 0; i < res.size(); i++) {
			sb.append(res.get(i)).append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
