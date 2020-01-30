package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 나무재테크_16235 {
	static class Ground {
		PriorityQueue<Tree> treeQueue;
		Queue<Tree> tmpQ;
		int energy;
		int addEnergy;
		
		public Ground(int energy, int addEnergy) {
			super();
			this.energy = energy;
			this.addEnergy = addEnergy;
			treeQueue = new PriorityQueue<Tree>();
			tmpQ = new LinkedList<Tree>();
		}
	}
	
	static class Tree implements Comparable<Tree> {
		int r;
		int c;
		int age;
		
		public Tree(int r, int c, int age) {
			super();
			this.r = r;
			this.c = c;
			this.age = age;
		}
		
		@Override
		public int compareTo(Tree o) {
			return this.age - o.age;
		}
	}
	
	//땅의 크기
	static int N;
	//나무개수
	static int M;
	//년수
	static int K;
	static Ground[][] map;
	static int[][] deadMap;
	static int[][] pos = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new Ground[N+1][N+1];
		deadMap = new int[N+1][N+1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = new Ground(5, Integer.parseInt(st.nextToken()));
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			Tree tree = new Tree(r, c, age);
			map[r][c].treeQueue.add(tree);
		}
		
		for (int tc = 0; tc < K; tc++) {
			//봄
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					while (!map[i][j].treeQueue.isEmpty()) {
						Tree tree = map[i][j].treeQueue.poll();
						if(map[i][j].energy >= tree.age) {
							map[i][j].energy -= tree.age;
							tree.age++;
							map[i][j].tmpQ.add(tree);
							if(tree.age % 5 == 0) {
								for (int k = 0; k < pos.length; k++) {
									int nr = tree.r + pos[k][0];
									int nc = tree.c + pos[k][1];
									
									if(posCheck(nr, nc)) {
										map[nr][nc].tmpQ.add(new Tree(nr, nc, 1));
									}
								}
							}
						}else {
							deadMap[i][j] += (int) (tree.age / 2);
						}
					}
					while (!map[i][j].tmpQ.isEmpty()) {
						
					}
					map[i][j].treeQueue.addAll(tmpQ);
					tmpQ.clear();
				}
			}
			
			//여름
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					map[i][j].energy += deadMap[i][j];
				}
			}
			
			//가을
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					while (!map[i][j].treeQueue.isEmpty()) {
						Tree tree = map[i][j].treeQueue.poll();
						if(tree.age % 5 == 0) {
							for (int k = 0; k < pos.length; k++) {
								int nr = tree.r + pos[k][0];
								int nc = tree.c + pos[k][1];
								
								if(posCheck(nr, nc)) {
									map[nr][nc].treeQueue.add(new Tree(nr, nc, 1));
								}
							}
						}
						tmpQ.add(tree);
					}
					map[i][j].treeQueue.addAll(tmpQ);
					tmpQ.clear();
				}
			}
			
			//겨울
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					map[i][j].energy += map[i][j].addEnergy;
				}
			}
		}
		
		int ans = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				ans += map[i][j].treeQueue.size();
			}
		}
		
		System.out.println(ans);
	}
	
	static boolean posCheck(int row, int col) {
		return row >= 1 && row <= N && col >= 1 && col <= N;
	}
}
