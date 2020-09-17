package encrypt.encryption;

class EncryptRot13 implements Encryption {
    @Override
    public String encrypt(String input) {
        StringBuilder sb = new StringBuilder();
        char[] inputs = input.toCharArray();
        char next;
        for (char c : inputs) {
            next = (char) (c >= 'N' ? c - 13 : c + 13);
            sb.append(next);
        }
        return sb.toString();
    }
}
