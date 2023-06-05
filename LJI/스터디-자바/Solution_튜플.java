
import java.util.PriorityQueue;

public class Solution_튜플 {

	// 원소 한개짜리부터 순서대로 찾기
		class Solution {
			public int[] solution(String s) {
				int[] answer = {};
				String[] arrS = s.substring(1, s.length() - 1).split("\\{",0);
				// 문자열 처리
				PriorityQueue<Set> pq=new PriorityQueue<>();
				int max=0;
				for (int i = 1; i < arrS.length; i++) {
	                //if(arrS[i].length()==0) continue;
					if (i == arrS.length - 1) {
						arrS[i] = arrS[i].substring(0, arrS[i].length() - 1);// } 제거
					} else {
						arrS[i] = arrS[i].substring(0, arrS[i].length() - 2);// }, 제거
					}
					
					String[] temp=arrS[i].split(",",0);
					max=Integer.max(max, temp.length);
					int[] tempInt=new int[temp.length];
					for (int j = 0; j < tempInt.length; j++) {
						tempInt[j]=Integer.parseInt(temp[j]);
					}
					
					pq.offer(new Set(tempInt,temp.length));
				}
				boolean[] v=new boolean[100001];
				answer=new int[max];
				int idx=0;
				while(!pq.isEmpty()) {
	                //if(idx==answer.length) break;
					Set cur=pq.poll();
					for(int now:cur.set) {
						if(v[now]) continue;
						
						v[now]=true;
						answer[idx]=now;
						idx++;
						break;
					}
				}
					
				return answer;
			}

			class Set implements Comparable<Set>{
				int [] set;
				int cnt;
				public Set(int[] set, int cnt) {
					super();
					this.set = set;
					this.cnt = cnt;
				}
				@Override
				public int compareTo(Set o) {//원소 갯수로 비교 
					return Integer.compare(this.cnt, o.cnt);
				}

			}
		}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
