

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Main_1013_Contact {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		// 정규표현식을 활용한다.
		Pattern P = Pattern.compile("(100+1+|01)+");
		StringBuilder sb = new StringBuilder();
		for (int t = 0; t < T; t++) {
			String s = br.readLine();
			if (P.matcher(s).matches()) sb.append("YES");
			else sb.append("NO");
			sb.append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
