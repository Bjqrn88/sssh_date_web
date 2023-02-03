package bjqrn.hem.sssh_date_web.util;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Component;

import bjqrn.hem.sssh_date_web.schedulingtasks.DateGenerator;

@Component
public class EncryptionUtil {
    private static final Logger log = LoggerFactory.getLogger(DateGenerator.class);
    private static final String SALT = KeyGenerators.string().generateKey();
    private String PASSWORD = "BANAN_CAKE_MAN";

    public byte[] encrypString(String text) throws UnsupportedEncodingException {
        log.info(String.format("salt: %s", SALT));

        BytesEncryptor encryptor = Encryptors.stronger(PASSWORD, SALT);
        byte[] encoded = encryptor.encrypt(text.getBytes("UTF-8"));
        log.info(String.format("Encoded: %s", encoded));
        return encoded;
    }

    public String decrypString(byte[] chiper) throws UnsupportedEncodingException {
        log.info(String.format("salt: %s", SALT));

        BytesEncryptor encryptor = Encryptors.stronger(PASSWORD, SALT);
        String decoded = new String(encryptor.decrypt(chiper),"UTF-8");
        log.info(String.format("Decoded: %s", decoded));
        return decoded;
    }
}
