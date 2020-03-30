package zum_codility;

import java.util.Arrays;

public class sol1 {
	public static void main(String[] args) {
		Solution s = new Solution();

		int[] a = { 3, 8, 2, 3, 3, 2 };

		int ans = s.solution(a);
		System.out.println(ans);
	}

	static class Solution {

		public int solution(int[] A) {
			Arrays.parallelSort(A);

			int target = 0;
			int count = 0;
			for (int i = A.length - 1; i >= 0; i--) {
				if (target == 0) {
					target = A[i];
					count++;
				} else {
					if (target == A[i]) {
						count++;
					} else {
						if (count == target) {
							break;
						}
						target = A[i];
						count = 1;
					}
				}
			}
			
			if(target != count) target = 0;

			return target;
		}
	}
}
