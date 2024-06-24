import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main_10816_숫자카드2 {
	static int N; 
	static HashMap<Integer, Integer> cards = new HashMap<>();
	static int M; 
	static int[] nums;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < N ; i++) {
			int in = Integer.parseInt(st.nextToken());
			if(cards.containsKey(in)) { // hashmap에서 해당 input이 있는지 확인하고 있다면 1증가시켜준다.
				cards.put(in, cards.get(in)+1);
			}else { // 해당 input이 없을 경우 1개 넣어 준다. 
				cards.put(in, 1);
			}
		}
		
		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0 ; i < M ; i++) {
			int in = Integer.parseInt(st.nextToken());
			if(cards.containsKey(in)) {
				sb.append(cards.get(in)+" ");
			} else {
				sb.append(0+ " ");
			}
		}
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
}
