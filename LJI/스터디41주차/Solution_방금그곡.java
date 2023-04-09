
public class Solution_방금그곡 {

	class Solution {
	    public String solution(String m, String[] musicinfos) {
	        String answer = "";
	        int n=musicinfos.length;
	        String [] musicPlay=new String [n];//실제 플레이된 노래 저장할 위치
	        String [] musicName=new String [n];
	        int [] musicPlayTime=new int[n];
	        //음악
	       
	        for(int i=0;i<n;i++) {
	        	String nowMusic=musicinfos[i];
	        	String [] infos=nowMusic.split(",");//0: startTime 1:endTime 2:노래제목 3:악보
	        	
	        	//00:00을 넘기는 일이 없으니 endTime이 무조건 크다
	        	//startTime
	        	String [] startTimes=infos[0].split(":");
	        	int startHour=Integer.parseInt(startTimes[0]);
	        	int startMin=Integer.parseInt(startTimes[1]);
	        	//endTime
	        	String [] endTimes=infos[1].split(":");
	        	int endHour=Integer.parseInt(endTimes[0]);
	        	int endMin=Integer.parseInt(endTimes[1]);
	        	
	        	//playTime 구하기
	        	int playTime=(endHour-startHour)*60+endMin-startMin;
	        	musicPlayTime[i]=playTime;
	        	//노래 제목 저장
	        	musicName[i]=infos[2];
	        	
	        	//노래 플레이 저장
	        	StringBuilder sb=new StringBuilder();
	        	
	        	//음악의 총 길이 //#은 포함 x
	        	int musicLength=0;
	        	for (int j = 0; j < infos[3].length(); j++) {
					if(infos[3].charAt(j)!='#') musicLength++;
				}
	        	//musicPlayTime[i]=musicLength;
	        	
	        	int fullTime=playTime/musicLength;//노래가 풀타임으로 반복된양
	        	int leastTime=playTime-(musicLength)*fullTime;//노래가 재생된 나머지 양
	        	
	        	//풀타임 문자열 더하기
	        	for (int j = 0; j < fullTime; j++) {
					sb.append(infos[3]);
				}
	        	//나머지 더하기
	        	int cnt=0;
	        	for (int j = 0; j < infos[3].length(); j++) {
	        		if(infos[3].charAt(j)!='#') cnt++;
					sb.append(infos[3].charAt(j));
					
					if(cnt==leastTime) {
						//혹시 #이 뒤에 있을 수도 있다
						if(j+1<infos[3].length()&& infos[3].charAt(j+1)=='#') sb.append('#');
						
						break;
					}
				}
	        	//음악 재생 저장
	        	musicPlay[i]=sb.toString();
	        	
	        }
	        
	        int maxLength=-1;
	        for(int i=0;i<n;i++) {
//				if(musicPlay[i].contains(m)) {
//					if(maxLength<musicPlayTime[i]) {
//						maxLength=musicPlayTime[i];
//						answer=musicName[i];
//					}
//				}
	        	
	        	int index=0;
	        	while(true) {
	        		if(index>musicPlay[i].length()-1)break;
	        		
	        		int find=musicPlay[i].indexOf(m, index);
	        		if(find>-1) {//같은 문자열 찾았다
	        			index=find+1;
	        			//뒤의 #이 있으면 pass
	        			int lastCheck=find+m.length();
	        			if(lastCheck==musicPlay[i].length()||musicPlay[i].charAt(lastCheck)!='#') {
	        				if(maxLength<musicPlayTime[i]) {
	    						maxLength=musicPlayTime[i];
	    						answer=musicName[i];
	    					}
	        			}
	        		}else {
	        			break;
	        		}
	        	}
			}
	        
	        if (answer=="")answer="(None)";
	        return answer;
	    }
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
