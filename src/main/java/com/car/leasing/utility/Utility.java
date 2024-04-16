package com.car.leasing.utility;

import com.car.leasing.repository.entity.User;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utility {


    public static String getEncryption(User user) {
        byte[] encryptedText = null;
        try {
            Date currentDate = new Date();
            SimpleDateFormat df = new SimpleDateFormat("dd-mm-yyyy HH:mm:ss");
            String value = user.toString() + df.format(currentDate);
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
            byte[] key = "777".getBytes();
            SecretKey secretKey = new SecretKeySpec(key, "DES");
            byte[] text = value.getBytes();
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            encryptedText = cipher.doFinal(text);
        }
        catch(Exception ex){
            throw new RuntimeException();
        }
        return new String(encryptedText);
    }
}
