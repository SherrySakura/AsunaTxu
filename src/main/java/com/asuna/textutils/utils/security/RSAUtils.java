package com.asuna.textutils.utils.security;

import org.apache.commons.codec.binary.Base64;
import org.springframework.core.io.ClassPathResource;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

public class RSAUtils {
    private static final String privateKeyFilename = "private.key";
    private static final String publicKeyFilename = "public.key";

    public static void generateKeyPair() throws NoSuchAlgorithmException, IOException {
        if (isKeyFileExist()){
            return;
        }
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(512, new SecureRandom());
        KeyPair keyPair = generator.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        //String privateKeyString = new String(Base64.getEncoder().encodeToString(privateKey.getEncoded()));
        String privateKeyString = new String(Base64.encodeBase64(privateKey.getEncoded()));
        String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));
        System.out.println(privateKeyString);
        System.out.println(publicKeyString);
        saveKeyPair(privateKeyString, publicKeyString);
    }

    public static String getPublicKey() throws IOException, NoSuchAlgorithmException {
        generateKeyPair();
        StringBuilder builder = new StringBuilder(1024);
        File publicKeyFile = new File(publicKeyFilename);
        BufferedReader reader = new BufferedReader(new FileReader(publicKeyFile));
        String tempLine = "";
        while ((tempLine = reader.readLine()) != null) {
            builder.append(tempLine);
        }
        reader.close();
        return builder.toString();
    }

    private static boolean isKeyFileExist(){
        File privateKeyFile = new File(privateKeyFilename);
        File publicKeyFile = new File(publicKeyFilename);
        return privateKeyFile.exists() && publicKeyFile.exists();
    }

    private static void saveKeyPair(String privateKeyString, String publicKeyString) throws IOException {
        FileWriter writer = new FileWriter(privateKeyFilename);
        writer.write(privateKeyString);
        writer.flush();
        writer = new FileWriter(publicKeyFilename);
        writer.write(publicKeyString);
        writer.flush();
        writer.close();
    }

    public static String encrypt(String content) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        String pk = getPublicKey();
        byte[] pkDecoded = Base64.decodeBase64(pk);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(pkDecoded);
        byte[] data = content.getBytes("UTF-8");
        KeyFactory factory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = factory.generatePublic(keySpec);
        Cipher cipher = Cipher.getInstance(factory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return Base64.encodeBase64String(cipher.doFinal(data));
    }
}
