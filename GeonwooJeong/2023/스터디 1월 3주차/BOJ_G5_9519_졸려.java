import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_G5_9519_졸려 {
   
   // 길이가 8이라면? -> abcdefgh -> ahbgcfde
   // 길이가 7이라면 ? -> abcdefg -> agbfced
   // 길이가 6이라면? -> abcdef -> afbecd
   // 길이가 5라면? -> abcde -> aebdc
   // 길이가 4라면? -> abcd -> adbc
   static String str, origin;
   static List<String> list = new ArrayList<>();
   static boolean flag;
   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      int X = Integer.parseInt(br.readLine());
      
      str = br.readLine();
      origin = str;
      list.add(str);
      
      while (!flag) {
         blink();
      }
      
      int cycle = list.size();

      System.out.println(list.get(X % cycle));

   }

   private static void blink() {
      StringBuilder sb = new StringBuilder();
      int length = str.length();
      int start = 0;
      int end = length - 1;
      
    	  for (int i = start; i <= end; i += 2) {
			sb.append(str.charAt(i));
    	  }
    	  
          if(length % 2 != 0) {
        	  end -= 1;
          }
    	  
    	  for (int i = end; i > 0; i -= 2) {
			sb.append(str.charAt(i));
		}
      
      str = sb.toString();
      if(str.equals(origin)) flag = true;
      else list.add(str);
      
   }

}
