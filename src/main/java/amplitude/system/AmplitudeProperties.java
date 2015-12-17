/*
 * (c) Copyright 2002 Dorwin T. Shields, Jr.
 * All Rights Reserved.
 */
package amplitude.system;

/**
 * Creation date: (12/1/2001 9:30:19 PM)
 */

import amplitude.utils.LameProc;

import java.io.File;
import java.util.prefs.Preferences;

public class AmplitudeProperties implements IAmplitudeProperties {
    final static String SONG_DIR = "mp3home";
    final static String LAME_COMMAND = "lame.command";
    final static String INPUT_BUFFERSIZE = "input.buffersize";
    final static String OUTPUT_BUFFERSIZE = "output.buffersize";
    final static String DEFAULT_BITRATE = "defaultBitRate";
    final static String INSTALL_DIR = "installDir";
    static AmplitudeProperties singleton;
    String databaseURL = null;
    int defaultBitRate;
    String lameCommand;
    String songDirectory;
    Preferences prefs = Preferences.userNodeForPackage(AmplitudeProperties.class);
    String installDir;

    private AmplitudeProperties() {

    }

    static synchronized public IAmplitudeProperties getAmplitudeProperties() {
        if (singleton == null) {
            singleton = new AmplitudeProperties();
            singleton.init();
        }
        return singleton;
    }
    public int getDefaultBitRate() {
        return defaultBitRate;
    }

    public void setDefaultBitRate(int newDefaultBitRate) {
        defaultBitRate = newDefaultBitRate;
        prefs.putInt(DEFAULT_BITRATE, defaultBitRate);
    }

    public java.lang.String getLameCommand() {
        return lameCommand;
    }

    public void setLameCommand(java.lang.String newLameCommand) {
        lameCommand = newLameCommand;
        prefs.put(LAME_COMMAND, lameCommand);
    }

    public java.lang.String getSongDirectory() {
        return songDirectory;
    }

    public void setSongDirectory(java.lang.String newSongDirectory) {
        songDirectory = newSongDirectory;
        prefs.put(SONG_DIR, songDirectory);
    }

    private void init() {
        try {

            orios.util.runtime.RuntimeReader.setInputBufferSize(prefs.getInt(INPUT_BUFFERSIZE, 8196));
            orios.util.runtime.RuntimeReader.setInputBufferSize(prefs.getInt(OUTPUT_BUFFERSIZE, 8196));
            defaultBitRate = prefs.getInt(DEFAULT_BITRATE, 96);
            lameCommand = prefs.get(LAME_COMMAND, "");
            LameProc.setLameCommand(lameCommand);

            songDirectory = prefs.get(SONG_DIR, System.getProperty("user.home") + File.separator + "My Music" + File.separator);
            installDir = prefs.get(INSTALL_DIR, System.getProperty("user.home") + File.separator + ".amplitude" + File.separator);
        } catch (Exception ex) {
            System.out.println("Unable to read Amplitude  preferences: " + ex);
            ex.printStackTrace(System.out);
        }
    }

    public String getInstallDir() {
        return installDir;
    }

    public void setInstallDir(String installDir) {
        this.installDir = installDir;
        prefs.put(INSTALL_DIR, installDir);
    }

    public String getDatabaseUser() {
        return "sa";
    }

    public String getDatabasePassword() {
        return "";
    }

    public String getDatabaseDriverName() {
        return "org.h2.Driver";
    }

    public String getDatabaseURL() {
        if (databaseURL != null)
            return databaseURL;
        return "jdbc:h2:file:" + getInstallDir() + (getInstallDir().endsWith(File.separator) ? "" : File.separator ) + "amplitude";
    }

}
