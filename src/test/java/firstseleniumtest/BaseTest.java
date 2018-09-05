package firstseleniumtest;


import com.sun.xml.internal.ws.policy.AssertionSet;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by dimaf on 12.12.2017.
 */
public class BaseTest {

    protected static WebDriver driver;

    @Before
    public void getDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\dimaf\\Documents\\NatashasWork\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        return driver;
    }

    @Test
    public void login() {
        WebElement loginFild = driver.findElement(By.id("id-1"));
        loginFild.sendKeys("natashyk221@ukr.net");
        WebElement password = driver.findElement(By.id("id-2"));
        password.sendKeys("1275natasha");
        WebElement loginButton = driver.findElement(By.xpath("/html/body/div/div/main/form/button"));
        loginButton.click();
        WebElement profileUser = driver.findElement(By.cssSelector(".login-button__user"));
        String mailUser = profileUser.getText();
        Assert.assertEquals("natashyk221@ukr.net", mailUser);
    }

//    @AfterClass
    public static void tearDown() {
        WebElement menuUser = driver.findElement(By.cssSelector(".login-button__user"));
        menuUser.click();
        WebElement logoutButton = driver.findElement(By.id("login_logout"));
        logoutButton.click();
        driver.quit();
    }

    @Test
    public void svgTest() throws Exception {
        driver.get("http://referencewebapp.qaautomation.net/svg.php");
        List<WebElement> rectsByCss = driver.findElements(By.cssSelector("svg > g > g > rect"));
        List<WebElement> rectsByCssFromChrome = driver.findElements(By.cssSelector("#svgchart > div > svg > g:nth-child(14) > g > rect"));
        List<WebElement> rectsByXpath = driver.findElements(By.xpath("//*[@id=\"svgchart\"]/div/svg/g[14]/g/rect"));

        Assert.assertEquals(rectsByCss.get(0).getTagName(), "rect");
        Assert.assertEquals(rectsByCss.get(0).getAttribute("width"), "145");
    }

    @Test
    public void findCellInTable() throws Exception {
        driver.get("http://ukr.net");
        String tableCss = "//*[@id=\"currency\"]/div[4]/table";
        WebElement table = driver.findElement(By.xpath(tableCss));
        WebElement element = table.findElement(By.xpath("//tr[" + 1 + "]/td[" + 1 + "]"));
        WebElement td1x1 = driver.findElement(By.xpath(tableCss + "/tbody/tr[" + 1 + "]/td[" + 1 + "]"));
        WebElement td1x2 = driver.findElement(By.xpath("//*[@id=\"currency\"]/div[4]/table/tbody/tr[" + 1 + "]/td[" + 2 + "]"));
        WebElement td2x1 = driver.findElement(By.xpath("//*[@id=\"currency\"]/div[4]/table/tbody/tr[" + 2 + "]/td[" + 1 + "]"));

        Assert.assertEquals(td1x1.getAttribute("innerText"), "USD");
        Assert.assertEquals(td2x1.getAttribute("innerText"), "EUR");
        Assert.assertEquals(td1x1.getAttribute("innerText"), element.getAttribute("innerText"));
    }
}

