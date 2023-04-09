
class kakao_2022_lv2_두큐합같게만들기 {
    
    public int solution(int[] queue1, int[] queue2) {
        long sum1 = 0;
        long sum2 = 0;
        int idx1 = 0;
        int idx2 = 0;
        int cnt = 0;
        int length = queue1.length;
        int [] q1 = new int[length*3];
        int [] q2 = new int[length*3];
        
        for(int i=0;i<length;i++) {
            sum1 += queue1[i];
            q1[i] = queue1[i];
            sum2 += queue2[i];
            q2[i] = queue2[i];
        }
        
        if(sum1 == sum2) return 0;
        
        long target = (sum1+sum2) / 2;
        
        while(cnt < length * 3) {
            if(sum1 > target) {
                int n = q1[idx1];
                sum1 -= n;
                q2[length+(idx1++)] = n;
                sum2 += n;
            } else if(sum2 > target) {
                int n = q2[idx2];
                sum2 -= n;
                q1[length+(idx2++)] = n;
                sum1 += n;
            }
            
            cnt++;
            
            // System.out.println("cnt : "+cnt+", sum1 : "+sum1+", sum2 : "+sum2);
            
            if(sum1 == target && sum2 == target) {
                break;
            }
        }
        
        if(cnt == length * 3) {
            return -1;
        }
        
        return cnt;
    }
}
