import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1451_직사각형으로나누기 {
	
	static int [][] rect;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		rect = new int [N][M];
		long max = 0;
		
		for (int i = 0; i < N; i++) {
			char[] num = br.readLine().toCharArray();
			
			// 직사각형내에 한 칸씩 숫자 저장
			for (int j = 0; j < M; j++) {
				rect[i][j] = num[j] - '0';
			}
		}
		
		// 가로 3줄
		for (int i = N-1; i>0; i--) {
			long x = rectsum(i, N, 0, M);
			
			for (int j = 1; j < M; j++) {
				long y = rectsum(0, i, 0, j);
				long z = rectsum(0, i, j, M);
				
				max = Math.max(max, x*y*z);
			}
		}
		
		// 세로 3줄
		for (int i = M-1; i > 0; i--) {
			long x = rectsum(0, N, i, M);
			
			for (int j = 1; j < N; j++) {
				long y = rectsum(0, j, 0, i);
				long z = rectsum(j, N, 0, i);
				
				max = Math.max(max, x*y*z);
			}
		}
		
		// 세로 1줄, 가로2줄 / 가로2줄, 세로1줄
		for(int i = 1; i < M; i++) {
            long x = rectsum(0, N, 0, i);
            
            for(int j = 1; j < N; j++) {
                long y = rectsum(0, j, i, M);
                long z = rectsum(j, N, i, M);

                max = Math.max(max, x*y*z);
            }
            
            for(int j = i + 1; j < M; j++) {
                long y = rectsum(0, N, i, j);
                long z = rectsum(0, N, j, M);
                
                max = Math.max(max, x*y*z);
            }    
        }
		
		// 가로 1줄, 세로2줄 / 세로2줄, 가로1줄
		for(int i = 1; i < N; i++) {
            long x = rectsum(0, i, 0, M);
            
            for(int j = 1; j < M; j++) {
                long y = rectsum(i, N, 0, j);
                long z = rectsum(i, N, j, M);
                
                max = Math.max(max, x*y*z);
            }
            
            for(int j = i + 1; j < N; j++) {
                long y = rectsum(i, j, 0, M);
                long z = rectsum(j, N, 0, M);
                
                max = Math.max(max, x*y*z);
            }    
        }
		
		System.out.println(max);

	}

	private static long rectsum(int a, int b, int c, int d) {
		long sum = 0;
		
		for (int i = a; i < b; i++) {
			for (int j = c; j < d; j++) {
				sum += rect[i][j];
			}
		}
		
		return sum;
	}

}
