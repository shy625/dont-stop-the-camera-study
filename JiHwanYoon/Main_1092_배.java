

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_1092_배 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<Integer> cranes = new ArrayList<Integer>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			cranes.add(Integer.parseInt(st.nextToken()));
		}
		int M = Integer.parseInt(br.readLine());
		ArrayList<Integer> w = new ArrayList<Integer>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			w.add(Integer.parseInt(st.nextToken()));
		}
		// 그리디 알고리즘을 활용
		// 크레인은 무게 제한이 높은 순서대로, 박스들은 무게가 높은 순서대로 정렬
		cranes.sort(Collections.reverseOrder());
		w.sort(Collections.reverseOrder());
		// 가장 무게 제한이 높은 크레인으로도 들 수 없는 박스가 있는 경우
		if (cranes.get(0) < w.get(0)) {
			System.out.println(-1);
			return;
		}
		int res = 0; // 모든 박스를 배로 옮기는데 드는 최소 시간
		// 남아있는 박스들에 대해, 크레인을 한 번씩 순회하면서 적절한 박스들을 분배하는 과정을 반복한다.
		while(w.size() > 0) {
            int wIdx = 0; // 박스 인덱스
            int cIdx = 0; // 크레인 인덱스
            while (cIdx < N) {
            	// 현재 크레인이 들 수 있는 박스면 크레인에 박스 할당
                if(cranes.get(cIdx) >= w.get(wIdx)) {
                    cIdx++;
                    w.remove(wIdx);
                // 그렇지 않으면 다음 박스로 이동
                } else wIdx++;
                // 모든 박스를 한 번 다 살펴본 경우
                if (wIdx == w.size()) break;
            }
            res += 1;
        }
		System.out.println(res);
	}

}
