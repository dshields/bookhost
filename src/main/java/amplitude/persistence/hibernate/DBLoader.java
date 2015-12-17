package amplitude.persistence.hibernate;

import amplitude.persistence.hibernate.dao.AlbumDAO;
import amplitude.persistence.hibernate.dao.ArtistDAO;
import amplitude.persistence.hibernate.dao.GenreDAO;
import amplitude.persistence.hibernate.dao.SongDAO;
import de.ueberdosis.mp3info.ID3Reader;
import de.ueberdosis.mp3info.ID3Tag;
import org.apache.log4j.Logger;
import org.hibernate.Query;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.SQLException;
import java.util.List;

public class DBLoader {
    ArtistDAO artistDAO = new ArtistDAO();
    AlbumDAO albumDAO = new AlbumDAO();
    SongDAO songDAO = new SongDAO();
    GenreDAO genreDAO = new GenreDAO();
    Artist artist;
    Album album;
    Song song;
    Logger logger = Logger.getLogger("Amplitude");

    private boolean songExists(Path song) throws SQLException, ClassNotFoundException, MalformedURLException {
        String path = song.toFile().toURL().toString();
        String queryString = "from Song where URL = ?";
        Query query = songDAO.getQuery(queryString);
        query.setString(0, path);
        Boolean exists = query.list().size() > 0;
        return exists;
    }

    public void addFromDirectory(File path) throws Exception {
        logger.error("Loading files from " + path.getAbsolutePath());
        Files.walkFileTree(Paths.get(path.getAbsolutePath()), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                addFile(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return null;
            }
        });
        ArtistDAO.closeCurrentSession();
    }

    private void addFile(Path song) {
        try {
            if (songExists(song))
                return;
            RandomAccessFile f = null;
            try {
                f = new RandomAccessFile(song.toFile(), "r");
                ID3Tag tag = ID3Reader.readTag(f);
                if (tag==null) return;
                boolean tagIsEmpty =
                        tag.getArtist().length()==0
                                && tag.getAlbum().length()==0
                                && tag.getTitle().length()==0;
                if (!tag.isValidTag() || tagIsEmpty)
                    return;
                logger.info("Adding " + song);
                artist = findOrCreateArtistByName(tag.getArtist());
                album = findOrCreateAlbum(artist, tag.getAlbum());
                this.song = createSong(artist, album, tag, song);
                artistDAO.saveOrUpdate(artist);
                albumDAO.saveOrUpdate(album);
                songDAO.saveOrUpdate(this.song);
            } catch (Exception e) {
                logger.error("Unable to load file " + song, e);
            } finally {
                if (f != null) {
                    f.close();
                }
            }
        }
        catch(Exception ex)
        {
            throw new RuntimeException(ex);
        }
    }


    Artist findOrCreateArtistByName(String name) throws Exception {
        if (artist != null && artist.getArtistName().equals(name))
            return artist;
        Query q = artistDAO.getQuery("from Artist where artistName = ?");
        q.setString(0, name);
        List<Artist> artists = q.list();
        if (artists.size() > 0)
            return artists.get(0);
        artist = new Artist();
        artist.setArtistName(name);
        return artist;
    }

    Album findOrCreateAlbum(Artist artist, String albumName) throws Exception {
        if (album != null && album.getAlbumName().equals(albumName) && album.getArtist().getArtistName().equals(artist.getArtistName()))
            return album;
        Query q = albumDAO.getQuery("from Album where albumName = ?");
        q.setString(0, albumName);
        List<Album> albums = q.list();
        if (albums.size() > 0)
            return albums.get(0);
        album = new Album();
        album.setArtist(artist);
        album.setAlbumName(albumName);
        return album;
    }

    Song createSong(Artist artist, Album album, ID3Tag tag, Path songFile) throws Exception {
        Song song = new Song();
        song.setAlbum(album);
        song.setFile(songFile);
        song.setGenre(getOrCreateGenre(tag.getGenreS()));
        song.setLength("0");
        song.setSongName(tag.getTitle());
        song.setTrackNumber(tag.getTrack());
        return song;
    }

    Genre getOrCreateGenre(String genreName) {
        Query q = genreDAO.getQuery("from Genre where genreName = ?");
        q.setString(0, genreName);
        List<Genre> genres = q.list();
        if (genres.size() > 0)
            return genres.get(0);
        Genre genre = new Genre();
        genre.setGenreName(genreName);
        genreDAO.save(genre);
        return genre;

    }
}
