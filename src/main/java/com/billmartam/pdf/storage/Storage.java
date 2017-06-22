package com.billmartam.pdf.storage;

import java.util.Set;

/**
 * Created by pp00344204 on 20/06/17.
 */
public interface Storage {
    boolean save(FileSpecification file);

    Set<FileSpecification> read();
}
