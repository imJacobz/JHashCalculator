package com.jacobz.jhash.config;

import java.util.HashMap;
import java.util.Map;

public class LabelConst {
    public static final Map<Integer, String> labelMap = new HashMap<>();

    static {
        labelMap.put(0, "MD5");
        labelMap.put(1, "SHA1");
        labelMap.put(2, "SHA256");
        labelMap.put(3, "SHA384");
        labelMap.put(4, "SHA512");
        labelMap.put(5, "CRC32");
    }
}
