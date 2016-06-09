package com.rosejames.picdemo.config;

import java.io.File;

/**
 * Created by rosejames on 16/6/7.
 */
public class FilePath {
    public static String DATA_DATA_ROOT;
    public static String SDCARD_ROOT;

    public static String SDCARD_SAVE_PIC_OBJECT;

    public static void init() {
        DATA_DATA_ROOT = "/data/data/" + PicDemoApplication.getApplication().getPackageName();
        SDCARD_ROOT = "/sdcard/" + PicDemoApplication.getApplication().getPackageName();

        SDCARD_SAVE_PIC_OBJECT = SDCARD_ROOT + "/pic_object.temp";

        makeRoot(DATA_DATA_ROOT);
        makeRoot(SDCARD_ROOT);
    }

    private static void makeRoot(String dir) {
        File file = new File(dir);
        if (!file.exists()) {
            file.mkdirs();
        }
    }
}
