package amplitude.servlets;

import amplitude.resource.Book;
import amplitude.utils.FileProc;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Filters;
import org.jboss.shrinkwrap.api.GenericArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.importer.ExplodedImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;

@RunWith(Arquillian.class)
@RunAsClient
public class WhenAccessingBookTest {

    @ArquillianResource
    URL contextPath;

    @Drone
    ChromeDriver driver;

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        WebArchive war = ShrinkWrap.create(WebArchive.class,"bookhost.war")
                .addPackage(Book.class.getPackage())
                .addPackage(BaseServlet.class.getPackage())
                .addPackage(FileProc.class.getPackage());
        war.merge(ShrinkWrap.create(GenericArchive.class).as(ExplodedImporter.class)
                        .importDirectory("src/main/webapp").as(GenericArchive.class),
                "/", Filters.includeAll());
        System.out.println(war.toString(true));
        return war;
    }

    @Before
    public void setup()
    {
        URL exampleBookUrl = Thread.currentThread().getContextClassLoader().getResource("book1.mp3");
        System.out.println("Book url is: " + exampleBookUrl);
        File exampleBook = new File(exampleBookUrl.getFile());
        System.setProperty("book", exampleBook.getAbsolutePath());
        System.out.println("Book is: " + System.getProperty("book"));
    }

    @After
    public void tearDown() throws SQLException, ClassNotFoundException {
    }

    @Test
    public void userIsShownBookPage() {
        driver.get(contextPath + "/");
        BookPage bookPage = new BookPage(contextPath, driver);
        Assert.assertEquals(contextPath + "book.html", driver.getCurrentUrl());
    }

}
