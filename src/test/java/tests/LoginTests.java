package tests;

import dto.User;
import managar.AppManager;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class LoginTests extends AppManager {
    @Test
    public void loginPositiveTest(){
        User user = User.builder()
                .email("marydvore07@gmail.com")
                .password("Rrsfsr2007Rrsfsr#")
                .build();
        new HomePage(getDriver()).clickBtnLogin();
        new LoginPage(getDriver()).login(user);


    }
}
