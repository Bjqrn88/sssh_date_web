package bjqrn.hem.sssh_date_web.util;

import java.io.UnsupportedEncodingException;

import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Component;

@Component
public class EncryptionUtil {
    private static final String SALT = KeyGenerators.string().generateKey();
    private String PASSWORD = "BANAN_CAKE_MAN";

    public byte[] encrypString(String text) throws UnsupportedEncodingException {
        BytesEncryptor encryptor = Encryptors.stronger(PASSWORD, SALT);
        byte[] encoded = encryptor.encrypt(text.getBytes("UTF-8"));
        return encoded;
    }

    public String decrypString(byte[] chiper) throws UnsupportedEncodingException {
        BytesEncryptor encryptor = Encryptors.stronger(PASSWORD, SALT);
        String decoded = new String(encryptor.decrypt(chiper), "UTF-8");
        return decoded;
    }
}
