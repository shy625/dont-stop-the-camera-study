import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S1_25602_캔주기 {
	
	static int N, K, ans;
	static int [] cans;
	static int [][] R, M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		// 보유한 캔의 개수를 저장할 배열
		cans = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			cans[i] = Integer.parseInt(st.nextToken());
		}
		
		// 랑이의 만족도를 저장할 2차원 배열
		R = new int[K][N];
		// 메리의 만족도를 저장할 2차원 배열
		M = new int[K][N];
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				R[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				M[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 먼저 랑이에게 줄 캔을 브루트포스로 구한다.
		dfsR(0, 0);
		
		System.out.println(ans);
		
	}

	private static void dfsR(int cnt, int sum) {
		// 랑이에게 줄 캔을 모두 구했다면, 메리에게 줄 캔을 브루트포스로 구한다.
		if(cnt == K) {
			dfsM(0, sum);
			return;
		}
		
		for (int i = 0; i < N; i++) {
			// i번째 캔이 더이상 없으면 넘어간다.
			if(cans[i] == 0) continue;
			
			// 캔을 1개 사용하고, 랑이의 만족도 만큼 sum에 더해준다.
			cans[i]--;
			sum += R[cnt][i];
			// 랑이에게 줄 캔을 마저 고른다.
			dfsR(cnt+1, sum);
			// 돌아온 뒤, 캔을 다시 돌려놓고, 랑이의 만족도 만큼 sum에서 빼준다.
			cans[i]++;
			sum -= R[cnt][i];
		}
		
	}

	private static void dfsM(int cnt, int sum) {
		// 메리에게 줄 캔까지 모두 구했다면, sum을 갖고 ans를 갱신해준다.
		if(cnt == K) {
			ans = Math.max(ans, sum);
			return;
		}
		
		for (int i = 0; i < N; i++) {
			// i번째 캔이 더이상 없으면 넘어간다.
			if(cans[i] == 0) continue;
			
			// 캔을 1개 사용하고, 메리의 만족도 만큼 sum에 더해준다.
			cans[i]--;
			sum += M[cnt][i];
			// 메리에게 줄 캔을 마저 고른다.
			dfsM(cnt+1, sum);
			// 돌아온 뒤, 캔을 다시 돌려놓고, 메리의 만족도 만큼 sum에서 빼준다.
			cans[i]++;
			sum -= M[cnt][i];
		}
		
	}

}
