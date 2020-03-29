import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		long ans = 0;
		
		String str = br.readLine();
		
		for (int i = 0; i < str.length()-K+1; i++) {
			ans = Math.max(Long.parseLong(str.substring(i, i+K)), ans);
		}
		
		System.out.println(ans);
	}
}
