import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_19539_사과나무 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int target = 0;
		int odd = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(st.nextToken());
			target += n;
			if(n % 2 == 1) {
				odd++;
			}
		}
		
		if(target % 3 == 0 && odd <= target / 3) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}

	}

}
