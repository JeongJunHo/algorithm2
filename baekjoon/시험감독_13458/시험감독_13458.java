import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 시험감독_13458 {
	static int N;
	static int[] arr;
	static int B, C;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		long ans = 0;
		for (int i = 0; i < N; i++) {
			arr[i] -= B;
			ans++;
			if(arr[i] > 0) {
				int result = (int)(arr[i] / C);
				int remain = arr[i] % C;
				if(remain > 0) result++;
				ans += result;
			}
		}
		
		System.out.println(ans);
	}
}
