package amplitude.resource;

import de.ueberdosis.mp3info.ID3Reader;
import de.ueberdosis.mp3info.ID3Tag;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by dorwins on 2/7/16.
 */

@Path("/book")
public class Book {

    @GET
    @Path("name")
    @Produces ( "application/json")
    public String getBookName() {
        return getBook().getTitle();
    }

    public ID3Tag getBook() {
        try {
            ID3Tag tag = ID3Reader.readTag(new RandomAccessFile(getBookFile(), "r"));
            return tag;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static File getBookFile() {
        File file = new File(System.getProperty("book"));
        if (!file.exists()) {
            throw new RuntimeException("Book not found: " + System.getProperty("book"));
        }
        return file;
    }
}
