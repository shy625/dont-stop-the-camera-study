import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_12908_텔레포트3 {
	static int er, ec;
	static long min;
	static int [][] arr;
	static boolean [] v;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 현재 위치
		int sr = Integer.parseInt(st.nextToken());
		int sc = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		// 집의 위치
		er = Integer.parseInt(st.nextToken());
		ec = Integer.parseInt(st.nextToken());
		
		// 텔레포트 좌표들을 저장할 2차원 배열
		arr = new int[3][4];
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
			arr[i][2] = Integer.parseInt(st.nextToken());
			arr[i][3] = Integer.parseInt(st.nextToken());
		}
		// i번째 텔레포트를 사용했는지 여부를 저장할 boolean 배열
		v = new boolean[3];
		min = Long.MAX_VALUE;
		
		move(sr, sc, 0);
		
		System.out.println(min);
		
	}

	private static void move(int r, int c, long sum) {
		if(r == er && c == ec) {
			min = Math.min(min, sum);
			
			return;
		}
		
		// 바로 집으로 걸어가는 방식 (텔레포트 더이상 이용 안함)
		move(er, ec, sum + Math.abs(er-r) + Math.abs(ec-c));
		
		// 텔레포트 사용
		for (int i = 0; i < 3; i++) {
			if(!v[i]) {
				v[i] = true;
				
				// 앞의 좌표에서 뒤의 좌표로 이동
				move(arr[i][2], arr[i][3], sum + Math.abs(arr[i][0] - r) + Math.abs(arr[i][1] - c) + 10);
				
				// 뒤의 좌표에서 앞의 좌표로 이동
				move(arr[i][0], arr[i][1], sum + Math.abs(arr[i][2] - r) + Math.abs(arr[i][3] - c) + 10);
				
				v[i] = false;
			}
		}
		
	}

}
