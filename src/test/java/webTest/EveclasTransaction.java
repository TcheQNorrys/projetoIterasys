package webTest;

// Generated by Selenium IDE
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
public class EveclasTransaction {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;
    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }
    @After
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void transao() {
        driver.get("https://testando.eveclass.com/pt");
        driver.manage().window().setSize(new Dimension(1552, 832));
        driver.findElement(By.cssSelector("#support-action > .button-text > span > span")).click();
        driver.findElement(By.cssSelector("#main-action > .button-text > span > span")).click();
        driver.findElement(By.cssSelector(".topbar-logo img")).click();
        driver.findElement(By.cssSelector("#support-action > .button-text > span > span")).click();
        driver.findElement(By.cssSelector("#support-action > .button-text > span > span")).click();
        driver.findElement(By.cssSelector("#support-action > .button-text > span > span")).click();
        driver.findElement(By.cssSelector("#support-action > .button-text > span > span")).click();
        {
            WebElement element = driver.findElement(By.cssSelector("#support-action > .button-text > span > span"));
            Actions builder = new Actions(driver);
            builder.doubleClick(element).perform();
        }
        driver.findElement(By.id("email-i-6154997624122103")).sendKeys("rafael408200983@gmail.com");
        driver.findElement(By.id("senha-i-4624475161085049")).sendKeys("B290589*m");
        driver.findElement(By.cssSelector(".button-text > span")).click();
        {
            WebElement element = driver.findElement(By.cssSelector(".MODULE"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        {
            WebElement element = driver.findElement(By.tagName("body"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element, 0, 0).perform();
        }
        {
            WebElement element = driver.findElement(By.linkText("Tenho Dúvidas"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        driver.findElement(By.cssSelector(".dropdown-item:nth-child(5) .infos-text")).click();
        {
            WebElement element = driver.findElement(By.cssSelector(".list-item:nth-child(1)"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        {
            WebElement element = driver.findElement(By.tagName("body"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element, 0, 0).perform();
        }
    }
}