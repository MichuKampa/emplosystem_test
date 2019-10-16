import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class ContactPageTest {
    WebDriver driver;
    static{
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Michał\\IdeaProjects\\es3\\src\\test\\java\\chromedriver.exe");
    }

    // DANE TESTOWE
    private String email1 = "michal@wp.pl";
    private String email2 = "aaaaa@wp.pl";
    private String email3 = "11@11.pl";
    private String phoneNumber1 = "555555555";
    private String phoneNumber2 = "666666666";
    private String phoneNumber3 = "a";

    @Before
        public void setUp() {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("http://employsystem.com/kontakt/#");
            WebElement element = driver.findElement(By.className("gtm-check"));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("scroll(0, 700);");
            driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
    }

    @Test
    public void shouldShowErrorMessageWhenNoEmail() {
        WebElement phoneNumberInput = driver.findElement(By.xpath("/html//input[@id='field_contact_telefon']"));
        phoneNumberInput.sendKeys(phoneNumber1);

        WebElement SubmitClick = driver.findElement(By.className("gtm-check"));
        SubmitClick.click();

        assertEquals("Wpisz swój adres e-mail!", driver.findElement(By.id("email-error")).getText());
    }

    @Test
    public void shouldShowErrorMessageWhenNoPhoneNumber() {
        WebElement emailInput = driver.findElement(By.xpath("/html//input[@id='field_contact_email']"));
        emailInput.sendKeys(email1);

        WebElement SubmitClick = driver.findElement(By.className("gtm-check"));
        SubmitClick.click();

        assertEquals("Wpisz swój telefon!", driver.findElement(By.id("phone-error")).getText());
    }

    @Test
    public void shouldShowErrorMessageWhenNoPhoneNumberAndEmail() {

        WebElement SubmitClick = driver.findElement(By.className("gtm-check"));
        SubmitClick.click();

        assertEquals("Wpisz swój telefon!", driver.findElement(By.id("phone-error")).getText());
        assertEquals("Wpisz swój adres e-mail!", driver.findElement(By.id("email-error")).getText());
    }


    @Test
    public void shouldShowErrorMessageWhenNoCaptchaMark() {
        WebElement emailInput = driver.findElement(By.xpath("/html//input[@id='field_contact_email']"));
        emailInput.sendKeys(email2);
        WebElement phoneNumberInput = driver.findElement(By.xpath("/html//input[@id='field_contact_telefon']"));
        phoneNumberInput.sendKeys(phoneNumber2);

        WebElement SubmitClick = driver.findElement(By.className("gtm-check"));
        SubmitClick.click();

        assertEquals("Kod reCAPTCHA nie jest prawidłowy.", driver.findElement(By.xpath("//form[@id='form_contact3']//fieldset/div[5]/div[@class='frm_error']")).getText());
    }

    @Test
    public void shouldShowErrorMessageWhenWrongEmail() {
        WebElement emailInput = driver.findElement(By.xpath("/html//input[@id='field_contact_email']"));
        emailInput.sendKeys(email3);

        WebElement SubmitClick = driver.findElement(By.className("gtm-check"));
        SubmitClick.click();

        assertEquals("Podany email jest niepoprawny!", driver.findElement(By.id("email-error")).getText());
    }


    @Test
    public void shouldShowErrorMessageWhenWrongPhoneNumber() {
        WebElement phoneNumberInput = driver.findElement(By.xpath("/html//input[@id='field_contact_telefon']"));
        phoneNumberInput.sendKeys(phoneNumber3);

        WebElement SubmitClick = driver.findElement(By.className("gtm-check"));
        SubmitClick.click();

        assertEquals("Podany numer jest niepoprawny!", driver.findElement(By.id("phone-error")).getText());
    }


    @Test
    public void sendingMessageTest() {
        WebElement emailAddressInput = driver.findElement(By.xpath("/html//input[@id='field_contact_email']"));
            emailAddressInput.sendKeys(email1);

            WebElement phoneNumberInput = driver.findElement(By.xpath("/html//input[@id='field_contact_telefon']"));
            phoneNumberInput.sendKeys(phoneNumber1);

            WebElement textInput = driver.findElement(By.xpath("/html//textarea[@id='field_kggkvh3']"));
            textInput.sendKeys("Jest Git!");

            driver.switchTo().frame(0);
            WebElement recaptchaCheckbox = driver.findElement(By.className("recaptcha-checkbox-border"));
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            recaptchaCheckbox.click();

            driver.switchTo().frame("relative=parent");
            WebElement SubmitClick = driver.findElement(By.className("gtm-check"));
            SubmitClick.click();
    }

    @After
        public void tearDown() {
            driver.quit();
    }

}
