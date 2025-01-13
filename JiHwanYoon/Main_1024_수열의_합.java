

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1024_수열의_합 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		while (true) {
			// 첫째항이 a, 등차가 d인 등차수열에 대해 n번째 항까지의 합이 n*(2*a+(n-1))/2임을 이용
			// 수열의 길이가 L, 등차가 1, 합이 N일 때 a 값을 구하고,
			// 만약 a가 음이 아닌 정수가 나오는 경우 a를 시작항으로 하는 n개의 연속된 수를 출력하고,
			// 그렇지 않으면 L을 1 증가시킨다.
			double startD = ((2.0*N)/L - (L-1.0))/2.0;
			if ((int)startD != startD || startD < 0) {
				// 만약 L이 100보다 커지면 -1 출력
				if (L >= 100) {
					System.out.println(-1);
					break;
				}
				L++;
				continue;
			}
			int start = (int)startD;
			StringBuilder sb = new StringBuilder();
			for (int i = start; i <= start + (L-1); i++) {
				sb.append(i).append(" ");
			}
			sb.setLength(sb.length()-1);
			System.out.println(sb.toString());
			break;
		}
	}

}
