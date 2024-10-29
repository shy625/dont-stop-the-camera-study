import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_G5_28140_달콤한솜사탕 {
	static List<Integer> Rlist, Blist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		String str = br.readLine();
		
		// R의 인덱스를 저장할 리스트
		Rlist = new ArrayList<>();
		// B의 인덱스를 저장할 리스트
		Blist = new ArrayList<>();
		
		/// R와 B의 인덱스를 리스트에 담아준다.
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			
			if(c == 'R') Rlist.add(i);
			else if(c == 'B') Blist.add(i);
		}
		
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			// 가능한 범위의 최솟값
			int min = Integer.parseInt(st.nextToken());
			// 가능한 범위의 최댓값
			int max = Integer.parseInt(st.nextToken());
			
			// 이분 탐색을 통해 범위 내에서 R이 등장하는 최솟값을 찾는다.
			int idx = binarySearch(min, max, 'R');
			
			// 범위 내에 R이 등장하지 않으면 솜사탕을 만들 수 없다.
			if(idx == -1) {
				sb.append("-1\n");
				continue;
			}
			
			// a와 b값을 갱신한다.
			int a = Rlist.get(idx);
			int b = Rlist.get(idx+1);
			
			// 이분 탐색을 통해 범위 내에서 B가 등장하는 최솟값을 찾는다.
			// 이 때, 범위는 min ~ max가 아니라 b+1 ~ max가 된다. (b < c 이어야 하므로)
			idx = binarySearch(b+1, max, 'B');
			
			// 범위 내에 B가 등장하지 않으면 솜사탕을 만들 수 없다.
			if(idx == -1) {
				sb.append("-1\n");
				continue;
			}
			
			// c와 d값을 갱신한다.
			int c = Blist.get(idx);
			int d = Blist.get(idx+1);
			
			sb.append(a+" "+b+" "+c+" "+d+"\n");
			
		}
		
		System.out.println(sb.toString());
		
	}

	private static int binarySearch(int min, int max, char target) {
		List<Integer> list = target == 'R' ? Rlist : Blist;
		
		// 이분 탐색의 왼쪽 범위
		int left = 0;
		// 이분 탐색의 오른쪽 범위
		int right = list.size()-1;
		
		// 정답이 될 idx 값
		int ans = -1;
		int mid = -1;
		
		while(left <= right) {
			mid = (left+right)/2;
			
			// mid번째 인덱스가 min 범위보다 작으면 left = mid+1 해준다.
			if(min > list.get(mid)) {
				left = mid+1;
				continue;
			// mid번째 인덱스가 해당 리스트의 마지막이 아니고
			// mid+1번째 인덱스가 max 범위보다 작으면 mid번째 값을 사용할 수 있다.
			// 원하는 값을 찾았지만 최대한 작은 숫자를 사용하는 것이 좋으므로 계속해서 탐색한다.
			} else if(mid != list.size()-1 && max >= list.get(mid+1)) {
				ans = mid;
			} 
			
			// 위의 조건을 만족하는 경우, 만족하지 않는 경우 모두 right = mid-1 해준다.
			right = mid-1;
			
		}
		
		return ans;
		
	}

}
