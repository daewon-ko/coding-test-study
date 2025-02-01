package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

/*
백준 / 폴더 정리 (small) / 골드3
https://www.acmicpc.net/problem/22860
 */
public class BOJ_22860 {

    static Map<String, List<String>> folderMap = new HashMap<>();
    static Map<String, List<String>> fileMap = new HashMap<>();
    static Set<String> fileSet;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());  //main 폴더 하위의 폴더의 개수
        int M = Integer.parseInt(st.nextToken());  //파일의 개수

        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());
            String P = st.nextToken();  //상위 폴더의 이름
            String F = st.nextToken();  //폴더 or 파일의 이름
            int C = Integer.parseInt(st.nextToken());  //1 -> 폴더, 0 -> 파일

            folderMap.putIfAbsent(P, new ArrayList<>());
            fileMap.putIfAbsent(P, new ArrayList<>());
            if (C == 1) {  //폴더라면
                folderMap.get(P).add(F);
                folderMap.putIfAbsent(F, new ArrayList<>());
                fileMap.putIfAbsent(F, new ArrayList<>());
            } else {
                fileMap.get(P).add(F);
            }
        }

        int Q = Integer.parseInt(br.readLine());  //쿼리의 개수
        for (int i = 0; i < Q; i++) {
            String[] split = br.readLine().split("/");
            String leafFolder = split[split.length - 1];

            count = 0;
            fileSet = new HashSet<>();

            for (String file : fileMap.get(leafFolder)) {
                fileSet.add(file);
                count++;
            }

            dfs(leafFolder);
            System.out.println(fileSet.size() + " " + count);
        }
    }

    static void dfs(String folder) {
        if (folderMap.get(folder).isEmpty()) {  //하위 폴더가 없을 경우
            return;
        }

        for (String subFolder : folderMap.get(folder)) {
            if (!folderMap.get(subFolder).isEmpty()) {  //하위 폴더가 하위 폴더가 존재할 경우
                dfs(subFolder);
            }

            for (String file : fileMap.get(subFolder)) {
                fileSet.add(file);
                count++;
            }
        }
    }
}
