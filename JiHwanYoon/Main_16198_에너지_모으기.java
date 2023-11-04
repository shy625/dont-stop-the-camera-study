

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_16198_에너지_모으기 {
	static int N, max;
	static ArrayList<Integer> energies;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		energies = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			energies.add(Integer.parseInt(st.nextToken()));
		}
		max = 0; // 모은 에너지의 최댓값
		perm(0, 0); // 모을 수 있는 에너지의 최댓값을 구한다.
		System.out.println(max);
	}
	// 순열을 활용해 가능한 모든 경우를 시도하고, 그 중 모을 수 있는 에너지의 최댓값을 구하는 함수
	private static void perm(int cnt, int sum) {
		// 에너지 구슬이 2개가 남아 더 이상 에너지를 얻을 수 없는 경우
		if (cnt == N-2) {
			max = Math.max(sum, max);
			return;
		}
		// 2번째 구슬부터 (energies.size()-1)번째 구슬 중 하나를 선택하고, 해당 구슬을 제거한다.
		for (int i = 1; i < energies.size()-1; i++) {
			sum += energies.get(i-1)*energies.get(i+1);
			int removed = energies.remove(i);
			perm(cnt+1, sum);
			// 백트래킹
			sum -= energies.get(i-1)*energies.get(i); // 현재 i번째에는 (i+1)번쨰 구슬이 있음에 유의
			energies.add(i, removed);
		}
	}

}
