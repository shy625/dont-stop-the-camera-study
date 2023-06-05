import java.util.Arrays;

public class Solution_합승택시요금 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	//플로이드 가능?
	//20000000최대? int 가능
	public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        int [][] arr=new int [n+1][n+1];//지점이 1부터 시작
        for (int i = 1; i <=n; i++) {
			Arrays.fill(arr[i], Integer.MAX_VALUE/10);
			arr[i][i]=0;
		}
        
        //간선 비용 구하기
        for(int [] cost:fares) {
        	arr[cost[0]][cost[1]]=cost[2];
        	arr[cost[1]][cost[0]]=cost[2];
        }
        
        //플로이드
        for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				if(i==k) continue;
				for (int j = 1; j <= n; j++) {
					if(j==k||i==j) continue;
					arr[i][j]=Math.min(arr[i][j], arr[i][k]+arr[k][j]);
				}
			}
		}
        
        //s부터 각 지점 
        //그 지점에서 a,b의 금액 체크
        //i는 둘이 합승해서 갈 지점
        answer=Integer.MAX_VALUE/10;
        for (int i = 1; i <= n; i++) {
			answer=Math.min(answer, arr[s][i]+arr[i][a]+arr[i][b]);
		}
        return answer;
    }
}
