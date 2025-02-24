package daewonko.pgs;

public class PGS_60058 {
    public String solution(String p) {
        if (p.isEmpty()) return "";

        int balance = 0, i = 0;
        do {
            balance += p.charAt(i) == '(' ? 1 : -1;
            i++;
        } while (balance != 0);

        String u = p.substring(0, i);
        String v = p.substring(i);

        if (isValid(u)) {
            return u + solution(v);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("(").append(solution(v)).append(")");
            for (int j = 1; j < u.length() - 1; j++) {
                sb.append(u.charAt(j) == '(' ? ')' : '(');
            }
            return sb.toString();
        }
    }

    private boolean isValid(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            count += c == '(' ? 1 : -1;
            if (count < 0) return false;
        }
        return true;
    }

}

