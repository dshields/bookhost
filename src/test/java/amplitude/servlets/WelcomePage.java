package amplitude.servlets;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.net.URL;

public class WelcomePage extends BasePage {

    final String welcomeText = "Welcome to Amplitude!";
    final String url = "firstrun.jsp";
    final By passwordLocator = By.id("password");
    final By directoryLocator = By.id("directory");
    final By welcomeLocator = By.id("welcome");
    final By setupLocator = By.id("setup");

    public WelcomePage(final URL contextURL, final WebDriver driver) {
        super(contextURL, driver);
        isCurrentPage = ExpectedConditions.textToBePresentInElement(welcomeLocator, welcomeText);
        waitForCurrentPage();
    }

    public SongPage submitForm(String password, String directory) {
        type(passwordLocator, password);
        type(directoryLocator, directory);
        element(setupLocator).click();
        return new SongPage(contextURL, driver);
    }
}
