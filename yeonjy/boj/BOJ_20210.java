package yeonjy.boj;

import java.io.*;
import java.util.*;

public class BOJ_20210 {
	static int N;
	static String[] str;
	static ArrayList<ArrayList<String>> al = new ArrayList<ArrayList<String>>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb;
		N = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < N; i++) {
			al.add(new ArrayList<>());
		}
		
		for(int i = 0; i < N; i++) {
			String s = br.readLine();
			
			for(int j = 0; j < s.length(); j++) {
				sb = new StringBuilder();
				if('0' <= s.charAt(j) && s.charAt(j)<= '9') {
					while(j < s.length() && '0' <= s.charAt(j) && s.charAt(j)<= '9') {
						sb.append(s.charAt(j++));
					}
					j--;
				}else {
					sb.append(s.charAt(j));
				}
				al.get(i).add(sb.toString());
			}
		}
		
		
		Collections.sort(al, new Comparator<ArrayList<String>>() {

			@Override
			public int compare(ArrayList<String> o1, ArrayList<String> o2) {
				int len1 = o1.size();
				int len2 = o2.size();
				int i = 0, j = 0;
				for(; i < len1 && j < len2; i++, j++) {
					if(o1.get(i).equals(o2.get(j)))
						continue;
					
					boolean numeric1 = isNum(o1.get(i));
					boolean numeric2 = isNum(o2.get(j));
					
					if(numeric1 && numeric2) {
						String s1 = o1.get(i).replaceAll("^0+","");
						String s2 = o2.get(j).replaceAll("^0+","");
						
						if(s1.length() > s2.length())
							return 1;
						if(s2.length() > s1.length())
							return -1;
						
						for(int a = 0, b = 0; a<s1.length() && b < s2.length(); a++, b++) {
							if(s1.charAt(a) > s2.charAt(b))
								return 1;
							else if (s1.charAt(a) < s2.charAt(b))
								return -1;
						}
						
						return o1.get(i).length() - o2.get(j).length();
					}
					if(!numeric1 && !numeric2) {
						char c1 = o1.get(i).charAt(0);
						char c2 = o2.get(j).charAt(0);

						boolean isUpper1 = c1 - 'a' < 0 ? true : false;
						boolean isUpper2 = c2 - 'a' < 0 ? true : false;
						
						int n1 = c1 - 'a' >= 0  ? c1 - 'a' : c1 - 'A';
						int n2 = c2 - 'a' >= 0 ? c2 - 'a' : c2 - 'A';

						if((isUpper1 && isUpper2) || (!isUpper1 && !isUpper2)) {
							return n1 - n2;
						}
						if(!isUpper1 && isUpper2) {
							if(n1 == n2)
								return 1;
							
							return n1 - n2;
						}
						if(isUpper1 && !isUpper2) {
							if(n1 == n2)
								return -1;
							
							return n1 - n2;
						}
					}
					if(!numeric1 && numeric2) {
						return 1;
					}
					if(numeric1 && !numeric2) {
						return -1;
					}
				}
				
				if(len1 != i) {
					return 1;
				}
				if(len2 != j) {
					return -1;
				}
				return 0;
			}
		});
		
		sb = new StringBuilder();
		for(int i = 0; i < al.size(); i++) {
			StringBuilder sb2 = new StringBuilder();
			for(String s : al.get(i)) {
				sb2.append(s);
			}
			sb.append(sb2.toString()).append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
	}
	static boolean isNum(String s) {
		if('0' <= s.charAt(0) && s.charAt(0) <= '9')
			return true;
		return false;
	}
}
