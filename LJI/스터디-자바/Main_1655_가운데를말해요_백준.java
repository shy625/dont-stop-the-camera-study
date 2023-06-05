import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main_1655_가운데를말해요_백준 {
	//우선순위 큐 두개 유지//중간값보다 낮은건 내림차순 배열//높은건 오름차수 배열
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		
		StringBuilder sb= new StringBuilder();
		PriorityQueue<Integer> lowQue=new PriorityQueue<>(Collections.reverseOrder());//중간값포함 낮은거//내림차순 배열//반환시 중간값
		PriorityQueue<Integer> highQue=new PriorityQueue<>();//중간값 제외 높은거//오름차순 큐//반환시 중간값 다음 큰거
		for (int t = 0; t < N; t++) {
			int newNum=Integer.parseInt(br.readLine());
			int lowNum=0;
			if(!lowQue.isEmpty()) {//로우큐는 항상 하이큐보다 하나 많거나 같게 유지해야한다
				lowNum=lowQue.poll();//전 중간값 꺼내기
				if(!highQue.isEmpty()) {
					int highNum=highQue.poll();
					if(newNum<lowNum) {//새로운 값이 가장 작음
						int temp=newNum;
						newNum=lowNum;
						lowNum=temp;
					}
					if(newNum>highNum) {//새로운 값이 가장 큼
						int temp=newNum;
						newNum=highNum;
						highNum=temp;
					}
					if(highQue.size()==lowQue.size()) {//중간값까지 로우큐
						lowQue.offer(lowNum);
						lowQue.offer(newNum);
						highQue.offer(highNum);
					}else {//두개의 값을 하이큐에
						lowQue.offer(lowNum);
						highQue.offer(newNum);
						highQue.offer(highNum);
					}
				}
				else {//하이큐가 비었을 때
					if(newNum<lowNum) {
						int temp=newNum;
						newNum=lowNum;
						lowNum=temp;
					}
					lowQue.offer(lowNum);
					highQue.offer(newNum);
				}
			}else {//로우큐가 비었을 때
				lowNum=newNum;
				lowQue.offer(lowNum);
			}
			
			int midNum=lowQue.poll();
			sb.append(midNum).append("\n");
			lowQue.offer(midNum);
		}
		
		System.out.println(sb.toString());
	}

}
