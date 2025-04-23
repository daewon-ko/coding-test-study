import java.util.HashSet;
import java.util.Set;

/*
프로그래머스 / 불량 사용자 / Level 3
https://school.programmers.co.kr/learn/courses/30/lessons/64064
 */
class PGS_64064 {

    private final Set<Set<String>> result = new HashSet<>();

    public int solution(String[] user_id, String[] banned_id) {
        dfs(new HashSet<>(), 0, user_id, banned_id);
        return result.size();
    }

    private void dfs(Set<String> selected, int depth, String[] user_id, String[] banned_id) {
        if (depth == banned_id.length) {
            result.add(new HashSet<>(selected));
            return;
        }

        for (String user : user_id) {
            if (selected.contains(user)) {
                continue;
            }
            if (isMatch(user, banned_id[depth])) {
                selected.add(user);
                dfs(selected, depth + 1, user_id, banned_id);
                selected.remove(user); // 백트래킹
            }
        }
    }

    private boolean isMatch(String user, String banned) {
        if (user.length() != banned.length()) {
            return false;
        }
        for (int i = 0; i < user.length(); i++) {
            if (banned.charAt(i) == '*') {
                continue;
            }
            if (user.charAt(i) != banned.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}
