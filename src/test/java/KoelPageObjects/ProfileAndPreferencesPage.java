package KoelPageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProfileAndPreferencesPage extends BasePage{
    public ProfileAndPreferencesPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getCurrentPasswordField(){
        By currentPasswordField = By.xpath("//*[@name='current_password']");
        return driver.findElement(currentPasswordField);
    }

    public WebElement getNewPasswordField(){
        By newPasswordField = By.xpath("//*[@name='new_password']");
        return driver.findElement(newPasswordField);
    }

    public WebElement getSaveBtn(){
        By saveBtnLocator = By.className("btn-submit");
        wait.until(ExpectedConditions.elementToBeClickable(saveBtnLocator));
        return driver.findElement(saveBtnLocator);
    }

    public WebElement getProfileUpdatedBanner(){
        By profileUpdatedBannerLocator = By.xpath("//*[@class='success show']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(profileUpdatedBannerLocator));
        return driver.findElement(profileUpdatedBannerLocator);

    }

    public WebElement getLogoutBtn(){
        By logoutBtnLocator = By.xpath("//*[@class='logout control']");
        wait.until(ExpectedConditions.elementToBeClickable(logoutBtnLocator));
        return driver.findElement(logoutBtnLocator);
    }

    public Boolean isOpen() {
        By errorFrameLocator = By.xpath("//*[contains(text(),'Profile & Preferences')]");
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(errorFrameLocator));
            return true;
        }catch (TimeoutException err){
            return false;
        }
    }


    public void updatePassword(String currentPassword, String newPassword) {
        getCurrentPasswordField().sendKeys(currentPassword);
        getNewPasswordField().sendKeys (newPassword);
        getSaveBtn().click();
    }

    public boolean isUpdated() {
        try {
            wait.until(ExpectedConditions.visibilityOf(getProfileUpdatedBanner()));
            return true;
        } catch (TimeoutException err) {
            return false;
        }

    }

    public void isLoggedOut() {
        wait.until(ExpectedConditions.invisibilityOf(getProfileUpdatedBanner()));
        getLogoutBtn().click();
    }

    public boolean shortPasswordError() {
        By passwordMinLengthErrorBanner = By.xpath("//*[contains(text(),'must be at least 10 characters')]");
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(passwordMinLengthErrorBanner));
            return true;
        }catch (TimeoutException err){
            return false;
        }
    }


    public boolean missingNumberError() {
        By passwordMinLengthErrorBanner = By.xpath("//*[contains(text(),'must contain at least one number')]");
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(passwordMinLengthErrorBanner));
            return true;
        }catch (TimeoutException err){
            return false;
        }
    }

    public boolean missingSpecialCharError() {
        By passwordMinLengthErrorBanner = By.xpath("//*[contains(text(),'must contain at least one symbol')]");
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(passwordMinLengthErrorBanner));
            return true;
        }catch (TimeoutException err){
            return false;
        }
    }
}
