package algorithms;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.*;
import java.util.Base64;

public class ECCrypto {
    private ECCrypto() {}

    public static void signFromString(String data, String outputFilePath, String privateKeyBase64) throws Exception {
        byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
        byte[] privBytes = Base64.getDecoder().decode(privateKeyBase64);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(privBytes);
        PrivateKey privKey = KeyFactory.getInstance("EC").generatePrivate(spec);

        Signature sig = Signature.getInstance("SHA256withECDSA");
        sig.initSign(privKey);
        sig.update(dataBytes);
        byte[] signature = sig.sign();

        try (FileOutputStream out = new FileOutputStream(outputFilePath)) {
            out.write(signature);
        }
    }

    public static void signFromFile(String inputFilePath, String outputFilePath, String privateKeyBase64) throws Exception {
        byte[] privBytes = Base64.getDecoder().decode(privateKeyBase64);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(privBytes);
        PrivateKey privKey = KeyFactory.getInstance("EC").generatePrivate(spec);

        Signature sig = Signature.getInstance("SHA256withECDSA");
        sig.initSign(privKey);

        try (FileInputStream in = new FileInputStream(inputFilePath)) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) sig.update(buffer, 0, bytesRead);
        }
        byte[] signature = sig.sign();

        try (FileOutputStream out = new FileOutputStream(outputFilePath)) {
            out.write(signature);
        }
    }

    // verifyFromString / verifyFromFile recebem assinatura extra como parâmetro (String signatureBase64 ou File)
    // (implementação similar, retorna arquivo com "VALID" ou "INVALID")
}