import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_20008_몬스터를처치하자 {
	static int N, ans;
	static int [][] skills;
	static int [] cooldown;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int hp = Integer.parseInt(st.nextToken());
		skills = new int[N][2];
		cooldown = new int[N];
		ans = Integer.MAX_VALUE;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			skills[i][0] = Integer.parseInt(st.nextToken());
			skills[i][1] = Integer.parseInt(st.nextToken());
		}
		
		solve(0, hp);
		
		System.out.println(ans);
		
	}

	private static void solve(int time, int hp) {
		if(hp <= 0) {
			ans = Math.min(ans, time);
			return;
		}
		
		for (int i = 0; i < N; i++) {
			// i번째 스킬을 쓰려고 할 때, 쿨타임이 돌아있는 경우
			if(cooldown[i] <= 0) {
				int tmp = cooldown[i];
				cooldown[i] = skills[i][0];
				
				for (int j = 0; j < N; j++) {
					if(i == j) continue;
					cooldown[j]--;
				}
				
				solve(time+1, hp-skills[i][1]);
				
				for (int j = 0; j < N; j++) {
					if(i == j) continue;
					cooldown[j]++;
				}
				
				cooldown[i] = tmp;
			
			// i번째 스킬을 쓰려고 할 때, 쿨타임을 기다려야 하는 경우
			} else if(cooldown[i] > 0) {
				int tmp = cooldown[i];
				cooldown[i] = skills[i][0];
				
				for (int j = 0; j < N; j++) {
					if(i == j) continue;
					cooldown[j] -= tmp;
				}
				
				solve(time+tmp, hp-skills[i][1]);
				
				for (int j = 0; j < N; j++) {
					if(i == j) continue;
					cooldown[j] += tmp;
				}
				
				cooldown[i] = tmp;
			}
		}
		
	}

}
