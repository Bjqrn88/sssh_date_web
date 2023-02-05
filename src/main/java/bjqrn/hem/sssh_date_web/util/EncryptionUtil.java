package bjqrn.hem.sssh_date_web.util;

import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EncryptionUtil {
    private static final Logger log = LoggerFactory.getLogger(EncryptionUtil.class);

    // Secret
    @Value("${secretKey}")
    private String secret;

    // Generate a Key use the init the Chipher instance
    private Key generatKey(String secret) {
        byte[] decoded = Base64.getDecoder().decode(secret.getBytes());
        Key key = new SecretKeySpec(decoded, "AES");
        return key;
    }

    // Encode a String
    // private String encodeKey(String str) {
    //     byte[] encoded = Base64.getEncoder().encode(str.getBytes());
    //     return new String(encoded);
    // }

    // Encrypt a string using AES
    public String encryptString_g(String str) {
        try {
            Key key = generatKey(secret);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encVal = cipher.doFinal(str.getBytes());
            String encoded = Base64.getEncoder().encodeToString(encVal);
            return encoded;
        } catch (Exception e) {
            log.info(String.format("encryptString_javax failed with error: %s", e.getMessage()));
        }
        return null;
    }

    // Decrypt a string using AES
    public String decryptString_g(String str) {
        try {
            Key key = generatKey(secret);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            String decoded = new String(cipher.doFinal(Base64.getDecoder().decode(str)));
            return decoded;
        } catch (Exception e) {
            log.info(String.format("decryptString_javax failed with error: %s", e.getMessage()));
        }
        return null;
    }

}
