package PageObject;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DeleteCustomerPage {

    private WebDriver driver;
    private WebElement deleteMessage;
    private WebElement input;
    private WebElement submit;

    public DeleteCustomerPage(WebDriver driver) {
        this.driver = driver;

        deleteMessage = driver.findElement(By.className("heading3"));
        input = driver.findElement(By.name("cusid"));
        submit = driver.findElement(By.name("AccSubmit"));
    }

    public void deleteUser(String id){
        //Enviamos la informacion y clicamos el boton
        input.sendKeys(id);
        submit.click();

        //Aceptamos la primera alerta
        Alert alert = driver.switchTo().alert();
        alert.accept();

        //Aceptamos la segunda alerta
        alert = driver.switchTo().alert();
        alert.accept();

    }

}
