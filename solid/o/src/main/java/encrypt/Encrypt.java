package encrypt;

import encrypt.encryption.Encryption;

public class Encrypt {

    private Encryption encryption;

    public String it(String type, String input) {

        encryption = Encryption.factory().get(type);
        if (encryption != null) {
            return encryption.encrypt(input);
        }

        throw new IllegalArgumentException();
    }
}
