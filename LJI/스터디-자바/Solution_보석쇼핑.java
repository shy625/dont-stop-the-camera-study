import java.util.HashMap;
import java.util.Map;

public class Solution_보석쇼핑 {

	//end부터 늘리고 start를 땡겨오는 방식?
		class Solution {
		    public int[] solution(String[] gems) {
		        int[] answer = {};
		        
		        //먼저 보석 종류들을 알아내야한다
		        Map<String, Integer> gemsMap=new HashMap<String, Integer>();
		        //보석 맵으로 넣기
		        for (String gem:gems) {
					if(!gemsMap.containsKey(gem))gemsMap.put(gem, 0);
				}
		        
		        
		        //보석들을 개수를 end를 늘려가면서 추가하면서 보석이 전부 포함되는 구간 찾기
		        //찾으면 앞을 한칸씩 줄여서 최소 길이 구간 만들기
		        int start=0;//시작점
		        int end=0;//끝점
		        int cnt=0;//구간 길이
		        int min=Integer.MAX_VALUE;
		        
		        int allCnt=0;//모든것이 포함됐는지 여부
		        int all=gemsMap.size();
		        //찾아내기
		        while(end<gems.length) {
		        	if(allCnt==all) {//전부 포함될 때
		        		//최적화 작업 필요
		        		//start 최대한 구간 땡기기
		        		while(allCnt==all) {
		        			if(gemsMap.get(gems[start])==1) allCnt--;
		        			gemsMap.put(gems[start], gemsMap.get(gems[start])-1);
		        			start++;
		        			cnt--;
		        			
		        		}
		        		//현재 start 바로 전이 실제 구간일 것
		        		if(cnt+1<min) {//정답 갱신
		        			answer=new int[] {start,end};
		        			min=cnt+1;
		        		}
		        	}else {//전부 포함되지 않을 때
		        		//end 늘려야할 것
		        		//없던 보석의 추가
		        		if(gemsMap.get(gems[end])==0) allCnt++;
		        		
		        		gemsMap.put(gems[end], gemsMap.get(gems[end])+1);
		        		end++;
		        		cnt++;
		        	}
		        }
		        
		        
		        if(allCnt==all) {//전부 포함될 때
	        		//최적화 작업 필요
	        		//start 최대한 구간 땡기기
	        		while(allCnt==all) {
	        			if(gemsMap.get(gems[start])==1) allCnt--;
	        			gemsMap.put(gems[start], gemsMap.get(gems[start])-1);
	        			start++;
	        			cnt--;
	        			
	        		}
	        		//현재 start 바로 전이 실제 구간일 것
	        		if(cnt+1<min) {//정답 갱신
	        			answer=new int[] {start,end};
	        			min=cnt+1;
	        		}
	        	}
		        //System.out.println("끝");
		        return answer;
		    }

		}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
