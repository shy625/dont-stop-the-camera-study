import java.util.*;

class Solution {
    
    private static final int TOOTHBRUSH = 100;
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        Map<String, Integer> memberMap = new HashMap<>();
        int n = enroll.length;
        
        memberMap.put("center", 0);
        for (int i = 0; i < n; i++) {
            memberMap.put(enroll[i], i + 1);
        }
        
        int[] parents = new int[n + 1];
        parents[0] = -1;
        for (int i = 0; i < n; i++) {
            if (referral[i].equals("-")) {
                parents[i + 1] = 0;
            } else {
                parents[i + 1] = memberMap.get(referral[i]);
            }
        }
        
        int m = seller.length;
        int[] profits = new int[n + 1];
        for (int i = 0; i < m; i++) {
            int curMember = memberMap.get(seller[i]);
            int profit = amount[i] * TOOTHBRUSH;
            distributeProfit(profits, parents, curMember, profit);
        }
                
        return Arrays.copyOfRange(profits, 1, n + 1);
    }
    
    private void distributeProfit(int[] profits, int[] parents, int curMember, int profit) {
        while (curMember >= 0) {
            int nextProfit = (int) (profit * 0.1);
            profits[curMember] += profit - nextProfit;
            curMember = parents[curMember];
            profit = nextProfit;
        }
    }
}

// 풀이 1
// int[] parents 배열을 선언하여 각 구성원의 부모값을 저장
// 각 구성원의 번호는 Map<String, Integer>를 선언하여 초기에 enroll 배열에 있는 순서대로 이름을 key, 등록순서를 구성원 번호로 하여 값에 저장하고 이후에 Map에서 이름을 가지고 번호 찾기
// 각 구성원들의 이익을 저장하는 int[] profits 선언
// 이익이 생기면 부모에게 줄 이익 0.1을 계산하고 남은 이익을 profit 배열에 저장
// 부모는 자식으로부터 받은 이익에 대해 동일하게 계산
// root까지 반복

// 풀이 2
// 1번 풀이에서 시간복잡도를 개선하기 위해 시도
// 모든 구성원들의 관계가 일렬로 구성되어 있는 경우, 1번과 같이 진행하면 최대 1만 * 10만의 시간복잡도
// 한 구성원이 이익을 연속으로 얻는 경우, 연속으로 얻은 이익을 모두 합쳐 한번에 계산하면 시간복잡도를 줄일 수 있을 것이라 생각
// BUT 다 합쳐서 계산하게 되면 개별로 계산했을 때와 1원 미만 단위가 절삭되는 값이 다르게 되므로 결과가 달라짐