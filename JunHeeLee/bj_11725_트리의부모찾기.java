package algo1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class bj_11725_트리의부모찾기 {
	static int N; //노드 갯수
	static int S,E; // 입력받을 node 두개
	static boolean[] visit;
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	static int[] answer;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		visit = new boolean[N+1]; //bfs시 방문했는지 판별할 visit배열
		answer= new int[N+1]; // 부모가 누구인지 담을 배열
		for(int i=0;i<N+1;i++) {
			graph.add(new ArrayList<>());//1번부터 N번까지 그래프생성
		}
		for(int i=0;i<N-1;i++) {
			String token[] = br.readLine().split(" ");
			S = Integer.parseInt(token[0]);
			E = Integer.parseInt(token[1]);
			graph.get(S).add(E);
			graph.get(E).add(S); //그래프 노두 두개 각각 연결
		}

		find();//1번 부모로부터 자식찾기
		for(int i=2;i<answer.length;i++) {
			System.out.println(answer[i]);
		}
	}
	private static void find() {
		Queue<Integer> q = new LinkedList<>();
		visit[1] = true;//1은 제일 큰 부모이므로 자식이 될 수 없다
		q.offer(1);
		while(!q.isEmpty()) {
			int parent = q.poll();
			for(int child : graph.get(parent)) {
				if(visit[child] == false) {
					q.offer(child);
					visit[child] = true;
					answer[child] = parent;
				}
			}
		}
	}

}
