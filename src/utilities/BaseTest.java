package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class BaseTest {





        public static WebDriver driver;


        public void openBrowser(String baseUrl) {     // to open any URL thats rhy parameter ma add karyu
            // String baseUrl = "https://demo.nopcommerce.com/";
            // Launch the Chrome Browser
            driver = new ChromeDriver();
            // Open the URL into Browser
            driver.get(baseUrl);
            // MaximiseBrowser
            driver.manage().window().maximize();
            // We give Implicit wait to Driver
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        }
        public void closeBrowser(){
            driver.quit();

        }

    }













