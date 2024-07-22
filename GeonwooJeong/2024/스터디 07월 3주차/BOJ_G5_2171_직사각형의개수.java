import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_G5_2171_직사각형의개수 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int ans = 0;
		
		// x좌표에 대한 y좌표를 모두 저장하기 위한 map
		Map<Integer, Set<Integer>> xMap = new HashMap<>();
		// y좌표에 대한 x좌표를 모두 저장하기 위한 map
		Map<Integer, Set<Integer>> yMap = new HashMap<>();
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			// xMap에 x,y를 저장한다.
			if(!xMap.containsKey(x)) xMap.put(x, new HashSet<>()); 
			xMap.get(x).add(y);
			
			// yMap에 y,x를 저장한다.
			if(!yMap.containsKey(y)) yMap.put(y, new HashSet<>());
			yMap.get(y).add(x);
		}
		
		// 직사각형을 이루려면 x1, y1 / x1, y2 / x2, y1 / x2, y2가 필요하다.
		// 가장 먼저 x1을 하나 잡고 시작한다.
		for (int x1 : xMap.keySet()) {
			// x1와 쌍을 이루는 y1, y2를 찾기 위해 xMap에서 x1에 저장된 set을 꺼낸다.
			Set<Integer> ySet = xMap.get(x1);
			
			// x1과 쌍을 이룰 수 있는 y1을 지정한다.
			for (int y1 : ySet) {
				
				// x1과 쌍을 이룰 수 있는 y2를 지정한다.
				for (int y2 : ySet) {
					// 이미 사용했던 y좌표는 선택하지 않는다.
					if(y1 <= y2) continue;
					
					// y1과 쌍을 이루는 x2를 찾기 위해 yMap에서 y1에 저장한 set을 꺼낸다.
					Set<Integer> xSet = yMap.get(y1);
					
					// y1과 쌍을 이룰 수 있는 x2를 지정한다.
					for (int x2 : xSet) {
						// 만약 x2, y1는 쌍으로 존재하지만, x2, y2가 존재하지 않는 경우는 해당 x2를 사용할 수 없다.
						if(!yMap.get(y2).contains(x2)) continue;
						
						// 이미 사용했던 x좌표는 선택하지 않는다.
						if(x2 <= x1) continue;
						
						// 그 외의 경우는 직사각형을 만들 수 있다.
						ans++;
					}
					
				}
			}
		}
		
		System.out.println(ans);
		
		
	}

}
