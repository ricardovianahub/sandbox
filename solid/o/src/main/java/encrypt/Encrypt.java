package encrypt;

public class Encrypt {
    public String it(String type, String input) {
        if ("PLUS1".equals(type)) {
            StringBuilder sb = new StringBuilder();
            char[] inputs = input.toCharArray();
            char next;
            for (char c : inputs) {
                next = (char) (c == 'Z' ? 'A' : c + 1);
                sb.append(next);
            }
            return sb.toString();
        }

        if ("MINUS1".equals(type)) {
            StringBuilder sb = new StringBuilder();
            char[] inputs = input.toCharArray();
            char next;
            for (char c : inputs) {
                next = (char) (c == 'A' ? 'Z' : c - 1);
                sb.append(next);
            }
            return sb.toString();
        }

        throw new IllegalArgumentException();
    }
}
