package amplitude.model;

import java.util.List;

public interface IPlaylist {
    long getPlaylistId();

    String getPlaylistName();

    void setPlaylistName(String name);

    List<? extends ISong> getSongs();

    void setSongsBySongIds(List<Long> songIds);

    void addSongBySongId(long songId);

    void removeSongBySongId(long songId);
}
