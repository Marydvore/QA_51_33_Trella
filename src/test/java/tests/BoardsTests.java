package tests;

import data_provider.DataProviderBoard;
import dto.Board;
import dto.User;
import managar.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.BoardsPage;
import pages.HomePage;
import pages.LoginPage;
import pages.MyBoardPage;
import utils.TestNGListener;

import static utils.PropertiesReader.gerProperty;

@Listeners(TestNGListener.class)
public class BoardsTests extends AppManager {
    @BeforeMethod(alwaysRun = true)
    public void login() {
        User user = User.builder()
                .email(gerProperty("base.properties", "email"))
                .password(gerProperty("base.properties", "password"))
                .build();
        new HomePage(getDriver()).clickBtnLogin();
        new LoginPage(getDriver()).login(user);
    }

    @Test(groups = "smoke")
    public void createNewBoardPositiveTest(){
        Board board = Board.builder()
                .boardTitle("qwerty")
                .build();
        BoardsPage boardsPage = new BoardsPage(getDriver());
        boardsPage.createNewBoard(board);
        boardsPage.clickBtnCreate();

        Assert.assertTrue(new MyBoardPage(getDriver())
                .validateBoardName(board.getBoardTitle()));
    }

    @Test
    public void createNewBoardNegativeTest_EmptyBoarTitle(){
        Board board = Board.builder()
                .boardTitle("")
                .build();
        BoardsPage boardsPage = new BoardsPage(getDriver());
        boardsPage.createNewBoard(board);

        Assert.assertTrue(boardsPage.btnCreateIsNotClickable());
    }

    @Test(dataProvider = "newBoardDP", dataProviderClass = DataProviderBoard.class)
    public void createNewBoardPositiveTestWithDP(Board board){
        BoardsPage boardsPage = new BoardsPage(getDriver());
        boardsPage.createNewBoard(board);
        boardsPage.clickBtnCreate();

        Assert.assertTrue(new MyBoardPage(getDriver())
                .validateBoardName(board.getBoardTitle()));
    }

    @Test(dataProvider = "newBoarsDPFromFile", dataProviderClass = DataProviderBoard.class)
    public void createNewBoardPositiveTestWithDPFromFile(Board board){
        BoardsPage boardsPage = new BoardsPage(getDriver());
        boardsPage.createNewBoard(board);
        boardsPage.clickBtnCreate();

        Assert.assertTrue(new MyBoardPage(getDriver())
                .validateBoardName(board.getBoardTitle()));
    }
}
