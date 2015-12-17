/*
 * (c) Copyright 2002 Dorwin T. Shields, Jr.
 * All Rights Reserved.
 */
package orios.util.runtime;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Insert the type's description here. Creation date: (9/22/2001 8:21:35 PM)
 *
 * @author:
 */
public class RuntimeHelper {
    public static void runCommand(String command, InputStream inputSource, OutputStream standardOutputSink, OutputStream errorSink) throws Exception {

        Process p = Runtime.getRuntime().exec(command);
        RuntimeReader outputReader = new RuntimeReader(p, p.getInputStream(), standardOutputSink);
        RuntimeReader errorReader = new RuntimeReader(p, p.getErrorStream(), errorSink);
        outputReader.start();
        errorReader.start();
        p.waitFor();
        outputReader.join();
        errorReader.join();
    }
}
