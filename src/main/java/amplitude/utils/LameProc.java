/*
 * (c) Copyright 2002 Dorwin T. Shields, Jr.
 * All Rights Reserved.
 */
package amplitude.utils;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Hashtable;

/**
 * Creation date: (3/5/2001 1:10:33 PM)
 */
public class LameProc {
    static String lameCommand = null;
    String mP3FileName;
    InputStream mP3Stream;
    int bitRate;

    /**
     * LameProc constructor comment.
     */
    public LameProc() {
        super();
    }

    public static java.lang.String getLameCommand() {
        return lameCommand;
    }

    public static void setLameCommand(java.lang.String newLameCommand) {
        lameCommand = newLameCommand;
    }

    public static String insertBitRate(int bitRate, String cmd) {
        int indexOfRepl = cmd.indexOf("%b");
        if (indexOfRepl < 0)
            return cmd;
        String returnCommand = cmd.substring(0, indexOfRepl) + Integer.toString(bitRate) + cmd.substring(indexOfRepl + 2);
        return returnCommand;
    }

    public int getBitRate() {
        return bitRate;
    }

    public void setBitRate(int newBitRate) {
        bitRate = newBitRate;
    }

    public java.lang.String getMP3FileName() {
        return mP3FileName;
    }

    public void setMP3FileName(java.lang.String newMP3FileName) {
        mP3FileName = newMP3FileName;
    }

    public void processMP3Stream(Hashtable args, OutputStream os) throws Exception {
        String cmdline = lameCommand + " " + mP3FileName + " - ";
        if (cmdline.indexOf(";") >= 0 || cmdline.indexOf("|") >= 0 || cmdline.indexOf("`") >= 0 || cmdline.indexOf("(") >= 0 || cmdline.indexOf(")") >= 0) {
            throw new RuntimeException("Security Violation in LameProc: " + cmdline);
        }
        // int bitRate = ((Integer)args.get("bitRate")).intValue();
        cmdline = insertBitRate(bitRate, cmdline);
        System.out.println(cmdline);
        orios.util.runtime.RuntimeHelper.runCommand(cmdline, null, os, null);
    }

}