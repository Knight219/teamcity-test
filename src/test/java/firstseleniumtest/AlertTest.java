package firstseleniumtest;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;


import org.junit.Before;
import org.junit.Test;

/**
 * Created by dimaf on 14.12.2017.
 */
public class AlertTest extends BaseTest {

    Alert alert;

    @Before
    public void goPage(){
//        getDriver().get("http://site.com/alert.html");
    }

    @Test
    public void alertText() {
        alert = driver.switchTo().alert();
        String actualText = alert.getText();
        String expectedText = "";
        Assert.assertEquals(actualText, expectedText);
    }
}
