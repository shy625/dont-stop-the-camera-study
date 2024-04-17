import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_S1_16206_롤케이크 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 0으로 끝나는 롤케이크를 저장할 list
		List<Integer> list = new ArrayList<Integer>();
		// 그 외의 롤케이크를 저장할 list
		List<Integer> list2 = new ArrayList<Integer>();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int ans = 0;
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(st.nextToken());
			// 길이가 10인 롤케이크는 자르지 않아도 ans에 포함된다.
			if(n == 10) {
				ans++;
			// 끝자리가 0으로 끝나지 않는 롤케이크는 한 번 더 자르는 작업이 필요하기 떄문에
			// 끝자리가 0인 롤케이크와 그렇지 않은 롤케이크를 다른 리스트에 담는다.
			} else {
				if(n % 10 == 0) {
					list.add(n);
				} else {					
					list2.add(n);
				}
			}
		}
		
		// 롤케이크는 길이 20짜리를 1번 잘라서 2개를 만들 때 최대 이익이 된다.
		// 그 외의 경우에는 한 번 자를 때, 1개가 나오기 때문에, 최대한 길이가 작은 롤케이크부터 자르는게 좋다.
		// 그러기 위해 리스트 2개를 모두 작은 순서대로 정렬해준다.
		Collections.sort(list);
		Collections.sort(list2);
		
		// 0으로 끝나는 롤케이크를 먼저 자르는게 무조건 이득이기 때문에, 해당 리스트를 먼저 조사한다.
		for(int n : list) {
			// head는 해당 롤케이크를 모두 10으로 만들기 위해 필요한 자르기 횟수이다.
			int head = (n / 10) - 1;
			
			// 남은 자르기 횟수가 head보다 크거나 같다면 이번 롤케이크는 모두 자를 수 있다.
			if(M >= head) {
				ans += head + 1; // 길이 30인 롤케이크는 2번 잘라서 3개를 얻으므로, head+1만큼 ans에 추가한다.
				M -= head; // 자른 횟수만큼 M에서 빼준다.
			// 남은 자르기 횟수가 head보다 작으면 남은 횟수 만큼만 자른다.
			} else {
				// 자른 횟수만큼 롤케이크가 나온다.
				ans += M;
				M = 0;
				break;
			}
		}
		
		// 0으로 끝나는 롤케이크만 잘랐는데 M이 0인 경우엔 종료한다.
		if(M == 0) {
			System.out.println(ans);
			System.exit(0);
		}
		
		// 그 외의 경우에는 0으로 끝나지 않는 롤케이크 리스트도 조사해본다.
		for(int n : list2) {
			// 만약 길이가 10보다 작으면, 어떻게 해서도 10을 만들 수 없기 때문에 넘어간다.
			if(n < 10) continue;
			
			// head는 해당 롤케이크를 모두 10으로 만들기 위해 필요한 자르기 횟수이다.
			int head = n / 10;
			
			// 남은 자르기 횟수가 head보다 크거나 같다면 이번 롤케이크는 모두 자를 수 있다.
			if(M >= head) {
				// 자른 횟수 만큼 ans를 더해주고, 그만큼 M을 빼준다.
				ans += head;
				M -= head;
			// 남은 자르기 횟수가 head보다 작으면 남은 횟수 만큼만 자른다.
			} else {
				// 자른 횟수만큼 롤케이크가 나온다.
				ans += M;
				M = 0;
				break;
			}
		}
		
		System.out.println(ans);
		
	}

}
