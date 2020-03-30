package zum_codility;

import java.util.HashMap;

public class sol4 {
	public static void main(String[] args) {
		Solution s = new Solution();

		String a = "John Doe; Peter Benjamin Parker; Mary Jane Watson-Parker; John Elvis Doe; John Evan Doe; Jane Doe; Peter Brian Parker ";

		String ans = s.solution(a, "Example");
		System.out.println(ans);
	}
	static class Solution {
		public String solution(String S, String C) {
			StringBuilder sb = new StringBuilder();
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			String[] split = S.split(";");
			for (String name : split) {
				name = name.trim();
				String modifyName = name.replace("-", "").toLowerCase().trim();
				String[] nameSplit = modifyName.split(" ");
				String emailId = nameSplit[nameSplit.length-1] + "_" + nameSplit[0];
				if(map.get(emailId) == null) {
					map.put(emailId, 1);
				}else {
					map.put(emailId, map.get(emailId)+1);
				}

				int cnt = map.get(emailId);
				if(cnt > 1) emailId = emailId + cnt;
				
				sb.append(name + " <" + emailId + "@" + C.toLowerCase() + ".com" + ">; ");
			}
	
			return sb.toString().substring(0, sb.length()-2);
		}
	}
}
