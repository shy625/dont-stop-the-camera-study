import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_G5_17430_가로등 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			
			// 같은 좌표 x에 대한 y좌표들을 저장하기 위한 map
			Map<Integer, Set<Integer>> map = new HashMap<>();
			// y좌표들을 저장하기 위한 set
			Set<Integer> ySet = new HashSet<>();
			
			// 가로등 i, j에 대해 xi = xj라면 두 가로등은 균형이 잡혀있다.
			// 균형이 잡히려면 서로다른 xi, xj에 대해서 y의 좌표 값들이 같아야 한다.
			// 그러므로 같은 x좌표에 대한 y좌표의 개수를 구하고, 전체 y좌표의 개수를 구해서
			// 모든 x좌표에 대해 y좌표의 개수가 같은지 확인해본다.
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				map.putIfAbsent(x, new HashSet<>());
				map.get(x).add(y);
				
				ySet.add(y);
			}
			
			// 균형적인지 확인할 변수
			boolean flag = true;
			
			// x좌표마다 y좌표의 개수를 확인해보면서 개수가 달라진다면 균형적이지 않다.
			for (int x : map.keySet()) {
				if(ySet.size() != map.get(x).size()) {
					flag = false;
					break;
				}
			}
			
			if(flag) sb.append("BALANCED\n");
			else sb.append("NOT BALANCED\n");
		}
		
		if(sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
		
	}

}
