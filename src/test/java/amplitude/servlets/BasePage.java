package amplitude.servlets;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public abstract class BasePage {

    protected URL contextURL;
    protected WebDriver driver;
    protected ExpectedCondition<Boolean> isCurrentPage;


    public BasePage(final URL contextURL, final WebDriver driver) {
        this.contextURL = contextURL;
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void waitForCurrentPage() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(isCurrentPage);
    }

    public boolean isCurrentPage() {
        return isCurrentPage.apply(driver);
    }

    public String getTitle() {
        return driver.getTitle();
    }
}
