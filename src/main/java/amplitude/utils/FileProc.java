/*
 * (c) Copyright 2002 Dorwin T. Shields, Jr.
 * All Rights Reserved.
 */
package amplitude.utils;

import java.io.*;

/**
 * Creation date: (3/5/2001 1:10:33 PM) Pass the file through with no
 * re-encoding
 */
public class FileProc {
    String mP3FileName;

    InputStream mP3Stream;

    /**
     * FileProc constructor comment.
     */
    public FileProc() {
        super();
    }

    public java.lang.String getMP3FileName() {
        return mP3FileName;
    }

    public void setMP3FileName(java.lang.String newMP3FileName) {
        mP3FileName = newMP3FileName;
    }

    public void processMP3Stream(OutputStream os) throws Exception {
        File f = new File(mP3FileName);
        if (!f.exists()) {
            return; // Fail silently
        }
        BufferedInputStream is = new BufferedInputStream(new FileInputStream(f));
        int ch = -1;
        while ((ch = is.read()) != -1) {
            os.write(ch);
        }
    }

}