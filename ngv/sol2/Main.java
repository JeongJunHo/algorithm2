import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int ans;
	static List<Integer>[] adj;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Queue<Integer> q = new LinkedList<Integer>();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		adj = new List[N+1];
		for (int i = 0; i <= N; i++) {
			adj[i] = new ArrayList<Integer>();
		}
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 1; i <= N; i++) {
			int nextToken = Integer.parseInt(st.nextToken());
			adj[nextToken].add(i);
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int firstNode = Integer.parseInt(st.nextToken());
			int secondNode = Integer.parseInt(st.nextToken());
			
			q.addAll(adj[firstNode]);
			ans += adj[firstNode].size();
			
			while (!q.isEmpty()) {
				Integer child = q.poll();
				if(child == secondNode) continue;
				
				ans += adj[child].size();
				q.addAll(adj[child]);
			}
		}
		
		System.out.println(ans);
	}
}