import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_14671_영정이의청소 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		// r,c가 홀홀/짝짝 이 있어야 (0,0)이 포함된 칸이 계속해서 증식할 수 있다.
		// r,c가 홀짝/짝홀 이 있어야 (0,1)이 포함된 칸이 계속해서 증식할 수 있다.
		// 따라서, 홀홀/짝짝/홀짝/짝홀 모든 케이스가 존재해야 모든 칸을 채울 수 있다.
		boolean [] locate = new boolean[4];
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			if(r % 2 == 0) {
				if(c % 2 == 0) locate[0] = true;
				else locate[1] = true;
			} else {
				if(c % 2 == 0) locate[2] = true;
				else locate[3] = true;
			}
		}
		
		if(locate[0] && locate[1] && locate[2] && locate[3]) System.out.println("YES");
		else System.out.println("NO");
	}

}
