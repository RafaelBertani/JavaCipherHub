package algorithms;

import java.io.*;
import java.security.*;
import java.security.spec.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.util.Base64;
import java.nio.charset.StandardCharsets;

public class ChaCha20Crypto {
    private ChaCha20Crypto() {}

    private static void processInternal(InputStream in, OutputStream out, int opmode, String password, String salt,
                                        String nonceBase64, String keyBase64, int keySize, int iterations) throws Exception {
        Cipher cipher = Cipher.getInstance("ChaCha20-Poly1305");
        SecretKey key;
        if (keyBase64 != null && !keyBase64.isEmpty()) {
            byte[] keyBytes = Base64.getDecoder().decode(keyBase64);
            key = new SecretKeySpec(keyBytes, "ChaCha20");
        } else {
            key = AESCrypto.deriveKey(password, salt, iterations, keySize); // reutiliza deriveKey do AES (mesmo PBKDF2)
        }

        byte[] nonce = nonceBase64 != null && !nonceBase64.isEmpty() ?
                Base64.getDecoder().decode(nonceBase64) : new byte[12];
        ChaCha20ParameterSpec paramSpec = new ChaCha20ParameterSpec(nonce, 0);

        cipher.init(opmode, key, paramSpec);

        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = in.read(buffer)) != -1) {
            byte[] output = cipher.update(buffer, 0, bytesRead);
            if (output != null) out.write(output);
        }
        byte[] finalOutput = cipher.doFinal();
        if (finalOutput != null) out.write(finalOutput);
    }

    // métodos encrypt/decrypt iguais ao AES (substitua AESCrypto por ChaCha20Crypto)
    public static void encryptFromString(String data, String outputFilePath, String password, String salt,
                                         String nonceBase64, String keyBase64, int keySize, int iterations) throws Exception {
        try (InputStream in = new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));
             FileOutputStream out = new FileOutputStream(outputFilePath)) {
            processInternal(in, out, Cipher.ENCRYPT_MODE, password, salt, nonceBase64, keyBase64, keySize, iterations);
        }
    }

    public static void encryptFromFile(String inputFilePath, String outputFilePath, String password, String salt,
                                       String nonceBase64, String keyBase64, int keySize, int iterations) throws Exception {
        try (FileInputStream in = new FileInputStream(inputFilePath);
             FileOutputStream out = new FileOutputStream(outputFilePath)) {
            processInternal(in, out, Cipher.ENCRYPT_MODE, password, salt, nonceBase64, keyBase64, keySize, iterations);
        }
    }

    public static void decryptFromString(String dataBase64, String outputFilePath, String password, String salt,
                                         String nonceBase64, String keyBase64, int keySize, int iterations) throws Exception {
        byte[] ciphertext = Base64.getDecoder().decode(dataBase64);
        try (InputStream in = new ByteArrayInputStream(ciphertext);
             FileOutputStream out = new FileOutputStream(outputFilePath)) {
            processInternal(in, out, Cipher.DECRYPT_MODE, password, salt, nonceBase64, keyBase64, keySize, iterations);
        }
    }

    public static void decryptFromFile(String inputFilePath, String outputFilePath, String password, String salt,
                                       String nonceBase64, String keyBase64, int keySize, int iterations) throws Exception {
        try (FileInputStream in = new FileInputStream(inputFilePath);
             FileOutputStream out = new FileOutputStream(outputFilePath)) {
            processInternal(in, out, Cipher.DECRYPT_MODE, password, salt, nonceBase64, keyBase64, keySize, iterations);
        }
    }
}