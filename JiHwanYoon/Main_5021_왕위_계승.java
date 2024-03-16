

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_5021_왕위_계승 {
	// 각 사람의 이름과 혈통을 저장하는 노드
	static class Node {
		String name;
		double blood;
		public Node(String name, double blood) {
			this.name = name;
			this.blood = blood;
		}
	}
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		HashMap<String, Node> peopleNode = new HashMap<>(); // 각 사람의 이름과 노드를 매칭시키는 맵
		HashMap<String, ArrayList<Node>> graph = new HashMap<>(); // 각 사람의 자식들을 그래프 형태로 저장하는 ArrayList
		HashMap<String, Integer> counts = new HashMap<>(); // 각 사람에 대해, 방문하지 않은 부모의 수를 저장하는 맵
		// 왕을 저장
		Node king = new Node(br.readLine(), 1.0);
		peopleNode.put(king.name, king);
		graph.put(king.name, new ArrayList<>());
		counts.put(king.name, 0);
		// 입력으로 주어지는 사람들을 저장
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String child = st.nextToken();
			String parent1 = st.nextToken();
			String parent2 = st.nextToken();
			if (!peopleNode.containsKey(parent1)) {
				peopleNode.put(parent1, new Node(parent1, 0.0));
				graph.put(parent1, new ArrayList<>());
				counts.put(parent1, 0);
			}
			if (!peopleNode.containsKey(parent2)) {
				peopleNode.put(parent2, new Node(parent2, 0.0));
				graph.put(parent2, new ArrayList<>());
				counts.put(parent2, 0);
			}
			if (!peopleNode.containsKey(child)) {
				peopleNode.put(child, new Node(child, 0.0));
				graph.put(child, new ArrayList<>());
				counts.put(child, 0);
			}
			graph.get(parent1).add(peopleNode.get(child));
			graph.get(parent2).add(peopleNode.get(child));
			counts.put(child, 2);
		}
		// 위상 정렬을 이용해 왕부터 순차적으로 탐색하면서,
		// 각 사람의 혈통을 계산한다.
		Queue<Node> q = new LinkedList<>();
		for (String person : peopleNode.keySet()) {
			// 왕이거나, 해당 사람의 부모가 입력으로 주어지지 않는 경우 우선 탐색
			if (counts.get(person) == 0) q.offer(peopleNode.get(person));
		}
		while (!q.isEmpty()) {
			Node u = q.poll();
			for (Node v : graph.get(u.name)) {
				v.blood += u.blood/2;
				counts.put(v.name, counts.get(v.name)-1);
				// 모든 부모에 대한 탐색이 끝난 경우 자식도 탐색
				if (counts.get(v.name) == 0) q.offer(v);
			}
		}
		// 출력
		double max = 0.0;
		String res = "";
		for (int i = 0; i < M; i++) {
			String person = br.readLine();
			// 입력 중 가족 관계로 한 번도 주어지지 않은 사람이 나오는 경우 제외
			if (!peopleNode.containsKey(person)) continue;
			if (peopleNode.get(person).blood > max) {
				max = peopleNode.get(person).blood;
				res = person;
			}
		}
		System.out.println(res);
	}

}
