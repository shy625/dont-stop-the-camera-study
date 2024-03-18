import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G4_5021_왕위계승 {
	static class Person {
		String name;
		double percent;
		
		public Person(String name, double percent) {
			this.name = name;
			this.percent = percent;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// 한 사람의 이름과 Person을 연결해주기 위한 맵
		Map<String, Person> blood = new HashMap<>();
		// 한 사람의 자식들을 리스트형태로 저장하기 위한 맵
		Map<String, List<Person>> map = new HashMap<>();
		// 다시 체크해야 할 부모의 수를 저장하기 위한 맵
		Map<String, Integer> remain = new HashMap<>();
		
		String king = br.readLine();
		// 왕의 혈통은 1.0으로 시작한다.
		Person origin = new Person(king, 1.0);
		blood.put(king, origin);
		map.put(king, new ArrayList<>());
		// 왕은 부모가 없으므로 체크하지 않아도 된다.
		remain.put(king, 0);
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String child = st.nextToken();
			String p1 = st.nextToken();
			String p2 = st.nextToken();
			
			// child가 이전에 등장한 적이 없으면 초기화해준다.
			if(!blood.containsKey(child)) {
				blood.put(child, new Person(child, 0.0));
				map.put(child, new ArrayList<>());
			}
			
			// p1이 이전에 등장한 적이 없으면 초기화해준다.
			if(!blood.containsKey(p1)) {
				blood.put(p1, new Person(p1, 0.0));
				map.put(p1, new ArrayList<>());
				remain.put(p1, 0);
			}
			
			// p2가 이전에 등장한 적이 없으면 초기화해준다.
			if(!blood.containsKey(p2)) {
				blood.put(p2, new Person(p2, 0.0));
				map.put(p2, new ArrayList<>());
				remain.put(p2, 0);
			}
			
			// p1의 자식리스트에 child를 추가해준다.
			map.get(p1).add(blood.get(child));
			// p2의 자식리스트에 child를 추가해준다.
			map.get(p2).add(blood.get(child));
			// child의 부모 2명의 혈통 비율이 변할 수 있으므로 다시 확인해야 할 필요가 있다.
			remain.put(child, 2);
		}
		
		// 값을 순서대로 갱신해주기 위한 Queue
		Queue<Person> q = new ArrayDeque<>();
		
		// 부모가 등장한 적 없는 사람들 먼저 값을 갱신해준다. (큐에 먼저 넣어준다.)
		for (String person : blood.keySet()) {
			if(remain.get(person) == 0) q.add(blood.get(person));
		}
		
		// 모든 사람에 대해 값을 갱신해준다.
		while(!q.isEmpty()) {
			// 사람 한 명을 뽑는다.
			Person cur = q.poll();
			// 그 사람의 자식들에 대해 갱신해준다.
			for (Person child : map.get(cur.name)) {
				// cur의 혈통 비율만큼 child에 더해준다.
				child.percent += cur.percent / 2.0;
				// child의 부모 확인이 필요한 횟수를 1 줄여준다.
				remain.put(child.name, remain.get(child.name)-1);
				
				// 만약 child의 모든 부모를 확인했다면(더 이상 값이 갱신되지 않는다면) child도 큐에 넣어줌으로써
				// child의 자식들에 대해 값을 갱신해 줄 준비를 한다.
				if(remain.get(child.name) == 0) q.add(child);
			}
		}
		
		double max = -1.0;
		String ans = "";
		
		// M명의 사람에 대해 혈통 비율을 조사한다.
		for (int i = 0; i < M; i++) {
			String name = br.readLine();
			
			// 만약 한 번도 등장하지 않은 사람이라면 넘어간다.
			if(!blood.containsKey(name)) continue;
			
			double value = blood.get(name).percent;
			if(value > max) {
				max = value;
				ans = name;
			}
		}
		
		System.out.println(ans);
		
	}

}
