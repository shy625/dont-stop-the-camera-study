import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_8901_화학제품 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			int [] material = new int[3];
			int [] price = new int[3];
			int max = 0;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 3; i++) {
				material[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 3; i++) {
				price[i] = Integer.parseInt(st.nextToken());
			}
			
			int AB = Math.min(material[0], material[1]);
			
			for (int i = 0; i <= AB; i++) {
				int sum = i * price[0];
				// AB 다음으로 BC를 먼저 만드는 경우
				if(price[1] > price[2]) {
					int BC = Math.min(material[1]-i, material[2]);
					int CA = Math.min(material[2]-BC, material[0]-i);
					sum += BC * price[1] + CA * price[2];
				// AB 다음으로 CA를 먼저 만드는 경우
				} else {
					int CA = Math.min(material[2], material[0]-i);
					int BC = Math.min(material[1]-i, material[2]-CA);
					sum += CA * price[2] + BC * price[1];
				}
				max = Math.max(max, sum);
			}
			
			sb.append(max).append("\n");
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
		
	}

}
