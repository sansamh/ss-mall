package io.sansam.config.controller;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.spec.InvalidParameterSpecException;
import java.util.Base64;


/**
 * <p>
 * AES
 * </p>
 *
 * @author houcb
 * @since 2020-04-23 18:20
 */
public class AES {

    //加密方式
    private static String keyAlgorithm = "AES";
    //避免重复new生成多个BouncyCastleProvider对象，因为GC回收不了，会造成内存溢出
    //只在第一次调用decrypt()方法时才new 对象
    private static boolean initialized = false;
    //用于Base64解密
    private static Base64.Decoder decoder = Base64.getDecoder();


    //待解密的数据
    private static String originalContent = "9Fi9FeI3z1SZOR38R+1inK3/Og6NDTVDnrA/XrEsIRyNxicDnjc+yE7eECJsu+dHtn5Ot43kUn" +
            "+2qlRwZmtZI3gwvVgaJXHEEncSnPj4KKTFJAU8fGWqaxVtm11W/PGpLyQXX2DJOl2wJ1hLPNQi24f5iDUSQw60q+QbIlH298p+ljprwIGFDN5Dk+nW+wmAPq2H68ZWFxR0gYsH0AK1xQ==";
    //会话密钥sessionKey
    private static String encryptKey = "eaFXunbdhVG7pvSPhnEv2w==";
    //加密算法的初始向量
    private static String iv = "YOFfM5fp3rs1vYdlLZuzxA==";

    public static void main(String[] args) throws Exception {
//        System.out.println(new AES().decrypt());
        // 开始解密
        byte[] encData = decoder.decode(originalContent);
        byte[] ivBytes = decoder.decode(iv);
        byte[] key = decoder.decode(encryptKey);
        IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        // 将解密结果 return
        System.out.println(new String(cipher.doFinal(encData), "UTF-8"));
    }

    /**
     * BouncyCastle作为安全提供，防止我们加密解密时候因为jdk内置的不支持改模式运行报错。
     **/
    private static void initialize() {
        if (initialized)
            return;
        Security.addProvider(new BouncyCastleProvider());
        initialized = true;
    }

    // 生成iv
    private static AlgorithmParameters generateIV(byte[] iv) throws NoSuchAlgorithmException, InvalidParameterSpecException {
        AlgorithmParameters params = AlgorithmParameters.getInstance(keyAlgorithm);
        params.init(new IvParameterSpec(iv));
        return params;
    }

    /**
     * AES解密
     * 填充模式AES/CBC/PKCS7Padding
     * 解密模式128
     *
     * @return 解密后的信息对象
     */
    public String decrypt() {
        initialize();
        try {
            //数据填充方式
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            Key sKeySpec = new SecretKeySpec(decoder.decode(this.encryptKey), keyAlgorithm);
            // 初始化
            cipher.init(Cipher.DECRYPT_MODE, sKeySpec, generateIV(decoder.decode(this.iv)));
            byte[] data = cipher.doFinal(decoder.decode(this.originalContent));
            String datastr = new String(data, StandardCharsets.UTF_8);
            System.out.println(datastr);
            return datastr;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


}
