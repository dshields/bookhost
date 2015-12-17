package amplitude.model;

import java.util.List;

public interface IAlbum {
    long getAlbumId();

    String getAlbumName();

    void setAlbumName(String name);

    IArtist getArtist();

    String getAlbumYear();

    int getTrackCount();

    List<? extends ISong> getSongs();
}
