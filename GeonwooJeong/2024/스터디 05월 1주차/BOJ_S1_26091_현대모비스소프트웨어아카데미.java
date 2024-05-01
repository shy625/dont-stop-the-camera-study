import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_S1_26091_현대모비스소프트웨어아카데미 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int [] arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		// 투포인터 알고리즘 사용
		int left = 0;
		int right = N-1;
		int ans = 0;
		
		while(left < right) {
			// 남은 인원 중 가장 낮은 능력치의 사람
			int a1 = arr[left];
			// 남은 인원 중 가장 높은 능력치의 사람
			int a2 = arr[right];
			
			// 둘을 합쳐 M 이상일 때에는 ans++ 후 해당 인원들을 제외한다.
			if(a1 + a2 >= M) {
				ans++;
				left++;
				right--;
			// 그 외의 경우에는 능력치가 가장 낮은 사람의 다음 사람부터 확인한다.
			} else {
				left++;
			}
			
		}
		
		System.out.println(ans);
		
		
	}

}
