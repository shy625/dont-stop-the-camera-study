import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_G5_19942_다이어트 {
	
	static int N, ans;
	static int [][] map;
	static int [] min, picks;
	static List<String> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][5];
		// 최소 영양소를 저장할 변수
		min = new int[4];
		// 최소 비용을 저장할 변수
		ans = Integer.MAX_VALUE;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			min[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 1개부터 N개까지 식재료를 선택하며 비교해본다.
		for (int i = 1; i <= N; i++) {
			picks = new int[N];
			// combination을 통해 모든 조합을 계산해본다.
			combi(i, 0, 0);
		}
		
		// 조건을 만족하는 경우가 1번도 없다면 -1 출력한다.
		if(ans == Integer.MAX_VALUE) {
			System.out.println(-1);
		// 조건을 만족한다면 사전순으로 가장 빠른 조합을 출력한다.
		} else {
			System.out.println(ans);
			
			Collections.sort(list);
			System.out.println(list.get(0));
		}
		
		
	}

	private static void combi(int level, int cnt, int start) {
		if(cnt == level) {
			// combination으로 식재료를 선택했으면, 조건에 충족하는지 체크해본다.
			check(level);
			return;
		}
		
		for (int i = start; i < N; i++) {
			picks[cnt] = i;
			combi(level, cnt+1, i+1);
		}
		
	}

	private static void check(int level) {
		int sum = 0;
		int [] arr = new int[4];
		
		for (int i = 0; i < level; i++) {
			arr[0] += map[picks[i]][0];
			arr[1] += map[picks[i]][1];
			arr[2] += map[picks[i]][2];
			arr[3] += map[picks[i]][3];
			sum += map[picks[i]][4];
		}
		
		// 최소 영양소를 넘기지 못하면 return한다.
		for (int i = 0; i < 4; i++) {
			if(arr[i] < min[i]) return;
		}
		
		// 최소 비용이거나, 이전의 최소 비용과 같을 경우에만 list에 넣어준다.
		if(ans >= sum) {
			// 이번에 계산한 비용이 최소라면 list를 초기화하고 ans를 갱신해준다.
			if(ans > sum) {
				list.clear();
				ans = sum;
			}
			
			// StringBuilder를 사용해서 list에 식재료 목록을 넣어준다.
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < level; i++) {
				sb.append((picks[i]+1)+" ");
			}
			sb.setLength(sb.length()-1);
			list.add(sb.toString());
		}
		
	}

}
