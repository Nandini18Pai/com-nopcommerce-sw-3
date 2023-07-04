package homepage;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utilities.Utility;

public class TopMenuTest extends Utility {

    String baseUrl = "https://demo.nopcommerce.com";


    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }


    public void selectMenu(String menu) {  //  1.1 create method with name "selectMenu" it has one parameter name "menu" of type string

        //   1.2 This method should click on the menu whatever name is passed as parameter.
        clickOnElement(By.xpath(menu));

    }

    //   method name verifyPageNavigation.use selectMenu method to select the Menu and click on it and verify the page navigation
    @Test
    public void verifyPageNavigation() {
        selectMenu("//body/div[6]/div[2]/ul[1]/li[1]/a[1]");
        String expectedText = "Computers";
        String actualText = driver.findElement(By.xpath("//h1[contains(text(),'Computers')]")).getText();
        Assert.assertEquals( actualText, expectedText);
    }


    @After
    public void tearDown() {
        closeBrowser();
    }


}
