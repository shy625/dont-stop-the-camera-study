package STUDY0512;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_Solution_17615_볼모으기 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		// <<<<<<<<< 입력 >>>>>>>>>
		char[] ball = new char[N];
		int cntR = 0;
		int cntB = 0;
		String str = br.readLine();
		for(int i=0; i<N; i++) {
			ball[i] = str.charAt(i);
			if(ball[i]=='R')cntR++;		// 빨간 공의 개수
			else cntB++;				// 파란 공의 개수
		}		
		
		// <<<<<<<<< 아이디어 - 로직 >>>>>>>>>
		int left_to_right_RED = 0;
		int left_to_right_BLUE = 0;
		int right_to_left_RED = 0;
		int right_to_left_BLUE = 0;
		// 경우1) 공을 왼쪽에서 오른쪽 방향으로 옮기는 경우
		// 방법1) 가장 오른쪽에 있는 같은 색깔의 공 무더기를 제외시킨다.
		// 방법2) 경우1-1) 빨간색 공을 옮길 경우
		//       방법2-1) 최소 이동횟수 : 마지막 공 무더기를 제외하고 난 나머지 공 중에서 빨간색 공의 갯수
		//       경우1-2) 파란색 공을 옮길 경우
		//       방법 2-1) 최소 이동횟수 : 마지막 공 무더기를 제외하고 난 나머지 공 중에서 파란색 공의 갯수
		// 주의) 모든 공이 같은 색깔로만 이루어진 경우 
		//      -> 가장 오른쪽에 있는 같은 색깔의 공  무더기를 제외시킬 때 index overflow조심 	
		
		int idx = N-1;
		int cntSide = 1;
		while(idx>0 && ball[idx]==ball[idx-1]) {
			idx--;
			cntSide++;
		}
		System.out.println("가장 오른쪽 같은색 공 무더기의 공 개수 cntSide:"+cntSide);
		if(ball[N-1]=='R') {
			left_to_right_RED = cntR - cntSide;
			left_to_right_BLUE = cntB;
		}else {
			left_to_right_RED = cntR;
			left_to_right_BLUE = cntB - cntSide;
		}
		
		// 경우2) 공을 오른쪽에서 왼쪽 방향으로 옮기는 경우
		// 방법1) 가장 왼쪽에 있는 같은 색깔의 공 무더기를 제외시킨다.
		// 방법2) 경우1-1) 빨간색 공을 옮길 경우
		//       방법2-1) 최소 이동횟수 : 마지막 공 무더기를 제외하고 난 나머지 공 중에서 빨간색 공의 갯수
		//       경우1-2) 파란색 공을 옮길 경우
		//       방법 2-1) 최소 이동횟수 : 마지막 공 무더기를 제외하고 난 나머지 공 중에서 파란색 공의 갯수
		// 주의) 모든 공이 같은 색깔로만 이루어진 경우 
		//      -> 가장 오른쪽에 있는 같은 색깔의 공  무더기를 제외시킬 때 index overflow조심
	
		idx = 0;
		cntSide = 1;
		while(idx<N-1 && ball[idx]==ball[idx+1]) {
			idx++;
			cntSide++;
		}
		System.out.println("가장 왼쪽 같은색 공 무더기의 공 개수 cntSide:"+cntSide);
		if(ball[0]=='R') {
			right_to_left_RED = cntR - cntSide;
			right_to_left_BLUE = cntB;
		}else {
			right_to_left_RED = cntR;
			right_to_left_BLUE = cntB - cntSide;
		}

		System.out.println("빨강공을 왼->오 때 최소 이동횟수:"+left_to_right_RED+"\n"+
							"파랑공을 왼->오 때 최소 이동횟수:"+left_to_right_BLUE+"\n"+
							"빨강공을 오->왼 때 최소 이동횟수:"+right_to_left_RED+"\n"+
							"파랑공을 오->왼 때 최소 이동횟수:"+right_to_left_BLUE+"\n");
		// <<<<<<<<< 정답 출력 >>>>>>>>>
		// 4가지 경우(빨간색 공을 왼쪽에서 오른쪽으로  옮기는 경우,
		//         파란색 공을 왼쪽에서 오른쪽으로 옮기는 경우,
		//         빨간색 공을 오른쪽에서 왼쪽으로 옮기는 경우,
		//         파란색 공을 오른쪽에서 왼쪽으로 옮기는 경우)
		// 모든 경우의 수 중에서 가장 작은 값 출력
		int ans = Math.min(right_to_left_RED, right_to_left_BLUE);
		ans = Math.min(ans, left_to_right_RED);
		ans = Math.min(ans, left_to_right_BLUE);
		System.out.println(ans);
		
	}
}
