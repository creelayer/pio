package com.home.pio.component.storage;

import java.io.FileNotFoundException;
import java.io.InputStream;

public interface FileResource {
    public String getFilename();
    public String getPrefix();
    public String getMimeType();
    public InputStream getInputStream() throws FileNotFoundException;
}
