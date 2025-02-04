import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
백준 / 톱니바퀴 / 골드5
https://www.acmicpc.net/problem/14891
 */
public class BOJ_14891 {

    private static int[][] tobni = new int[5][8];
    private static int[] clone;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 1; i <= 4; i++) {
            char[] charArray = br.readLine().toCharArray();
            for (int j = 0; j < 8; j++) {
                tobni[i][j] = charArray[j] - '0';
            }
        }

        int K = Integer.parseInt(br.readLine());  //회전 횟수
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());  //톱니바퀴 번호
            int dir = Integer.parseInt(st.nextToken());  //1 -> 시계, -1 -> 반시계
            boolean[] canTurn = new boolean[5];

            if (dir == 1) {  //시계 방향 회전

                if (num == 1) {
                    if (tobni[num][2] != tobni[2][6]) {  //1번과 2번 톱니
                        canTurn[2] = true;  //2번 톱니 회전 가능
                    }
                    if (canTurn[2]) {  //2번이 회전가능할 때
                        if (tobni[2][2] != tobni[3][6]) {  //2번과 3번 톱니
                            canTurn[3] = true;  //3번 톱니 회전 가능
                        }
                        if (canTurn[3]) {
                            if (tobni[3][2] != tobni[4][6]) {  //3번가 4번 톱니
                                canTurn[4] = true;  //4번 톱니 회전 가능
                            }
                        }
                    }

                    clone = tobni[num].clone();
                    //1번 톱니 회전
                    turnRight(num);

                    if (canTurn[2]) {  //2번 톱니 반시계 방향 회전
                        clone = tobni[2].clone();
                        turnLeft(2);

                        if (canTurn[3]) {  //3번 톱니 시계 방향 회전
                            clone = tobni[3].clone();
                            turnRight(3);

                            if (canTurn[4]) {  //4번 톱니 반시계 방향 회전
                                clone = tobni[4].clone();
                                turnLeft(4);
                            }
                        }
                    }

                } else if (num == 2) {
                    if (tobni[num][6] != tobni[1][2]) {  //1번과 2번 톱니
                        canTurn[1] = true;  //1번 톱니 회전 가능
                    }
                    if (tobni[num][2] != tobni[3][6]) {  //2번과 3번 톱니
                        canTurn[3] = true;
                    }
                    if (canTurn[3]) {  //3번이 회전 가능할 때
                        if (tobni[3][2] != tobni[4][6]) {  //3번과 4번 톱니
                            canTurn[4] = true;
                        }
                    }

                    clone = tobni[num].clone();
                    //2번 톱니 회전
                    turnRight(2);

                    if (canTurn[1]) {  //1번 톱니 반시계 방향 회전
                        clone = tobni[1].clone();
                        turnLeft(1);
                    }

                    if (canTurn[3]) {  //3번 톱니 반시계 방향 회전
                        clone = tobni[3].clone();
                        turnLeft(3);

                        if (canTurn[4]) {  //4번 톱니 시계 방향 회전
                            clone = tobni[4].clone();
                            turnRight(4);
                        }
                    }

                } else if (num == 3) {
                    if (tobni[num][6] != tobni[2][2]) {  //2번과 3번 톱니
                        canTurn[2] = true;
                    }
                    if (canTurn[2]) {  //2번이 회전 가능할 때
                        if (tobni[1][2] != tobni[2][6]) {
                            canTurn[1] = true;
                        }
                    }
                    if (tobni[num][2] != tobni[4][6]) {  //3번과 4번 톱니
                        canTurn[4] = true;
                    }

                    clone = tobni[num].clone();
                    //3번 톱니 회전
                    turnRight(3);

                    if (canTurn[4]) {  //4번 톱니 반시계 방향 회전
                        clone = tobni[4].clone();
                        turnLeft(4);
                    }

                    if (canTurn[2]) {  //2번 톱니 반시계 방향 회전
                        clone = tobni[2].clone();
                        turnLeft(2);

                        if (canTurn[1]) {  //1번 톱니 시계 방향 회전
                            clone = tobni[1].clone();
                            turnRight(1);
                        }
                    }

                } else if (num == 4) {
                    if (tobni[num][6] != tobni[3][2]) {  //3번과 4번 톱니
                        canTurn[3] = true;  //2번 톱니 회전 가능
                    }
                    if (canTurn[3]) {  //2번이 회전가능할 때
                        if (tobni[2][2] != tobni[3][6]) {  //2번과 3번 톱니
                            canTurn[2] = true;  //3번 톱니 회전 가능
                        }
                        if (canTurn[2]) {
                            if (tobni[1][2] != tobni[2][6]) {  //1번가 2번 톱니
                                canTurn[1] = true;  //1번 톱니 회전 가능
                            }
                        }
                    }

                    clone = tobni[num].clone();
                    //4번 톱니 회전
                    turnRight(4);

                    if (canTurn[3]) {  //3번 톱니 반시계 방향 회전
                        clone = tobni[3].clone();
                        turnLeft(3);

                        if (canTurn[2]) {  //2번 톱니 시계 방향 회전
                            clone = tobni[2].clone();
                            turnRight(2);

                            if (canTurn[1]) {  //1번 톱니 반시계 방향 회전
                                clone = tobni[1].clone();
                                turnLeft(1);
                            }
                        }
                    }
                }

            } else if (dir == -1) {  //반시계 방향 회전

                if (num == 1) {
                    if (tobni[num][2] != tobni[2][6]) {  //1번과 2번 톱니
                        canTurn[2] = true;  //2번 톱니 회전 가능
                    }
                    if (canTurn[2]) {  //2번이 회전가능할 때
                        if (tobni[2][2] != tobni[3][6]) {  //2번과 3번 톱니
                            canTurn[3] = true;  //3번 톱니 회전 가능
                        }
                        if (canTurn[3]) {
                            if (tobni[3][2] != tobni[4][6]) {  //3번가 4번 톱니
                                canTurn[4] = true;  //4번 톱니 회전 가능
                            }
                        }
                    }

                    clone = tobni[num].clone();
                    //1번 톱니 회전
                    turnLeft(num);

                    if (canTurn[2]) {  //2번 톱니 시계 방향 회전
                        clone = tobni[2].clone();
                        turnRight(2);

                        if (canTurn[3]) {  //3번 톱니 반시계 방향 회전
                            clone = tobni[3].clone();
                            turnLeft(3);

                            if (canTurn[4]) {  //4번 톱니 시계 방향 회전
                                clone = tobni[4].clone();
                                turnRight(4);
                            }
                        }
                    }

                } else if (num == 2) {
                    if (tobni[num][6] != tobni[1][2]) {  //1번과 2번 톱니
                        canTurn[1] = true;  //1번 톱니 회전 가능
                    }
                    if (tobni[num][2] != tobni[3][6]) {  //2번과 3번 톱니
                        canTurn[3] = true;
                    }
                    if (canTurn[3]) {  //3번이 회전 가능할 때
                        if (tobni[3][2] != tobni[4][6]) {  //3번과 4번 톱니
                            canTurn[4] = true;
                        }
                    }

                    clone = tobni[num].clone();
                    //2번 톱니 회전
                    turnLeft(2);

                    if (canTurn[1]) {  //1번 톱니 시계 방향 회전
                        clone = tobni[1].clone();
                        turnRight(1);
                    }

                    if (canTurn[3]) {  //3번 톱니 시계 방향 회전
                        clone = tobni[3].clone();
                        turnRight(3);

                        if (canTurn[4]) {  //4번 톱니 반시계 방향 회전
                            clone = tobni[4].clone();
                            turnLeft(4);
                        }
                    }

                } else if (num == 3) {
                    if (tobni[num][6] != tobni[2][2]) {  //2번과 3번 톱니
                        canTurn[2] = true;
                    }
                    if (canTurn[2]) {  //2번이 회전 가능할 때
                        if (tobni[1][2] != tobni[2][6]) {
                            canTurn[1] = true;
                        }
                    }
                    if (tobni[num][2] != tobni[4][6]) {  //3번과 4번 톱니
                        canTurn[4] = true;
                    }

                    clone = tobni[num].clone();
                    //3번 톱니 회전
                    turnLeft(3);

                    if (canTurn[4]) {  //4번 톱니 시계 방향 회전
                        clone = tobni[4].clone();
                        turnRight(4);
                    }

                    if (canTurn[2]) {  //2번 톱니 시계 방향 회전
                        clone = tobni[2].clone();
                        turnRight(2);

                        if (canTurn[1]) {  //1번 톱니 반시계 방향 회전
                            clone = tobni[1].clone();
                            turnLeft(1);
                        }
                    }

                } else if (num == 4) {
                    if (tobni[num][6] != tobni[3][2]) {  //3번과 4번 톱니
                        canTurn[3] = true;  //2번 톱니 회전 가능
                    }
                    if (canTurn[3]) {  //2번이 회전가능할 때
                        if (tobni[2][2] != tobni[3][6]) {  //2번과 3번 톱니
                            canTurn[2] = true;  //3번 톱니 회전 가능
                        }
                        if (canTurn[2]) {
                            if (tobni[1][2] != tobni[2][6]) {  //1번가 2번 톱니
                                canTurn[1] = true;  //1번 톱니 회전 가능
                            }
                        }
                    }

                    clone = tobni[num].clone();
                    //4번 톱니 회전
                    turnLeft(4);

                    if (canTurn[3]) {  //3번 톱니 시계 방향 회전
                        clone = tobni[3].clone();
                        turnRight(3);

                        if (canTurn[2]) {  //2번 톱니 반시계 방향 회전
                            clone = tobni[2].clone();
                            turnLeft(2);

                            if (canTurn[1]) {  //1번 톱니 시계 방향 회전
                                clone = tobni[1].clone();
                                turnRight(1);
                            }
                        }
                    }
                }
            }
        }

        int result = 0;
        int score = 1;
        for (int i = 1; i < 5; i++) {
            if (tobni[i][0] == 1) {
                result += score;
            }
            score *= 2;
        }

        System.out.println(result);
    }

    private static void turnLeft(int num) {
        for (int j = 0; j < 7; j++) {
            tobni[num][j] = clone[j + 1];
        }
        tobni[num][7] = clone[0];
    }

    private static void turnRight(int num) {
        for (int j = 1; j < 8; j++) {
            tobni[num][j] = clone[j - 1];
        }
        tobni[num][0] = clone[7];
    }
}