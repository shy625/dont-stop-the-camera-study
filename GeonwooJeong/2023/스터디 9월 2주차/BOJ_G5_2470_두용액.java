import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_G5_2470_두용액 {
	static int N, min;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		// 혼합 용액의 특성값의 최솟값을 저장할 변수, 초기 값은 최댓값으로 지정해준다.
		int min = Integer.MAX_VALUE;
		// 혼합 용액의 특성값이 최소일 때 더 작은 용액의 특성값을 저장할 변수
		int minLeft = 0;
		// 혼합 용액의 특성값이 최대일 때 더 큰 용액의 특성값을 저장할 변수
		int minRight = 0;
		
		int [] arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 특성 값들의 순서는 상관이 없으므로 정렬을 사용한다.
		Arrays.sort(arr);
		
		int left = 0;
		int right = N-1;
		
		// 투포인터 알고리즘 사용
		while(left < right) {
			int leftNum = arr[left];
			int rightNum = arr[right];
			int sum = leftNum + rightNum;
			
			// 혼합 용액의 특성값의 절댓값이 min값보다 작은 경우(min보다 0에 가까운 경우에 값을 업데이트해준다.)
			if(Math.abs(sum) < min) {
				min = Math.abs(sum);
				minLeft = leftNum;
				minRight = rightNum;
			}
			
			// 혼합 용액의 특성값이 0보다 크면 right를 -1해줘서 더 작은 값이 나오게 한다.
			if(sum > 0) {
				right--;
			// 혼합 용액의 특성값이 0보다 작거나 같으면 left를 +1해줘서 더 큰 값이 나오게 한다.
			} else {
				left++;
			}
		}
		
		System.out.println(minLeft+" "+minRight);
		
	}

}
