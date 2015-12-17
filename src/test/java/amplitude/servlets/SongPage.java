package amplitude.servlets;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.net.URL;

public class SongPage extends BasePage {

    public SongPage(final URL contextURL, final WebDriver driver) {
        super(contextURL, driver);
        isCurrentPage = ExpectedConditions.titleContains("Songs");
        waitForCurrentPage();
    }

    public SongPage browse()
    {
        element(By.linkText("Browse")).click();
        return this;
    }

    public int getSongCount()
    {
        return 0;
    }
}
