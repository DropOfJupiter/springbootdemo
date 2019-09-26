package com.example.qiutt.demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.StringUtils;
import sun.security.util.SecurityConstants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yangyg
 * @date 创建时间：2019/5/22
 * 认证加密解密
 */
@Slf4j
public class EncodeDecodeUtil {
    public static final String BASE64HASH = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    public static final String ASCII = "([^\\u0000-\\u00ff])";

    public static boolean isMatcher(String str, String reg) {
        /**编译成一个正则表达式模式**/
        Pattern pattern = Pattern.compile(reg);
        /**匹配模式**/
        Matcher matcher = pattern.matcher(str);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    /**
     * bota 将字符串转成base64字符串
     * @param inEncodeStr
     * @return
     */
    public static String btoa(String inEncodeStr){
        if(StringUtils.isEmpty(inEncodeStr) || isMatcher(inEncodeStr ,ASCII)){
            return null;
        }
        StringBuilder result = new StringBuilder();

        int i=0;
        int mod=0;
        int ascii;
        int prev=0;
        while (i < inEncodeStr.length()){
            ascii = inEncodeStr.charAt(i);
            mod = i % 3;

            switch(mod){
                case 0:
                    result.append(String.valueOf(BASE64HASH.charAt(ascii >> 2)));
                    break;
                case 1:


                    result.append(String.valueOf(BASE64HASH.charAt((prev & 3) << 4 | (ascii >> 4))));
                    break;
                case 2:
                    result.append(String.valueOf(BASE64HASH.charAt((prev & 0x0f) << 2 | (ascii >> 6))));
                    result.append(String.valueOf(BASE64HASH.charAt(ascii & 0x3f)));
                    break;

                default:
                    return null;
            }

            prev = ascii;
            i++;
        }

        if(mod == 0) {
            result.append(String.valueOf(BASE64HASH.charAt((prev & 3) << 4)));
            result.append("==");
        } else if (mod == 1) {
            result.append(String.valueOf(BASE64HASH.charAt((prev & 0x0f) << 2)));
            result.append("=");
        }


        return result.toString();
    }

    /**
     * atob算法解密btoa加密的字符窜
     * @param encodeStr
     * @return
     */
    public static String atob(String encodeStr){
        if (StringUtils.isEmpty(encodeStr)){
            return null;
        }
        encodeStr = encodeStr.replaceAll("\\s|=", "");
        StringBuilder decodeStr = new StringBuilder();
        /**当前字符位置**/
        int cur;
        int prev = -1;
        int mod;
        int i=0;

        while (i< encodeStr.length()){
            cur = BASE64HASH.indexOf(encodeStr.charAt(i));
            mod = i % 4;
            switch (mod){
                case 0:
                    break;
                case 1:
                    decodeStr.append(String.valueOf( (char)( prev << 2 | cur >> 4) ));
                    break;
                case 2:
                    decodeStr.append(String.valueOf((char)( (prev & 0x0f) << 4 | cur >> 2) ));
                    break;
                case 3:
                    decodeStr.append(String.valueOf( (char)((prev & 3) << 6 | cur) ));
                    break;
                default:
                    break;
            }
            prev = cur;
            i++;
        }

        return decodeStr.toString();
    }

    @Test
    public void testatob(){
        String basicAuthInfo="Basic MTU2NzQwMzUyMzg2NzpVMzdKMFU1TThMVjM6UEkyalVucVpsUEd0UXM=";
        String authInfo = basicAuthInfo.replace("Basic ",basicAuthInfo);
        String result=atob(authInfo);

        log.info("result:{}",result);
    }

}

