import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_FlymetotheAlphaCentauri {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			int dist = y - x;
			int max = (int)Math.sqrt(dist);
			
			// dist의 제곱근이 max와 일치할 경우 max*2-1번만 움직여도 된다.
			if(max == Math.sqrt(dist)) {
				sb.append(max*2-1+"\n");
			// dist가 max*max+max보다 작거나 같을 경우 max*2번만 움직여도 된다.
			} else if(dist <= max*max+max) {
				sb.append(max*2+"\n");
			// dist가 max*max+max보다 클 경우 max*2+1번만 움직여도 된다.
			} else if(dist > max*max+max) {
				sb.append(max*2+1+"\n");
			}
			
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
		
	}

}
