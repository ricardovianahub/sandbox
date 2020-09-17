package encrypt;

public class Encrypt {
    public String it(String type, String input) {
        if ("PLUS1".equals(type)) {
            StringBuilder sb = new StringBuilder();
            char[] inputs = input.toCharArray();
            for (char c : inputs) {
                if (c == 'Z') {
                    sb.append("A");
                } else if (c == 'z') {
                    sb.append("a");
                } else {
                    sb.append((char) (c + 1));
                }
            }
            return sb.toString();
        }

        throw new IllegalArgumentException();
    }
}
