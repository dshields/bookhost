package amplitude.system;

public interface IAmplitudeProperties {

    /* these db properties need to be moved to an IAmplitudeDBProperties as they are implementation specific */
    public String getDatabaseDriverName();

    public String getDatabaseUser();

    public String getDatabasePassword();

    public String getDatabaseURL();

    public int getDefaultBitRate();

    public void setDefaultBitRate(int newDefaultBitRate);

    public String getLameCommand();

    public void setLameCommand(String newLameCommand);

    public String getSongDirectory();

    public void setSongDirectory(String newSongDirectory);

    public String getInstallDir();

    public void setInstallDir(String installDir);

}