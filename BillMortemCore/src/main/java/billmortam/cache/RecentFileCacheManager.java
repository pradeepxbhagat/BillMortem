package billmortam.cache;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by pp00344204 on 20/06/17.
 */
public class RecentFileCacheManager extends CacheManager {
    private static final String RECENT_FILE_STORAGE_DIR = "./config";
    public static final String RECENT_FILE_STORAGE_FILE = "recent_files.rf";

    public static CacheManager getManager() {
        return new RecentFileCacheManager();
    }

    @Override
    public boolean save(Object file) {
        Set<FileSpecification> files = read(getRecentFileStoragePath(RECENT_FILE_STORAGE_FILE));
        if (files == null) {
            files = new HashSet<>();
        } else if(files.contains(file)){
            files.remove(file);
        }

        files.add((FileSpecification) file);

        return writeCache(files,getRecentFileStoragePath(RECENT_FILE_STORAGE_FILE));
    }

    @Override
    public Set<FileSpecification> read(Object path) {
        return (Set<FileSpecification>) readCache(getRecentFileStoragePath((String) path));
    }

    @Override
    public boolean clear() {
        return false;
    }

    private static String getRecentFileStoragePath(String path) {
        File file = new File(RECENT_FILE_STORAGE_DIR);
        if (!file.exists()) {
            file.mkdirs();
        }

        return RECENT_FILE_STORAGE_DIR + "/" + RECENT_FILE_STORAGE_FILE;
    }
}
