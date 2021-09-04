package com.jacobz.jhash.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.CRC32;

public class CRC32Checker {
    public static String getCRC32(InputStream inputStream) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        CRC32 crc = new CRC32();
        byte[] bytes = new byte[1024];
        int cnt;
        while ((cnt = bufferedInputStream.read(bytes)) != -1) {
            crc.update(bytes, 0, cnt);
        }
        bufferedInputStream.close();
        return Long.toHexString(crc.getValue());
    }


}
