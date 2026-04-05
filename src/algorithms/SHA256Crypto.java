package algorithms;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.*;

public class SHA256Crypto {
    private SHA256Crypto() {}

    private static void hashInternal(InputStream in, OutputStream out, String salt, String password) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        if (salt != null && !salt.isEmpty()) md.update(salt.getBytes(StandardCharsets.UTF_8));
        if (password != null && !password.isEmpty()) md.update(password.getBytes(StandardCharsets.UTF_8));

        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = in.read(buffer)) != -1) md.update(buffer, 0, bytesRead);

        byte[] digest = md.digest();
        out.write(digest); // digest binário
    }

    public static void hashFromString(String data, String outputFilePath, String salt, String password) throws Exception {
        try (InputStream in = new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));
             FileOutputStream out = new FileOutputStream(outputFilePath)) {
            hashInternal(in, out, salt, password);
        }
    }

    public static void hashFromFile(String inputFilePath, String outputFilePath, String salt, String password) throws Exception {
        try (FileInputStream in = new FileInputStream(inputFilePath);
             FileOutputStream out = new FileOutputStream(outputFilePath)) {
            hashInternal(in, out, salt, password);
        }
    }
}