package algorithms;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.*;
import java.util.Base64;
import javax.crypto.*;

public class RSACrypto {
    private RSACrypto() {}

    private static void processInternal(InputStream in, OutputStream out, int opmode,
                                        String publicKeyBase64, String privateKeyBase64,
                                        String padding) throws Exception {
        String transformation = "RSA/None/" + padding;
        Cipher cipher = Cipher.getInstance(transformation);

        if (opmode == Cipher.ENCRYPT_MODE) {
            byte[] keyBytes = Base64.getDecoder().decode(publicKeyBase64);
            X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
            PublicKey pubKey = KeyFactory.getInstance("RSA").generatePublic(spec);
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        } else {
            byte[] keyBytes = Base64.getDecoder().decode(privateKeyBase64);
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
            PrivateKey privKey = KeyFactory.getInstance("RSA").generatePrivate(spec);
            cipher.init(Cipher.DECRYPT_MODE, privKey);
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

    public static void encryptFromString(String data, String outputFilePath,
                                         String publicKeyBase64, String padding) throws Exception {
        try (InputStream in = new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));
             FileOutputStream out = new FileOutputStream(outputFilePath)) {
            processInternal(in, out, Cipher.ENCRYPT_MODE, publicKeyBase64, null, padding);
        }
    }

    public static void encryptFromFile(String inputFilePath, String outputFilePath,
                                       String publicKeyBase64, String padding) throws Exception {
        try (FileInputStream in = new FileInputStream(inputFilePath);
             FileOutputStream out = new FileOutputStream(outputFilePath)) {
            processInternal(in, out, Cipher.ENCRYPT_MODE, publicKeyBase64, null, padding);
        }
    }

    public static void decryptFromString(String dataBase64, String outputFilePath,
                                         String privateKeyBase64, String padding) throws Exception {
        byte[] ciphertext = Base64.getDecoder().decode(dataBase64);
        try (InputStream in = new ByteArrayInputStream(ciphertext);
             FileOutputStream out = new FileOutputStream(outputFilePath)) {
            processInternal(in, out, Cipher.DECRYPT_MODE, null, privateKeyBase64, padding);
        }
    }

    public static void decryptFromFile(String inputFilePath, String outputFilePath,
                                       String privateKeyBase64, String padding) throws Exception {
        try (FileInputStream in = new FileInputStream(inputFilePath);
             FileOutputStream out = new FileOutputStream(outputFilePath)) {
            processInternal(in, out, Cipher.DECRYPT_MODE, null, privateKeyBase64, padding);
        }
    }
}