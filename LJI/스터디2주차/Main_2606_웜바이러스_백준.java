import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_2606_웜바이러스_백준 {

	static int com,N;
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		com=scann.nextInt();//컴퓨터의 수
		N=scann.nextInt();//간선의 수
		int cnt=-1;//1번 컴퓨터는 감염된 수에 포함시키지 않는다
		ArrayList<ArrayList<Integer>> graph= new ArrayList<>();
		boolean [] checked= new boolean[com+1];
		for (int i = 0; i <= com; i++) {//컴퓨터 수만큼 인접리스트 생성
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < N; i++) {
			int node=scann.nextInt();
			int	node2=scann.nextInt();
			graph.get(node).add(node2);
			graph.get(node2).add(node);
		}
		Queue<Integer> queue=new LinkedList<Integer>();
		queue.offer(1);
		while(!queue.isEmpty()) {
			int node=queue.poll();//노드 하나 꺼내보기//처음은 무조건 1
			checked[node]=true;
			for(int i=0;i<graph.get(node).size();i++) {
				if(!checked[graph.get(node).get(i)]) {//만약 연결된 노드가 감염되지 않았다면 큐에 집어넣어라
					queue.offer(graph.get(node).get(i));
				}
			}
		}
		for (int i = 0; i < checked.length; i++) {
			if(checked[i]) cnt++;
		}
		System.out.println(cnt);
	}
}
