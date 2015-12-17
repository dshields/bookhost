package amplitude.model;

import java.util.List;

public interface IAmplitudeDb {
    ISong getSongById(long songId);

    IAlbum getAlbumById(long albumId);

    IArtist getArtistById(long artistId);

    List<? extends IArtist> findArtists(String searchText);

    IPager<? extends IArtist> findArtistsPager(String searchText, int pageSize);

    List<? extends IArtist> getAllArtists();

    IPager<? extends IArtist> getAllArtistsPager(int pageSize);

    IPlaylist loadPlaylist(long id);

    void savePlaylist(IPlaylist playlist);

    List<? extends IPlaylist> getAllPlaylists();

    IPager<? extends IPlaylist> getAllPlaylistsWithPager(int pageSize);

    boolean databaseExists();

}
