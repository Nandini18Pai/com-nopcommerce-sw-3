package computer;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import utilities.Utility;

public class TestSuite extends Utility {
    String baseUrl = "https://demo.nopcommerce.com";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }


    @Test
    public void verifyProductArrangeInAlphaBaticalOrder() {
        clickOnElement(By.xpath("//body/div[6]/div[2]/ul[1]/li[1]/a[1]")); // Click on Computer Menu
        clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[2]/div[1]/div[2]/ul[1]/li[1]/ul[1]/li[1]/a[1]"));  //  Click on Desktop
        //Select Sort By position "Name: Z to A
        // selectByVisibleTextFromDropDown(By.id("products-orderby"),"Name: Z to A");
        WebElement dropDown = driver.findElement(By.id("products-orderby"));
        Select select = new Select(dropDown);
        //Select by visible text
        select.selectByVisibleText("Name: Z to A");

        //1.4 Verify the Product will arrange in Descending order.

        String expectedDisplay = "Name: Z to A";
        String actualDisplay = driver.findElement(By.xpath("//option[contains(text(),'Name: Z to A')]")).getText();
        Assert.assertEquals(expectedDisplay, actualDisplay);

    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {
        // Click on Computer Menu.
        clickOnElement(By.xpath("//body/div[6]/div[2]/ul[1]/li[1]/a[1]"));
        //  Click on Desktop
        clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[2]/div[1]/div[2]/ul[1]/li[1]/ul[1]/li[1]/a[1]"));
        //  Select Sort By position "Name: A to Z
        WebElement dropDown = driver.findElement(By.id("products-orderby"));
        Select select = new Select(dropDown);
        //Select by visible text
        select.selectByVisibleText("Name: A to Z");

        Thread.sleep(1000);
        // 2.4 Click on "Add To Cart
        clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[3]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/div[2]/button[1]"));

        // 2.5 Verify the Text "Build your own computer"
        String expectedText = "Build your own computer";
        String actualText = driver.findElement(By.xpath("//h1[contains(text(),'Build your own computer')]")).getText();
        Assert.assertEquals(expectedText, actualText);
        // 2.6 Select "2.2 GHz Intel Pentium Dual-Core E2200" using Select class
        WebElement dropDown1 = driver.findElement(By.id("product_attribute_1"));
        Select select1 = new Select(dropDown1);
        //Select by visible text
        select1.selectByVisibleText("2.2 GHz Intel Pentium Dual-Core E2200");

        // Select "8GB [+$60.00]" using Select class.
        WebElement ram = driver.findElement(By.name("product_attribute_2"));
        Select selectRam = new Select(ram);
        selectRam.selectByVisibleText("8GB [+$60.00]");
        // Select HDD radio "400 GB [+$100.00]
        clickOnElement(By.id("product_attribute_3_7"));
        // Select OS radio "Vista Premium [+$60.00]"
        clickOnElement(By.xpath("//label[@for='product_attribute_4_9']"));
        // Check Two Check boxes "Microsoft Office [+$50.00]" and "Total Commander[+$5.00]

        driver.findElement(By.xpath("//input[@id='product_attribute_5_12']")).click();
        Thread.sleep(2000);
        // Verify the price "$1,475.00
        String expectedAmount = "$1,475.00";
        String actualAmount = driver.findElement(By.xpath("//span[@id='price-value-1']")).getText();
        Assert.assertEquals(expectedAmount, actualAmount);

        // Click on "ADD TO CARD" Button.
        clickOnElement(By.id("add-to-cart-button-1"));

        // Verify the Message "The product has been added to your shopping cart" on Top green Bar
        String expectedMessage = "The product has been added to your shopping cart";
        String actualMessage = driver.findElement(By.xpath("//body/div[@id='bar-notification']/div[1]/p[1]")).getText();
        Assert.assertEquals(expectedMessage, actualMessage);
        // After that close the bar clicking on the cross button.
        clickOnElement(By.xpath("//body/div[@id='bar-notification']/div[1]/span[1]"));
        // Then MouseHover on "Shopping cart" and Click on "GO TO CART" button.

        Actions cart = new Actions(driver);
        cart.moveToElement(driver.findElement(By.xpath("//span[contains(text(),'Shopping cart')]"))).perform();
        driver.findElement(By.xpath("//*[@id=\"flyout-cart\"]/div/div[4]/button")).click();


        // Verify the message "Shopping cart"
        String expectedText1 = "Shopping cart";
        String actualText2 = driver.findElement(By.xpath("//h1[contains(text(),'Shopping cart')]")).getText();
        Assert.assertEquals(expectedText1, actualText2);

        // Change the Qty to "2" and Click on "Update shopping cart"
        clearTextToElement(By.className("qty-input"));
        sendTextToElement(By.className("qty-input"), "2");
        clickOnElement(By.id("updatecart"));

        //2.17 Verify the Total"$2,950.00"
        String expectedTotalAmount = "$2,950.00";
        String actualTotalAmount = driver.findElement(By.xpath("//span[@class='product-subtotal']")).getText();
        Assert.assertEquals(expectedTotalAmount, actualTotalAmount);
        // 2.18 click on checkbox “I agree with the terms of service
        clickOnElement(By.id("termsofservice"));

        // 2.19 Click on “CHECKOUT
        clickOnElement(By.id("checkout"));

        //2.20 Verify the Text “Welcome, Please Sign In!”

        String expectedWelcomeText = "Welcome, Please Sign In!";
        String actualWelcomeText = driver.findElement(By.xpath("//h1[contains(text(),'Welcome, Please Sign In!')]")).getText();
        Assert.assertEquals(expectedWelcomeText, actualWelcomeText);

        // 2.21Click on “CHECKOUT AS GUEST”Tab
        clickOnElement(By.xpath("//button[@class='button-1 checkout-as-guest-button']"));

        // 2.22 Fill the all mandatory field
        sendTextToElement(By.id("BillingNewAddress_FirstName"), "Champa");
        sendTextToElement(By.id("BillingNewAddress_LastName"), "Chameli");
        sendTextToElement(By.id("BillingNewAddress_Email"), "monday@gmail.com");

        WebElement country = driver.findElement(By.name("BillingNewAddress.CountryId"));
        Select selectCountry = new Select(country);
        selectCountry.selectByVisibleText("United Kingdom"); //Select by visible text
        sendTextToElement(By.id("BillingNewAddress_City"), "London");
        sendTextToElement(By.id("BillingNewAddress_Address1"), "15 Shetty Road");
        sendTextToElement(By.id("BillingNewAddress_ZipPostalCode"), "NN1 1PD");
        sendTextToElement(By.id("BillingNewAddress_PhoneNumber"), "01234567890");
        clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/ol[1]/li[1]/div[2]/div[1]/button[4]"));
        //2.23 Click on “CONTINUE
        clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/ol[1]/li[1]/div[2]/div[1]/button[4]"));
        // Click on Radio Button “Next Day Air($0.00)
        clickOnElement(By.id("shippingoption_1"));
        //  Click on “CONTINUE”
        clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/ol[1]/li[3]/div[2]/form[1]/div[2]/button[1]"));
        // 2.26 Select Radio Button “Credit Card
        clickOnElement(By.id("paymentmethod_1"));

        driver.findElement(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/ol[1]/li[4]/div[2]/div[1]/button[1]")).click();

        // 2.27 Select “Master card” From Select credit card dropdown
        WebElement creditCard = driver.findElement(By.name("CreditCardType"));
        Select selectCard = new Select(creditCard);
        selectCard.selectByVisibleText("Master card"); //Select by visible text

        //  Fill all the details
        sendTextToElement(By.id("CardholderName"), "Champa Chameli");
        sendTextToElement(By.id("CardNumber"), "5368392055488809");
        WebElement expiryDate = driver.findElement(By.id("ExpireMonth"));
        Select date = new Select(expiryDate);
        date.selectByVisibleText("03"); //Select by visible text

        WebElement expiryYear = driver.findElement(By.id("ExpireYear"));
        Select year = new Select(expiryYear);
        year.selectByVisibleText("2024"); //Select by visible text
        sendTextToElement(By.id("CardCode"), "365");

        //  Click on “CONTINUE
        clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/ol[1]/li[5]/div[2]/div[1]/button[1]"));

        // 2.30 Verify “Payment Method” is “Credit Card

        String expectedPayment = "Payment Method: Credit Card";
        String actualPayment = driver.findElement(By.xpath("//li[@class='payment-method']")).getText();
        Assert.assertEquals(expectedPayment, actualPayment);

        // 2.32 Verify “Shipping Method” is “Next Day Air
        //li[@class='shipping-method']
        String expectedMethod = "Shipping Method: Next Day Air";
        String actualMethod = driver.findElement(By.xpath("//li[@class='shipping-method']")).getText();
        Assert.assertEquals(expectedMethod, actualMethod);

        // 2.33 Verify Total is “$2,950.00

        String expectedPayment1 = "$2,950.00";
        String actualPayment1 = getTextFromElement(By.xpath("//*[@id=\"shopping-cart-form\"]/div[3]/div/div/table/tbody/tr[4]/td[2]/span/strong"));
        Assert.assertEquals(expectedPayment1, actualPayment1);

        // 2.34 Click on “CONFIRM
        clickOnElement(By.xpath("//button[contains(text(),'Confirm')]"));

        //  Verify the Text “Thank You”

        String expectedThankYou = "Thank you";
        String actualThankYou = driver.findElement(By.xpath("//h1[contains(text(),'Thank you')]")).getText();
        Assert.assertEquals(expectedThankYou, actualThankYou);


        //Verify the message “Your order has been successfully processed!

        String expectedMessage1 = "Your order has been successfully processed!";
        String actualMessage1 = driver.findElement(By.xpath("//strong[contains(text(),'Your order has been successfully processed!')]")).getText();
        Assert.assertEquals(expectedMessage1, actualMessage1);

        // Click on “CONTINUE
        clickOnElement(By.xpath("//button[contains(text(),'Continue')]"));

        // Verify the text “Welcome to our store"
        String expectedWelcome = "Welcome to our store";
        String actualWelcome = driver.findElement(By.xpath("//h2[contains(text(),'Welcome to our store')]")).getText();
        Assert.assertEquals(expectedWelcomeText, actualWelcomeText);


    }


    @After
    public void tearDown() {
         closeBrowser();
    }


}
