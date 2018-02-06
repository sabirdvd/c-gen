package com.test.common.util;

import java.io.*;

/**
 * Created by shuisheng on 2018/2/6.
 */
public class FileUtils {

    public static String fileEncoding = "utf-8";
    public static String dir = "tmp/";

    //写入内容到文件
    public static void writeFile(File file, String content, String fileEncoding) throws IOException {
        FileOutputStream fos = new FileOutputStream(file, false);
        OutputStreamWriter osw;
        if (fileEncoding == null) {
            osw = new OutputStreamWriter(fos);
        } else {
            osw = new OutputStreamWriter(fos, fileEncoding);
        }

        BufferedWriter bw = new BufferedWriter(osw);
        bw.write(content);
        bw.close();
    }

    public static void writeFile(String filename, String content) {
        try {
            File file = new File(dir + filename);
            writeFile(file, content, fileEncoding);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
