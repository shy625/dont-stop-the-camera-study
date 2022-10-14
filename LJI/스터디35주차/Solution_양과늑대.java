import java.util.ArrayList;

class Solution {
	
	static ArrayList<ArrayList<Integer>> graph;
	static boolean[] visited;
	static int max;
    public int solution(int[] info, int[][] edges) {
        //
        int answer = 0;
        
        int size=info.length;
        //그래프 생성
        graph=new ArrayList<>();
        for (int i = 0; i < size; i++) {
			graph.add(new ArrayList<>());
		}
        
        for (int i = 0; i < size-1; i++) {
			graph.get(edges[i][0]).add(edges[i][1]);
		}
        
        visited=new boolean[size];
        //sheep=0;
        //wolf=0;
        //노드0의 양
        
        max=0;
//        ArrayList<Integer> list=new ArrayList<Integer>();
//        list.ad
        //반복문 돌면서 연결된 늑대 노드들 dfs 로 돌면서 카운트
        //하나 연결되면 그 상태로 다시 새로운 연결 시도
        dfs(0,0,0,new ArrayList<Integer>(),info);
        answer=max;
        return answer;
    }
	private void dfs(int node, int sheep, int wolf, ArrayList<Integer> list,int[] info) {//현재 노드,양의 수, 늑대의 수, 갈 수 있는 노드
		//이번 노드가 늑대인지 양인지
		if(info[node]==0)sheep++;
		else wolf++;
		//늑대가 같아졌거나 커졌다면 리턴
		if(wolf>=sheep) return;
		
		//양 값 갱신
		max=Math.max(max, sheep);
		
		//리스트 바꾸기
		//현재 노드의 자식 노드가 있다면 리스트에 더하기
		for (int i = 0; i < graph.get(node).size(); i++) {
			list.add(graph.get(node).get(i));
		}
		
		//newList에서 갈 수 있는 곳을 탐색
		for(int i=0; i< list.size();i++) {
			ArrayList<Integer> newList=(ArrayList<Integer>) list.clone();
			int newNode=list.get(i);
			newList.remove(i);
			dfs(newNode,sheep,wolf,newList,info);
		}
		
	}
}