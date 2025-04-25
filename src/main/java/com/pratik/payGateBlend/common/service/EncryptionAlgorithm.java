package com.pratik.payGateBlend.common.service;

import com.pratik.payGateBlend.config.Settings;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@AllArgsConstructor(onConstructor_ = @__({@Autowired}))
@NoArgsConstructor
@Service
public class EncryptionAlgorithm {
    private static final Logger logger = LoggerFactory.getLogger(EncryptionAlgorithm.class);

    private Settings settings;

    public String encryptThree(String json) throws Exception {
        // Decode Base64-encoded key and IV
        String gripsThreeKey = settings.getGripsThreeKey();
        String gripsThreeIv = settings.getGripsThreeIv();
        byte[] keyBytes = gripsThreeKey.getBytes("UTF-8");
        byte[] ivBytes = gripsThreeIv.getBytes("UTF-8");
        logger.info(" encrypt step 1");

        // Initialize AES key and IV parameter specs
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(ivBytes);
        logger.info(" encrypt step 2");

        // Initialize cipher for AES encryption
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParameterSpec);
        logger.info(" encrypt step 3");

        // Encrypt the plaintext using ByteArrayOutputStream and BufferedWriter
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"))) {
            writer.write(json);
            writer.flush(); // Write all data to the stream
        }
        logger.info(" encrypt step 4");
        // Perform AES encryption
        byte[] encryptedBytes = cipher.doFinal(outputStream.toByteArray());
        logger.info(" encrypt step 5");

        // Return Base64 encoded encrypted bytes
        return Base64.getEncoder().encodeToString(encryptedBytes);

    }

    public String decryptThree(String encdata) throws Exception {

        String gripsThreeKey = settings.getGripsThreeKey();
        String gripsThreeIv = settings.getGripsThreeIv();

        // Decode Base64-encoded key and IV
        byte[] keyBytes = gripsThreeKey.getBytes("UTF-8");
        byte[] ivBytes = gripsThreeIv.getBytes("UTF-8");

        // Initialize AES key and IV parameter specs
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(ivBytes);

        // Initialize cipher for AES encryption
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParameterSpec);

        // Decrypt bytes to a string
        byte[] cipherBytes = Base64.getDecoder().decode(encdata);
        byte[] decryptedBytes = cipher.doFinal(cipherBytes);

        return new String(decryptedBytes, "UTF-8");
    }


    public String checkSum(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));

            // Convert byte array to hex string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02X", b)); // Uppercase hex
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found", e);
        }
    }



}
