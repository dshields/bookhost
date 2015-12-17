package amplitude.persistence.hibernate;

import amplitude.model.*;
import amplitude.persistence.hibernate.dao.*;
import amplitude.utils.QueryPager;
import org.hibernate.Query;

import java.util.List;

public class AmplitudeDB implements IAmplitudeDb {

    final private String findbase = "from Artist r inner join r.albums album inner join album.songs songs " +
            "where lower(r.artistName) like :search " +
            "OR lower(album.albumName) like :search " +
            "OR lower(songs.songName) like :search order by r.artistName";
    final private String FIND_ARTISTS = "select distinct r " + findbase;
    final private String COUNT_ARTISTS = "select count(distinct r) " + findbase;

    static public SongDAO getSongDAO() {
        return new SongDAO();
    }

    static public ArtistDAO getArtistDAO() {
        return new ArtistDAO();
    }

    static public AlbumDAO getAlbumDAO() {
        return new AlbumDAO();
    }

    static public PlaylistDAO getPlaylistDAO() {
        return new PlaylistDAO();
    }

    static public TagDAO getTagDAO() {
        return new TagDAO();
    }

    public List<IArtist> findArtists(String searchText) {
        ArtistDAO dao = getArtistDAO();
        Query query = dao.getQuery(FIND_ARTISTS);
        query.setString("search", "%" + searchText.toLowerCase() + "%");
        return query.list();
    }

    public IPager<? extends IArtist> findArtistsPager(String searchText, int pageSize) {
        ArtistDAO dao = getArtistDAO();
        Query query = dao.getQuery(FIND_ARTISTS);
        query.setString("search", "%" + searchText.toLowerCase() + "%");
        Query countQuery = dao.getQuery(COUNT_ARTISTS);
        return new QueryPager<IArtist>(countQuery, query, pageSize);
    }

    public IAlbum getAlbumById(long id) {
        AlbumDAO dao = getAlbumDAO();
        Query query = dao.getQuery("from Album where albumId = ?");
        query.setLong(0, id);
        return (IAlbum) query.uniqueResult();
    }

    public List<? extends IArtist> getAllArtists() {
        ArtistDAO dao = getArtistDAO();
        return dao.findAll();
    }

    public IPager<? extends IArtist> getAllArtistsPager(int pageSize) {

        ArtistDAO dao = getArtistDAO();
        Query query = dao.getQuery("from Artist");
        Query countQuery = dao.getQuery("select count(*) from Artist");
        return new QueryPager<IArtist>(countQuery, query, pageSize);
    }

    public IArtist getArtistById(long id) {
        ArtistDAO dao = getArtistDAO();
        return dao.load(id);
    }

    public ISong getSongById(long id) {
        SongDAO dao = getSongDAO();
        return dao.load(id);
    }

    public IPlaylist loadPlaylist(long id) {

        PlaylistDAO dao = getPlaylistDAO();
        return dao.load(id);
    }

    public void savePlaylist(IPlaylist playlist) {
        PlaylistDAO dao = getPlaylistDAO();
        dao.saveOrUpdate((Playlist) playlist);
    }

    public List<? extends IPlaylist> getAllPlaylists() {
        PlaylistDAO dao = getPlaylistDAO();
        return dao.findAll();
    }

    public IPager<? extends IPlaylist> getAllPlaylistsWithPager(int pageSize) {
        PlaylistDAO dao = getPlaylistDAO();
        Query query = dao.getQuery("from Playlist");
        Query countQuery = dao.getQuery("select count(*) from Playlist");
        return new QueryPager<IPlaylist>(countQuery, query, pageSize);

    }

    public boolean databaseExists() {
        DBUtils utils = new DBUtils();
        return utils.databaseExists();
    }

}
