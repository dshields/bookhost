package amplitude.utils;

import amplitude.model.IAlbum;
import amplitude.model.IArtist;
import amplitude.model.ISong;

import java.util.ArrayList;
import java.util.List;

public class SongUtils {
    static public List<? extends ISong> getSongsByArtist(IArtist artist) {
        List<ISong> songs = new ArrayList<ISong>();
        for (IAlbum album : artist.getAlbums()) {
            songs.addAll(album.getSongs());
        }
        return songs;
    }

    static public List<? extends ISong> getAllSongs(List<? extends IArtist> artistList) {
        List<ISong> songs = new ArrayList<ISong>();
        for (IArtist artist : artistList) {
            if (artist.getAlbums() != null) {
                for (IAlbum album : artist.getAlbums()) {
                    songs.addAll(album.getSongs());
                }
            }
        }
        return songs;
    }
}
