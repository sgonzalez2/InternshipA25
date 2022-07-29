package KoelPageObjectTests;

import KoelPageObjects.LoginPage;
import KoelPageObjects.MainPage;
import KoelPageObjects.ProfileAndPreferencesPage;
import PasswordGenerator.RandomPasswordGenerator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PasswordUpdateTest  extends BaseTest{
    @Test
    public void updatePasswordFromProfilePage_passwordUpdated(){
        MainPage mainPage = new MainPage(driver);
        mainPage.isOpen();
        ProfileAndPreferencesPage profilePage = mainPage.userProfileSettings();
        profilePage.isOpen();
        RandomPasswordGenerator passGen = new RandomPasswordGenerator();
        String newPassword = passGen.generatePassayPassword();
        profilePage.updatePassword(defaultPassword,newPassword);
        Assert.assertTrue(profilePage.isUpdated());
    }

    @Test
    public void updatePasswordFromProfilePage_loginWithNewPassword_loggedIn(){
        MainPage mainPage = new MainPage(driver);
        mainPage.isOpen();
        ProfileAndPreferencesPage profilePage = mainPage.userProfileSettings();
        profilePage.isOpen();
        RandomPasswordGenerator passGen = new RandomPasswordGenerator();
        String newPassword = passGen.generatePassayPassword();
        profilePage.updatePassword(defaultPassword,newPassword);
        profilePage.isLoggedOut();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open(url);
        loginPage.newLoginInfo(username,newPassword);
        Assert.assertTrue(mainPage.isOpen()||profilePage.isOpen());

    }

    @Test
    public void updatePasswordFromProfilePage_loginWithOldPassword_errorFrame(){
        MainPage mainPage = new MainPage(driver);
        mainPage.isOpen();
        ProfileAndPreferencesPage profilePage = mainPage.userProfileSettings();
        profilePage.isOpen();
        RandomPasswordGenerator passGen = new RandomPasswordGenerator();
        String newPassword = passGen.generatePassayPassword();
        profilePage.updatePassword(defaultPassword,newPassword);
        profilePage.isLoggedOut();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open(url);
        loginPage.logInToKoelApp(username,defaultPassword);
        Assert.assertTrue(loginPage.isErrorFrame());
    }

    @Test
    public void updatePasswordFromProfilePage_passwordLengthUnder7_error10CharLimit(){
        MainPage mainPage = new MainPage(driver);
        mainPage.isOpen();
        ProfileAndPreferencesPage profilePage = mainPage.userProfileSettings();
        profilePage.isOpen();
        RandomPasswordGenerator shortPassword = new RandomPasswordGenerator();
        String newPassword = shortPassword.generateSixDigitPassword();
        profilePage.updatePassword(defaultPassword,newPassword);
        Assert.assertTrue(profilePage.shortPasswordError());
    }

    @Test
    public void updatePasswordFromProfilePage_passwordLength16_error15CharLimit(){
        MainPage mainPage = new MainPage(driver);
        mainPage.isOpen();
        ProfileAndPreferencesPage profilePage = mainPage.userProfileSettings();
        profilePage.isOpen();
        RandomPasswordGenerator longPassword = new RandomPasswordGenerator();
        String newPassword = longPassword.generateSixteenDigitPassword();
        profilePage.updatePassword(defaultPassword,newPassword);
        Assert.assertFalse(profilePage.isUpdated());
    }
    @Test
    public void updatePasswordFromProfilePage_createPasswordWithoutUppercase_errorNoUpperCaseChar(){
        MainPage mainPage = new MainPage(driver);
        mainPage.isOpen();
        ProfileAndPreferencesPage profilePage = mainPage.userProfileSettings();
        profilePage.isOpen();
        RandomPasswordGenerator missingUppercasePassword = new RandomPasswordGenerator();
        String newPassword = missingUppercasePassword.generateMissingRequirementPasswordUpperCase();
        profilePage.updatePassword(defaultPassword,newPassword);
        Assert.assertFalse(profilePage.isUpdated());
    }

    @Test
    public void updatePasswordFromProfilePage_createPasswordWithoutLowercaseChar_errorNoLowerCaseChar(){
        MainPage mainPage = new MainPage(driver);
        mainPage.isOpen();
        ProfileAndPreferencesPage profilePage = mainPage.userProfileSettings();
        profilePage.isOpen();
        RandomPasswordGenerator missingLowerCasePassword = new RandomPasswordGenerator();
        String newPassword = missingLowerCasePassword.generatePasswordWithMissingRequirementLowerCaseChar();
        profilePage.updatePassword(defaultPassword,newPassword);
        Assert.assertFalse(profilePage.isUpdated());
    }

    @Test
    public void updatePasswordFromProfilePage_createPasswordWithoutNumber_errorMissingNumber(){
        MainPage mainPage = new MainPage(driver);
        mainPage.isOpen();
        ProfileAndPreferencesPage profilePage = mainPage.userProfileSettings();
        profilePage.isOpen();
        RandomPasswordGenerator missingNumberPassword = new RandomPasswordGenerator();
        String newPassword = missingNumberPassword.generatePasswordWithoutNumber();
        profilePage.updatePassword(defaultPassword,newPassword);
        Assert.assertTrue(profilePage.missingNumberError());
    }

    @Test
    public void updatePasswordFromProfilePage_createPasswordWithoutSpecialCharacter_errorMissingSpecialChar(){
        MainPage mainPage = new MainPage(driver);
        mainPage.isOpen();
        ProfileAndPreferencesPage profilePage = mainPage.userProfileSettings();
        profilePage.isOpen();
        RandomPasswordGenerator missingSpecialChar = new RandomPasswordGenerator();
        String newPassword = missingSpecialChar.generatePasswordWithoutSpecialCharacter();
        profilePage.updatePassword(defaultPassword,newPassword);
        Assert.assertTrue(profilePage.missingSpecialCharError());
    }
}
