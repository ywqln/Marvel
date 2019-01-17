package com.ywqln.marvellib.utils;

import android.text.TextUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 描述:加密工具.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/1/17
 */
public class EncryptUtil {
    public static final String md5(final String message) {
        StringBuilder result = new StringBuilder();
        if (TextUtils.isEmpty(message)) {
            return result.toString();
        }

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] input = message.getBytes();
            byte[] buff = messageDigest.digest(input);

            for (int digital : buff) {
                if (digital < 0) {
                    digital += 256;
                }
                if (digital < 16) {
                    result.append("0");
                }
                result.append(Integer.toHexString(digital));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
