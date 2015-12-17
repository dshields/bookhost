package amplitude.model;

import java.util.List;

public interface IArtist {
    long getArtistId();

    String getArtistName();

    void setArtistName(String name);

    int getTrackCount();

    int getAlbumCount();

    List<? extends IAlbum> getAlbums();

    List<? extends ISong> getSongs();
}
