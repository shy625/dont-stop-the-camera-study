import java.util.*;

class Node {
    int v;
    int dist;
    
    public Node (int v, int dist){
        this.v = v;
        this.dist = dist;
    }
}

class kakao_2022_lv3_등산코스정하기 {
    static ArrayList<ArrayList<Node>> list;
    static int [] answer;
    
    static void dijkstra (int n, int [] gates, int [] summits) {
        Queue<Node> q = new LinkedList<>();
        int [] d = new int[n+1];
        Arrays.fill(d, Integer.MAX_VALUE);
        
        for (int gate : gates) {
            q.add(new Node(gate, 0));
            d[gate] = 0;
        }
        
        while(!q.isEmpty()) {
            Node cur = q.poll();
            int curV = cur.v;
            int curDist = cur.dist;
            
            if(curDist > d[curV]) continue;
            
            for(Node node : list.get(curV)) {
                if(d[node.v] > Math.max(d[curV], node.dist)) {
                    d[node.v] = Math.max(d[curV], node.dist);
                    q.add(new Node(node.v, d[node.v]));
                }
            }
            
        }
        
        int idx = 0;
        int min = Integer.MAX_VALUE;
        
        for(int summit : summits) {
            if(min > d[summit]) {
                idx = summit;
                min = d[summit];
            }
        }
        
        answer = new int[] {idx, min}; 
        
    }
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        list = new ArrayList<>();
        Arrays.sort(summits);
        
        for(int i=0;i<=n;i++) {
            list.add(new ArrayList<>());
        }
        
            for(int i=0;i<paths.length;i++) {
            int from = paths[i][0];
            int to = paths[i][1];
            int weight = paths[i][2];
            
            if(isGate(from, gates) || isSummit(to, summits)) {
                list.get(from).add(new Node(to, weight));
            } else if(isGate(to, gates) || isSummit(from, summits)) {
                list.get(to).add(new Node(from, weight));
            } else {
                list.get(from).add(new Node(to, weight));
                list.get(to).add(new Node(from, weight));
            }
        }
        
        dijkstra(n, gates, summits);
        
        return answer;
    }
    
    static boolean isGate(int n, int[] gates) {
        for(int i=0;i<gates.length;i++) {
            if(n == gates[i]) {
                return true;
            }
        }
        return false;
    }
    
    static boolean isSummit(int n, int[] summits) {
        for(int i=0;i<summits.length;i++) {
            if(n == summits[i]) {
                return true;
            }
        }
        return false;
    }
}
