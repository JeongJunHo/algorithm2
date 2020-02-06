import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 주사위윷놀이_17825 {
	static class Place{
		int score;
		int startNext;
		int continueNext;
		boolean standed;
		public Place(int score, int startNext, int continueNext) {
			super();
			this.score = score;
			this.startNext = startNext;
			this.continueNext = continueNext;
		}
	}
	
	static class Horse{
		int idx;
		boolean goal;
		public Horse(int idx) {
			super();
			this.idx = idx;
		}
	}
	static Place[] board = new Place[33];
	static int[] arr = new int[10];
	static int ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		init();
		
		for (int i = 0; i < 10; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		comb_re(new int[10], 0, 0);
		
		System.out.println(ans);
	}

	private static void comb_re(int[] sel, int idx, int s_idx) {
		if(sel.length == s_idx) {
			int tmpAns = 0;
			for (int i = 0; i < board.length; i++) {
				board[i].standed = false;
			}
			Horse[] horseArr = new Horse[4];
			for (int i = 0; i < horseArr.length; i++) {
				horseArr[i] = new Horse(0);
			}
			
			for (int i = 0; i < sel.length; i++) {
				Horse horse = horseArr[sel[i]];
				if(horse.goal) return;
				boolean start = true;
				board[horse.idx].standed = false;
				for (int j = 0; j < arr[i]; j++) {
					int tmpNext = 0;
					if(start) {
						tmpNext = board[horse.idx].startNext;
						start = false;
					}else {
						tmpNext = board[horse.idx].continueNext;
					}
					
					horse.idx = tmpNext;
					if(horse.idx == 32) {
						horse.goal = true;
						break;
					}
				}
				if(horse.idx != 32 && board[horse.idx].standed) {
					return;
				}else {
					board[horse.idx].standed = true;
					tmpAns += board[horse.idx].score;
				}
			}
			
			ans = Math.max(ans, tmpAns);
			
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			sel[s_idx] = i;
			comb_re(sel, i, s_idx+1);
		}
	}

	private static void init() {
		board[0] = new Place(0, 1, 1);
		board[1] = new Place(2, 2, 2);
		board[2] = new Place(4, 3, 3);
		board[3] = new Place(6, 4, 4);
		board[4] = new Place(8, 5, 5);
		board[5] = new Place(10, 21, 6);
		board[6] = new Place(12, 7, 7);
		board[7] = new Place(14, 8, 8);
		board[8] = new Place(16, 9, 9);
		board[9] = new Place(18, 10, 10);
		board[10] = new Place(20, 28, 11);
		board[11] = new Place(22, 12, 12);
		board[12] = new Place(24, 13, 13);
		board[13] = new Place(26, 14, 14);
		board[14] = new Place(28, 15, 15);
		board[15] = new Place(30, 27, 16);
		board[16] = new Place(32, 17, 17);
		board[17] = new Place(34, 18, 18);
		board[18] = new Place(36, 19, 19);
		board[19] = new Place(38, 20, 20);
		board[20] = new Place(40, 32, 32);
		board[21] = new Place(13, 22, 22);
		board[22] = new Place(16, 23, 23);
		board[23] = new Place(19, 24, 24);
		board[24] = new Place(25, 30, 30);
		board[25] = new Place(26, 24, 24);
		board[26] = new Place(27, 25, 25);
		board[27] = new Place(28, 26, 26);
		board[28] = new Place(22, 29, 29);
		board[29] = new Place(24, 24, 24);
		board[30] = new Place(30, 31, 31);
		board[31] = new Place(35, 20, 20);
		board[32] = new Place(0, -1, -1);
	}
}
