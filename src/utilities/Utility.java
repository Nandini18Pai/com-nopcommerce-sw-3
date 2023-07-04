package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Utility extends BaseTest {

    /**
     * This method will Click on element
     */

    public void clickOnElement(By by){
        driver.findElement(by).click();
    }

    /**
     * This method will get text from element
     */
    public String getTextFromElement(By by){

     return driver.findElement(by).getText();

    }


    /**
     * This method will send text to element
     */
    public void sendTextToElement(By by, String text){
        driver.findElement(by).sendKeys(text);

    }

    public void selectByValue(By by, String value){
        new Select(driver.findElement(by)).selectByValue(value);    // Select method
    }


    public void selectMenu(String menu) {
        clickOnElement(By.xpath(menu));
    }



    public void selectByVisibleTextFromDropDown(By by, String text) {

        WebElement dropDown = driver.findElement(By.name("countryby"));
        Select select = new Select(dropDown);
        //Select by visible text
        select.selectByVisibleText("text");




    }

    public void clearTextToElement(By by) {
        driver.findElement(by).clear();
    }
}
