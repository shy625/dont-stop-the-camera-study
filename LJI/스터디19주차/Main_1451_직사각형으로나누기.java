import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1451_직사각형으로나누기 {

	static int N,M;
	static int map[][];//값 기록
	static int square[][];//사각형 각각 저장
	//작은 직사각형 세개의 가로 세로 길이 저장할 배열
	static int sR[]=new int[3];
	static int sC[]=new int[3];
	
	//최댓값 저장
	static int max;
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		max=0;
		//맵 기록
		map=new int[N][M];
		square=new int[N][M];
		for (int i = 0; i < N; i++) {
			char ch[]=br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				map[i][j]=ch[j]-'0';
			}
		}
		
		//작은 사각형 3개의 길이 정하기
		int bigArea=N*M;//최대 넓이
		for (int s1R = 1; s1R <= N; s1R++) {
			for (int s1C = 1; s1C <= M; s1C++) {
				sR[0]=s1R;
				sC[0]=s1C;
				int area1=s1R*s1C;
				for (int s2R = 1; s2R <= N; s2R++) {
					for (int s2C = 1; s2C <= M; s2C++) {
						sR[1]=s2R;
						sC[1]=s2C;
						int area2=s2R*s2C;
						//넓이가 전체 넓이보다 커지면 어차피 불가능하다
						if(area1+area2>bigArea) break;
						for (int s3R = 1; s3R <= N; s3R++) {
							for (int s3C = 1; s3C <= M; s3C++) {
								sR[2]=s3R;
								sC[2]=s3C;
								
								int area3=s3R*s3C;
								if(area1+area2+area3>bigArea)break;
								
								int totalR=0;
								int totalC=0;
								
								for (int i = 0; i < 3; i++) {
									totalR+=sR[i];
									totalC+=sC[i];
								}
								
								//작은 사각형의 가로 세로 각각의 총합이 큰 사각형의 가로 세로를 각각 넘지 못하면 차피 모든 칸을 채울 수 없다
								if(totalR<N || totalC<M) continue;
								
								setSquare(0,0);
								
							}
						}
					}
				}
			}
		}
		
		System.out.println(max);
	}
	
	//스퀘어에 작은 사각형들 배치해보는 함수
	private static void setSquare(int start,int cnt) {
		if(cnt==3) {//사각형이 배치된 후
			//square의 배치 안된 0이 있는지 검사하면서
			//각 스퀘어의 합 구하기
			int sum[]=new int [4];//1~3만 사용
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(square[i][j]==0) return;//빈칸 있으면 더 할 필요 없다
					
					sum[square[i][j]]+=map[i][j];
				}
			}
			//합의 곱 구하기
			int total=sum[1]*sum[2]*sum[3];
			//max값과 비교
			max=Math.max(max, total);
			return;
		}
		
		//이번 사각형의 가로 세로
		int height=sR[cnt];
		int width=sC[cnt];
		
		//i,j 의 위치부터 사각형을 오른쪽 아래 방향으로 배치 시도
		for (int i = 0; i < N; i++) {
			//이 보다 뒤는 더 이상 배치 불가->할 필요 없다
			if(i+height>N) break;
			for (int j = 0; j < M; j++) {
				if(j+width>M) break;
				
				int startSet=i*M+j;
				if(start>startSet) continue;
				
				//square에 0으로 되어있어야만 배치 가능
				boolean canSquare=true;
				for (int r = i; r < height+i; r++) {
					for (int c = j; c < width+j; c++) {
						if(square[r][c]!=0) {
							canSquare=false;
							break;
						}
					}
					if(!canSquare) break;
				}
				
				if(!canSquare)continue;
				
				//사각형 배치
				for (int r = i; r < height+i; r++) {
					for (int c = j; c < width+j; c++) {
						square[r][c]=cnt+1;
					}
				}
				//다음 사각형 진행
				int temp=i*M+j;
				setSquare(temp,cnt+1);
				
				//사각형 초기화
				for (int r = i; r < height+i; r++) {
					for (int c = j; c < width+j; c++) {
						square[r][c]=0;
					}
				}
			}
		}
	}

}
