package com.cytmxk.utils.common;

import java.io.Closeable;
import java.io.IOException;

public class CloseableUtils {

    public static void close(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
