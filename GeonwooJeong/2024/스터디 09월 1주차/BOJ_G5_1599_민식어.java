import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BOJ_G5_1599_민식어 {

   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      int N = Integer.parseInt(br.readLine());
      
      Map<String, String> map = new HashMap<String, String>();
      List<String> list = new ArrayList<String>();
      
      for (int i = 0; i < N; i++) {
         String origin = br.readLine();
         String newStr = changeStr(origin);
         
         map.put(newStr, origin);
         list.add(newStr);
      }
      
      Collections.sort(list);
      
      StringBuilder sb = new StringBuilder();
      
      for(String str : list) {
         sb.append(map.get(str)+"\n");
      }
      
      sb.setLength(sb.length()-1);
      System.out.println(sb.toString());
      
   }

   private static String changeStr(String str) {
      str = str.replaceAll("p", "q");
      str = str.replaceAll("o", "p");
      str = str.replaceAll("ng", "o");
      str = str.replaceAll("k", "c");
      
      return str;
   }

}
