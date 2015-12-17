/*
 * (c) Copyright 2002 Dorwin T. Shields, Jr.
 * All Rights Reserved.
 */
package orios.util.runtime;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Insert the type's description here. Creation date: (9/22/2001 8:21:35 PM)
 *
 * @author:
 */
public class RuntimeReader extends Thread {
    static int inputBufferSize = 8196;
    static int outputBufferSize = 8196;
    InputStream is;
    OutputStream os;
    Process p;

    /**
     * RuntimeReader constructor comment.
     */
    public RuntimeReader(Process p, InputStream isIn) {
        super();
        this.p = p;
        is = new BufferedInputStream(isIn, inputBufferSize);
    }

    /**
     * RuntimeReader constructor comment.
     */
    public RuntimeReader(Process p, InputStream isIn, OutputStream osIn) {
        super();
        this.p = p;
        is = new BufferedInputStream(isIn, inputBufferSize);
        os = osIn;
    }

    /**
     * Insert the method's description here. Creation date: (9/23/2001 2:08:19
     * AM)
     *
     * @return int
     */
    public static int getInputBufferSize() {
        return inputBufferSize;
    }

    /**
     * Insert the method's description here. Creation date: (9/23/2001 2:08:19
     * AM)
     *
     * @param newInputBufferSize int
     */
    public static void setInputBufferSize(int newInputBufferSize) {
        inputBufferSize = newInputBufferSize;
    }

    /**
     * Insert the method's description here. Creation date: (9/23/2001 2:08:19
     * AM)
     *
     * @return int
     */
    public static int getOutputBufferSize() {
        return outputBufferSize;
    }

    /**
     * Insert the method's description here. Creation date: (9/23/2001 2:08:19
     * AM)
     *
     * @param newOutputBufferSize int
     */
    public static void setOutputBufferSize(int newOutputBufferSize) {
        outputBufferSize = newOutputBufferSize;
    }

    public void eat() {
        int ch = -1;
        try {
            while ((ch = is.read()) != -1) {
            }
        } catch (Exception ex) {
            System.out.println("Error in eat: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException ie) {
            }
        }
    }

    public void run() {
        if (os == null) {
            eat();
        } else {
            tie();
        }
    }

    public void tie() {
        int ch = -1;
        try {
            while ((ch = is.read()) != -1) {
                os.write(ch);
            }
        } catch (Exception ex) {
            System.out.println("Error in tie: " + ex.getMessage());
            if (ex.getMessage() == null) {
                ex.printStackTrace();
            }
            if (is == null) {
                System.out.println("input stream is null");
            }
            if (os == null) {
                System.out.println("output stream is null");
            }
            p.destroy();
            // eat();
        } finally {
            try {
                is.close();
            } catch (IOException ie) {
            }
        }
    }
}
