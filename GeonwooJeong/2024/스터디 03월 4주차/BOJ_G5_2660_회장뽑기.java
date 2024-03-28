import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_G5_2660_회장뽑기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int [][] relation = new int[N+1][N+1];
		
		// 거리를 INF(2501)로 초기화, 자기 자신과의 거리는 0이다.
		for (int i = 0; i < N; i++) {
			Arrays.fill(relation[i], 2501);
			relation[i][i] = 0;
		}
		
		// a, b가 -1일 때 까지 입력받는다.
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			
			if(a == -2 && b == -2) break;
			
			// a와 b는 친구, b와 a는 친구 관계이다.
			relation[a][b] = 1;
			relation[b][a] = 1;
		}
		
		// 플로이드 워셜 알고리즘
		// 경유지 k
		for (int k = 0; k < N; k++) {
			// 시작지 i
			for (int i = 0; i < N; i++) {
				// 도착지 j
				for (int j = 0; j < N; j++) {
					// i->j 거리보다 i->k->j 거리가 더 가까우면 해당 거리로 값을 갱신해준다.
					relation[i][j] = Math.min(relation[i][j], relation[i][k] + relation[k][j]);
				}
			}
		}
		
		// 회장 후보를 담을 ArrayList
		List<Integer> list = new ArrayList<Integer>();
		
		// 회장 후보의 관계 거리를 저장할 변수
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			int max = -1;
			for (int j = 0; j < N; j++) {
				// 자기 자신을 제외한 사람들과의 관계 거리 중, 가장 큰 값을 찾는다.
				if(i == j) continue;
				
				max = Math.max(max, relation[i][j]);
			}
			
			// 그 값이 min보다 작으면 유력한 회장 후보가 된다. (list 초기화)
			if(max < min) {
				min = max;
				list.clear();
				list.add(i+1);
			// 그 값이 min과 같다면 회장 후보에 추가된다.
			} else if(max == min) {
				list.add(i+1);
			}
		}
		
		System.out.println(min+" "+list.size());
		
		StringBuilder sb = new StringBuilder();
		for(int n : list) {
			sb.append(n+" ");
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
