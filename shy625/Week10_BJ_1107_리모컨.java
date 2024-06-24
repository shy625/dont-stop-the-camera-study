/**
 * BJ 1107 리모컨
 * @ prob : https://www.acmicpc.net/problem/1107
 * @ level : G5
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Week10_BJ_1107_리모컨 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        
        int availableSet = (1 << 10) - 1;
        if(M != 0) {
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < M; i++) {
                int num = Integer.parseInt(st.nextToken());
                availableSet &= ~(1 << num);
            }
        }

        int min = Math.abs(N - 100);    // +, - 로만 이동하는 경우로 초기화

        if(M != 10) {   // 고장나지 않은 버튼이 있는 경우
            for(int i = 0; i < 1000000; i++) {
                int count = checkAvailable(i, availableSet);    // 누를 수 있는 번호인지 확인
                if(count > 0) {
                    count += Math.abs(N - i);
                    min = Math.min(min, count);
                }
            }
        }

        System.out.println(min);

        br.close();
    }

    // 주어진 번호가 누를 수 있는 번호인지 확인
    private static int checkAvailable(int num, int availableSet) {
        int tmp = num;
        if(tmp == 0) {      // 주어진 번호가 0인 경우
            if((availableSet & 1) == 0) {   // 0이 고장났으면 -1 반환
                return -1;
            }
            return 1;   // 0이 고장나지 않았으면 자릿수 반환
        }

        int count = 0;
        while(tmp > 0) {
            int remain = tmp % 10;
            if((availableSet & 1 << remain) == 0) {     // 고장난 번호가 있으면 -1 출력
                return -1;
            }
            tmp /= 10;
            count++;
        }
        return count;   // 고장난 번호가 없어 누를 수 있는 번호이면 자릿수 출력
    }
}

// 풀이 1 - 실패
// 주어진 수 N부터 시작해서 계속 +1, -1을 한 수에 대해 가능 여부를 확인하고
// 가능하다면 그때의 버튼 횟수와 100에서 +, - 버튼만 사용할 때의 버튼 횟수와 비교하여 최솟값을 출력
// 반례
// 10
// 8
// 0 2 3 4 5 6 7 8
// 출력 : 3, 정답 : 2
// https://www.acmicpc.net/source/41947923

// 풀이 2
// 0부터 100만까지의 수에 대해 주어진 번호로 만들 수 있는지 확인하고, 만들 수 있다면 버튼을 누르는 횟수를 계산하여 그 중 최솟값을 출력
// N = 50만, 고장나지 않은 버튼이 0, 9라고 했을 때
// 만들 수 있는 N에 가장 가까운 수는 99999 -> 40만 1, 900000 -> 40만으로 버튼 클릭 수를 고려하면 둘 다 40만 6회가 된다.
// 즉, 답을 계산할 때 적어도 90만까지는 답이 될 수 있으므로 고려해야 하는데, 코드의 편의성을 위해 90만 대신 100만까지로 for문을 작성하였다.
// https://www.acmicpc.net/source/41993414

// 다른 풀이 1
// 중복 순열
// 고장나지 않은 번호로 만들 수 있는 모든 조합의 번호를 구하고, 각각에 대해 버튼을 누르는 횟수를 계산하여 그 중 최솟값을 출력
// https://github.com/shy625/dont-stop-the-camera-study/blob/main/JiHwanYoon/Main_1107_%EB%A6%AC%EB%AA%A8%EC%BB%A8.java