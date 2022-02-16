import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class BOJ_Solution_2606_바이러스 {
    static int N;               // computer size
    static int[] com;           //  if a computer has worm virus or not (0:not detected, 1: detected)
    static int[][] pair;        // how to chain with each computer

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력
        N = Integer.parseInt(br.readLine());
        com = new int[N];
        com[0] = 1;

        int P = Integer.parseInt(br.readLine());    // pair size
        pair = new int[P][2];
        for (int p = 0; p < P; p++) {
            String[] str = br.readLine().split(" ");
            pair[p][0] = Integer.parseInt(str[0]);
            pair[p][1] = Integer.parseInt(str[1]);
            // pair 정렬 : pair[p][0]의 값에는 com의 작은 인덱스, pair[p][1]의 값에는 com의 큰 인덱스
            if (pair[p][0] > pair[p][1]) {
                int temp = pair[p][0];
                pair[p][0] = pair[p][1];
                pair[p][1] = temp;
            }
        }

        // pair 정렬 : pair[0][0]의 값에 com의 작은 인덱스, pair[1][0]의 값에는 com의 큰 인덱스
        for (int i=0; i < P-2; i++) {
            for (int j=0; j<P-i-1; j++) {
                if (pair[j][0] > pair[j+1][0]) {      // 값이 큰게 뒤로 보내지는 솙팅
                    int[] temp = new int[2];
                    temp[0] = pair[j][0];
                    temp[1] = pair[j][1];
                    pair[j][0]   = pair[j+1][0];
                    pair[j][1]   = pair[j+1][1];
                    pair[j+1][0] = temp[0];
                    pair[j+1][1] = temp[1];
                }
            }
        }


        // 원 바이러스 감염시키기
        for(int i=0; i<P; i++){
            for(int k=0; k<P; k++){
                if(com[pair[k][0]-1]==1) com[pair[k][1]-1]=1;
                if(com[pair[k][1]-1]==1) com[pair[k][0]-1]=1;       // 쌍방향으로 감염되기 때문
                //System.out.println("연결된 정보 pair["+k+"]기반으로 감염완료:"+pair[k][0]+","+pair[k][1]);
            }
        }


        // v_com : 웜 바이러스에 감연된 컴퓨터 수 구하기
        int v_com = 0;
        for(int i=0; i<N; i++){
            //System.out.println(i+1+"번 컴퓨터 감염여부(0-감염안됨, 1-감염됨) : " + com[i]);
            if(com[i]==1) v_com++;
        }

        // 정답 출력 :컴퓨터1을 통해 웜바이러스에 감염된 컴퓨터 수 (이미 웜바이러스에 감염된 컴퓨터1은 제외시킴)
        System.out.print(v_com-1);
    }
}