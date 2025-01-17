package yeonjy.boj;

import java.util.Scanner;

public class BOJ_1541 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        String[] arr = input.split("-");
        int total = 0;

        String[] first = arr[0].split("\\+");
        for (String num : first) {
            total += Integer.parseInt(num);
        }

        for (int i = 1; i < arr.length; i++) {
            String[] nums = arr[i].split("\\+");
            for (String num : nums) {
                total -= Integer.parseInt(num);
            }
        }

        System.out.println(total);
    }
}
