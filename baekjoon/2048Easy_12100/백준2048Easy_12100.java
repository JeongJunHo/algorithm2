import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class 백준2048Easy_12100 {
	static int N;
	static int[][] arr;
	static int[][] pos = {{-1,0},{1,0},{0,-1},{0,1}};
	static int ans;
	static class Block{
		int num;
		boolean isSum;
		public Block(int num) {
			super();
			this.num = num;
		}
	}
	static Deque<Block> dq = new ArrayDeque<Block>();
	static class Tree {
		int num;
		int test;
		public Tree(int num, int test) {
			super();
			this.num = num;
			this.test = test;
		}
		
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		perm(0, new int[5]);
		
		System.out.println(ans);
	}
	private static void perm(int idx, int[] sel) {
		if(idx == 5) {
			solve(sel, deapCopy());
			
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			sel[idx] = i;
			perm(idx+1, sel);
		}
	}
	
	private static void solve(int[] sel, int[][] copyArr) {
		for (int i = 0; i < sel.length; i++) {
			if(sel[i] == 0) {
				for (int col = 0; col < N; col++) {
					for (int row = 0; row < N; row++) {
						dequeAdd(row,col, copyArr);
					}
					
					for (int row = 0; row < N; row++) {
						dequePoll(row,col,copyArr);
					}
				}
			}else if (sel[i] == 1) {
				for (int col = N-1; col >= 0; col--) {
					for (int row = N-1; row >= 0; row--) {
						dequeAdd(row,col, copyArr);
					}
					
					for (int row = N-1; row >= 0; row--) {
						dequePoll(row,col,copyArr);
					}
				}
			}else if (sel[i] == 2) {
				for (int row = 0; row < N; row++) {
					for (int col = 0; col < N; col++) {
						dequeAdd(row,col, copyArr);
					}
					
					for (int col = 0; col < N; col++) {
						dequePoll(row,col,copyArr);
					}
				}
			}else if (sel[i] == 3) {
				for (int row = N-1; row >= 0; row--) {
					for (int col = N-1; col >= 0; col--) {
						dequeAdd(row,col, copyArr);
					}
					
					for (int col = N-1; col >= 0; col--) {
						dequePoll(row,col,copyArr);
					}
				}
			}
		}
		
		int max = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				max = Math.max(max, copyArr[i][j]);
			}
		}
		
		ans = Math.max(max, ans);
	}
	
	private static void dequePoll(int row, int col, int[][] copyArr) {
		Block block = dq.poll();
		if(block == null) {
			copyArr[row][col] = 0;
		}else {
			copyArr[row][col] = block.num;
		}
	}
	private static void dequeAdd(int row, int col, int[][] copyArr) {
		Block peekLast = dq.peekLast();
		if(peekLast != null && !peekLast.isSum && peekLast.num == copyArr[row][col]) {
			peekLast.num *= 2;
			peekLast.isSum = true;
		}else {
			if(copyArr[row][col] > 0) {
				dq.add(new Block(copyArr[row][col]));
			}
		}
	}
	
	static int[][] deapCopy(){
		int[][] copyArr = new int[N][N];
		for (int i = 0; i < copyArr.length; i++) {
			copyArr[i] = arr[i].clone();
		}
		
		return copyArr;
	}
}
