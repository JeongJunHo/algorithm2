import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 지희의고장난계산기_1808{
	private static int min;
	private static boolean[] btn;
	private static int X;
	private static int size;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			min = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine().trim(), " ");
			btn = new boolean[10];
			int num;
			for (int i = 0; i < 10; i++) {
				num = Integer.parseInt(st.nextToken());
				if(num == 1) {
					btn[i] = true;
				}
			}
			X = Integer.parseInt(br.readLine().trim());
			size = (int)Math.sqrt(X);
			  
		}
	}
}