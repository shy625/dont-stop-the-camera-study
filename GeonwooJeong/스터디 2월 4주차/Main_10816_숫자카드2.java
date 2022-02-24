import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_10816_숫자카드2 { // 지환님 코드 참고했습니다.

	static int N;
	static int [] nums;
	static int first, last; // num의 가장 앞의 index, 가장 뒤의 index
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		nums = new int[N]; // 숫자를 입력받아 저장할 배열
		
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(nums); // 개수를 세기 위해 sort해준다.
		
		int M = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine()," ");
		for (int i = 0; i < M; i++) {
			search(Integer.parseInt(st.nextToken())); // 숫자의 개수를 세기 위한 작업
			sb.append(last-first).append(" "); // 마지막index - 처음index를 sb에 넣어준다.
		}
		
		System.out.println(sb.toString());
		
	}

	private static void search(int n) {
		first = 0;
		last = 0;
		
		int left = 0;
		int right = N-1;
		
		while(left<right) { // 숫자의 앞쪽 index를 구하기 위한 작업
			int mid = (left+right)/2;
			
			if(nums[mid] < n) {
				left = mid+1;
			} else {
				right = mid;
			}
		}
		first = left;
		
		left = 0;
		right = N-1;
		
		while(left < right) { // 숫자의 뒤쪽 index를 구하기 위한 작업
			int mid = (left+right)/2;
			if(nums[mid] < n+1) {
				left = mid+1;
			} else
				right = mid;
		}
		
		last = left;
		
		if(nums[last] == n) { // 뒤쪽 index가 배열의 크기를 넘어설 경우 1을 더해준다.
			last++;
		}
		
	}

}
