import java.util.Arrays;

class Solution {
	static int dp[][];
	static int maxR;
	static int maxC;
    public int solution(int alp, int cop, int[][] problems) {
        //bfs??
    	//dp?특정 문제 알고력 코딩력에 도달하는 최소 시간대//alp,cop의 크기는 0부터 150까지...[151][151] 배열
    	//최소시간 차례로 갱신해가고
    	//문제를 풀 수 있다면 이후 값 갱신
    	//자기보다 왼쪽 위에 있는 경우들에게서 +
    	//r:알고력 c:코딩력
        //알고력, 코딩력이 요구하는 최대 값을 각각 기억한 후 bfs를 돌면서 목적에 도달하면 종료
    	//시간을 넣고 pq 정렬?
    	
        int answer = 0;
        maxR=0;
        maxC=0;
        int needR=0;
        int needC=0;
        int size=problems.length;
        //요구하는 최대값 갱신
        for(int i=0;i<size;i++) {
        	needR=Math.max(needR, problems[i][0]);
        	needC=Math.max(needC, problems[i][1]);
        }
        //alp,cop와 요구 값으로 최대값 갱신
        maxR=Math.max(alp, needR);
        maxC=Math.max(cop, needC);
        dp=new int[maxR+1][maxC+1];//dp 배열 생성
        for (int i = 0; i <=maxR; i++) {
			Arrays.fill(dp[i], Integer.MAX_VALUE/10);
		}
        //alp,cop 전은 전부 0
        for (int i = 0; i <= alp; i++) {
			for (int j = 0; j <= cop; j++) {
				dp[i][j]=0;
			}
		}
        //alp,cop위치부터 시작하게 패스 설정
        for (int i = 0; i <= maxR; i++) {
			for (int j = 0; j <=maxC; j++) {
				if(!(i<=alp&&j<=cop)) {//0일테니 차피 할 필요 없다 최소값 갱신은 할 필요 없다
				
				int min=dp[i][j];
				//왼쪽과 위쪽에서 +1 했을때 작은 값으로 갱신
				if(check(i-1,j)) {//위쪽이 있다면 알고력 +1
					min=Math.min(dp[i-1][j]+1, min);
				}
				if(check(i,j-1)) {//왼쪽이 있다면 코딩력 +1
					min=Math.min(dp[i][j-1]+1, min);
				}
				//현재 위치 값 갱신
				dp[i][j]=min;
				}
				//현재 최소값기준으로
				//문제 풀 수 있다면 갱신//이때 maxR,maxC 초과시 maxR,maxC로 교체
				for (int p = 0; p < size; p++) {
					if(i>=problems[p][0]&&j>=problems[p][1]) {//문제를 풀 수 있다면 풀었을때 얻는 시간 계산
						int nr=i+problems[p][2];//풀면 이동할 수 있는 알고력
						int nc=j+problems[p][3];//풀면 이동할 수 있는 코딩력
						if(nr>needR)nr=needR;
						if(nc>needC)nc=needC;
						
						dp[nr][nc]=Math.min(dp[nr][nc], dp[i][j]+problems[p][4]);
					}
				}
			}
		}
        
        answer=dp[needR][needC];
        return answer;
    }
	private boolean check(int r, int c) {
		return r>=0&&r<=maxR&&c>=0&&c<=maxC;
	}
}