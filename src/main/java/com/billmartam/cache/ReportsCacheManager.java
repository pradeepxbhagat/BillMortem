package com.billmartam.cache;

import java.io.File;

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

        return RECENT_FILE_STORAGE_DIR + "/" + Math.abs(path.hashCode());
    }

    @Override
    public Object read(Object path) {
        return readCache(getReportsCachePath((String) path));
    }

    public static CacheManager getManager() {
        return new ReportsCacheManager();
    }

}
