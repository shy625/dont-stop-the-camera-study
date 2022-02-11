package algo1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

class Node implements Comparable<Node>{
    int next_node, weight;

    public Node(int weight, int next_node){
        this.next_node = next_node;
        this.weight = weight;
    }
    @Override
    public int compareTo(Node o) {
        return weight - o.weight;
    }
}
public class bj_2606_바이러스 {
	static int N;//computer 수
	static int cnt;//computer쌍 수
	static int S,E; // 그래프입력받을떄 연결된 computer
	static int[] dist;
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in);
		N = scann.nextInt();
		cnt = scann.nextInt();
		for(int i=0;i<N+1;i++) { //com번호가 1번부터 N까지 이므로 N+1까지 그래프 생성
			graph.add(new ArrayList<>());
		}
		for(int i=0;i<cnt;i++) {
			S = scann.nextInt();
			E = scann.nextInt();
			graph.get(S).add(E);
			graph.get(E).add(S);
		}
		dijkstra(1);
		int cnt=0;
		for(int i=0;i<dist.length;i++) {
			if(dist[i]!= Integer.MAX_VALUE) {
				cnt++;
			}
		}
		System.out.println(cnt-1); //1번에 의해 감염되는 컴퓨터수이므로 1번컴퓨터는 제외
	}
	private static void dijkstra(int start_node) {
		dist = new int[N+1]; //1번 computer 부터 N번 com까지의 거리
		Arrays.fill(dist, Integer.MAX_VALUE);//처음 에는 모두 무한대
		PriorityQueue<Node> pq = new PriorityQueue<>();
		dist[start_node] = 0; //자기자신(1번)노드 까지의 거리는 0
		pq.offer(new Node(0,start_node)); //처음 1번노드와 1번노드까지의거리 0 add
		while(!pq.isEmpty()) {
			Node temp = pq.poll();
			int cdist = temp.weight; //현재까지의 거리
			int cnode = temp.next_node; //현재 노드
			if(cdist> dist[cnode]) continue; //cnode까지의 거리가 cdist보다 작다면 갈필요없음
			for(int nnode : graph.get(cnode)) { //현재 노드와 연결되어있는 다음 노드
				int distance = dist[cnode]+1; // 현재노드에서 다음노드까지는 모두 가중치 1
				if(dist[nnode]>distance) {
					dist[nnode] = distance;
					pq.offer(new Node(distance,nnode));
				}
			}
		}
	}
}

