import java.util.*;

class kakao_2022_lv2_양궁대회 {
    static int max = -1;
    static ArrayList<int []> list = new ArrayList<int []>();
    
    public int[] solution(int n, int[] info) {
        
        dfs(n, info, 0, 0, new int[11]);
        
        if(max == -1) {
            return (new int[] {-1});
        } else {
            Collections.sort(list, (o1, o2) -> {
                for(int i=10;i>=0;i--) {
                    if(o1[i] != o2[i]) return o2[i]-o1[i];
                }
                return 0;
            });
            return list.get(0);
        }
    }
    
    static void dfs(int n, int[] info, int turn, int idx, int [] record) {
        if(turn == n) {
            int peach = 0;
            int ryan = 0;
            
            for(int i=0;i<10;i++) {
                int peach_a = info[i];
                int ryan_a = record[i];
                
                if(peach_a == 0 && ryan_a == 0) continue;
                
                if(peach_a >= ryan_a) {
                    peach += (10-i);
                } else {
                    ryan += (10-i);
                }
            }
            
            if(ryan > peach) {
                int diff = ryan - peach;
                if(diff > max) {
                    max = diff;
                    list.clear();
                    list.add(record.clone());
                } else if(diff == max) {
                    list.add(record.clone());
                }
            }
            return ;
        }
        
        for(int i=idx;i<11;i++) {
            record[i]++;
            dfs(n, info, turn+1, i, record);
            record[i]--;
        }
        
        
    }
    
}
