package encrypt.encryption;

import java.util.HashMap;
import java.util.Map;

public interface Encryption {
    String encrypt(String input);

    static Map<String,Encryption> factory() {
        return new HashMap<String,Encryption>() {{
            put("PLUS1", new EncryptPlus1());
            put("MINUS1", new EncryptMinus1());
            put("ROT13", new EncryptRot13());
            put("CYPHER01", new EncryptCypher01());
        }};
    }
}
