package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Clase que representa la vista de management de la aplicación del banco.
 * Sí, está copiada de las diapositivas.
 */
public class ManagerPage {

    private WebDriver driver;
    private WebElement welcomeMessage;
    private WebElement newCustomer;
    private WebElement deleteCustomer;

    public ManagerPage(WebDriver driver) {
        this.driver = driver;

        //Link para crear un nuevo consumidor
        newCustomer = driver.findElement(By.linkText("New Customer"));

        //Mensaje de bienvenida
        welcomeMessage = driver.findElement(By.cssSelector("marquee.heading3"));

        //Eliminar usuario
        deleteCustomer = driver.findElement(By.linkText("Delete Customer"));
    }

    public String getWelcomeMessage(){
        return welcomeMessage.getText();
    }

    public void moveToNewCustomerPage(){
        newCustomer.click();
    }

    public void moveToDeleteCustomerPage() { deleteCustomer.click(); }

}
