package KoelPageObjectTests;

import KoelPageObjects.LoginPage;
import KoelPageObjects.MainPage;
import KoelPageObjects.RegistrationPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.Random;

public class BaseTest {
    protected WebDriver driver;

    protected String url;
    protected String username;
    protected String defaultPassword;


    @BeforeMethod
    public void setUp(){

        //launch Chrome Browser using WebDriverManager
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        //send browser to url
        url = "https://bbb.testpro.io";
        driver.get(url);

        RegistrationPage registrationPage = new RegistrationPage(driver);
        //Create Random User
        Random random = new Random();
        int ints = random.nextInt(1000);
        username = ("TestUser"+ints+"@testpro.io");

        //login to Koel App
        LoginPage loginPage = registrationPage.RegisterNewUser(username);
        defaultPassword = "te$t$tudent";
        MainPage mainPage = loginPage.logInToKoelApp(username,defaultPassword);


    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(100);
        driver.quit();
    }
}
