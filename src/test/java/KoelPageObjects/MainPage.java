package KoelPageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends BasePage{
    public MainPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getProfileBtn() {
        By profileBtnLocator = By.className("view-profile");
        wait.until(ExpectedConditions.visibilityOfElementLocated(profileBtnLocator));
        return driver.findElement(profileBtnLocator);
    }

    public boolean isOpen(){
        By homeLocator = By.className("home");
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(homeLocator));
            return true;
        }catch (TimeoutException err){
            return false;
        }
    }

    public ProfileAndPreferencesPage userProfileSettings(){
        getProfileBtn().click();
        return new ProfileAndPreferencesPage(driver);
    }
}
