package com.qycloud.oatos.license.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Created by jiuyuehe on 2015/2/4.
 */
public class LicSecurity {

    /**
     * BASE64解密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptBASE64(String key)   {
        byte[] by =null;
        try{
          by =  (new BASE64Decoder()).decodeBuffer(key);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return by;
    }

    /**
     * BASE64加密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(byte[] key)  {
        String str =null;
        try {
           str =  (new BASE64Encoder()).encodeBuffer(key);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return str;
    }
}
