import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_G4_1327_소트게임 {
	static int N, K, ans = -1;
	static String origin, sorted;
	static class Node {
		String str;
		int cnt;
		
		public Node(String str, int cnt) {
			this.str = str;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		char [] arr = new char[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = st.nextToken().charAt(0);
		}
		
		// 처음 순열을 origin에 저장한다.
		origin = new String(arr);
		
		// arr 배열을 정렬한 뒤, 오름차순이 된 순열을 sorted에 저장한다.
		Arrays.sort(arr);
		sorted = new String(arr);
		
		bfs();
		
		System.out.println(ans);
		
	}

	private static void bfs() {
		Queue<Node> q = new LinkedList<>();
		// 이미 방문한 순열인지 확인하기 위한 set
		Set<String> v = new HashSet<>();
		q.add(new Node(origin, 0));
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			
			// cur의 순열 순서가 sorted와 같다면 ans를 갱신하고 종료한다.
			if(cur.str.equals(sorted)) {
				ans = cur.cnt;
				return;
			}
			
			// 이미 방문한 순열 순서라면 넘긴다.
			if(v.contains(cur.str)) continue;
			v.add(cur.str);
			
			// left부터 right까지의 수를 뒤집기 위해 변수를 설정해준다.
			int left = 0;
			int right = K;
			
			// right를 +1씩 늘려나가면서, N보다 작거나 같을때 까지만 진행한다.
			while(right <= N) {
				// 최종적으로 완성된 순열 순서를 저장하기 위한 sb
				StringBuilder sb = new StringBuilder();
				// left부터 right까지의 수를 뒤집기 위한 sb
				StringBuilder rev = new StringBuilder();
				
				// 맨 앞부터 ~ left 전까지의 숫자들은 sb에 그대로 담아준다.
				sb.append(cur.str.substring(0, left));
				
				// left부터 ~ right 전까지의 숫자들은 rev에 담고, 뒤집어서 sb에 담아준다.
				for (int i = left; i < right; i++) {
					rev.append(cur.str.charAt(i));
				}
				sb.append(rev.reverse().toString());
				
				// right ~ 마지막까지의 숫자들을 sb에 담아준다.
				sb.append(cur.str.substring(right, N));
				
				// 한 번 뒤집기 때문에 cnt+1해서 완성된 sb를 큐에 다시 넣어준다.
				q.add(new Node(sb.toString(), cur.cnt+1));
				// 뒤집을 구역을 바꾸기 위해 left와 right를 +1씩 해준다.
				left++;
				right++;
			}
			
		}
		
	}

}
