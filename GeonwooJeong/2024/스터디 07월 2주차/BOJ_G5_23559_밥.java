import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_B5_23559_밥 {
	
	static class Menu implements Comparable<Menu> {
		int A;
		int B;
		
		public Menu(int a, int b) {
			A = a;
			B = b;
		}

		@Override
		public int compareTo(Menu o) {
			return Integer.compare(o.A-o.B, this.A-this.B);
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		
		// 메뉴들을 담을 리스트
		List<Menu> menus = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			menus.add(new Menu(A, B));
		}
		
		// 메뉴들을 정렬한다.
		// 이때, 정렬은 (A메뉴 맛 - B메뉴 맛)이 더 큰 순으로 한다.
		Collections.sort(menus);
		
		// 모든 맛의 합을 저장할 변수
		int sum = 0;
		// 특정 날짜 이후로는 무조건 B 메뉴만 선택할 수 있다. 그 시점을 체크하기 위한 변수
		boolean skip = false;
		
		for (int i = 0; i < N; i++) {
			Menu m = menus.get(i);
			
			// 특정 날짜 이후라면 무조건 B 메뉴만 선택한다.
			if(skip) {
				sum += m.B;
				continue;
			}
			
			// A메뉴와 B메뉴 중 A메뉴를 선택할 조건
			// 1. A의 맛이 B의 맛보다 커야한다.
			// (A메뉴 맛-B메뉴 맛)으로 정렬했기 때문에 한 번이라도 A메뉴가 B메뉴보다 맛이 없다면, skip을 true로 바꿔준다.
			// 2. A메뉴를 사먹을 돈이 있어야 한다.
			// 전체 예산을 확인해서, 더이상 A메뉴를 사먹을 여유가 없다면 skip을 true로 바꿔준다.
			if(m.A > m.B && (N-i-1)*1000 + 5000 <= X) {
				sum += m.A;
				X -= 5000;
			} else {
				sum += m.B;
				X -= 1000;
				skip = true;
			}
			
		}
		
		System.out.println(sum);
		
	}

}
