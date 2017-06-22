package com.billmartam.pdf;

import org.junit.Assert;
import org.junit.Test;
import com.billmartam.pdf.storage.FileSpecification;
import com.billmartam.pdf.storage.RecentFileStorage;
import com.billmartam.pdf.storage.Storage;

import java.util.Set;

/**
 * Created by pp00344204 on 20/06/17.
 */
public class RecentFileStorageTest {

    @Test
    public void test_paths_storing(){
        FileSpecification file = new FileSpecification("/pradeep/pankaj/this_is_a_path",System.currentTimeMillis());
        Storage storage = RecentFileStorage.getStorage();
        boolean result = storage.save(file);
        boolean actual = true;
        Assert.assertEquals(actual,result);
    }

    @Test
    public void test_multiple_paths_storing(){
        FileSpecification file = new FileSpecification("/pradeep/pankaj/this_is_a_path",System.currentTimeMillis());
        FileSpecification file1 = new FileSpecification("/pradeep/pankaj/this_is_a_path1",System.currentTimeMillis());
        Storage storage = RecentFileStorage.getStorage();
        storage.save(file);
        boolean result = storage.save(file1);
        boolean actual = true;
        Assert.assertEquals(actual,result);
    }
    @Test
    public void test_duplicate_paths_storing(){
        FileSpecification file = new FileSpecification("/pradeep/pankaj/this_is_a_path",System.currentTimeMillis());
        FileSpecification file1 = new FileSpecification("/pradeep/pankaj/this_is_a_path",System.currentTimeMillis());
        Storage storage = RecentFileStorage.getStorage();
        storage.save(file);
        storage.save(file1);

        Set<FileSpecification> result = storage.read();
        Assert.assertEquals(2,result.size());
    }

    @Test
    public void test_paths_reading_null_check(){
        Storage storage = RecentFileStorage.getStorage();
        Set<FileSpecification> result = storage.read();
//        FileSpecification actual = new FileSpecification("/pradeep/pankaj/this_is_a_path",System.currentTimeMillis());
        Assert.assertNull(result);
    }

    @Test
    public void test_paths_reading_path(){
        Storage storage = RecentFileStorage.getStorage();
        Set<FileSpecification> result = storage.read();
        FileSpecification actual = new FileSpecification("/pradeep/pankaj/this_is_a_path",System.currentTimeMillis());
        Assert.assertEquals(true,result.contains(actual));
    }
}
