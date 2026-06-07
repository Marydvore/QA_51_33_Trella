package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public abstract class BasePage {
    static WebDriver driver;

    Logger logger = LoggerFactory.getLogger(BasePage.class);

    public static void setDriver(WebDriver wd){
        driver = wd;
    }

    public void pause(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void clickWait(WebElement element){
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions
                            .elementToBeClickable(element)).click();
        }catch (Exception e){
            //e.printStackTrace();
            //System.out.println("created exception");
            logger.error("created exception", e);
        }
    }

    public boolean isTextInElementPresent(WebElement element, String text){
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions
                            .textToBePresentInElement(element, text));
        }catch (NoSuchElementException | TimeoutException e){
            //e.printStackTrace();
            //System.out.println("created exception");
            logger.error("created exception", e);
            return false;
        }
    }
}
