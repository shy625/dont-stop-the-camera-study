package 스터디_2월3주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_Solution_11725_트리의부모찾기_dfs {
	
	static int N ;						// 부모노드를 검색할 트리의 노드 수
	static int[] parent;				// (정답 배열) 각 노드의 부모노드 정보가 저장될 배열
	static ArrayList<Integer>[] list;	// 연결 정보가 저장되어있는 배열
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 입력받기
		N = Integer.parseInt(br.readLine());		// 트리의 노드 수
		parent = new int[N+1];						// 인덱스가 곧 값이 되도록 N+1만큼 할당
		list = new ArrayList[N+1];					// 연결 정보가 저장되어있는 배열 할당 (의미 : list의 인덱스와 cond의값이 연결되어있음.)
	
		for (int i=1; i<=N; i++) {					// 인덱스 : 1 ~ N
			list[i] = new ArrayList<Integer>();
		}
		
		int node1, node2;
		for (int i=0; i<N-1; i++) {					// 간선 개수 = 노드개수 - 1 이므로
			st = new StringTokenizer(br.readLine());
			node1 = Integer.parseInt(st.nextToken());
			node2 = Integer.parseInt(st.nextToken());
			
			// ArrayList에 삽입
			list[node1].add(node2);
			list[node2].add(node1);					// list[a]=b : 노드a와 연결된 노드는 b다. (b는 1개이상)
		}
		
//		// 연결정보 list에 들어간 데이터 확인
//		for (int i=0; i<list.length; i++) {
//			System.out.println("list의 "+i+"번째 인덱스에 해당하는 값:"+list[i]);			
//		}	

		
		// 각 노드의 부모노드 찾기 (인덱스 : 자식노드 , 값 : 부모노드)
		dfs(1,0);
		
		// 정답 출력 (2번노드부(index=2)터 각 노드에 해당하는 부모노드 출력)
		for(int i=2; i<=N; i++) {
			System.out.println(parent[i]);
		}

		
		
	}

	private static void dfs(int node, int parent_node) {
		parent[node] = parent_node;						// 연결 정보가 저장된 list에서 자식-부모노드 초기화
		
//		for(int i=0; i<list.length; i++) {				// 코드 오류
//			if(list[node].get(i) != parent_node) {
//				dfs(i, node);
//			}
//		}
		
		for(int value : list[node]) {					// node에 연결된 노드들 : value (get(index)메소드으로 접근)
			System.out.println("check list["+node+"] : "+list[node]);
			if(value != parent_node) dfs(value,node);
		}
		
		
	} 
	
}




