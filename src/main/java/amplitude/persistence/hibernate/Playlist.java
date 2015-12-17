package amplitude.persistence.hibernate;

import amplitude.model.IPlaylist;
import amplitude.model.ISong;
import amplitude.persistence.hibernate.base.BasePlaylist;
import amplitude.persistence.hibernate.dao.PlaylistDAO;
import amplitude.system.AmplitudeSystem;

import java.util.ArrayList;
import java.util.List;


public class Playlist extends BasePlaylist implements IPlaylist {
    private static final long serialVersionUID = 1L;

    /*[CONSTRUCTOR MARKER BEGIN]*/
    public Playlist() {
        super();
    }

    /**
     * Constructor for primary key
     */
    public Playlist(long playlistId) {
        super(playlistId);
    }
    /*[CONSTRUCTOR MARKER END]*/

    public void addSongBySongId(long songId) {
        if (getSongs() == null) {
            setSongs(new ArrayList<ISong>());
        }
        getSongs().add(AmplitudeSystem.getAmplitudeDB().getSongById(songId));
    }

    public void removeSongBySongId(long songId) {
        ISong song = AmplitudeSystem.getAmplitudeDB().getSongById(songId);
        getSongs().remove(song);

    }

    public void setSongsBySongIds(List<Long> songIds) {
        for (long songId : songIds) {
            addSongBySongId(songId);
        }

    }

    public PlaylistDAO getPlaylistDAO() {
        return AmplitudeDB.getPlaylistDAO();
    }

    public void save() {
        getPlaylistDAO().save(this);
    }

    public void saveOrUpdate() {
        getPlaylistDAO().saveOrUpdate(this);
    }

    public void delete() {
        getPlaylistDAO().delete(this);
    }


}