import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 다리만들기2_17472 {
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static boolean[][] adj;
	static int[][] pos = {{-1,0},{1,0},{0,-1},{0,1}};
	static int island = 0;
	static class Edge{
		int start;
		int end;
		int cost;
		public Edge(int start, int end, int cost) {
			super();
			this.start = start;
			this.end = end;
			this.cost = cost;
		}
		@Override
		public String toString() {
			return "Edge [start=" + start + ", end=" + end + ", cost=" + cost + "]";
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
	static List<Edge> list = new ArrayList<Edge>();
	static Queue<Location> queue = new LinkedList<Location>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(!visited[i][j] && map[i][j] == 1) {
					island++;
					visited[i][j] = true;
					map[i][j] = island;
					queue.add(new Location(i, j));
					while (!queue.isEmpty()) {
						Location loc = queue.poll();
						
						for (int k = 0; k < pos.length; k++) {
							int nr = loc.row + pos[k][0];
							int nc = loc.col + pos[k][1];
							
							if(posCheck(nr, nc) && !visited[nr][nc] && map[nr][nc] == 1) {
								visited[nr][nc] = true;
								map[nr][nc] = island;
								queue.add(new Location(nr, nc));
							}
						}
					}
				}
			}
		}
		
		adj = new boolean[island+1][island+1];
		visited = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(!visited[i][j] && map[i][j] != 0) {
					visited[i][j] = true;
					queue.add(new Location(i, j));
					while (!queue.isEmpty()) {
						Location loc = queue.poll();
						
						for (int k = 0; k < pos.length; k++) {
							int nr = loc.row + pos[k][0];
							int nc = loc.col + pos[k][1];
							
							if(posCheck(nr, nc) && !visited[nr][nc]) {
								if(map[nr][nc] == 0) {
									int nnr = nr;
									int nnc = nc;
									int cost = 1;
									while (true) {
										nnr += pos[k][0];
										nnc += pos[k][1];
										cost++;
										
										if(posCheck(nnr, nnc)) {
											if(map[nnr][nnc] != 0) {
												if(cost > 2) {
													list.add(new Edge(map[i][j], map[nnr][nnc], cost-1));
												}
												break;
											}
										}else {
											break;
										}
									}
								}else if(map[nr][nc] == 1) {
									visited[nr][nc] = true;
									queue.add(new Location(nr, nc));
								}
							}
						}
					}
				}
			}
		}
		
		int ans = 0;
		int select = 0;
		boolean[] checked = new boolean[island+1];
		checked[1] = true;
		
		for (int i = 0; i < island-1; i++) {
			int dest = 0;
			int min = Integer.MAX_VALUE;
			for (int k = 0; k < list.size(); k++) {
				Edge edge = list.get(k);
				if(checked[edge.start] && !checked[edge.end]) {
					if(min > edge.cost) {
						dest = edge.end;
						min = edge.cost;
					}
				}
			}
			
			if(dest != 0) {
				ans += min;
				select++;
				checked[dest] = true;
			}
		}
		
		if(select < island-1) {
			ans = -1;
		}
		System.out.println(ans);
	}
	
	static boolean posCheck(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < M;
	}
}
