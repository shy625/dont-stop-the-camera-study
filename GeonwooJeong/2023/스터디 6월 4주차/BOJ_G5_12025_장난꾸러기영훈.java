import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_G5_12025_장난꾸러기영훈 {

   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      String str = br.readLine();
      long K = Long.parseLong(br.readLine());
      
      // 숫자를 하나씩 저장할 배열
      char [] nums = new char[str.length()];
      // 1, 2, 6, 7이 존재할 때 해당 숫자가 몇번째 자리에 있는지 저장할 리스트
      List<Integer> list = new ArrayList<>();
      // 최대 몇가지의 경우의수가 나올지 계산할 변수(K와 비교하기 위함)
      long number = 1;
      
      for (int i = 0; i < str.length(); i++) {
         char c = str.charAt(i);
         nums[i] = c;
         
         if(c == '1' || c == '6') {
            nums[i] = '1';
            number *= 2;
         } else if(c == '2' || c == '7') {
            nums[i] = '2';
            number *= 2;
         }
      }
      
      // 뒤에 자리수부터 숫자를 바꿔야 하므로 뒤에부터 탐색함
      for (int i = str.length()-1; i >= 0; i--) {
    	  if(nums[i] == '1' || nums[i] == '2') {
    		  list.add(i);
    	  }
      }
      
      // K번째 비밀번호가 존재하지 않으면 -1 출력
      if(K > number) {
         System.out.println(-1);
      } else {
    	 K--;
         StringBuilder sb = new StringBuilder();
         int idx = 0;
         
         while(K > 0) {
        	// 2로 나누었을 때 1이 남는다면 둘 중 더 큰 숫자를 사용해야 한다.
            if(K % 2 == 1) {
               if(nums[list.get(idx)] == '1') {
                  nums[list.get(idx)] = '6';
               } else if(nums[list.get(idx)] == '2') {
                  nums[list.get(idx)] = '7';
               }
            }
            
            K /= 2;
            idx++;
            
         }
         
         // 최종적으로 비밀번호 구성
         for (char c : nums) {
            sb.append(c);
         }

         System.out.println(sb.toString());
         
      }

   }

}
