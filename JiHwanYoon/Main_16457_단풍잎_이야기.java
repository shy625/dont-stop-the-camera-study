

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16457_단풍잎_이야기 {
	static int n, m, k, max;
	static int[] quests;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		max = 0; // 깰 수 있는 일간 퀘스트 개수의 최댓값
		quests = new int[m]; // 각 일간 퀘스트에서 사용해야 할 키들을 비트로 표현한 배열
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int bit = 0;
			for (int j = 0; j < k; j++) {
				bit += 1 << (Integer.parseInt(st.nextToken())-1);
			}
			quests[i] = bit;
		}
		combi(0, 0, 0); // 언제 가장 많은 일간 퀘스트를 깰 수 있는지를 모든 경우를 고려해 판단
		System.out.println(max);
	}
	// 조합을 이용해 2*n개의 키 중 n개를 고르는 모든 경우의 수 중, 깰 수 있는 일간 퀘스트의 개수의 최댓값을 구하는 함수
	private static void combi(int cnt, int start, int bit) {
		// n개의 키를 모두 고른 경우
		if (cnt == n) {
			int num = 0; // 고른 키로 깰 수 있는 일간 퀘스트의 수
			for (int i = 0; i < m; i++) {
				int quest = quests[i];
				int cntKey = 0; // 일간 퀘스트를 깨기 위해 필요한 키 중 사용할 수 있는 키의 개수
				// 비트 연산을 통해 일간 퀘스트를 깨기 위해 필요한 키를 사용할 수 있는지 확인
				for (int j = 0; j < 2*n; j++) {
					if (((1 << j) & bit) != 0 && ((1 << j) & quest) != 0) cntKey++;
				}
				// 일간 퀘스트를 깨기 위해 필요한 키를 모두 사용할 수 있는지 확인
				if (cntKey == k) num++;
			}
			// 최댓값 갱신
			max = Math.max(max, num);
			return;
		}
		// 조합을 이용해 2*n개의 키 중 n개 키 선택
		for (int i = start; i < 2*n; i++) {
			combi(cnt+1, i+1, bit | (1 << i));
		}
	}

}
