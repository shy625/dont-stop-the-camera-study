

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main_16925_문자열_추측 {
	static int N;
	static String[] strs;
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		strs = new String[2*N-2];
		// 접두사 및 접미사를 길이별로 저장해두는 ArrayList
		ArrayList<ArrayList<String>> lenStrMap = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			lenStrMap.add(new ArrayList<>());
		}
		for (int i = 0; i < 2*N-2; i++) {
			strs[i] = br.readLine();
			lenStrMap.get(strs[i].length()).add(strs[i]);
		}
		// 길이가 1인 부분문자열과 길이가 N-1인 부분문자열을 합쳐서
		// 문자열을 만들고 해당 문자열이 모든 부분문자열을 접두사 및 접미사로 가질 수 있는지 확인
		for (String s1 : lenStrMap.get(1)) {
			for (String s2 : lenStrMap.get(N-1)) {
				String temp = s1 + s2; // 길이가 1인 부분문자열(접두사) + 길이가 N-1인 부분문자열(접미사)
				simulate(temp);
				temp = s2 + s1; // 길이가 N-1인 부분문자열(접두사) + 길이가 1인 부분문자열(접미사)
				simulate(temp);
			}
		}
	}
	// 본래 문자열이 temp인지 확인하는 함수
	private static void simulate(String temp) {
		visited = new boolean[N][2]; // 길이가 i인 부분문자열이 접두사 및 접미사로 쓰였는지를 i번째 인덱스에 나타내는 배열
		char[] arr = new char[2*N-2]; // 각 부분문자열이 접두사 및 접미사인지를 나타내는 배열
		for (int i = 0; i < 2*N-2; i++) {
			// temp에서 i번째 부분문자열이 접두사, 접미사 혹은 둘 다 아닌지 확인
			int res = check(temp, i);
			// temp에서 i번째 부분문자열이 접두사 또는 접미사가 아닌 경우
			if (res == 0) return;
			arr[i] = res == 1 ? 'P' : 'S';
		}
		// 출력
		StringBuilder sb = new StringBuilder();
		sb.append(temp).append("\n");
		for (char c : arr) {
			sb.append(c);
		}
		System.out.println(sb.toString());
		System.exit(0);
	}
	// str에서 idx번째 부분문자열이 접두사 또는 접미사인지 확인하는 함수
	private static int check(String str, int idx) {
		String subStr = strs[idx];
		// idx번째 부분문자열이 str의 접두사인지 확인
		boolean temp = true;
		for (int i = 0; i < subStr.length(); i++) {
			// 이미 subStr의 길이에 대한 접두사를 찾은 경우
			if (visited[subStr.length()][0]) {
				temp = false;
				break;
			}
			if (str.charAt(i) != subStr.charAt(i)) {
				temp = false;
				break;
			}
		}
		// subStr이 str의 접두사로 가능한 경우
		if (temp) {
			visited[subStr.length()][0] = true;
			return 1;
		}
		// idx번째 부분문자열이 str의 접미사인지 확인
		temp = true;
		for (int i = 0; i < subStr.length(); i++) {
			// 이미 subStr의 길이에 대한 접미사를 찾은 경우
			if (visited[subStr.length()][1]) {
				temp = false;
				break;
			}
			if (str.charAt(str.length() - subStr.length() + i) != subStr.charAt(i)) {
				temp = false;
				break;
			}
		}
		// subStr이 str의 접미사로 가능한 경우
		if (temp) {
			visited[subStr.length()][1] = true;
			return -1;
		}
		// subStr이 str의 접두사도, 접미사도 될 수 없는 경우
		return 0;
	}

}
