package org.homebudget.services;

import java.security.MessageDigest;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    public static String getHash(String value) throws Exception {
        final MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(value.getBytes());
        byte byteData[] = md.digest();
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            String hex = Integer.toHexString(0xff & byteData[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);

        }

        return hexString.toString();
    }

}
