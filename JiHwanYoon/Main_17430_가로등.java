

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_17430_가로등 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		outer : for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			// 각 좌표를 HashMap을 활용해 저장
			Map<Integer, Set<Integer>> points = new HashMap<>();
			Set<Integer> xSet = new HashSet<>(); // 서로 다른 x좌표들의 Set
			Set<Integer> ySet = new HashSet<>(); // 서로 다른 y좌표들의 Set
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				points.putIfAbsent(x, new HashSet<>());
				points.get(x).add(y);
				xSet.add(x);
				ySet.add(y);
			}
			// 모든 가로등 쌍이 균형을 이루려면 각 x좌표에 대한 서로 다른 y좌표들의 개수가
			// 모든 서로 다른 y좌표들의 개수와 동일해야 한다.
			for (int x : points.keySet()) {
				Set<Integer> ys = points.get(x);
				if (ys.size() != ySet.size()) {
					sb.append("NOT BALANCED").append("\n");
					continue outer;
				}
			}
			sb.append("BALANCED").append("\n");
		}
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
