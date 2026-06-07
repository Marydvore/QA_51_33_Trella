package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.nio.file.Watchable;

public class MyBoardPage extends BasePage{

    public MyBoardPage(WebDriver driver){
        PageFactory.initElements(new AjaxElementLocatorFactory
                (driver,10), this);
    }

    @FindBy(xpath = "//h1[@data-testid='board-name-display']")
    WebElement nameBoard;
    @FindBy(xpath = "//button[@class='QCfb_k37Q8MX7C PhzBALMp63PY_y ybVBgfOiuWZJtD Yt_v_LmarJM9ZS']")
    WebElement btnDots;
    @FindBy(xpath = "//div[text()='Close board']")
    WebElement btnCloseBoard;
    @FindBy(xpath = "//button[@data-testid='popover-close-board-confirm']")
    WebElement btnClose;
    @FindBy(xpath = "//button[@data-testid='close-board-delete-board-button']")
    WebElement btnDeleteBoard;
    @FindBy(xpath = "//button[@data-testid='close-board-delete-board-confirm-button']")
    WebElement btnDelete;

    public boolean validateBoardName(String name){
        return isTextInElementPresent(nameBoard,name);
    }

    public void deleteBoard() {
        clickWait(btnDots);
        clickWait(btnCloseBoard);
        clickWait(btnClose);
        driver.navigate().refresh();
        clickWait(btnDots);
        clickWait(btnDeleteBoard);
        clickWait(btnDelete);
    }
}
