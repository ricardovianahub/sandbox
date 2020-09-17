package encrypt.encryption;

class EncryptPlus1 implements Encryption {
    @Override
    public String encrypt(String input) {
        StringBuilder sb = new StringBuilder();
        char[] inputs = input.toCharArray();
        char next;
        for (char c : inputs) {
            next = (char) (c == 'Z' ? 'A' : c + 1);
            sb.append(next);
        }
        return sb.toString();
    }
}
