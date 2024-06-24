import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main_23349_졸업사진 {

	static class Info implements Comparable<Info>{
		String name;
		String place;
		int time;
		boolean start;
		public Info(String name, String place, int time, boolean start) {
			super();
			this.name = name;
			this.place = place;
			this.time = time;
			this.start = start;
		}
		
		@Override
		public int compareTo(Info o) {
			return this.time==o.time? this.place.compareTo(o.place):Integer.compare(this.time, o.time);
		}

	}

	static int N;

	static int maxHuman;
	static String maxPlace;
	static int maxStart;
	static int maxEnd;
	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in);
		N=scann.nextInt();
		
		HashMap<String, Boolean> nameMap=new HashMap<>();
		HashMap<String, Integer> placeMap=new HashMap<>();
		PriorityQueue<Info> pq=new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			String name=scann.next();
			String place=scann.next();
			int startTime=scann.nextInt();
			int endTime=scann.nextInt();
			
			//이미 제출한 사람이면 패스
			if(nameMap.containsKey(name))continue;
			
			nameMap.put(name, true);
			placeMap.put(place, 0);
			pq.offer(new Info(name, place, startTime, true));
			pq.offer(new Info(name, place, endTime, false));
		}
		
		maxHuman=0;
		
		int preTime=0;
		boolean same=false;//같은 구간인지 검색
		while(!pq.isEmpty()) {
			Info cur=pq.poll();
			//System.out.println(cur.place + cur.time + cur.start);
			
			//System.out.println(cur.place+placeMap.get(cur.place));
			
			if(cur.start) {//장소의 사람 추가
				placeMap.put(cur.place, placeMap.get(cur.place)+1);
			}else {//장소의 사람 제거
				placeMap.put(cur.place, placeMap.get(cur.place)-1);
			}
			
			//System.out.println(cur.place+placeMap.get(cur.place));
			//System.out.println();
			//
			if(placeMap.get(cur.place)>maxHuman || (placeMap.get(cur.place)==maxHuman && (cur.place.compareTo(maxPlace)<0))) {//최대 위치장소 변경
				
				maxHuman=placeMap.get(cur.place);
				maxPlace=cur.place;
				maxStart=cur.time;
				maxEnd=cur.time;
				same=true;
				//System.out.println(maxPlace+maxHuman);
			}
			
			//최대구간 끝
			//System.out.println(cur.place+" "+ maxHuman+" "+placeMap.get(cur.place)+same);
			
			if(same && maxPlace.equals(cur.place) && (maxHuman>placeMap.get(cur.place))) {
				maxEnd=cur.time;
				same=false;
			}
			
		}
		
		System.out.println(maxPlace+" "+maxStart+" "+maxEnd);
	}

}
