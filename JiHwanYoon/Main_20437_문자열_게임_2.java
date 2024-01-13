

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_20437_문자열_게임_2 {

	public static void main(String[] args) throws IOException {
		// 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= T; t++) {
            String W = br.readLine();
            int K = Integer.parseInt(br.readLine());
            // K가 1인 경우는 항상 결과가 정해져 있다.
            if (K == 1) {
                sb.append("1 1\n");
                continue;
            }
            int[] cnts = new int[26]; // 문자열 내 각 알파벳의 개수
            for (int i = 0; i < W.length(); i++) {
                cnts[W.charAt(i)-'a']++;
            }
            int min = Integer.MAX_VALUE; // 어떤 문자를 K개 포함하고 있는 가장 짧은 문자열의 길이
            int max = -1; // 어떤 문자를 K개 포함하고, 첫 번째와 마지막 문자가 같은 가장 긴 문자열의 길이
            // 문자열 내 각 문자를 첫 번째 문자로 하는 부분 문자열을 추출해 계산한다.
            for (int i = 0; i < W.length(); i++) {
            	// 어떤 경우에도 첫 번째 문자를 K개 포함하는 문자열을 만들 수 없는 경우
                if (cnts[W.charAt(i)-'a'] < K) continue; 
                int count = 1;
                for (int j = i+1; j < W.length(); j++) {
                    if (W.charAt(i) == W.charAt(j)) count++;
                    // 첫 번째 문자를 K개 포함하는 문자열을 찾은 경우
                    // 이때 마지막 문자는 첫 번째 문자와 같다.
                    if (count == K) {
                        min = Math.min(min, j-i+1);
                        max = Math.max(max, j-i+1);
                        break;
                    }
                }
            }
            // 출력
            if (min == Integer.MAX_VALUE || max == -1) sb.append("-1");
            else sb.append(min).append(" ").append(max);
            sb.append("\n");
        }
        if (sb.length() > 0) sb.setLength(sb.length()-1);
        System.out.println(sb.toString());
	}
}
