package algorithms;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.crypto.*;
import javax.crypto.spec.*;

public class DESCrypto {
    private DESCrypto() {}

    private static void processInternal(InputStream in, OutputStream out, int opmode, String password, String salt,
                                        String ivBase64, String keyBase64, String mode, String padding,
                                        int keySize, int iterations) throws Exception {
        String transformation = "DES/" + mode + "/" + padding;
        Cipher cipher = Cipher.getInstance(transformation);

        SecretKey key;
        if (keyBase64 != null && !keyBase64.isEmpty()) {
            byte[] keyBytes = Base64.getDecoder().decode(keyBase64);
            key = new SecretKeySpec(keyBytes, "DES");
        } else {
            // reutiliza o mesmo deriveKey do AES (PBKDF2)
            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(StandardCharsets.UTF_8), iterations, keySize);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            key = skf.generateSecret(spec);
        }

        if (ivBase64 != null && !ivBase64.isEmpty()) {
            byte[] iv = Base64.getDecoder().decode(ivBase64);
            cipher.init(opmode, key, new IvParameterSpec(iv));
        } else {
            cipher.init(opmode, key);
        }

        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = in.read(buffer)) != -1) {
            byte[] output = cipher.update(buffer, 0, bytesRead);
            if (output != null) out.write(output);
        }
        byte[] finalOutput = cipher.doFinal();
        if (finalOutput != null) out.write(finalOutput);
    }

    public static void encryptFromString(String data, String outputFilePath, String password, String salt,
                                         String ivBase64, String keyBase64, String mode, String padding,
                                         int keySize, int iterations) throws Exception {
        try (InputStream in = new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));
             FileOutputStream out = new FileOutputStream(outputFilePath)) {
            processInternal(in, out, Cipher.ENCRYPT_MODE, password, salt, ivBase64, keyBase64, mode, padding, keySize, iterations);
        }
    }

    public static void encryptFromFile(String inputFilePath, String outputFilePath, String password, String salt,
                                       String ivBase64, String keyBase64, String mode, String padding,
                                       int keySize, int iterations) throws Exception {
        try (FileInputStream in = new FileInputStream(inputFilePath);
             FileOutputStream out = new FileOutputStream(outputFilePath)) {
            processInternal(in, out, Cipher.ENCRYPT_MODE, password, salt, ivBase64, keyBase64, mode, padding, keySize, iterations);
        }
    }

    public static void decryptFromString(String dataBase64, String outputFilePath, String password, String salt,
                                         String ivBase64, String keyBase64, String mode, String padding,
                                         int keySize, int iterations) throws Exception {
        byte[] ciphertext = Base64.getDecoder().decode(dataBase64);
        try (InputStream in = new ByteArrayInputStream(ciphertext);
             FileOutputStream out = new FileOutputStream(outputFilePath)) {
            processInternal(in, out, Cipher.DECRYPT_MODE, password, salt, ivBase64, keyBase64, mode, padding, keySize, iterations);
        }
    }

    public static void decryptFromFile(String inputFilePath, String outputFilePath, String password, String salt,
                                       String ivBase64, String keyBase64, String mode, String padding,
                                       int keySize, int iterations) throws Exception {
        try (FileInputStream in = new FileInputStream(inputFilePath);
             FileOutputStream out = new FileOutputStream(outputFilePath)) {
            processInternal(in, out, Cipher.DECRYPT_MODE, password, salt, ivBase64, keyBase64, mode, padding, keySize, iterations);
        }
    }
}