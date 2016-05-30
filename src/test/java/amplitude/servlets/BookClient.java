package amplitude.servlets;

import amplitude.resource.Book;
import org.apache.cxf.jaxrs.client.WebClient;

import javax.ws.rs.core.MediaType;

/**
 * Created by dorwins on 5/30/16.
 */
public class BookClient {

    String path;
    WebClient client;

    public BookClient(String basePath) {
        path = basePath;
        client = WebClient.create(path);
    }

    public String getBookName() {
        client.reset();
        client.accept("application/json");
        String book = client.path("/book/name").get(String.class);
        return book;
    }

    public Book getBook()
    {
        client.reset();
        client.accept(MediaType.APPLICATION_JSON);
        Book book = client.path("/book").get(Book.class);
        return book;
    }

    public String getBookJson()
    {
        client.reset();
        client.accept(MediaType.APPLICATION_JSON);
        String json = client.path("/book").get(String.class);
        return json;
    }

    public byte[] getBookImage()
    {
        client.reset();
        client.accept(MediaType.MEDIA_TYPE_WILDCARD);
        byte[] image = client.path("/book/image").get(byte[].class);
        return image;
    }
}
