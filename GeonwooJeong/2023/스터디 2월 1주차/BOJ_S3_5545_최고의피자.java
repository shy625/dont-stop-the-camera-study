import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_S3_5545_최고의피자 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(br.readLine());
		
		Integer [] topping = new Integer[N];
		
		for (int i = 0; i < N; i++) {
			topping[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(topping, Collections.reverseOrder());
		
		int max = C / A;
		
		int sum = 0;
		int cnt = 1;
		
		for (int i = 0; i < N; i++) {
			sum += topping[i];
			int tmp = (C + sum) / (A + B * cnt++);
			if(tmp >= max) {
				max = tmp;
			} else {
				break;
			}
		}

		System.out.println(max);

	}

}
