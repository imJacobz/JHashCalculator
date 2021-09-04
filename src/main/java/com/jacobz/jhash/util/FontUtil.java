package com.jacobz.jhash.util;

import java.awt.*;
import java.io.*;

public class FontUtil {
    public static Font loadFont(InputStream is ) throws IOException, FontFormatException {
        return Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.PLAIN,14);
    }
}
