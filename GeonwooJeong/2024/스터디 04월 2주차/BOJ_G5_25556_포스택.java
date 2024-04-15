import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_G5_25556_포스택 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		
		// 스택을 PQ로 사용하기 위한 pq
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
		
		// 모든 스택 중 가장 작은 값을 저장할 변수 min
		int min = Integer.MAX_VALUE;
		// 모든 숫자를 스택에 담을 수 있는지 확인할 변수
		boolean check = true;

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(st.nextToken());
			// 현재 스택들에 들어있는 값보다 더 작은 값이 들어온 경우에는
			// 항상 새로운 스택에 값을 넣어야 한다.
			if (n < min) {
				// 만약 이미 스택 4개를 모두 사용하고 있다면 순열을 청소할 수 없다.
				if (pq.size() == 4) {
					check = false;
					break;
				}
				// 새로운 값이 제일 작으므로 min을 갱신한다.
				min = n;
				// 스택에 n을 넣는다.
				pq.add(n);
			// 그 외의 경우(현재의 스택 중 한 곳에 넣을 수 있는지 확인할 경우)
			} else {
				// 스택들 중 가장 위의 있는 값을 저장할 임시 list
				List<Integer> list = new ArrayList<Integer>();
				// 스택들 중 가장 위의 숫자가 바뀌었는지 확인할 변수
				boolean changed = false;
				while(!pq.isEmpty()) {
					// 스택 중 가장 위의 값을 꺼낸다.
					int poll = pq.poll();
					// 그 값보다 n이 작다면 poll을 임시 리스트에 저장해놓는다. (해당 스택에 쌓아올릴 수 없음)
					if(poll > n) {
						list.add(poll);
					// 그 값보다 n이 크다면 해당 스택 위에 n을 쌓아 올린다.
					// 만약 해당 스택이 4번쨰 스택(가장 작은 수)라면 min을 갱신한다.
					} else {
						if(pq.isEmpty()) {
							min = n;
						}
						pq.add(n);
						changed = true;
						break;
					}
				}
				
				// 스택을 모두 확인했는데 n을 쌓아올릴 수 없는 경우
				// 순열을 청소할 수 없다.
				if(!changed) {
					check = false;
					break;
				}
				
				// 만약 스택 어딘가에 n을 쌓아 올렸다면, 방금 빼내었던 숫자들을 다시 pq에 넣어준다.
				for(int num : list) {
					pq.add(num);
				}
			}
		}

		if (check) {			
			System.out.println("YES");
		} else {
			System.out.println("NO");			
		}

	}

}
