import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_S3_2548_대표자연수 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int [] arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(st.nextToken());
			arr[i] = n;
		}
		
		Arrays.sort(arr);
		int length = arr.length;
		
		if(N % 2 != 0) {
			System.out.println(arr[length/2]);
		} else {
			int n1 = arr[length/2-1];
			double sum1 = 0;
			int n2 = arr[length/2];
			double sum2 = 0;
			
			for (int i = 0; i < N; i++) {
				sum1 += Math.abs(n1 - arr[i]);
				sum2 += Math.abs(n2 - arr[i]);
			}
			
			if(sum1 < sum2) System.out.println(n1);
			else if(sum1 > sum2) System.out.println(n2);
			else System.out.println(Math.min(n1, n2));
		}

	}

}
