package zum_codility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class sol2 {
	public static void main(String[] args) {
		Solution s = new Solution();

		int[] a = {51,71,17,42};
		

		int ans = s.solution(a);
		System.out.println(ans);
	}

	static class Solution {
		List<Integer>[] listArr;
		int max = -1;
		public int solution(int[] A) {
			listArr = new List[82];
			
			for (int i = 0; i < listArr.length; i++) {
				listArr[i] = new ArrayList<Integer>();
			}
			
			for (int i : A) {
				int sumNumber = numberChangeBySumNumber(i);
				listArr[sumNumber].add(i);
			}
			
			for (List<Integer> list : listArr) {
				if(list.size() < 2) continue;
				
				Collections.sort(list, new Comparator<Integer>() {

					@Override
					public int compare(Integer o1, Integer o2) {
						return o2 - o1;
					}
				});
				
				int sum = list.get(0) + list.get(1);
				max = Math.max(sum, max);
			}

			return max;
		}
		
		public int numberChangeBySumNumber(int num) {
			String str = String.valueOf(num);
			int sum = 0;
			for (int i = 0; i < str.length(); i++) {
				sum += Character.getNumericValue(str.charAt(i));
			}
			
			return sum;
		}
	}
}
