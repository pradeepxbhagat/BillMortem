package com.billmartam.cache;

import java.io.*;
import java.util.Set;

/**
 * Created by pp00344204 on 20/06/17.
 */
public abstract class CacheManager {
    public abstract boolean save(Object file);
    public abstract Object read(Object path);

    protected boolean writeCache(Object files, String path) {
        ObjectOutputStream outputStream = null;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            outputStream = new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(files);
            return true;
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
    }


    protected Object readCache(String path){
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(path));
            return (Object) inputStream.readObject();
        } catch (IOException e) {
            return null;
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

}
