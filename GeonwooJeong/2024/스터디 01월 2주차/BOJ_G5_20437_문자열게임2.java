import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BOJ_G5_20437_문자열게임2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			String str = br.readLine();
			int K = Integer.parseInt(br.readLine());
			
			if(K == 1) {
				sb.append("1 1").append("\n");
				continue;
			}
			
			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;
			
			List<Queue<Integer>> list = new ArrayList<>();
			for (int i = 0; i < 26; i++) {
				list.add(new ArrayDeque<>());
			}
			
			for (int i = 0; i < str.length(); i++) {
				int n = str.charAt(i) - 'a';
				
				int size = list.get(n).size();
				if(size == K-1) {
					int head = list.get(n).peek();
					min = Math.min(min, i-head+1);
					max = Math.max(max, i-head+1);
				} else if(size >= K) {
					list.get(n).poll();
					int head = list.get(n).peek();
					min = Math.min(min, i-head+1);
					max = Math.max(max, i-head+1);
				}
				
				list.get(n).add(i);
			}
			
			if(min == Integer.MAX_VALUE || max == Integer.MIN_VALUE) {
				sb.append(-1).append("\n");
			} else {
				sb.append(min+" "+max).append("\n");
			}
			
		}
		
		if(sb.length() > 0) {
			sb.setLength(sb.length()-1);
			System.out.println(sb.toString());
		}
		
	}

}
