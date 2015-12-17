package amplitude.model;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public interface ISong {
    InputStream getStream() throws IOException;

    URL getURL();

    int getTrackNumber();

    String getArtistName();

    String getAlbumName();

    String getSongName();

    String getLength();

    String getGenreName();

    IArtist getArtist();

    IAlbum getAlbum();

    long getSongId();
}
