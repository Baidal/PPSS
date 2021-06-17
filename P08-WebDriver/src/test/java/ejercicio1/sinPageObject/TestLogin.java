package ejercicio1.sinPageObject;

import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

        //Accedemos a la página web
        driver.get("http://demo.guru99.com/V5/");

        //introducimos el usuario
        driver.findElement(By.name("uid")).sendKeys("mngr334277");

        //introducimos la contraseña
        driver.findElement(By.name("password")).sendKeys("zebabEp");

        //Iniciamos sesión
        driver.findElement(By.name("frmLogin")).submit();

        //Una vez la sesión está iniciada, comprobamos que exista el mensaje de bienvenida y este sea igual al esperado
        WebElement bienvenida = driver.findElement(By.cssSelector("marquee.heading3"));

        String bienvenidaObtenida = bienvenida.getText();

        Assertions.assertEquals(bienvenidaEsperada,bienvenidaObtenida);
    }

    @Test
    public void test_Login_Incorrect() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,2);

        //Accedemos a la página web
        driver.get("http://demo.guru99.com/V5/");

        //introducimos el usuario y contraseña
        driver.findElement(By.name("uid")).sendKeys("no");
        driver.findElement(By.name("password")).sendKeys("funciona :(");

        //Iniciamos sesión SPOILER: NO VA A IR
        driver.findElement(By.name("frmLogin")).submit();

        Alert alert = driver.switchTo().alert();

        String mensajeObtenido = alert.getText();
        String mensajeEsperado = "User is not valid";

        Assertions.assertEquals(mensajeEsperado,mensajeObtenido);
    }



}
