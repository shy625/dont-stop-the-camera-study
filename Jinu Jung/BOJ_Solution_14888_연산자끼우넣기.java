package 스터디_2월3주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_Solution_14888_연산자끼우넣기 {
	static int[] nums;
	static int N;
	static int MIN = Integer.MAX_VALUE;
	static int MAX = Integer.MIN_VALUE;
	static int[] oper = new int[4];	// 연산자별 개수 (인덱스0:+ , 인덱스1:-, 인덱스2:* 인덱스3:/몫) 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 입력받기
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		nums = new int[N];
		for(int i=0; i<N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<4; i++) {
			oper[i] = Integer.parseInt(st.nextToken());
		}

	

		
		// 모든 경우(BFS or DFS)마다 계산하기
		dfs(nums[0],1);
		
		//정답 출력
		System.out.println(MAX+"\n"+MIN);
	
	}

	
	private static void dfs(int result, int cnt) {		// n : 피연산자, cnt : 지금까지 연산횟수
		if(cnt == N) {								// 모든 연산자를 사용 한 경우(계산 끝)
			MAX = Math.max(MAX, result);
			MIN = Math.min(MIN, result);
			return;
		}
		
		for (int i=0; i<4; i++) {					// +-*/ 순으로 모든 경우의수 접근
			//System.out.println("oper["+i+"]=? "+oper[i]);
			if(oper[i] > 0) {						// 사용할 연산자가 남아 있다.
				oper[i]--;							// 연산자 사용표시(무한루프 방지)
				
				if(i==0) {							// + 인 경우
					dfs(result + nums[cnt], ++cnt);	// 지금까지 계산 결과값에 이어서 계산, cnt++: 다음 피연산자로 이동
					break;
				}else if(i==1) {					// - 인 경우
					dfs(result-nums[cnt],++cnt);
					break;
				}else if(i==2) {					// * 인 경우	
					dfs(result*nums[cnt],++cnt);
					break;
				}else if(i==3) {					// / 인 경우
					dfs(result/nums[cnt],++cnt);
					break;
				}
				
//				switch (i) {				
//				case 0:	dfs(result + nums[cnt], cnt + 1);	break;
//				case 1:	dfs(result - nums[cnt], cnt + 1);	break;
//				case 2:	dfs(result * nums[cnt], cnt + 1);	break;
//				case 3:	dfs(result / nums[cnt], cnt + 1);	break;
//				}
				
				oper[i]++;
			}
		}
		
	}

	
	
	
}
