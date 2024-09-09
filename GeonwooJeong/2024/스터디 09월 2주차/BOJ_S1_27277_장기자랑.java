import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_S1_27277_장기자랑 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int [] arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		int sum = 0;
		int last = 0;
		
		for (int i = 0; i < N; i++) {
			int n = i % 2 == 0 ? arr[N-1-(i/2)] : arr[i/2];
			
			sum += Math.max(0, n - last);
			
			last = n;
			
		}
		
		System.out.println(sum);
		
	}

}
