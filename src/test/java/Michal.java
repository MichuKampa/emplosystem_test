import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Michal {
        @Test
    public void sendingMessageTest() {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\Michał\\IdeaProjects\\es3\\src\\test\\java\\chromedriver.exe");
            WebDriver driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("http://employsystem.com/kontakt/#");
            driver.manage().timeouts().implicitlyWait(010, TimeUnit.MILLISECONDS);

            WebElement emailAddressInput = driver.findElement(By.xpath("/html//input[@id='field_contact_email']"));
            emailAddressInput.sendKeys("michal@wp.pl");

            WebElement phoneNumberInput = driver.findElement(By.xpath("/html//input[@id='field_contact_telefon']"));
            phoneNumberInput.sendKeys("555555555");

            WebElement textInput = driver.findElement(By.xpath("/html//textarea[@id='field_kggkvh3']"));
            textInput.sendKeys("Jest Git!");

            driver.switchTo().frame(0);
            WebElement recaptchaCheckbox = driver.findElement(By.className("recaptcha-checkbox-border"));
            recaptchaCheckbox.click();

            driver.switchTo().frame("relative=parent");


            // potwierdzenie wyslania
            WebElement SubmitClick = driver.findElement(By.className("gtm-check"));
                   SubmitClick.click();

                   driver.quit();



        }
    
}
