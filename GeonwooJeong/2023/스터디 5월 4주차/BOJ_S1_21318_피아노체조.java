import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S1_21318_피아노체조 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int [] sum = new int[N+1];
		
		int past = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		// i번째에는 실수를 총 몇 번 했는지 sum 배열에 저장한다.
		for (int i = 1; i <= N; i++) {
			int n = Integer.parseInt(st.nextToken());
			// 이전의 악보보다 난이도가 낮아진 경우
			if(past > n) {
				// 실수를 1회 증가하여 저장한다.
				sum[i] = sum[i-1] + 1;
			// 이전의 악보보다 난이도가 같거나 높아진 경우에는 실수의 횟수가 증가하지 않는다.
			} else {
				sum[i] = sum[i-1];
			}
			past = n;
		}
		
		int Q = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			// 악보 y까지 실수한 횟수에서 악보 x까지 실수한 횟수를 빼면 x부터 y까지의 실수 횟수가 된다.
			sb.append((sum[y]-sum[x])+"\n");
		}
		
		System.out.println(sb.toString());
		
	}

}
