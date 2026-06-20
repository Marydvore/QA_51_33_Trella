package pages;

import dto.Board;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BoardsPage extends BasePage{

    public BoardsPage(WebDriver driver){
        PageFactory.initElements(new AjaxElementLocatorFactory
                (driver,10), this);
    }

    @FindBy(xpath = "//button[@data-testid='create-board-tile']")
    WebElement btnCreateNewBoard;
    @FindBy(xpath = "//input[@data-testid='create-board-title-input']")
    WebElement inputBoardName;
    @FindBy(xpath = "//button[@data-testid='create-board-submit-button']")
    WebElement btnCreate;
    @FindBy(xpath = "//h2[@class='VmbXKMJLSqfD0U']")
    WebElement messageBoardDelete;
    @FindBy(xpath = "//button[@data-testid='header-member-menu-button']")
    WebElement btnAccount;
    @FindBy(xpath = "//a[@data-testid='manage-account-link']")
    WebElement btnManageAccount;

    public boolean validateMessageBoardDelete(String text){
        return isTextInElementPresent(messageBoardDelete, text);
    }

    public boolean validateUrl(String url){  //isUrlValid
        return new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlContains(url));
    }

    public void createNewBoard(Board board){
        clickWait(btnCreateNewBoard);
        inputBoardName.sendKeys(board.getBoardTitle());
    }

    public void clickBtnCreate(){
        clickWait(btnCreate);
    }

    public boolean btnCreateIsNotClickable(){
        return new WebDriverWait(driver,Duration.ofSeconds(5)).until(ExpectedConditions
                        .not(ExpectedConditions.elementToBeClickable(btnCreate)));
    }


    public void openMyAccount() {
        clickWait(btnAccount);
        clickWait(btnManageAccount);
    }
}
