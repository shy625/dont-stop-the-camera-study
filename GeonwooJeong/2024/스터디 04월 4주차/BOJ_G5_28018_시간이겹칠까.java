import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_28018_시간이겹칠까 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int [] plus = new int[1000002];
		int [] minus = new int[1000002];
		
		int max = 0;
		
		int N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			plus[S]++;
			minus[E+1]--;
			
			max = Math.max(max, E+1);
		}
		
		int [] sum = new int[1000002];
		
		for (int i = 1; i <= max; i++) {
			sum[i] = sum[i-1] + plus[i] + minus[i];
		}
		
		StringBuilder sb = new StringBuilder();
		
		int Q = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < Q; i++) {
			int n = Integer.parseInt(st.nextToken());
			sb.append(sum[n]+"\n");
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
		
	}

}
