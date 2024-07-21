

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_2171_직사각형의_개수 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// 각 점의 정보를, x좌표가 동일한 점들의 y좌표를 모아두는 Map과,
		// y좌표가 동일한 점들의 x좌표를 모아두는 Map에 저장한다.
		Map<Integer, Map<Integer, Boolean>> xMap = new HashMap<>();
		Map<Integer, Map<Integer, Boolean>> yMap = new HashMap<>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			xMap.putIfAbsent(x, new HashMap<>());
			xMap.get(x).put(y, true);
			yMap.putIfAbsent(y, new HashMap<>());
			yMap.get(y).put(x, true);
		}
		int cnt = 0; // 직사각형의 개수
		// 첫 점 A (x1, y1) 결정
		for (int x1 : xMap.keySet()) {
			Map<Integer, Boolean> ys = xMap.get(x1);
			for (int y1 : ys.keySet()) {
				// A와 동일한 x좌표를 가지는 점 B (x1, y2) 선택 
				for (int y2 : ys.keySet()) {
					if (y1 <= y2) continue; // 중복되는 경우 제외
					Map<Integer, Boolean> xs = yMap.get(y1);
					// A와 동일한 y좌표를 가지는 점 C (x2, y1) 선택
					for (int x2 : xs.keySet()) {
						if (x2 <= x1) continue; // 중복되는 경우 제외
						// (x2, y2)가 있는 경우에만 직사각형을 만들 수 있다.
						if (!yMap.get(y2).containsKey(x2)) continue;
						cnt++;
					}
				}				
			}
		}
		System.out.println(cnt);
	}

}
