package yeonjy.pgs;

import java.util.*;

class PGS_150367 {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            answer[i] = getAnswer(numbers[i]);
        }
        return answer;
    }
    
    static int getAnswer(long number) {
        // 1) 십진수 -> 이진수 변환
        String binary = Long.toBinaryString(number);
        
        // 2) 이진수에 더미 노드 채우기
        double size = getSize(binary.length());
        StringBuilder add = new StringBuilder();
        for (int i = binary.length(); i < size; i++) {
            add.append("0");
        }
        binary = add.toString() + binary;
        
        // 3) 만든 트리가 올바른 트리인지 확인하기
        if (isBinaryTree(binary)) {
            return 1;
        }
        return 0;
    }
    
    static int getSize(int length) {
        int h = 1;
        while((int) Math.pow(2, h) - 1 < length) {
            h++;
        }
        return (int) Math.pow(2, h) - 1;
    }
    
    static boolean isBinaryTree(String tree) {
        int len = tree.length();
        if (tree.length() == 0) {
            return true;
        }
        
        int root = len / 2;
        String leftSubTree = tree.substring(0, root);
        String rightSubTree = tree.substring(root + 1);
        if (tree.charAt(root) == '0') {
            return isZeroTree(tree.substring(0, root)) && isZeroTree(tree.substring(root + 1));
        }
        return isBinaryTree(leftSubTree) && isBinaryTree(rightSubTree);
    }
                                                                    
    static boolean isZeroTree(String tree) {
        int len = tree.length();
        if (tree.length() == 0) return true;

        int root = len / 2;
        String leftSubTree = tree.substring(0, root);
        String rightSubTree = tree.substring(root + 1);

        if (tree.charAt(root) == '1') {
            return false;
        }

        return isZeroTree(leftSubTree) && isZeroTree(rightSubTree);
    }
}
