package yeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_22860 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());  // 폴더 수
        int M = Integer.parseInt(st.nextToken());  // 파일 수

        HashMap<String, Folder> folderMap = new HashMap<>();

        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());

            String parent = st.nextToken();
            String child = st.nextToken();
            String isFolder = st.nextToken();

            if (folderMap.get(parent) == null) {
                folderMap.put(parent, new Folder());
            }

            Folder parentFolder = folderMap.get(parent);

            if (isFolder.equals("1")) {
                Folder folder = folderMap.getOrDefault(child, new Folder());

                parentFolder.addFolder(child, folder);
                folderMap.put(child, folder);
            } else {
                parentFolder.addFile(child);
            }
        }

        int Q = Integer.parseInt(br.readLine());
        for (int i = 0; i < Q; i++) {
            String query = br.readLine();

            int index = query.lastIndexOf("/");
            String s = query.substring(index + 1);

            Folder folder = folderMap.get(s);

            FileInfo info = folder.getFileInfo();

            sb.append(info.files.size() + " " + info.fileCnt + "\n");
        }
        System.out.println(sb);

    }

    static class Folder {
        HashSet<String> files;
        HashMap<String, Folder> folders;
        FileInfo fileInfo;

        public Folder() {
            folders = new HashMap<>();
            files = new HashSet<>();
        }

        public void addFolder(String name, Folder folder) {
            folders.put(name, folder);
        }

        public void addFile(String file) {
            files.add(file);
        }

        public FileInfo getFileInfo() {
            if (fileInfo != null) {
                return fileInfo;
            }
            HashSet<String> currentFiles = new HashSet<>(files);
            int fileCnt = files.size();

            for (Folder folder : folders.values()) {
                FileInfo info = folder.getFileInfo();
                currentFiles.addAll(info.files);
                fileCnt += info.fileCnt;
            }

            return fileInfo = new FileInfo(currentFiles, fileCnt);
        }
    }

    static class FileInfo {
        HashSet<String> files;
        int fileCnt;

        public FileInfo(HashSet<String> files, int fileCnt) {
            this.files = files;
            this.fileCnt = fileCnt;
        }
    }

}
