import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class 원자소멸시뮬레이션_5648 {
	static int N;
	static int[][] pos = {{1,0},{-1,0},{0,-1},{0,1}};
	static byte[][] map;
	static int ans;
	static class Atomic{
		int row;
		int col;
		int dir;
		int k;
		public Atomic(int row, int col, int dir, int k) {
			super();
			this.row = row;
			this.col = col;
			this.dir = dir;
			this.k = k;
		}
	}
	static class Location{
		int row;
		int col;
		public Location(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
	}
	static List<Location> removeList;
	static LinkedList<Atomic> list;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			ans = 0;
			map = new byte[4001][4001];
			removeList = new ArrayList<Location>();
			list = new LinkedList<Atomic>();
			N = Integer.parseInt(br.readLine());
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int x = (Integer.parseInt(st.nextToken()) + 1000) * 2;
				int y = (Integer.parseInt(st.nextToken()) + 1000) * 2;
				int dir = Integer.parseInt(st.nextToken());
				int k = Integer.parseInt(st.nextToken());
				
				list.add(new Atomic(y, x, dir, k));
				map[y][x]++;
			}
			
			while (list.size() > 0) {
				
				for (Iterator iterator = list.iterator(); iterator.hasNext();) {
					Atomic atomic = (Atomic) iterator.next();
					map[atomic.row][atomic.col]--;
					atomic.row += pos[atomic.dir][0];
					atomic.col += pos[atomic.dir][1];
					
					if(posCheck(atomic.row, atomic.col)) {
						map[atomic.row][atomic.col]++;
						if(map[atomic.row][atomic.col] == 2) {
							removeList.add(new Location(atomic.row, atomic.col));
						}
					}else {
						iterator.remove();
					}
				}
				
				for (Location loc : removeList) {
					for (Iterator iterator = list.iterator(); iterator.hasNext();) {
						Atomic atomic = (Atomic) iterator.next();
						
						if(loc.row == atomic.row && loc.col == atomic.col) {
							ans += atomic.k;
							map[atomic.row][atomic.col]--;
							iterator.remove();
						}
					}
				}
				
				removeList.clear();
			}
			
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static boolean posCheck(int row, int col) {
		return row >= 0 && row <= 4000 && col >= 0 && col <= 4000;
	}
}
