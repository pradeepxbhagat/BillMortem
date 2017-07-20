package com.billmortam.cache;

import java.io.File;
import java.security.MessageDigest;

/**
 * Created by pp00344204 on 03/07/17.
 */
public class ReportsCacheManager extends CacheManager {
    private static final String RECENT_FILE_STORAGE_DIR = "./config/reports";
    private ReportsCacheManager() {
    }

    @Override
    public boolean save(Object file) {
        TransactionReportCache reportCache = (TransactionReportCache) file;
        return writeCache(reportCache,getReportsCachePath(reportCache.getFileName()));
    }

    private String getReportsCachePath(String path) {
        File file = new File(RECENT_FILE_STORAGE_DIR);
        if (!file.exists()) {
            file.mkdirs();
        }

        return RECENT_FILE_STORAGE_DIR + "/" + MD5(path);
    }

    @Override
    public Object read(Object path) {
        return readCache(getReportsCachePath((String) path));
    }

    @Override
    public boolean clear() {
        return delete(RECENT_FILE_STORAGE_DIR);
    }

    public static CacheManager getManager() {
        return new ReportsCacheManager();
    }

    public String MD5(String md5) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte anArray : array) {
                sb.append(Integer.toHexString((anArray & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return null;
    }
}
