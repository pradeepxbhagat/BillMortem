package com.billmartam.pdf.storage;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import com.billmartam.pdf.storage.FileSpecification;
/**
 * Created by pp00344204 on 20/06/17.
 */
public class RecentFileStorage implements Storage {
    private static final String RECENT_FILE_STORAGE_DIR = "./config";
    private static final String RECENT_FILE_STORAGE_FILE = "recent_files.pp";

    public static Storage getStorage() {
        return new RecentFileStorage();
    }

    @Override
    public boolean save(FileSpecification file) {
        Set<FileSpecification> files = read();
        if(files == null){
            files = new HashSet<>();
        }

        ObjectOutputStream outputStream = null;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(getRecentFileStoragePath());
            outputStream = new ObjectOutputStream(fileOutputStream);
            if(files.contains(file)){
                files.remove(file);
            }
            files.add(file);
            outputStream.writeObject(files);
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public Set<FileSpecification> read() {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(getRecentFileStoragePath()));
            return (Set<FileSpecification>) inputStream.readObject();
        } catch (IOException e) {
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getRecentFileStoragePath() {
        File file = new File(RECENT_FILE_STORAGE_DIR);
        if(!file.exists()){
            file.mkdirs();
        }

        return RECENT_FILE_STORAGE_DIR+"/"+RECENT_FILE_STORAGE_FILE;
    }
}
