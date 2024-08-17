

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_16722_결_합 {
	static Map<String, Integer> shapes; // 카드의 도형의 모양을 정수로 변환하는 Map
	static Map<String, Integer> colors; // 카드의 도형의 색을 정수로 변환하는 Map
	static Map<String, Integer> backgrounds; // 카드의 배경색을 정수로 변환하는 Map
	// 카드의 도형의 모양, 색, 배경색을 나타내는 클래스
	static class Card {
		int s, c, b;
		public Card(int s, int c, int b) {
			this.s = s;
			this.c = c;
			this.b = b;
		}
	}
	static Card[] cards;
	static int[] p;
	static boolean[] visited; // 이전에 합으로 외친 카드 조합인지를 나타내는 배열 
	static ArrayList<Integer> hapList; // 합으로 외칠 수 있는 카드 조합들
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		shapes = new HashMap<>();
		colors = new HashMap<>();
		backgrounds = new HashMap<>();
		shapes.put("CIRCLE", 0); shapes.put("TRIANGLE", 1); shapes.put("SQUARE", 2);
		colors.put("YELLOW", 0); colors.put("RED", 1); colors.put("BLUE", 2);
		backgrounds.put("GRAY", 0); backgrounds.put("WHITE", 1); backgrounds.put("BLACK", 2);
		cards = new Card[10];
		for (int i = 1; i <= 9; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = shapes.get(st.nextToken());
			int c = colors.get(st.nextToken());
			int b = backgrounds.get(st.nextToken());
			cards[i] = new Card(s, c, b);
		}
		p = new int[3];
		hapList = new ArrayList<>();
		// 순열을 활용해 가능한 합의 조합을 모두 찾는다.
		combi(0, 1);
		int n = Integer.parseInt(br.readLine());
		int point = 0; // 플레이어의 최종 점수
		int cnt = hapList.size(); // 합을 외칠 수 있는, 남은 카드 조합
		boolean gyul = false; // 결을 외쳤는지를 나타내는 변수
		visited = new boolean[1000];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char c = st.nextToken().charAt(0);
			// 합을 외치는 경우
			if (c == 'H') {
				int[] arr = new int[3];
				arr[0] = Integer.parseInt(st.nextToken());
				arr[1] = Integer.parseInt(st.nextToken());
				arr[2] = Integer.parseInt(st.nextToken());
				Arrays.sort(arr); // 카드 조합을 정렬해 중복되는 경우를 대비
				int num = arr[0]*100 + arr[1]*10 + arr[2];
				// 이전에 합을 외치지 않은 카드 조합인 경우
				if (!visited[num] && hapList.contains(num)) {
					visited[num] = true;
					point++;
					cnt--;
				}
				else point--;
			} else {
				// 결을 외치지 않았고, 모든 합이 되는 카드 조합을 찾은 경우
				if (!gyul && cnt == 0) {
					point += 3;
					gyul = true;
				} else point--;
			}
		}
		System.out.println(point);
	}
	// 순열을 활용해 모든 합의 카드 조합을 찾는 함수
	private static void combi(int cnt, int cur) {
		if (cnt == 3) {
			if (check()) {
				hapList.add(p[0]*100 + p[1]*10 + p[2]);
			}
			return;
		}
		for (int i = cur; i <= 9; i++) {
			p[cnt] = i;
			combi(cnt+1, i+1);
		}		
	}
	// 현재 카드 조합이 합이 될 수 있는지를 확인하는 함수
	private static boolean check() {
		Card c1 = cards[p[0]];
		Card c2 = cards[p[1]];
		Card c3 = cards[p[2]];
		// 3장의 카드가 모두 속성이 같거나 다르려면 
		// 속성을 정수 0, 1, 2로 변환했을 때 속성의 합이 3의 배수면 된다.
		return (c1.s + c2.s + c3.s)%3 == 0 && (c1.c + c2.c + c3.c)%3 == 0 && (c1.b + c2.b + c3.b)%3 == 0; 
	}

}
