package com.billmartam.cache;

import java.io.Serializable;

/**
 * Created by pp00344204 on 20/06/17.
 */
public class FileSpecification implements Serializable{
    private final String path;
    private final long timeMillis;

    public FileSpecification(String path, long timeMillis) {

        this.path = path;
        this.timeMillis = timeMillis;
    }

    public long getTimeMillis() {
        return timeMillis;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FileSpecification that = (FileSpecification) o;

        return path != null ? path.equals(that.path) : that.path == null;
    }

    @Override
    public int hashCode() {
        return path != null ? path.hashCode() : 0;
    }
}
