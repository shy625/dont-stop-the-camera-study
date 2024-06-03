import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_G5_1092_배 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		Integer [] crane = new Integer[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			crane[i] = Integer.parseInt(st.nextToken());
		}
		
		// 크레인 무게 제한이 높은 순으로 정렬
		Arrays.sort(crane, Collections.reverseOrder());
		
		int M = Integer.parseInt(br.readLine());
		
		List<Integer> list = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		
		// 박스 무게가 높은 순으로 정렬
		Collections.sort(list, Collections.reverseOrder());
		
		// 무게 제한이 제일 큰 크레인으로 무게가 가장 큰 박스를 못 들 경우
		if(crane[0] < list.get(0)) {
			System.out.println(-1);
			System.exit(0);
		}
		
		int ans = 0;
		
		// 옮길 박스가 남아있을 때 까지 진행
		while(list.size() > 0) {
			ans++;
			
			// 크레인 인덱스
			int idx1 = 0;
			// 박스 인덱스
			int idx2 = 0;
			
			// 하루동안 크레인을 모두 사용하면 다음 날로 넘어간다.
			while(idx1 < N) {
				int box = list.get(idx2);
				
				// idx2번 째 상자를 들어올릴 수 있으면 해당 크레인을 사용하고, 박스를 제거한다.
				if(crane[idx1] >= box) {
					idx1++;
					list.remove(idx2);
				// 그 외의 경우 무게가 더 작은 박스를 확인한다.
				} else {
					idx2++;
				}
				
				// 모든 박스를 확인했다면 다음 날로 넘어간다.
				if(idx2 >= list.size()) break;
			}
			
		}
		
		System.out.println(ans);
		
	}

}
