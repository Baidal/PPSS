package ejercicio2.conPO;

import PageObject.LoginPage;
import PageObject.ManagerPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestLogin {

    private WebDriver driver;
    private WebDriverWait wait;

    @Test
    public void test_Login_Correct() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,2);

        String bienvenidaEsperada = "Welcome To Manager's Page of Guru99 Bank";

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("mngr334277","zebabEp");

        ManagerPage managerPage = new ManagerPage(driver);

        Assertions.assertEquals(bienvenidaEsperada,managerPage.getWelcomeMessage());

    }

    @Test
    public void test_Login_Incorrect() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,2);

        String mensajeEsperado = "User is not valid";

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("esto","no");

        Assertions.assertEquals(mensajeEsperado,loginPage.getWrongLoginAlert());
    }
}
