import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S2_2477_참외밭 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int K = Integer.parseInt(br.readLine());
		
		int [][] arr = new int[6][2];
		int [] value = new int[5];
		
		int maxR = 0;
		int maxrIdx = -1;
		int maxC = 0;
		int maxcIdx = -1;
		
		for (int i = 0; i < 6; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			
			arr[i][0] = d;
			arr[i][1] = n;
			
			if(d > 2) {
				if(n > maxR) {
					maxR = n;
					maxrIdx = i;
				}
			} else {
				if(n > maxC) {
					maxC = n;
					maxcIdx = i;
				}
			}
			
		}
		
		int minR = arr[(maxrIdx + 3) % 6][1];
		int minC = arr[(maxcIdx + 3) % 6][1];
		
		System.out.println((maxR*maxC - minR * minC) * K);
		
	}

}
