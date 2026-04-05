package algorithms;

// Mesma estrutura do SHA-256, só troca o algoritmo
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.*;

public class MD5Crypto {
    private MD5Crypto() {}

    private static void hashInternal(InputStream in, OutputStream out, String salt, String password) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        if (salt != null && !salt.isEmpty()) md.update(salt.getBytes(StandardCharsets.UTF_8));
        if (password != null && !password.isEmpty()) md.update(password.getBytes(StandardCharsets.UTF_8));

        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = in.read(buffer)) != -1) md.update(buffer, 0, bytesRead);

        byte[] digest = md.digest();
        out.write(digest);
    }

    // hashFromString e hashFromFile iguais ao SHA256Crypto
}