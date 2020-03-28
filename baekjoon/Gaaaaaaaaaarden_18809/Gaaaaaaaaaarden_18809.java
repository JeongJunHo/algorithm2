import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

class Main{
	static int N, M, G, R, ans;
	static int[][] map;
	static List<Location> goldGround;
	static CultureLiquid[][] timeMap;
	static LinkedList<CulLiLocation> q;
	static int[][] pos = {{-1,0},{1,0},{0,-1},{0,1}};
	
	static class Location{
		int row;
		int col;
		
		public Location(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}

		@Override
		public String toString() {
			return "Location [row=" + row + ", col=" + col + "]";
		}
	}
	
	static class CulLiLocation extends Location{
		char color;
		
		public CulLiLocation(int row, int col, char color) {
			super(row, col);
			this.color = color; 
		}

		@Override
		public String toString() {
			return "CulLiLocation [color=" + color + ", row=" + row + ", col=" + col + "]";
		}
	}
	
	static class CultureLiquid{
		int time;
		char color;
		boolean flower;
		public CultureLiquid(int time, char color, boolean flower) {
			super();
			this.time = time;
			this.color = color;
			this.flower = flower;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		goldGround = new ArrayList<Location>();
		q = new LinkedList<CulLiLocation>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) {
					goldGround.add(new Location(i, j));
				}
			}
		}
		
		//배양액 뿌릴 자리를 선정한다.
		comb(0, 0, new int[G+R]);
		
		System.out.println(ans);
	}
	private static void comb(int idx, int s_idx, int[] sel) {
		if(s_idx == sel.length) {
			//어떤 배양액을 뿌릴지 정한다.
			perm(sel, 0, new char[G+R], 0, 0);
			
			return;
		}

		if(idx == goldGround.size()) {
			return;
		}
		
		sel[s_idx] = idx;
		comb(idx+1, s_idx+1, sel);
		sel[s_idx] = idx;
		comb(idx+1, s_idx, sel);
	}
	private static void perm(int[] groundIdx, int idx, char[] sel, int green, int red) {
		if(idx == groundIdx.length) {
			//어디에 무엇을 뿌릴지 까지 골랐다.
			//이제 뿌린다.
			go(groundIdx, sel);
			
			return;
		}
		if(green < G) {
			sel[idx] = 'G';
			perm(groundIdx, idx+1, sel, green+1, red);
		}
		if(red < R) {
			sel[idx] = 'R';
			perm(groundIdx, idx+1, sel, green, red+1);
		}
	}
	private static void go(int[] groundIdx, char[] sel) {
		q.clear();
		int time = 0;
		int tmpAns = 0;
		timeMap = new CultureLiquid[N][M];
		
		for (int i = 0; i < groundIdx.length; i++) {
			Location loc = goldGround.get(groundIdx[i]);
			q.add(new CulLiLocation(loc.row, loc.col, sel[i]));
			timeMap[loc.row][loc.col] = new CultureLiquid(time, sel[i], false);
		}
//		System.out.println("초기값");
//		timeMapPrint();
		
		while (!q.isEmpty()) {
			int qSize = q.size();
			time++;
			for (int qIdx = 0; qIdx < qSize; qIdx++) {
				CulLiLocation loc = q.poll();
				//이미 꽃으로 변했다면
				if(timeMap[loc.row][loc.col].flower) continue;
				
				for (int i = 0; i < pos.length; i++) {
					//타임테이블 체크 후 객체가 없을 경우에는 채우고 있는 경우에는 시간이 같고 색이 다를 때 진행해서 꽃으로 변경, 꽃 카운트
					int nr = loc.row + pos[i][0];
					int nc = loc.col + pos[i][1];
					
					//확인사항
					//범위 초과하지않는지
					//해당 자리가 땅인지
					//처음방문인지(객체가 없는지)
					//객체가 있다면 꽃인지
					//꽃이 아니라면 같은 초이면서 같은 색인지
					if(posCheck(nr, nc) && map[nr][nc] != 0) {
						if(timeMap[nr][nc] == null) {
							timeMap[nr][nc] = new CultureLiquid(time, loc.color, false);
							q.add(new CulLiLocation(nr, nc, loc.color));
						}else {
							if(!timeMap[nr][nc].flower && timeMap[nr][nc].time == time && timeMap[nr][nc].color != loc.color) {
								timeMap[nr][nc].flower = true;
								tmpAns++;
							}
						}
					}
				}
			}
//			timeMapPrint();
		}
		ans = Math.max(ans, tmpAns);
	}
	
	private static void timeMapPrint() {
		for (int j = 0; j < N; j++) {
			for (int j2 = 0; j2 < M; j2++) {
				if(map[j][j2] == 0) {
					System.out.print("W ");
				}else {
					if(timeMap[j][j2] == null) {
						System.out.print("N ");
					}else {
						if(timeMap[j][j2].flower) {
							System.out.print("F ");
						}else {
							if(timeMap[j][j2].color == 'R') {
								System.out.print("R ");
							}else {
								System.out.print("G ");
							}
						}
					}	
				}
			}
			System.out.println();
		}
		System.out.println("=============");
	}
	
	private static boolean posCheck(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < M;
	}
}