package com.app.chat.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class CommonUtil {

    public static long getCurrentTimeStamp() {
        return LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
    }
}
