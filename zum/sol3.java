package zum_codility;

public class sol3 {
	public static void main(String[] args) {
		Solution s = new Solution();

		int[] a = { 1,3,1,2 };

		int ans = s.solution(a);
		System.out.println(ans);
	}

	static class Solution {
		static final String BIG = "big";
		static final String SMALL = "small";
		int check = 0;
		int ans = 0;
		
		public int solution(int[] A) {
			String compare = "";
			for (int i = 1; i < A.length; i++) {
				if(compare.equals("")) {
					compare = autoCompare(A[i-1], A[i]);
				}else {
					String current = autoCompare(A[i-1], A[i]);
					
					if(compare.equals(current)) {
						check = -1;
						break;
					}else {
						compare = current;
					}
				}
			}
			
			if(check == -1) {
				for (int i = 0; i < A.length; i++) {
					compare = "";
					int prev = 0;
					boolean success = true;
					for (int j = 0; j < A.length; j++) {
						if(j == i) continue;
						if(prev == 0) {
							prev = A[j];
							continue;
						}
						if(compare.equals("")) {
							compare = autoCompare(prev, A[j]);
							prev = A[j];
						}else {
							String current = autoCompare(prev, A[j]);
							
							if(compare.equals(current)) {
								success = false;
								break;
							}else {
								compare = current;
							}
						}
					}
					if(success) ans++;
				}
			}
			
			if(check == -1 && ans == 0) ans = -1;

			return ans;
		}
		
		public String autoCompare(int a, int b) {
			String compare = "";
			if(a - b > 0) {
				compare = BIG;
			}else {
				compare = SMALL;
			}
			
			return compare;
		}
	}
}
