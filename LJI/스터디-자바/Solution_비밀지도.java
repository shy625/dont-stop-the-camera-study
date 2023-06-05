
public class Solution_비밀지도 {

	class Solution {
	     //맵을 선언하고 지도 1과 2를 각각 적용하면 될 것 같은디?
	    public String[] solution(int n, int[] arr1, int[] arr2) {
	        String[] answer = {};
	        answer=new String[n];
	        //맵 선언
	        //기본은 0 , # 이면 1
	        int [][] map=new int [n][n];
	        
	        //줄 체크
	        //arr1 이나 2 둘 중 하나라도 있다면 1로 체크
	        for(int i=0;i<n;i++){
	            //오른쪽부터 시작합시다
	            for(int j=n-1;j>=0;j--){
	                //이진수에서 자리 구하기
	                int bin=(n-1)-j;
	                if((arr1[i]&1<<bin)==1<<bin || (arr2[i]&1<<bin)==1<<bin)
	                    map[i][j]=1;
	               
	            }
	        }
	        //n줄만큼
	        
	        //문자열 변환
	        for(int i=0;i<n;i++){
	            StringBuilder sb=new StringBuilder();
	            for(int j=0;j<n;j++){
	                if(map[i][j]==1){
	                    sb.append("#");
	                }else{
	                    sb.append(" ");
	                }
	            }
	            
	            answer[i]=sb.toString();
	        }
	        return answer;
	    }
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
