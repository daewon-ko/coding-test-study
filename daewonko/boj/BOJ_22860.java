package daewonko.boj;

import java.util.*;
import java.io.*;

public class BOJ_22860 {


    static int N;
    static int M;
    static int diff;
    static int many;
    static List<Folder> folder;
    static Map<String, Integer> indexFolder;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = br.readLine().split(" ");


        folder = new ArrayList<>();
        indexFolder = new HashMap<>();

        N = Integer.parseInt(strs[0]);
        M = Integer.parseInt(strs[1]);

        Folder root = new Folder("main");
        folder.add(root);
        indexFolder.put("main", 0);
        for (int i = 0; i < N + M; i++) {
            strs = br.readLine().split(" ");
            String P = strs[0];
            String F = strs[1];
            int C = Integer.parseInt(strs[2]);
            if (C == 1) {
                Folder now = null;
                for (int j = 0; j < folder.size(); j++) {
                    if (folder.get(j).name.equals(F)) {
                        now = folder.get(j);
                        break;
                    }
                }
                if (now == null) {
                    now = new Folder(F);
                    folder.add(now);
                    indexFolder.put(F, folder.size() - 1); // 텅 빈 폴더를 만들고
                }

                Folder parent = null;

                if (indexFolder.get(P) == null) {
                    parent = new Folder(P);
                    folder.add(parent);
                    indexFolder.put(P, folder.size() - 1); // 텅 빈 폴더를 만들고
                } else {
                    int index = indexFolder.get(P);
                    parent = folder.get(index);
                }

                //이름으로 -> 인덱스를 찾아서 -> 부모 폴더를 찾아서 -> child에 추가
                parent.child.add(now);

            } else if (C == 0) {
                Folder parent = null;

                if (indexFolder.get(P) == null) {
                    parent = new Folder(P);
                    folder.add(parent);
                    indexFolder.put(P, folder.size() - 1); // 텅 빈 폴더를 만들고
                } else {
                    int index = indexFolder.get(P);
                    parent = folder.get(index);
                }
                // 이름으로 -> 인덱스를 찾아서 -> 부모 폴더를 찾아서 ->file에 추가
                parent.file.add(F);

            }
        }


        int Q = Integer.parseInt(br.readLine());

        for (int i = 0; i < Q; i++) {
            many = 0;
            HashSet<String> diff = new HashSet<>();
            strs = br.readLine().split("/");
            int index = indexFolder.getOrDefault(strs[strs.length - 1], -1);
            if (index == -1) {
                continue;
            } else {
                Folder find = folder.get(index);
                findFolder(find, diff);
                System.out.println(diff.size() + " " + many);
            }

        }

    }

    public static void findFolder(Folder find, HashSet<String> diff) {
        many += find.file.size();
        for (String s : find.file) {
            diff.add(s);
        }
        if (find.child.size() != 0) {

            for (Folder f : find.child)
                findFolder(f, diff);
        }
    }

    static class Folder {
        String name;
        List<Folder> child = new ArrayList<>();
        List<String> file = new ArrayList<>();


        Folder(String name) {
            this.name = name;
        }
    }
}

