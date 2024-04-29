import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_G5_29792_규칙적인보스돌이 {
	static class Boss {
		long HP;
		int meso;
		
		public Boss(long hP, int meso) {
			HP = hP;
			this.meso = meso;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		long [] damage = new long[N];
		Boss [] bossArr = new Boss[K];
		
		for (int i = 0; i < N; i++) {
			damage[i] = Long.parseLong(br.readLine());
		}
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			
			long P = Long.parseLong(st.nextToken());
			int Q = Integer.parseInt(st.nextToken());
			
			bossArr[i] = new Boss(P, Q);
		}
		
		// M개의 캐릭터만 사용할 수 있으므로 PQ 사용
		PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());
		
		// N개의 캐릭터마다 최대 메소를 계산한다.
		for (int i = 0; i < N; i++) {
			long deal = damage[i];
			
			// K가지의 보스에 대해 900초를 사용했을 때 최대 메소를 구하기 위한 2차원 dp 배열
			long[][] dp = new long[K+1][901];
			
			// K가지 보스에 대해 계산한다.
			for (int j = 1; j <= K; j++) {
				Boss boss = bossArr[j-1];
				
				// j번째 보스를 잡지 않으면 j-1번째와 메소양이 똑같다.
				for (int k = 0; k <= 900 ; k++) {
					dp[j][k] = dp[j-1][k];
				}
				
				// 해당 보스를 잡을 때 소요되는 시간
				long time = (boss.HP-1)/deal + 1;
				
				// 900초를 다 사용해도 보스를 잡지 못하면 해당 보스는 잡을 수 없다.
				if(time > 900) continue;
				
				// j번째 보스를 잡으면 최소 time만큼 시간이 걸리므로 time부터 체크한다.
				// j번째 보스를 잡지 않았을 때와, [j-1][k-time]에서 j번째 보스를 잡았을 때의 메소 중 더 큰 값을 [j][k]에 저장한다.
				for (int k = (int)time; k <= 900; k++) {
					dp[j][k] = Math.max(dp[j][k], dp[j-1][k-(int)time] + boss.meso);
				}		
			}
			
			// 최대 메소 수입을 pq에 집어넣는다.
			pq.add(dp[K][900]);
			
		}
		
		long ans = 0;
		
		// 최대 M개의 캐릭터만 사용할 수 있다.
		for (int i = 0; i < M; i++) {
			ans += pq.poll();
		}
		
		System.out.println(ans);
		
	}

}
