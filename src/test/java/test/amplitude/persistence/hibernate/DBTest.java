package test.amplitude.persistence.hibernate;

import amplitude.model.IArtist;
import amplitude.model.IPager;
import amplitude.model.IPlaylist;
import amplitude.model.ISong;
import amplitude.persistence.hibernate.*;
import amplitude.persistence.hibernate.dao.GenreDAO;
import amplitude.persistence.hibernate.dao.PlaylistDAO;
import amplitude.persistence.hibernate.dao.TagDAO;
import amplitude.persistence.hibernate.dao._RootDAO;
import amplitude.system.AmplitudeProperties;
import amplitude.system.AmplitudeSystem;
import amplitude.system.IAmplitudeProperties;
import amplitude.utils.SongUtils;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Properties;

public class DBTest {
    AmplitudeDB db = new AmplitudeDB();
    IAmplitudeProperties props = AmplitudeProperties.getAmplitudeProperties();

    public static void initializeProperties() {
        String installDir = System.getProperty("java.io.tmpdir") + ".amplitude";
        String songDir = System.getProperty("java.io.tmpdir") + File.separator + "amplitudeTestMusic" + File.separator;


        IAmplitudeProperties props = AmplitudeProperties.getAmplitudeProperties();
        props.setSongDirectory(songDir);
        props.setInstallDir(installDir);
    }

    static Logger getLogger() {
        return Logger.getLogger("Amplitude");
    }

    @org.junit.BeforeClass
    static public void before() throws Exception {
        try {
            getLogger().debug("Setup Started");
            initializeProperties();
            copyFiles();
            DBUtils.initialize(true);
            DBUtils utils = new DBUtils();
            utils.loadDatabase();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @AfterClass
    static public void after() throws Exception {
        DBUtils utils = new DBUtils();
        initializeProperties();
        utils.deleteDatabase();
    }

    static public void copyFiles() {
        try {
            URL testFilesURL = DBTest.class.getResource("/test.properties");
            File testFiles = new File(testFilesURL.getPath()).getParentFile();
            File testMusicDirectory = new File(testFiles.getAbsolutePath() + File.separator + "music" + File.separator);
            if (!testMusicDirectory.exists()) {
                throw new Exception("Test files not found.");
            }
            FileUtils.copyDirectoryToDirectory(testMusicDirectory, new File(AmplitudeProperties.getAmplitudeProperties().getSongDirectory()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Before
    public void setup() {
        initializeProperties();
        _RootDAO.initialize();

    }

    @Test
    public void willNotAddDuplicatesTest() throws Exception {
        getLogger().debug("willNotAddDuplicatesTest");
        DBUtils utils = new DBUtils();
        utils.loadDatabase();
        List<? extends IArtist> artists = db.getAllArtists();
        List<? extends ISong> songs = SongUtils.getAllSongs(artists);
        Assert.assertEquals(3, artists.size());
        Assert.assertEquals(5, songs.size());
    }

    @Test
    public void getAllSongsTest() throws Exception {
        getLogger().debug("getAllSongsTest");
        List<? extends IArtist> artists = db.getAllArtists();

        List<? extends ISong> songs = SongUtils.getAllSongs(artists);
        for (ISong song : songs) {
            System.out.println(song.getURL());
            System.out.println("\t" + song.getArtistName() + " - " + song.getAlbumName() + " - " + song.getSongName());
            System.out.println("\t" + song.getArtist().getArtistId() + " - " + song.getAlbum().getAlbumId() + " - " + song.getSongId());
        }
        Assert.assertEquals(3, artists.size());
        Assert.assertEquals(5, songs.size());
    }

    @Test
    public void pagerTest() throws Exception {
        getLogger().debug("PagerTest");
        IPager<? extends IArtist> pager = db.getAllArtistsPager(10);
        Assert.assertEquals(1, pager.getPageCount());
        Assert.assertEquals(3, pager.getPageItems().size());

        IPager<? extends IArtist> pager2 = db.getAllArtistsPager(1);
        Assert.assertEquals(3, pager2.getPageCount());
        Assert.assertEquals(1, pager2.getPageItems().size());
    }

    @Test
    public void search() throws Exception {
        getLogger().debug("SearchTest");
        List<IArtist> artists = db.findArtists("test");
        Assert.assertEquals(3, artists.size());

        artists = db.findArtists("Test5");
        Assert.assertEquals(1, artists.size());
        Assert.assertEquals(1, artists.get(0).getAlbums().size());
        Assert.assertEquals(1, artists.get(0).getAlbums().get(0).getSongs().size());
        Assert.assertEquals("test5", artists.get(0).getAlbums().get(0).getSongs().get(0).getSongName().toLowerCase());
    }

    @Test
    public void genreTest() throws Exception {
        getLogger().debug("Genre Test");
        GenreDAO dao = new GenreDAO();
        for (Genre genre : dao.findAll()) {
            System.out.println("Genre: " + genre.getGenreId() + "|" + genre.getGenreName());
        }
    }

    @Test
    public void tagTest() throws Exception {
        getLogger().debug("Tag Test");
        Song song = (Song) db.findArtists("Test5").get(0).getAlbums().get(0).getSongs().get(0);
        TagDAO tagDAO = new TagDAO();
        Tag tag = new Tag();
        tag.setTagDescription("test tag");
        tag.setTagName("test tagname");
        tag.save();
        tag.addTosongs(song);

        Assert.assertEquals(1, tagDAO.findAll().size());
        //Assert.assertEquals(1, song.getTags().size());
        tag.getSongs().remove(0);
        tagDAO.save(tag);
        Assert.assertEquals(0, song.getTags().size());
        tagDAO.delete(tag.getTagId());
        Assert.assertEquals(0, tagDAO.findAll().size());
    }

    @Test
    public void playlistTest() throws Exception {
        getLogger().debug("Playlist Test");
        Playlist playlist = new Playlist();
        playlist.setPlaylistName("favorites");
        Song song = (Song) db.findArtists("Test5").get(0).getAlbums().get(0).getSongs().get(0);
        playlist.addSongBySongId(song.getSongId());
        db.savePlaylist(playlist);
        playlist = null;

        List<? extends IPlaylist> playlists = db.getAllPlaylists();
        Assert.assertEquals(1, playlists.size());
        Playlist playlist2 = (Playlist) db.getAllPlaylists().get(0);
        Assert.assertEquals(1, playlist2.getSongs().size());


        PlaylistDAO dao = new PlaylistDAO();
        playlist2.removeSongBySongId(song.getSongId());
        dao.delete(playlist2);
    }

}
