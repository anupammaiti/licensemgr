package com.qycloud.oatos.license.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class LicenseUtil {

    public static final Long DAY = 60*60*24*1000l;

    private String[] strs = {"!", "g", "5", "?", "#", "k", "@", "7", "&", "^"};

    private int[] chars = {13, 36, 62, 91, 103};

    private static XStream xstream;

    private LicenseUtil() {
        xstream = new XStream(new DomDriver("UTF-8"));
        xstream.processAnnotations(License.class);
    }

    public void toXml(License license, File file) throws Exception {
        FileOutputStream out = null;
        try {
            String sha = sha(license);
            String[] uuids = UUID.randomUUID().toString().split("-");
            StringBuilder sign = new StringBuilder(sha);
            for (int i = 0; i < 5; i++) {
                sign.setCharAt(chars[i], uuids[i].charAt(0));
            }

            license.setSignature(sign.toString());
            out = new FileOutputStream(file);
            xstream.toXML(license, out);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (Exception ex) {
            }
        }
    }

    private String sha(License license) {
        StringBuilder sb = new StringBuilder();
        sb.append(strs[0]).append(license.getLicenseType());
        sb.append(strs[1]).append(license.getLicenseVersion());
        sb.append(strs[2]).append(license.getProductVersion());
        sb.append(strs[3]).append(license.getLicenseTo());
        sb.append(strs[4]).append(license.getKey()).append(license.getKey2());
        sb.append(strs[5]).append(license.getMaxUser());
        sb.append(strs[6]).append(license.getExpiry());
        sb.append(strs[7]).append(license.getLicenseBy());
        sb.append(strs[8]).append(license.getEmail());
        sb.append(strs[9]).append(license.getTel());

        String sha = DigestUtils.sha512Hex(sb.toString());
        return sha;
    }

    private String getSignature(String str) {
        StringBuilder sign = new StringBuilder(str);
        for (int i = chars.length; i > 0; i--) {
            sign.deleteCharAt(chars[i - 1]);
        }
        return sign.toString();
    }

    public License fromXML(File file) {
        License license = (License) xstream.fromXML(file);
        String sign1 = getSignature(license.getSignature());
        System.out.println(sign1);
        String sign2 = getSignature(sha(license));
        System.out.println(sign2);
        if (!sign1.equals(sign2)) {
            return null;
        }
        return license;
    }

    public static LicenseUtil get() {
        return LicenseCreaterHolder.instance;
    }

    private static class LicenseCreaterHolder {
        private static LicenseUtil instance = new LicenseUtil();
    }

    public String getLicNo() {
        StringBuffer key = new StringBuffer();
        key.append(Security.randomCharString(5)).append("-");
        key.append(Security.randomCharString(5)).append("-");
        key.append(Security.randomCharString(5)).append("-");
        key.append(Security.randomCharString(5)).append("-");
        key.append(Security.randomCharString(5));
        return key.toString().toUpperCase();
    }


    private static final SimpleDateFormat sdf = new SimpleDateFormat(
            "yyyy-MM-dd");

    public String getExpiry(int num) {
        long c = new Date().getTime();
        long s = c + DAY*num;
        return sdf.format(s);
    }

}
