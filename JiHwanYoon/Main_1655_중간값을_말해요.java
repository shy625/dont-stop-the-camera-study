
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_1655_중간값을_말해요 {
	/**
	 * 전체적인 아이디어
	 * 1. 지금까지 받은 수들을 작은 수들과 큰 수들로 정확히 이등분해 저장합니다.
	 * 2. 그러면 작은 수들의 가장 큰 수와, 큰 수들의 가장 작은 수가 중간값 후보가 됩니다.
	 * 3. 이등분할 때도 작은 수들의 개수가 큰 수들의 개수보다 같거나(지금까지 받은 숫자 개수가 짝수) 한 개 더 많도록(지금까지 받은 숫자 개수가 홀수) 조절합니다.
	 * 4. 그러면 지금까지 받은 숫자의 개수가 홀수이면 작은 수들 중 가장 큰 값이 중간값이 되고, 짝수면 작은 수들 중 가장 큰 값과 큰 수들 중 가장 작은 값이 중간값 후보가 됩니다.
	 * 5. 문제 조건에 의해 항상 작은 수들 중 가장 큰 값이 중간값이 됩니다.
	 * ex) 지금까지 1 2 3 4 5를 받았으면 작은 수들은 1, 2, 3, 큰 수들은 4, 5가 되어 중간값은 작은 수들 중 가장 큰 값인 3이 됩니다.
	 *     지금까지 1 2 3 4 5 6을 받았으면 작은 수들은 1, 2, 3, 큰 수들은 4, 5, 6이 되어 중간값은 3 또는 4이나 문제 조건에 의해 중간값은 작은 수들 중 가장 큰 값인 3이 됩니다.
	 */
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// 작은 수들의 집합과 큰 수들의 집합을 heap으로 구현해 작은 수들 중 가장 큰 값과, 큰 수들 중 가장 작은 값을 빠르게 뽑을 수 있도록 했습니다.
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder()); // 작은 수들의 집합
		PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // 큰 수들의 집합
		// 출력 저장
		StringBuilder sb = new StringBuilder();
		// 숫자들을 넣는 과정
		// 숫자를 넣을 때 전체적으로 크기가 maxHeap 1 증가 -> minHeap 1 증가 -> maxHeap 1 증가 -> ... 이런 식으로 이루어집니다.
		for (int i = 1; i <= N; i++) {
			// 입력
			int n = Integer.parseInt(br.readLine());
			// 처음에는 maxHeap과 minHeap이 모두 비었으므로 maxHeap에 먼저 숫자를 넣어줍니다.
			if (maxHeap.isEmpty()) maxHeap.offer(n);
			// maxHeap 중 가장 큰 값보다 작으면 maxHeap에 넣습니다. 단, 정확한 중간값 출력을 위해 maxHeap의 크기 조절을 해야 합니다.
			else if (n <= maxHeap.peek()) {
				// 만약 maxHeap의 크기가 더 크면 작은 수를 넣었을 때 두 집합의 크기 차가 2 이상 날 수 있으므로 
				// maxHeap에서 가장 큰 값을 꺼내 minHeap에 넣어줍니다.
				if (maxHeap.size() > minHeap.size()) minHeap.offer(maxHeap.poll());
				maxHeap.offer(n);
			// 입력으로 받은 수가 중간값이 될 수 있는 경우
			// 경우에 따라 maxHeap에 들어갈 수도, minHeap에 들어갈 수도 있기 때문에 경우를 따로 분리했습니다.
			} else if (!minHeap.isEmpty() && maxHeap.peek() < n && n <= minHeap.peek()) {
				if (maxHeap.size() > minHeap.size()) minHeap.offer(n);
				else maxHeap.offer(n);
			// minHeap이 비었거나, minHeap 중 가장 작은 값보다 크면 minHeap에 넣습니다. 단, 정확한 중간값 출력을 위해 minHeap의 크기 조절을 해야 합니다.
			} else {
				// minHeap의 크기는 maxHeap의 크기보다 크면 안 되므로 minHeap에서 가장 작은 값을 꺼내 maxHeap에 넣어줍니다.
				if (minHeap.size() >= maxHeap.size()) maxHeap.offer(minHeap.poll());
				minHeap.offer(n);
			}
			sb.append(maxHeap.peek()+" ");
		}
		// 출력
		sb.setLength(sb.length() - 1);
		System.out.println(sb.toString());
	}

}
