package PageObject;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Clase que representa la vista de login de la aplicación del banco.
 * Sí, está copiada de las diapositivas.
 */
public class LoginPage {
    WebDriver driver;
    WebElement userID;
    WebElement password;
    WebElement form;
    WebElement pTitle;

    public LoginPage(WebDriver driver){
        this.driver = driver;

        this.driver.get("http://demo.guru99.com/V5/");
        userID = driver.findElement(By.name("uid"));
        password = driver.findElement(By.name("password"));
        pTitle = driver.findElement(By.cssSelector("marquee.heading3"));
        form = driver.findElement(By.name("frmLogin"));
    }

    public void login(String user, String pass){
        userID.sendKeys(user);
        password.sendKeys(pass);
        form.submit();
    }

    public String getPageTitle(){
        return pTitle.getText();
    }

    public String getWrongLoginAlert(){
        Alert alert = driver.switchTo().alert();

        return alert.getText();
    }

}
