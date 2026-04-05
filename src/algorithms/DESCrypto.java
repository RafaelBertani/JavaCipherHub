package algorithms;

import java.io.*;
import java.security.*;
import java.security.spec.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.util.Base64;
import java.nio.charset.StandardCharsets;

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
            key = AESCrypto.deriveKey(password, salt, iterations, keySize); // PBKDF2
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

    // encryptFromString, encryptFromFile, decryptFromString, decryptFromFile (igual ao AES)
    // (copie os 4 métodos do AES e troque o nome da classe)
}