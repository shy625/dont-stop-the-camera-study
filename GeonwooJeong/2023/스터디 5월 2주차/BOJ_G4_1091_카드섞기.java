import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_G4_1091_카드섞기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int ans = 0;
		int [] P = new int[N];
		int [] S = new int[N];
		int [] card = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			P[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(st.nextToken());
			S[n] = i;
			card[i] = i%3;
		}
		
		// 처음 카드의 순서를 저장할 배열
		int [] origin = Arrays.copyOf(card, N);
		// 카드를 섞는 과정에 쓰일 배열
		int [] next = new int[N];
		
		while(true) {
			// 현재의 카드 순서와 목표하던 카드 순서가 같으면 종료
			if(Arrays.equals(card, P)) {
				break;
			}
			
			// 카드를 한 번 이상 섞었고, 현재의 카드 순서와 처음 카드 순서가 같다면 -1 출력
			if(Arrays.equals(card, origin) && ans > 0) {
				System.out.println(-1);
				System.exit(0);
			}
			
			// 문제의 설명대로 카드를 섞는다. (next 배열에 저장하고 복사하는 방법 사용)
			for (int i = 0; i < N; i++) {
				int tmp = S[i];
				next[tmp] = card[i];
			}
			
			card = Arrays.copyOf(next, N);
			ans++;
		}
		
		System.out.println(ans);
		
	}

}
