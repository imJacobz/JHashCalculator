package com.jacobz.jhash.service;

import com.jacobz.jhash.util.CRC32Checker;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HashService {
    public Map<String, String> calcHashValue(String filePath, Set<String> types) throws IOException {
        Map<String, String> result = new HashMap<>();
        for (String t : types) {
            InputStream is = new FileInputStream(filePath);
            switch (t){
                case "MD5":
                    result.put(t, DigestUtils.md5Hex(is));
                    break;
                case "SHA1":
                    result.put(t, DigestUtils.sha1Hex(is));
                    break;
                case "SHA256":
                    result.put(t, DigestUtils.sha256Hex(is));
                    break;
                case "SHA384":
                    result.put(t, DigestUtils.sha384Hex(is));
                    break;
                case "SHA512":
                    result.put(t, DigestUtils.sha512Hex(is));
                    break;
                case "CRC32":
                    result.put(t, CRC32Checker.getCRC32(is));
                    break;
                default:
                    break;
            }
            is.close();
        }
        return result;
    }
}
