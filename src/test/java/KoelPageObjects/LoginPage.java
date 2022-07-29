package KoelPageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    private WebElement getEmailField(){
        By emailFieldLocator = By.xpath("//*[@type='email']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailFieldLocator));
        return driver.findElement(emailFieldLocator);
    }

    private WebElement getPasswordField(){
        By passwordFieldLocator = By.xpath("//*[@type='password']");
        return driver.findElement(passwordFieldLocator);
    }

    private WebElement getLoginBtn(){
        By loginBtnLocator = By.xpath("//*[@type='submit']");
        wait.until(ExpectedConditions.elementToBeClickable(loginBtnLocator));
        return driver.findElement(loginBtnLocator);
    }

    public MainPage logInToKoelApp(String username, String password) {
        getEmailField().sendKeys(username);
        getPasswordField().sendKeys(password);
        getLoginBtn().click();
        return new MainPage(driver);
    }


    public boolean isErrorFrame() {
        By errorFrameLocator = By.className("error");
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(errorFrameLocator));
            return true;
        }catch (TimeoutException err){
            return false;
        }
    }

    public void open(String url) {
        driver.get(url);
    }

    public void newLoginInfo(String username, String newPassword) {
        getEmailField().sendKeys(username);
        getPasswordField().sendKeys(newPassword);
        getLoginBtn().click();
    }
}
