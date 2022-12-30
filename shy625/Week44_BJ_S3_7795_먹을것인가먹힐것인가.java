import java.io.*;
import java.util.*;

public class Week44_BJ_S3_7795_먹을것인가먹힐것인가 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int numA = Integer.parseInt(st.nextToken());
            int numB = Integer.parseInt(st.nextToken());

            int[] arrA = new int[numA];
            int[] arrB = new int[numB];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < numA; i++) {
                arrA[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < numB; i++) {
                arrB[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arrA);
            Arrays.sort(arrB);

            int count = 0;
            for (int i = 0; i < numA; i++) {
                for (int j = 0; j < numB; j++) {
                    if (arrA[i] <= arrB[j]) {
                        break;
                    }
                    count++;
                }
            }

            sb.append(count).append(System.lineSeparator());
        }
        
        System.out.println(sb.toString());

        br.close();
    }
}

// 풀이 1
// A와 B 집합에 주어진 수를 정렬
// A에 대해 B의 숫자를 차례대로 크기 비교
// A가 B보다 작거나 같으면 해당 A 수에 대해 확인 종료
// 각 집합을 정렬했기 때문에 B의 다음 수는 확인할 필요없이 A보다 큼
// A 집합의 다음 수와 B 집합과 확인

// 풀이 2
// A 집합과 B 집합의 수 정렬 후
// A에 대해 B의 수를 비교할 때
// binary search를 이용해 확인하고자 하는 A의 수가 B에서는 몇 번째로 큰지 더 빠르게 확인 가능