package com.billmartam.cache;

import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

/**
 * Created by pp00344204 on 20/06/17.
 */
public class RecentFileCacheManagerTest {

    @Test
    public void test_paths_storing(){
        FileSpecification file = new FileSpecification("/pradeep/pankaj/this_is_a_path",System.currentTimeMillis());
        CacheManager storage = RecentFileCacheManager.getManager();
        boolean result = storage.save(file);
        boolean actual = true;
        Assert.assertEquals(actual,result);
    }

    @Test
    public void test_multiple_paths_storing(){
        FileSpecification file = new FileSpecification("/pradeep/pankaj/this_is_a_path",System.currentTimeMillis());
        FileSpecification file1 = new FileSpecification("/pradeep/pankaj/this_is_a_path1",System.currentTimeMillis());
        CacheManager storage = RecentFileCacheManager.getManager();
        storage.save(file);
        boolean result = storage.save(file1);
        boolean actual = true;
        Assert.assertEquals(actual,result);
    }
    @Test
    public void test_duplicate_paths_storing(){
        FileSpecification file = new FileSpecification("/pradeep/pankaj/this_is_a_path",System.currentTimeMillis());
        FileSpecification file1 = new FileSpecification("/pradeep/pankaj/this_is_a_path",System.currentTimeMillis());
        CacheManager storage = RecentFileCacheManager.getManager();
        storage.save(file);
        storage.save(file1);

        Set<FileSpecification> result = (Set<FileSpecification>) storage.read(RecentFileCacheManager.RECENT_FILE_STORAGE_FILE);
        Assert.assertEquals(2,result.size());
    }


    @Test
    public void test_paths_reading_path(){
        CacheManager storage = RecentFileCacheManager.getManager();
        Set<Object> result = (Set<Object>) storage.read(RecentFileCacheManager.RECENT_FILE_STORAGE_FILE);
        FileSpecification actual = new FileSpecification("/pradeep/pankaj/this_is_a_path",System.currentTimeMillis());
        Assert.assertEquals(true,result.contains(actual));
    }

}
