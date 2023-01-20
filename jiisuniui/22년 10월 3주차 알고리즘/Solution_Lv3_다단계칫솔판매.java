
public class Solution_Lv3_다단계칫솔판매 {
    public static class Member {
        String name;
        String parent;
        int parentnum;
        int profit;

        public Member(String name, String parent, int parentnum) {
            this.name = name;
            this.parent = parent;
            this.parentnum = parentnum; // 계산시간 줄이기위해
            this.profit = 0;
        }

        public void plusProfit(int plus) {
            this.profit += plus;
        }
    }

    static Member[] members;

    public static void main(String[] args) {
        String[] enroll = { "john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young" };
        String[] referral = { "-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward" };
        String[] seller1 = { "young", "john", "tod", "emily", "mary" };
        int[] amount1 = { 12, 4, 2, 5, 10 };
        String[] seller2 = { "sam", "emily", "jaimie", "edward" };
        int[] amount2 = { 2, 3, 5, 4 };

        int[] answer = solution(enroll, referral, seller1, amount1);
        // int[] answer = solution(enroll, referral, seller2, amount2);
        // System.out.println(answer);
        for (int i = 0; i < enroll.length; i++) {
            System.out.println(answer[i]);
        }
    }

    public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];
        members = new Member[enroll.length];
        for (int i = 0; i < enroll.length; i++) {
            int refnum = Integer.MAX_VALUE;
            if (!referral[i].equals("-")) {
                for (int j = 0; j < enroll.length; j++) {
                    if (referral[i].equals(enroll[j])) {
                        refnum = j;
                    }
                }
            }
            members[i] = new Member(enroll[i], referral[i], refnum);
        }

        for (int i = 0; i < seller.length; i++) {
            for (int j = 0; j < enroll.length; j++) {
                if (seller[i].equals(enroll[j])) { // seller와 enroll의 이름이 같으면
                    allocationProfit(enroll, referral, j, amount[i] * 100); // 이익배분계산
                    break;
                }
            }
        }

        for (int i = 0; i < enroll.length; i++) {
            answer[i] = members[i].profit;
        }

        return answer;
    }

    public static int allocationProfit(String[] enroll, String[] referral, int sellernum, int sellPF) {
        int parentPF = sellPF / 10;
        int myPF = sellPF - parentPF;
        members[sellernum].plusProfit(myPF);

        if ((parentPF == 0)) {
            return 0;
        } // 더이상 배분할 이익이 없음

        // 배분한 이익을 등록한 후에 내가 center 밑인지 판별 (center에도 배분하기때문)
        if (referral[sellernum].equals("-")) {
            return 0;
        }

        allocationProfit(enroll, referral, members[sellernum].parentnum, parentPF);
        return 0;
    }
}
