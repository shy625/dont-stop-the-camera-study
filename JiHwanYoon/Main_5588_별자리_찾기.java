

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_5588_별자리_찾기 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int m = Integer.parseInt(br.readLine());
		long[] arr = new long[m];
		// 좌표 저장 시 x*10_000_000 + y로 저장한다.(x, y좌표가 0 이상 1_000_000 이하이기 때문에)
		long CONST = 10_000_000; // 좌표 구분을 위한 상수
		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			arr[i] = x*CONST + y;
		}
		int n = Integer.parseInt(br.readLine());
		// 별들의 위치를 Set에 저장
		Set<Long> stars = new HashSet<>();
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			stars.add(x*CONST + y);
		}
		// 각 별들에 대해, 상근이가 찾고자 하는 별자리의 첫 번째 별로 간주했을 때
		// 별자리의 나머지 별들이 있어야 할 곳에 실제 별들이 존재하는지 확인
		outer: for (long star : stars) {
			long x = star/CONST;
			long y = star%CONST;
			long diffX = arr[0]/CONST - x;
			long diffY = arr[0]%CONST - y;
			for (int i = 1; i < m; i++) {
				long tempX = arr[i]/CONST;
				long tempY = arr[i]%CONST;
				if (!stars.contains((tempX - diffX)*CONST + (tempY - diffY))) {
					continue outer;
				}
			}
			System.out.println((-diffX)+" "+(-diffY));
			System.exit(0);
		}
	}

}
