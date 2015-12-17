package amplitude.servlets;

import amplitude.persistence.hibernate.DBUtils;
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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.sql.SQLException;

@RunWith(Arquillian.class)
@RunAsClient
public class WhenSettingUp {

    @ArquillianResource
    URL contextPath;

    @Drone
    FirefoxDriver driver;

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        WebArchive war = ShrinkWrap.create(WebArchive.class,"mp3.war").addAsDirectory("src/main/webapp");
        war.merge(ShrinkWrap.create(GenericArchive.class).as(ExplodedImporter.class).importDirectory("src/main/webapp").as(GenericArchive.class), "/", Filters.includeAll());
        return war;
    }

    @After
    public void tearDown() throws SQLException, ClassNotFoundException {
        DBUtils utils = new DBUtils();
        utils.deleteDatabase();
    }

    @Test
    public void userIsRedirectedToFirstRun() {
        System.out.println("Getting " + contextPath + "songs");
        driver.get(contextPath + "songs");
        WelcomePage welcomePage = new WelcomePage(contextPath, driver);
        Assert.assertEquals(contextPath + "firstrun.jsp", driver.getCurrentUrl());
    }

    @Test
    public void songsDisplayAfterSetup() {
        driver.get(contextPath + "songs");
        WelcomePage welcomePage = new WelcomePage(contextPath, driver);
        SongPage songPage = welcomePage.submitForm("test", "/Users/dorwins/Music");
        Assert.assertTrue(songPage.isCurrentPage());
        songPage.browse();
        Assert.assertTrue(songPage.getSongCount()>0);
    }

    @Test
    public void songsDisplayAfterSetupSelenium() {
        driver.get(contextPath + "songs");
        Assert.assertTrue(driver.findElement(By.id("welcome")).getText().contains("Welcome to Amplitude"));
        driver.findElement(By.id("password")).sendKeys("test");
        driver.findElement(By.id("directory")).sendKeys("/Users/dorwins/Music");
        driver.findElement(By.id("setup")).click();
        try {
            Thread.currentThread().sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.titleContains("Songs"));
        Assert.assertTrue(driver.getTitle().contains("Songs"));
    }
}
