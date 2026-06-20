package tests;

import dto.Board;
import dto.User;
import managar.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AtlassianPage;
import pages.BoardsPage;
import pages.HomePage;
import pages.LoginPage;
import utils.RetryAnalyzer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static utils.PropertiesReader.gerProperty;

public class ChangeProfilePhotoTests extends AppManager {
    BoardsPage boardsPage;

    @BeforeMethod(alwaysRun = true)
    public void login() {
        User user = User.builder()
                .email(gerProperty("base.properties", "email"))
                .password(gerProperty("base.properties", "password"))
                .build();
        new HomePage(getDriver()).clickBtnLogin();
        new LoginPage(getDriver()).login(user);
        boardsPage = new BoardsPage(getDriver());
    }

    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "smoke")
    public void changeProfilePhotoPositiveTest(){
        boardsPage.openMyAccount();
        List<String> tabs = new ArrayList<>(getDriver()
                .getWindowHandles());
        System.out.println(tabs);
        getDriver().switchTo().window(tabs.get(1));
        AtlassianPage atlassianPage = new AtlassianPage(getDriver());
        atlassianPage.changePhotoInMyAvatar("src/main/resources/23fff4ad-ea3f-4765-bbe9-fc4f7e83445f.jpg");
        Assert.assertTrue(atlassianPage.validateMessage("Avatar added"));
    }

    @Test
    public void changeProfilePhotoWrongFormatFileNegativeTest(){
        boardsPage.openMyAccount();
        List<String> tabs = new ArrayList<>(getDriver()
                .getWindowHandles());
        System.out.println(tabs);
        getDriver().switchTo().window(tabs.get(1));
        AtlassianPage atlassianPage = new AtlassianPage(getDriver());
        atlassianPage.changePhotoInMyAvatar("src/test/resources/Boards.csv");
        Assert.assertTrue(atlassianPage.validateWrongMessage("Upload a photo or select from some default options"));
    }
}
