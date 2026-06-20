package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import javax.swing.*;
import java.io.File;

public class AtlassianPage extends BasePage {
    public AtlassianPage(WebDriver driver) {
        PageFactory.initElements(new AjaxElementLocatorFactory
                (driver, 10), this);
    }

    @FindBy(xpath = "//button[@data-testid='profile-avatar-dropdown-trigger']")
    WebElement btnAvatar;
    @FindBy(xpath = "//div[@data-test-selector='profile-hover-info']")
    WebElement divProfilePhoto;
    @FindBy(xpath = "//span[text()='Change profile photo']")
    WebElement btnChangeProfilePhoto;
    @FindBy(xpath = "//input[@data-testid='image-navigator-input-file']")
    WebElement inputUploadPhoto;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnUpload;
    @FindBy(xpath = "//span[text()='Avatar added']")
    WebElement messageAvatarAdded;
    @FindBy(xpath = "//span[text()='Upload a photo or select from some default options']")
    WebElement messageWrong;

    public void changePhotoInMyAvatar(String fileName) {
        Actions actions = new Actions(driver);
        actions.moveToElement(divProfilePhoto).pause(1500).click().perform();
        clickWait(btnChangeProfilePhoto);
        File photo = new File(fileName);
        inputUploadPhoto.sendKeys(photo.getAbsolutePath());
        clickWait(btnUpload);
    }

    public boolean validateMessage(String text){
        return isTextInElementPresent(messageAvatarAdded, text);
    }

    public boolean validateWrongMessage(String text){
        return isTextInElementPresent(messageWrong, text);
    }
}
