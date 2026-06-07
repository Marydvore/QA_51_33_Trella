package pages;

import dto.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginPage extends BasePage{

    public LoginPage(WebDriver driver){
        PageFactory.initElements(new AjaxElementLocatorFactory
                (driver,10), this);
    }

    @FindBy(xpath = "//input[@name='username']")
    WebElement inputEmail;
    @FindBy(xpath = "//*[@type='submit']") // //button[@id='login-submit']
    WebElement btnContinue;
    @FindBy(id = "password")
    WebElement inputPassword;
    @FindBy(id = "login-submit")
    WebElement btnLogIn;

    public void login(User user){
        inputEmail.sendKeys(user.getEmail());
        btnContinue.click();
        //pause(4000);
        clickWait(inputPassword);
        inputPassword.sendKeys(user.getPassword());
        btnLogIn.click();
    }
}
