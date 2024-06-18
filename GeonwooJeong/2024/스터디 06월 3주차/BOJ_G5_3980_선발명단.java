import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_3980_선발명단 {
	
	static int [][] stats;
	static boolean [] v;
	static int max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			// 선수들의 능력치를 저장할 2차원 배열
			stats = new int[11][11];
			// 각 선수들을 1번씩 사용했는지 확인하기 위한 배열
			v = new boolean[11];
			// 능력치 합의 최댓값을 저장할 변수
			max = 0;
			
			for (int i = 0; i < 11; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 11; j++) {
					stats[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			dfs(0, 0);
			
			sb.append(max+"\n");
			
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
		
	}

	private static void dfs(int idx, int sum) {
		// 모든 선수를 선택했을 때의 능력치의 합을 max에 갱신한다.
		if(idx == 11) {
			max = Math.max(max, sum);
			
			return;
		}
		
		for (int i = 0; i < 11; i++) {
			// i번째 선수를 이미 사용했거나, 해당 포지션의 능력치가 0일 경우엔 넘어간다.
			if(v[i] || stats[i][idx] == 0) continue;
			
			// 사용했음 처리하고, sum에 능력치를 더해주고, 다음 포지션으로 넘긴다.
			v[i] = true;	
			sum += stats[i][idx];
			idx++;
			dfs(idx, sum);
			// 돌아온 뒤, 사용했음 처리를 해제하고, sum에 능력치를 빼주고, 이전 포지션으로 넘어온다.
			idx--;
			sum -= stats[i][idx];
			v[i] = false;
			
		}
		
	}

}
