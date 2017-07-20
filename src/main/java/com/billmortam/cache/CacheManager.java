package com.billmortam.cache;

import java.io.*;

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
            return inputStream.readObject();
        } catch (IOException e) {
            return null;
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public abstract boolean clear();

    protected boolean delete(String filePath){
        File dir = new File(filePath);
        if(dir.exists()){
            File[] files = dir.listFiles();
            for (File file : files){
                file.delete();
            }
            return dir.delete();
        }

        return false;
    }
}
