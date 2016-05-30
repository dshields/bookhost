package amplitude.resource;

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
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by dorwins on 2/7/16.
 */

@Path("/book")
@XmlRootElement
public class Book {

    @GET
    @Path("/")
    @Produces("application/json")
    public Response getBookInfo()
    {
        Response.ResponseBuilder response = Response.ok();
        response.entity(new Book());
        return response.build();
    }

    @GET
    @Path("name")
    @Produces("application/json")
    public String getBookName() {
        return getBook().getFirstTitle();
    }


    public ID3v1Tag getBook() {
        ID3v1Tag tag = getBookFile().getID3v1Tag();
        return tag;
    }

    @GET
    @Path("image")
    public Response getImage() {
        List<Artwork> art = getBookFile().getID3v2Tag().getArtworkList();
        if(art.size()<=0) return null;
        Artwork artwork = art.get(0);
        Response.ResponseBuilder response = Response.ok();
        response.type(artwork.getMimeType());
        response.entity(artwork.getBinaryData());
        return response.build();
    }

    public static String getFilename() {
        if (bookFile == null)
            getBookFile();
        return bookFile != null ? bookFile.getFile().getAbsolutePath() : null;
    }


    static MP3File bookFile = null;
    static Object lock = new Object();
    private static MP3File getBookFile() {
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
}
