package com.example.seo.blog.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.RSAPublicKeySpec;
import java.util.HashMap;
import java.util.Map;

public class RSAUtil {

    protected final static Logger LOGGER = LoggerFactory.getLogger(RSAUtil.class);

    public final static String PRIVATE_KEY = "privateKey";
    public final static String PUBLIC_KEY = "publicKey" ;
    public final static String PUBLIC_KEY_MODULUS = "publicKeyModulus" ;
    public final static String PUBLIC_KEY_EXPONENT = "publicKeyExponent" ;
    
    
    // 1024비트 RSA 키쌍 생성
    public static KeyPair genKey() throws NoSuchAlgorithmException {
        SecureRandom secureRandom = new SecureRandom();
        KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
        gen.initialize(1024, secureRandom);
        return gen.genKeyPair();
    }
    
    public static Map<String, String> getKeySpec(PublicKey publicKey) {
        Map<String, String> spec = new HashMap<>();

        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            RSAPublicKeySpec publicSpec = keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
            spec.put(RSAUtil.PUBLIC_KEY_MODULUS, publicSpec.getModulus().toString(16));
            spec.put(RSAUtil.PUBLIC_KEY_EXPONENT, publicSpec.getPublicExponent().toString(16));

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        return spec;
    }

    // PrivateKey로 RSA 복호화 수행
    public static String decryptRSA(String encrypted, PrivateKey privateKey) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {

        String decrypted = "";

        try {
            Cipher cipher = Cipher.getInstance("RSA");
            byte[] encryptedBytes = hexToByteArray(encrypted);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            decrypted = new String(decryptedBytes, StandardCharsets.UTF_8);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return decrypted;
    }

    public static byte[] hexToByteArray(String hex) {
        if(hex == null || hex.length() % 2 != 0) {
            return new byte[]{};
        }
        byte[] bytes = new byte[hex.length() / 2];
        for(int i = 0; i < hex.length(); i += 2) {
            byte value = (byte)Integer.parseInt(hex.substring(i, i + 2), 16);
            bytes[(int) Math.floor(i / 2)] = value;
        }
        return bytes;
    }

}
