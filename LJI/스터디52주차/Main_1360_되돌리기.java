import java.util.ArrayList;
import java.util.Scanner;

public class Main_1360_되돌리기 {
	//역순으로 undo만 쭉 처리하고 다시 순서대로 type만 처리하면 될 것 같다
	
	static class Order{
		String type;
		String text;
		int time;
		public Order(String type, String text, int time) {
			super();
			this.type = type;
			this.text = text;
			this.time = time;
		}
		
	}
	static int N;
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		N=scann.nextInt();
		
		//명령어 입력
		ArrayList<Order> orderList=new ArrayList<>();
		for (int i = 0; i < N; i++) {
			String type=scann.next();
			String text=scann.next();
			int time=scann.nextInt();
			orderList.add(new Order(type, text, time));
		}
		
		//역순으로 undo명령어들 처리하자
		for (int i = N-1; i >=0; i--) {
			//orderList역순으로 가면서 undo찾기
			Order order=orderList.get(i);
			if(order.type.equals("undo")) {
				//몇초까지의 명령어들을 무효화 할지 계산
				int time=order.time - Integer.parseInt(order.text);
				
				//현재 명령어는 이제 지우자
				orderList.remove(i);
				
				//이전 명령어들 중 time보다 크거나 같은 것들 다 지우자
				int index=i-1;
				while(index>=0) {
					//지워야되는 명령어
					if(orderList.get(index).time>=time) {
						orderList.remove(index);
						index--;
						i--;
					}else break;
				}
			}
		}
		//정순으로 text추가하자
		StringBuilder sb=new StringBuilder();
		for(Order order:orderList) {
			sb.append(order.text);
		}
		
		//출력
		System.out.println(sb.toString());
	}

}
