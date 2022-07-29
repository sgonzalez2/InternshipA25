package KoelPageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationPage extends BasePage{


    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getRegistrationBtn(){
            By registerBtnLocator = By.xpath("//*[@id='hel']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(registerBtnLocator));
            return driver.findElement(registerBtnLocator);
        }

        public WebElement getEmailFieldLocator(){
            By emailFieldLocator = By.xpath("//*[@type='email']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(emailFieldLocator));
            return driver.findElement(emailFieldLocator);
        }

        public WebElement getRegisterBtn(){
            By registerBtnLocator = By.xpath("//*[@type='submit']");
            wait.until(ExpectedConditions.elementToBeClickable(registerBtnLocator));
            return driver.findElement(registerBtnLocator);
        }

        public WebElement getLoginBtn(){
            By loginBtnLocator = By.className("button");
            wait.until(ExpectedConditions.visibilityOfElementLocated(loginBtnLocator));
            return driver.findElement(loginBtnLocator);
        }

        public LoginPage RegisterNewUser(String username) {
            getRegistrationBtn().click();
            getEmailFieldLocator().sendKeys(username);
            wait.until(ExpectedConditions.elementToBeClickable(getRegisterBtn()));
            getRegisterBtn().click();
            getLoginBtn().click();
            return new LoginPage(driver);
        }
    }

