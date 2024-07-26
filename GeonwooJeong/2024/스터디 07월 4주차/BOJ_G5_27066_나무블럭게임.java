import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_G5_27066_나무블럭게임 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		// N이 1일 경우에는 해당 나무 블럭이 최대다.
		if(N == 1) {
			System.out.println(br.readLine());
			System.exit(0);
		}
		
		// 나무 블럭들을 저장할 배열
		int [] nums = new int[N];
		// 나무 블럭들의 합을 저장할 배열
		int sum = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(st.nextToken());
			sum += n;
			nums[i] = n;
		}
		
		Arrays.sort(nums);
		
		// 나무 블럭에 대해 조사한 결과(0123, 6789, 0189, 0129)
		// N-1번째 블럭이나, 모두 더해서 평균낸 값이 최대이다.
		// 따라서 그 두 가지 값을 비교해본다.
		double ans = Math.max(nums[N-2], (double)sum / N);
		
		System.out.println(ans);
		
	}

}

//Set<String> set = new HashSet<>();
//
//while(true) {
//	String str = br.readLine();
//	if(str.equals("-1")) break;
//	set.add(str);
//}
//
//int max = -1;
//
//for(String str : set) {
//	String [] nums = str.split(" ");
//	int [] arr = new int[nums.length];
//	for (int i = 0; i < nums.length; i++) {
//		String num = nums[i];
//		int sum = 0;
//		for (int j = 0; j < num.length(); j++) {
//			sum += num.charAt(j) - '0';
//		}
//		arr[i] = sum / num.length();
//	}
//	
//	Arrays.sort(arr);
//	max = Math.max(max, arr[(nums.length+1)/2-1]);
//	
//}
//
//System.out.println(max);
