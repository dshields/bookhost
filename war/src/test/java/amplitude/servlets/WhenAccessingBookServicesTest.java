package amplitude.servlets;

import amplitude.resource.Book;
import amplitude.utils.FileProc;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
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

import java.io.File;
import java.net.URL;
import java.sql.SQLException;


@RunWith(Arquillian.class)
@RunAsClient
public class WhenAccessingBookServicesTest {

    @ArquillianResource
    URL contextPath;

    BookClient bookClient;

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        WebArchive war = ShrinkWrap.create(WebArchive.class, "bookhost.war").addPackage(Book.class.getPackage()).addPackage(BaseServlet.class.getPackage()).addPackage(FileProc.class.getPackage());
        war.merge(ShrinkWrap.create(GenericArchive.class).as(ExplodedImporter.class).importDirectory("src/main/webapp").as(GenericArchive.class), "/", Filters.includeAll());
        System.out.println(war.toString(true));
        return war;
    }

    @Before
    public void setup() {
        URL exampleBookUrl = Thread.currentThread().getContextClassLoader().getResource("book1.mp3");
        System.out.println("Book url is: " + exampleBookUrl);
        File exampleBook = new File(exampleBookUrl.getFile());
        System.setProperty("book", exampleBook.getAbsolutePath());
        System.out.println("Book is: " + System.getProperty("book"));
        bookClient = new BookClient(contextPath + "");

    }

    @After
    public void tearDown() throws SQLException, ClassNotFoundException {
    }

    @Test
    public void bookNameIsCorrect() {
        String book = bookClient.getBookName();
        Assert.assertFalse("Test Book 2".equals(book));
        Assert.assertEquals("Test Book 1", book);
    }

    @Test
    public void bookDataIsCorrect() {
        String json = bookClient.getBookJson();
        String expected = "{\"bookName\":\"Test Book 1\",\"imageMimeType\":\"image/png\",\"image\":\"iVBORw0KGgoAAAANSUhEUgAAAoAAAAHgCAIAAAC6s0uzAAAAAXNSR0IArs4c6QAAAAlwSFlzAAALEwAACxMBAJqcGAAAA6ppVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IlhNUCBDb3JlIDUuNC4wIj4KICAgPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4KICAgICAgPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIKICAgICAgICAgICAgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIgogICAgICAgICAgICB4bWxuczp0aWZmPSJodHRwOi8vbnMuYWRvYmUuY29tL3RpZmYvMS4wLyIKICAgICAgICAgICAgeG1sbnM6ZXhpZj0iaHR0cDovL25zLmFkb2JlLmNvbS9leGlmLzEuMC8iPgogICAgICAgICA8eG1wOk1vZGlmeURhdGU+MjAxNi0wMi0wN1QxODowMjoyNDwveG1wOk1vZGlmeURhdGU+CiAgICAgICAgIDx4bXA6Q3JlYXRvclRvb2w+UGl4ZWxtYXRvciAzLjQuMjwveG1wOkNyZWF0b3JUb29sPgogICAgICAgICA8dGlmZjpPcmllbnRhdGlvbj4xPC90aWZmOk9yaWVudGF0aW9uPgogICAgICAgICA8dGlmZjpDb21wcmVzc2lvbj41PC90aWZmOkNvbXByZXNzaW9uPgogICAgICAgICA8dGlmZjpSZXNvbHV0aW9uVW5pdD4yPC90aWZmOlJlc29sdXRpb25Vbml0PgogICAgICAgICA8dGlmZjpZUmVzb2x1dGlvbj43MjwvdGlmZjpZUmVzb2x1dGlvbj4KICAgICAgICAgPHRpZmY6WFJlc29sdXRpb24+NzI8L3RpZmY6WFJlc29sdXRpb24+CiAgICAgICAgIDxleGlmOlBpeGVsWERpbWVuc2lvbj42NDA8L2V4aWY6UGl4ZWxYRGltZW5zaW9uPgogICAgICAgICA8ZXhpZjpDb2xvclNwYWNlPjE8L2V4aWY6Q29sb3JTcGFj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18xp8QRYvkCRGWieUrWu6t///veDDz44kj+9qOq73/1u+PhHJOcYQ6CECCDACVwszbnVWFx44Pbt24cbyzJcrrRiflLzsMJX+FKHPtKIfVVWoKhKoxD7eunFI488svfee0dyri+gWpEm0tBOJP8YQ6AkCCDACVymqN/9owpweLdPJ6/HOouPQC/M0dKGgXHDRXRLh6DYkkneU/RaSS0WvdNOO0Xy/O677w4ePDjSamiR/GMMgdpPAAFO4BpFHfht165dpFrqT6EW7w0skogAq88UPgYQSUQ3O2tQbAakQIfbbbedvlHtuOOOkfy//PLLejfJ2rVrI5XCGAJ1hgACnMClvOWWWyJFjdoDlvMjjjgiMMSbb745Z86cQON8mY0bNy7Qle4yRr3FuJlnUGwGpECHnTp1irFY9MSJE0888UTNiihQrXALgdpMAAEu9tWZOnVqpDcMqn4xBFiLHoSf2AMPPBBunLul7vyFv2FC8tmlS5dcgoIiF3qRyvbp0+exxx5r3rx5pFL6NnbWWWdFKoIxBOoGAQS42Ndx7NixUUPus88+UYto3fxDDz00sJTWCCzm2wj0/KgWuA6s2/nnnx9oWZ0ZKKojU4h0LX4SY7HoP/3pT7/+9a8LUR98QqA2E0CAi3p1tKqzXg4TKaRehB51eov3Hy5dH3300dVXXx2pVrGN33nnHf21DSy+77775jIDKx0FFGkURdjRN7+//vWvUZ+du+yyy/RUcRGqRwgI1B4CCHDxroWe+dFC9uGdP1+z8FuYm52Jbp1qBcfNEqs7vOGGG6LOza7OVfZ0DQiHExg1alR2b4G5oAgElS+z4447LsarF7S85f3335+vOuAHArWfAAJcpGukG59afEBPAEeN973vfS9qkbR9uIB9+eWXF154YbpggXb0NlnN0wl0rlu/WrYw0LhGM1DUiCi/Brqte8kll0TyqalYJ510kqZlRSqFMQRKlwACXIxrt3DhQj3yGOnte75aegXQt771rdhVVEckfAmLe++99+abb44dq8aCWntSa/HXaJY2OOeccyK9MypdMOMOKDJiKWiiRpXPOOOMSCH0SJIeTNLjSZFKYQyBEiWAABf2wqlneeWVV/bu3Tve35TTTjstFxHSKkWRXht35plnSoYLQURPOh1yyCHhKx/16NFD557HmoAijzDDXf3xj38cNmxYuL0stTSHvq0mtUBbpKpiDIFcCegdYfVh0+2lcFJ6UU/uTDSepuUmunbtGh53M8sddthBk5Nzr8mPfvSjzTxnOWzUqJGeJMk9aFUP77//vh4SzRJ0syyJpZ7UquohX/ugEMnwkZjDDz88d/Jr1qzRd6/NLnGNh2owGjfKPXp98xB+I+n222+vb3Bq4fnSA67xT0EEA11g/dXQYxgjR46UfA4dOvS9996LUP7rptddd50W+ft6WpwjPfikLnhgSS1Q9f3vf18zaDZs2BBYJLuZ5FxLWUWa4XXttdfuueee2d3GywVFPG65lNLXqYcffvib3/xmJCdqMJozodc2RCqFMQRKi0DD0qpucWqrN6b5gV8JqgRJs3a1+R196jbVF198odWMly5dqk9tGlmV0GqUdd68eRpzzksl999/f922zIurZs2aaWBZD2iqLxLicPXq1eeee67ecnPrrbfuscceIUUy2ui7iO7jahggY251iZp0plLV5eaYDoocAcYrru+R/ntYpIFlvbJQLy7Umi1RV/aIV0lKQaD4BBDgDMw7dOiQIbWISdIJ3TzLY8Ddd9/9mmuukayG+3zllVe0AIjeuH7yySdHfdeN5ltpeaMxY8asXLkyPKIstZjwX/7yl0hFohqDIiqxvNhrsWhNgNdT3fpaFu7wxRdf1HjMP/7xD90ZCS9V0pZz587NZR7466+/Hnj6zzzzjLoTgcZbmukJhfAF57csTkqKQC0cFi9ElSLdA062cUh9J0+eXAgIuqUX79Q6duwogPoXq65/dRVT1pQpUy644ALNn4oXRaMOzz33XHX+85ten1EU+R5w1QunTq0m9kdtHscff7xmVFT1U4f377vvvqh8ErHXF/Q6fBWKdmplRYuUbKBSEeCmTZtOmjSpQKzUH9WYXo7/XLfaais9oas1qo488kgNF+veXufOnZWYo9smTZpoEYYCnfiWbuszigQFWBdCndoYQ8r697vlRayTKQhwnbys1Z0UApyjcOSzuNRXd7yqu1R5SdegkyaI5bPS+fClXtHzzz+flxMMd1JvUSQrwLpAGovWzKyoDUdLiIdf3NK1RIBL99rFqDmzoKP+HSiUvUZu9UbVgw46qFABnN+GDRvecccdF110UUGjRHLevXv3adOm5bLeSKRwaWNQpFEUeWfQoEFaLFovmowU9+KLL476Hs9I/jGGQPEJRPs3UPz6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IIAAJwCdkBCAAAQgAAEEmDYAAQhAAAIQSIAAApwAdEJCAAIQgAAEEGDaAAQgAAEIQCABAghwAtAJCQEIQAACEECAaQMQgAAEIACBBAggwAlAJyQEIAABCEAAAaYNQAACEIAABBIggAAnAJ2QEIAABCAAAQSYNgABCEAAAhBIgAACnAB0QkIAAhCAAAQQYNoABCAAAQhAIAECCHAC0AkJAQhAAAIQQIBpAxCAAAQgAIEECCDACUAnJAQgAAEIQAABpg1AAAIQgAAEEiCAACcAnZAQgAAEIAABBJg2AAEIQAACEEiAAAKcAHRCQgACEIAABBBg2gAEIAABCEAgAQIIcALQCQkBCEAAAhBAgGkDEIAABCAAgQQIIMAJQCckBCAAAQhAAAGmDUAAAhCAAAQSIIAAJwCdkBCAAAQgAAEEmDYAAQhAAAIQSIAAApwAdEJCAAIQgAAEEGDaAAQgAAEIQCABAghwAtAJCQEIQAACEECAaQMQgAAEIACBBAggwAlAJyQEIAABCEAAAaYNQAACEIAABBIggAAnAJ2QEIAABCAAAQSYNgABCEAAAhBIgAACnAB0QkIAAhCAAAQQYNoABCAAAQhAIAECCHAC0AkJAQhAAAIQQIBpAxCAAAQgAIEECCDACUAnJAQgAAEIQAABpg1AAAIQgAAEEiCAACcAnZAQgAAEIAABBJg2AAEIQAACEEiAAAKcAHRCQgACEIAABBBg2gAEIAABCEAgAQIIcALQCQkBCEAAAhBAgGkDEIAABCAAgQQIIMAJQCckBCAAAQhAAAGmDUAAAhCAAAQSIIAAJwCdkBCAAAQgAAEEmDYAAQhAAAIQSIAAApwAdEJCAAIQgAAEEGDaAAQgAAEIQCABAghwAtAJCQEIQAACEECAaQMQgAAEIACBBAggwAlAJyQEIAABCEAAAaYNQAACEIAABBIggAAnAJ2QEIAABCAAAQSYNgABCEAAAhBIgAACnAB0QkIAAhCAAAQQYNoABCAAAQhAIAECCHAC0AkJAQhAAAIQQIBpAxCAAAQgAIEECCDACUAnJAQgAAEIQAABpg1AAAIQgAAEEiCAACcAnZAQgAAEIAABBJg2AAEIQAACEEiAAAKcAHRCQgACEIAABBBg2gAEIAABCEAgAQIIcALQCQkBCEAAAhBAgGkDEIAABCAAgQQIIMAJQCckBCAAAQhAAAGmDUAAAhCAAAQSIIAAJwCdkBCAAAQgAAEEmDYAAQhAAAIQSIAAApwAdEJCAAIQgAAEEGDaAAQgAAEIQCABAghwAtAJCQEIQAACEECAaQMQgAAEIACBBAggwAlAJyQEIAABCEAAAaYNQAACEIAABBIggAAnAJ2QEIAABCAAAQSYNgABCEAAAhBIgAACnAB0QkIAAhCAAAQQYNoABCAAAQhAIAECCHAC0AkJAQhAAAIQQIBpAxCAAAQgAIEECCDACUAnJAQgAAEIQAABpg1AAAIQgAAEEiCAACcAnZAQgAAEIAABBJg2AAEIQAACEEiAAAKcAHRCQgACEIAABBBg2gAEIAABCEAgAQIIcALQCQkBCEAAAhBAgGkDEIAABCAAgQQIIMAJQCckBCAAAQhAAAGmDUAAAhCAAAQSIIAAJwCdkBCAAAQgAAEEmDYAAQhAAAIQSIAAApwAdEJCAAIQgAAEEGDaAAQgAAEIQCABAghwAtAJCQEIQAACEECAaQMQgAAEIACBBAggwAlAJyQEIAABCEAAAaYNQAACEIAABBIggAAnAJ2QEIAABCAAAQSYNgABCEAAAhBIgAACnAB0QkIAAhCAAAQQYNoABCAAAQhAIAECCHAC0AkJAQhAAAIQQIBpAxCAAAQgAIEECCDACUAnJAQgAAEIQOD/Ays2n0CLWhfiAAAAAElFTkSuQmCC\"}";
        Assert.assertEquals(expected,json);
    }

}