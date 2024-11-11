

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_1469_숌_사이_수열 {
	static int N;
	static int[] arr;
	static int[] p;
	static ArrayList<String> possibles;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		// 브루트포스 알고리즘 이용
		// 집합 X에 있는 수를 모든 자리에 넣어보면서 숌 사이 수열을 만들 수 있는지 확인
		p = new int[2*N];
		Arrays.fill(p, -1);
		possibles = new ArrayList<>(); // 가능한 숌 사이 수열의 List
		perm(0);
		// 숌 사이 수열을 만들 수 없는 경우
		if (possibles.size() == 0) {			
			System.out.println(-1);
		// 숌 사이 수열을 만들 수 있는 경우 사전 순으로 가장 앞서는 숌 사이 수열을 출력
		} else {
			Collections.sort(possibles);
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < 2*N; i++) {
				// 숌 사이 수열을 알파벳으로 나타낸 것을 다시 숫자로 치환해 출력
				sb.append(possibles.get(0).charAt(i) - 'A').append(" ");
			}
			sb.setLength(sb.length()-1);
			System.out.println(sb.toString());
		}
	}
	// cnt번째 수를 넣는 것을 재귀적으로 시도하는 함수
	private static void perm(int cnt) {
		// 숌 사이 수열을 만든 경우
		if (cnt == N) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < 2*N; i++) {
				// 사전 순으로 가장 앞서는 숌 사이 수열을 구하기 위해, 수열 내 수들을 알파벳으로 치환한 뒤
				// 알파벳의 문자열로 저장
				sb.append((char)(p[i]+'A'));
			}
			possibles.add(sb.toString());
			return;
		}
		for (int i = 0; i < 2*N; i++) {
			// i번째와 i + arr[cnt] + 1번째 자리에 cnt번째 숫자를 넣는 것을 시도 
			if (p[i] != -1 || i + arr[cnt] + 1 >= 2*N || p[i + arr[cnt] + 1] != -1) continue;
			p[i] = p[i + arr[cnt] + 1] = arr[cnt];
			perm(cnt+1);
			// 백트래킹
			p[i] = p[i + arr[cnt] + 1] = -1;
		}
	}

}
