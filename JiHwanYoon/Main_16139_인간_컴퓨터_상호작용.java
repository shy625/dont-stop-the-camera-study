

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_16139_인간_컴퓨터_상호작용 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		int N = Integer.parseInt(br.readLine());
		int l = s.length();
		// arr1[i][j]는 [0, i-1] 구간에서 알파벳에서 j번째 문자가 나타난 횟수를 의미한다. 
		int[][] arr1 = new int[l+1][26];
		// arr2[i][j]는 [i+1, l-1] 구간에서 알파벳에서 j번째 문자가 나타난 횟수를 의미한다.
		int[][] arr2 = new int[l+1][26];
		// arr1을 앞에서부터 채워나간다.
		for (int i = 0; i < l; i++) {
			char c = s.charAt(i);
			for (int j = 0; j < 26; j++) {
				arr1[i+1][j] = arr1[i][j];
			}
			arr1[i+1][c - 'a']++;
		}
		// arr1를 앞에서부터 채워나간다.
		arr2[0] = Arrays.copyOf(arr1[l], 26);
		arr2[0][s.charAt(0)-'a']--;
		for (int i = 1; i < l; i++) {
			char c = s.charAt(i);
			for (int j = 0; j < 26; j++) {
				arr2[i][j] = arr2[i-1][j];
			}
			arr2[i][c - 'a']--;
		}
		// [l, r] = [0, length-1] - [0, l-1] - [r+1, length-1]임을 활용한다.
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int cur = st.nextToken().charAt(0) - 'a';
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			sb.append(arr1[l][cur] - arr1[start][cur] - arr2[end][cur]).append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
