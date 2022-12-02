import java.io.*;
import java.util.*;

class kakao_2019_lv1_실패율 {
    public int[] solution(int N, int[] stages) {
        int [] result = new int[N];
        int [] fail = new int[N+1];
        int [] player = new int[N+1];
        Map<Integer, Double> map = new HashMap<>();
        
        for(int i=0;i<stages.length;i++) {
            int n = stages[i];
            if(n == N+1) {
                for(int j=1;j<=N;j++) {
                    player[j] += 1;
                }
            } else {
                for(int j=1;j<=n;j++) {
                    player[j] += 1;
                }
                fail[n] += 1;
            }
        }
        
        for(int i=1;i<=N;i++) {
            map.put(i, (double)fail[i]/player[i]);
        }
        
        List<Map.Entry<Integer, Double>> list = new LinkedList<>(map.entrySet());
        
        Collections.sort(list, new Comparator<Map.Entry<Integer, Double>>() {
            @Override
            public int compare(Map.Entry<Integer, Double> o1,
                               Map.Entry<Integer, Double> o2) {
                if (o1.getValue() > o2.getValue()) {
                    return -1;
                }
                else if (o1.getValue() < o2.getValue()) {
                    return 1;
                }
 
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        
        int cnt = 0;
        
        for (Map.Entry<Integer, Double> entry : list) {
            int key =  entry.getKey();
            result[cnt++] = key;
        }
        
        return result;
    }
}
