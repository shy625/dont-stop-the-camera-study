

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_1351_무한_수열 {
	static long P, Q;
	static Map<Long, Long> map; // 각 N에 대한 A_N을 저장하는 맵
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long N = Long.parseLong(st.nextToken());
		P = Long.parseLong(st.nextToken());
		Q = Long.parseLong(st.nextToken());
		map = new HashMap<>();
		map.put(0L, 1L);
		// 재귀함수를 통해 A_N을 구한다.
		long result = simul(N);
		System.out.println(result);
	}
	// A_n을 재귀함수를 통해 계산하는 함수
	private static long simul(long n) {
		if (map.containsKey(n)) return map.get(n);
		map.put(n/P, simul(n/P));
		map.put(n/Q, simul(n/Q));
		return map.get(n/P) + map.get(n/Q);
	}
}
