package bjqrn.hem.sssh_date_web.util;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Component;

@Component
public class EncryptionUtil {
    private static final Logger log = LoggerFactory.getLogger(EncryptionUtil.class);
    private static final String SALT = KeyGenerators.string().generateKey();
    private String PASSWORD = "BANAN_CAKE_MAN16";
    private byte[] key;
    private SecretKeySpec secretKey;

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

    public void setKey(final String mkey) {
        MessageDigest sha = null;
        try {
            this.key = mkey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            this.key = sha.digest(this.key);
            this.key = Arrays.copyOf(this.key, 16);
            this.secretKey = new SecretKeySpec(this.key, "AES");
        } catch (Exception e) {
            log.info(String.format("setKey failed with error: %s", e.getMessage()));
        }
    }

    private Key generatKey(String secret) {
        byte[] decoded = Base64.getDecoder().decode(secret.getBytes());
        Key key = new SecretKeySpec(decoded, "AES");
        return key;
    }

    private String encodeKey(String str) {
        byte[] encoded = Base64.getEncoder().encode(str.getBytes());
        return new String(encoded);
    }

    private String byteArrayToHexString(byte[] data) {
        StringBuilder sb = new StringBuilder();
        for (byte b: data) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public String encryptString_javax(final String str, final String secret) {
        try {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, this.secretKey);
            String encoded = Base64.getEncoder().encodeToString(cipher.doFinal(str.getBytes("UTF-8")));
            return encoded;
        } catch (Exception e) {
            log.info(String.format("encryptString_javax failed with error: %s", e.getMessage()));
        }
        return null;
    }

    public String decryptString_javax(final String str, final String secret) {
        try {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, this.secretKey);
            String decoded = new String(cipher.doFinal(Base64.getDecoder().decode(str)));
            return decoded;
        } catch (Exception e) {
            log.info(String.format("decryptString_javax failed with error: %s", e.getMessage()));
        }
        return null;
    }

    public String encryptString_g(String str) {
        try {
            String encodedKey = "QkFOQU5fQ0FLRV9NQU4xNg==";//encodeKey(PASSWORD);
            log.info("encodedKey: "+ encodedKey);
            Key key = generatKey(encodedKey);
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

    public String encryptString_hex(String str) {
        try {
            String encodedKey = encodeKey(PASSWORD);
            log.info("encodedKey: "+ encodedKey);
            Key key = generatKey(encodedKey);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encVal = cipher.doFinal(str.getBytes());
            String encoded = byteArrayToHexString(encVal);
            return encoded;
        } catch (Exception e) {
            log.info(String.format("encryptString_javax failed with error: %s", e.getMessage()));
        }
        return null;
    }

    public String decryptString_g(String str) {
        try {
            String encodedKey = encodeKey(PASSWORD);
            Key key = generatKey(encodedKey);
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
