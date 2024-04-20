package com.car.leasing.utility;

import com.car.leasing.repository.SessionRepository;
import com.car.leasing.repository.entity.Session;
import com.car.leasing.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class Utility {
    public static Session getEncryption(User user, SessionRepository sessionRepo) {
        Session session = null;
        try {
            Date currentDate = new Date();
            SimpleDateFormat df = new SimpleDateFormat("dd-mm-yyyy HH:mm:ss");
            String value = user.toString() + df.format(currentDate);
            value.replace("\\s+", "");
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] text = value.getBytes("UTF8");
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedText = cipher.doFinal(text);
            String cookie = Base64.getEncoder().encodeToString(encryptedText);
            cookie.replace("\\s+", "");
            session = new Session(cookie, user, df.parse(df.format(currentDate)));

        }
        catch(Exception ex){
            throw new RuntimeException();
        }
        return session;
    }
}
