import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main_1446 {
	static int N,D;
	static int [] dis;
	
	static class Node{
		int end;
		int dis;
		public Node(int end, int dis) {
			super();
			this.end = end;
			this.dis = dis;
		}
	}
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		
		N=scann.nextInt();
		D=scann.nextInt();
		dis=new int[D+1];//거리만큼 존재하기 위함
		
		
		for (int i = 0; i <= D; i++) {
			dis[i]=i;//이 거리를 가는데 최소 거리
		}
		
		Map<Integer, ArrayList<Node>> graph=new HashMap<Integer, ArrayList<Node>>();
		for (int i = 0; i < N; i++) {
			int start=scann.nextInt();
			int end=scann.nextInt();
			int dis=scann.nextInt();
			
			if(end>D) continue;//최종 도착점보다 멀면 굳이 계산할 필요는 없다
			
			if(!graph.containsKey(start)) {//현재 같은 위치에 지름길이 없는 경우
				graph.put(start, new ArrayList<>());
				
				graph.get(start).add(new Node(end, dis));
			}else {
				graph.get(start).add(new Node(end, dis));
			}
		}
		
		
		for (int i = 0; i <D; i++) {//모든 거리에서 반복하자
			
			if(graph.containsKey(i)) {//현재 위치에 지름길이 있다면?
				for (int j = 0; j < graph.get(i).size(); j++) {//지름길 갯수만큼 반복
					int end=graph.get(i).get(j).end;
					int distance=graph.get(i).get(j).dis;
					if(dis[i]+distance<dis[end]) {//end포인트 길에서 최솟값 갱신
						dis[end]=dis[i]+distance;
					}
				}
			}
			//바뀐 길이 더 짧다면 그 뒤 길도 이 길을 탄 루트로 교체되어야한다
			if(dis[i]+1<dis[i+1]) {
				dis[i+1]=dis[i]+1;
			}
		}
		
		System.out.println(dis[D]);
	}
}
