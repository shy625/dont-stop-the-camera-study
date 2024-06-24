

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2374_같은_수로_만들기 {
	static int N;
	static long[] nums;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nums = new long[N];
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}
		// 분할 정복을 이용해 연산 횟수의 최솟값을 찾는다.
		// 연산 횟수가 int의 최대 범위를 넘을 수 있어 long 타입으로 선언
		long res = divide(0, N-1);
		System.out.println(res);
	}
	/**
	 * 전체적인 아이디어
	 * 우선 배열의 최댓값의 인덱스를 찾는다.
	 * 그리고 그 인덱스를 중심으로 좌측 배열과 우측 배열에 대해 divide 메서드를 수행하고,
	 * 그 결과에 전체 배열의 최댓값과 좌측 배열의 최댓값의 차이, 전체 배열의 최댓값과 우측 배열의 최댓값의 차이를 더한 값을 반환한다.
	 * 이렇게 구하는 이유를 설명하자면, 연산 횟수를 최소로 하려면 구덩이가 불규칙하게 파여져 있는 땅에 비가 내릴 때 빗물이 차오르는 과정을 생각하면 된다.
	 * 즉, 높이가 가장 낮은 곳부터 연산을 한 뒤, 높이가 이웃한 곳과 같아지면 이웃한 곳과 함께 연산을 진행하는 방식을 반복하면 되는데, 
	 * 아래 메서드를 이러한 과정을 역으로 진행한 것이다.
	 */
	private static long divide(int l, int r) {
		if (l == r) return 0;
		int maxIdx = findMaxIdx(l, r);
		// 좌측 배열이 없는 경우
		if (maxIdx == l) {
			int maxIdxR = findMaxIdx(maxIdx+1, r);
			return divide(l+1, r) + nums[l] - nums[maxIdxR];
		} else if (maxIdx == r) { // 우측 배열이 없는 경우
			int maxIdxL = findMaxIdx(l, maxIdx-1);
			return divide(l, r-1) + nums[r] - nums[maxIdxL];
		} else { // 일반적인 경우
			int maxIdxL = findMaxIdx(l, maxIdx-1);
			int maxIdxR = findMaxIdx(maxIdx+1, r);
			return divide(l, maxIdx-1) + divide(maxIdx+1, r) + 2*nums[maxIdx] - nums[maxIdxL] - nums[maxIdxR];
		}
	}
	// nums 배열에서 l번째 원소부터 r번째 원소까지 중 최댓값을 가지는 원소의 인덱스를 구하는 함수
	private static int findMaxIdx(int l, int r) {
		int idx = l;
		long max = nums[l];
		for (int i = l; i <= r; i++) {
			if (max < nums[i]) {
				idx = i;
				max = nums[i];
			}
		}
		return idx;
	}
}
