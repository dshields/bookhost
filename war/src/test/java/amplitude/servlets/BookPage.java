package amplitude.servlets;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.net.URL;

public class BookPage extends BasePage {

    @FindBy(id="duration")
    WebElement durationDiv;

    public BookPage(final URL contextURL, final WebDriver driver) {
        super(contextURL, driver);
        isCurrentPage = ExpectedConditions.titleContains("Book");
        waitForCurrentPage();
    }

    public boolean durationShows()
    {
        return (durationDiv !=null && durationDiv.isDisplayed());
    }

}
