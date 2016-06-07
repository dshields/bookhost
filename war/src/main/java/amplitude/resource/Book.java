package amplitude.resource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.id3.ID3v1Tag;
import org.jaudiotagger.tag.images.Artwork;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by dorwins on 2/7/16.
 */

@Path("/book")
public class Book {

    @GET
    @Path("/")
    @Produces("application/json")
    @JsonIgnore
    public Book getBookInfo() {
        Book b = new Book();
        return b;
    }

    @GET
    @Path("name")
    @Produces("application/json")
    @JsonProperty
    public String getBookName() {
        return getBook().getFirstTitle();
    }


    private ID3v1Tag getBook() {
        ID3v1Tag tag = getBookFile().getID3v1Tag();
        return tag;
    }

    @GET
    @Path("image")
    @JsonIgnore
    public Response getImageContent() {
        Response.ResponseBuilder response = Response.ok();
        response.type(getImageMimeType());
        response.entity(getArtwork().getBinaryData());
        return response.build();

    }

    public String getImageMimeType() {
        return getArtwork().getMimeType();
    }

    public byte[] getImage() {
        return getArtwork().getBinaryData();
    }

    public static String getFilename() {
        if (bookFile == null)
            getBookFile();
        return bookFile != null ? bookFile.getFile().getAbsolutePath() : null;
    }

    static private MP3File bookFile = null;
    static private Object lock = new Object();

    static private MP3File getBookFile() {
        if (bookFile == null) {
            synchronized (lock) {
                try {
                    if (bookFile != null)
                        return bookFile;
                    File book = new File(System.getProperty("book"));
                    if (!book.exists()) {
                        throw new RuntimeException("File not found: " + System.getProperty("book"));
                    }
                    bookFile = (MP3File) AudioFileIO.read(book);
                } catch (IOException | CannotReadException | ReadOnlyFileException | TagException | InvalidAudioFrameException ex) {
                    throw new RuntimeException("Error reading file", ex);
                }
            }
        }
        return bookFile;
    }

    static private Artwork artwork;

    private Artwork getArtwork() {
        if (artwork == null) {
            synchronized (lock) {
                if (artwork != null)
                    return artwork;

                List<Artwork> art = getBookFile().getID3v2Tag().getArtworkList();
                if (art.size() <= 0)
                    return null;
                artwork = art.get(0);
            }
        }
        return artwork;
    }

}
