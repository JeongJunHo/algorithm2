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
		int energy;
		int addEnergy;
		
		public Ground(int energy, int addEnergy) {
			super();
			this.energy = energy;
			this.addEnergy = addEnergy;
			treeQueue = new PriorityQueue<Tree>();
		}
	}
	
	static class Tree implements Comparable<Tree> {
		int r;
		int c;
		int age;
		boolean isDead;
		
		public Tree(int r, int c, int age) {
			super();
			this.r = r;
			this.c = c;
			this.age = age;
			this.isDead = false;
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
	static Queue<Tree> tmpQ = new LinkedList<Tree>();
	static Queue<Tree> deadQ = new LinkedList<Tree>();
	static int[][] pos = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new Ground[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
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
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					while (!map[i][j].treeQueue.isEmpty()) {
						Tree tree = map[i][j].treeQueue.poll();
						if(map[i][j].energy >= tree.age) {
							map[i][j].energy -= tree.age;
							tree.age++;
							tmpQ.add(tree);
						}else {
							tree.isDead = true;
							deadQ.add(tree);
						}
						
					}
					map[i][j].treeQueue.addAll(tmpQ);
				}
			}
			
			//여름
			while (!deadQ.isEmpty()) {
				Tree tree = map[i][j].treeQueue
			}
		}
	}
}
