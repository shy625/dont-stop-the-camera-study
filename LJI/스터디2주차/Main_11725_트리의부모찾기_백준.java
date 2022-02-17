import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main_11725_트리의부모찾기_백준 {
	static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		Queue<Integer> qu=new LinkedList<Integer>();
		int [] parent= new int[N+1];//부모의 번호를 담을 배열
		
		ArrayList<ArrayList<Integer>> graph= new ArrayList<>();//인접리스트
		for (int i = 0; i <= N+1; i++) {//
			graph.add(new ArrayList<>());
		}
		
		for (int i = 0; i < N-1; i++) {//간선 연결
			String [] str=br.readLine().split(" ");
			int n1=Integer.parseInt(str[0]);
			int n2=Integer.parseInt(str[1]);
			graph.get(n1).add(n2);
			graph.get(n2).add(n1);
		}
		
		qu.offer(1);//루트 노드
		while(!qu.isEmpty()) {
			int n=qu.poll();
			for (int i = 0; i < graph.get(n).size(); i++) {
				if(parent[graph.get(n).get(i)]==0) {
					parent[graph.get(n).get(i)]=n;
					qu.offer(graph.get(n).get(i));
				}
			}
		}
		
		for (int i = 2; i <= N; i++) {
			System.out.println(parent[i]);
		}
	}

}
