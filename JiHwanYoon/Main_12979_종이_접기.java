

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_12979_종이_접기 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		int A = Integer.parseInt(st.nextToken());
		int min = Integer.MAX_VALUE; // 넓이를 A로 만들기 위해 접는 횟수의 최솟값
		// 브루트포스 알고리즘 활용
		// 넓이 A를 만들기 위한 너비 w와 높이 h를 지정(단, w와 h는 A의 약수여야 함)
		// 그리고 접는 횟수를 최소화하기 위해 W와 H를 계속 반으로 나눠 언제 w와 h가 되는지 구한다.
		// 단, 홀수인 경우를 대비해 W와 H에 1을 더한 뒤 2로 나눈 몫을 계속 구해 나간다.
		// 마지막에는 w와 h에 맞춰 적절히 접어주면 된다.
		for (int w = 1; w <= A; w++) {
			if (A%w != 0) continue;
			int h = A/w;
			int tempW = W;
			int tempH = H;
			if (w > tempW || h > tempH) continue;
			int cnt = 0;
			while (tempW > w) {
				tempW = (tempW+1)/2;
				cnt++;
			}
			while (tempH > h) {
				tempH = (tempH+1)/2;
				cnt++;
			}
			min = Math.min(min, cnt);
		}
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}

}
