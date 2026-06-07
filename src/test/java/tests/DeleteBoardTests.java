package tests;

import dto.Board;
import dto.User;
import managar.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BoardsPage;
import pages.HomePage;
import pages.LoginPage;
import pages.MyBoardPage;

import java.util.Random;

import static utils.PropertiesReader.gerProperty;

public class DeleteBoardTests extends AppManager {
    BoardsPage boardsPage;

    @BeforeMethod
    public void login() {
        User user = User.builder()
                .email(gerProperty("base.properties", "email"))
                .password(gerProperty("base.properties", "password"))
                .build();
        new HomePage(getDriver()).clickBtnLogin();
        new LoginPage(getDriver()).login(user);

        int i = new Random().nextInt(100);
        Board board = Board.builder().boardTitle("board" + i).build();

        boardsPage = new BoardsPage(getDriver());
        boardsPage.createNewBoard(board);
        boardsPage.clickBtnCreate();
    }

    @Test
    public void deletePositiveTest(){
        new MyBoardPage(getDriver()).deleteBoard();
        Assert.assertTrue(boardsPage.validateMessageBoardDelete("Board deleted."));
    }
}
