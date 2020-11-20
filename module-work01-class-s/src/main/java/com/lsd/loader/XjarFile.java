package com.lsd.loader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @description:
 * @author: nhsoft.lsd
 * @create: 2020-11-20 15:42
 */
public class XjarFile {

    private ZipFile zipFile;

    public XjarFile(String path)throws Exception {
        uncompress(path);
    }

    public void uncompress(String inputFile) throws Exception {
        File srcFile = new File(inputFile);
        if (!srcFile.exists()) {
            throw new Exception(srcFile.getPath() + "所指文件不存在");
        }
        zipFile = new ZipFile(srcFile);

    }

    public java.util.Enumeration<? extends java.util.zip.ZipEntry> entries () {
        return zipFile.entries();
    }

    public InputStream getInputStream (ZipEntry entry)throws IOException {
        return zipFile.getInputStream(entry);
    }

    public String getName() {
        return zipFile.getName();
    }


}
