package org.lsd.extend;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author nhsoft.lsd
 */
public class Test {

    private static Object o = new Object();

    public static void main(String[] args) throws Exception {

//       String result = decrypt("jFWIbm4LZF7m2kvIQ4esexMidi5viOcgONImpMdaReiGlKcRPr+7qa0+U1WLQq699B/6YMamTTs/U3EDD5VNaCTQy4xB1eMYalcgkybSRYXV5UnCwgk0FVs6LQpUgTrPKhYM35iFrbpB1Iniu33GzPbvLEJST3Xb3QI7K2FOe9Y9K1TVYG7GlhYfU7Z6mVaEHiV6+4hXk8TwonY9fjmExQGdZ5wKwrCdi1UkHBr+yM8=", "uIz19lPqmZoFViA4FHrO2B");
//        System.out.println(result);

        String s =  encrypt("{\"encryptUrl\":{\"bookCode\":\"4030\",\"bookScopeId\":\"374a3551-1182-41aa-bd3f-77e59f41ce9b\",\"version\":\"20221102\",\"timestamp\":1667790043017,\"appUserNum\":434400017,\"branchNum\":99}}", "uIz19lPqmZoFViA4FHrO2B");
        System.out.println(s);
    }

    public static void test(int x) {

        x = x + 1;

    }

    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";


    public static String encrypt(String sSrc, String sKey){
        if (sKey == null) {

            return null;
        }
        if (sKey.length() < 16) {
            return null;
        }
        // 判断Key是否为16位
        if (sKey.length() > 16) {
            sKey = sKey.substring(0, 16);
        }
        try {

            byte[] raw = sKey.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);//"算法/模式/补码方式"
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));

            return new String(Base64.getEncoder().encode(encrypted));//此处使用BASE64做转码功能，同时能起到2次加密的作用。
        } catch (Exception e) {
            return null;
        }
    }

    // 解密
    public static String decrypt(String sSrc, String sKey) throws Exception {
        try {
            // 判断Key是否正确
            if (sKey == null) {
                return null;
            }
            if (sKey.length() < 16) {
                return null;
            }
            // 判断Key是否为16位
            if (sKey.length() > 16) {
                sKey = sKey.substring(0, 16);
            }
            byte[] raw = sKey.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] encrypted1 = Base64.getDecoder().decode(sSrc.getBytes("utf-8"));//先用base64解密

            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original,"utf-8");
            return originalString;

        } catch (Exception e) {
            return null;
        }
    }
}
