package PageObject;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CreatedCustomerPage {

    private WebDriver driver;
    private WebElement customerID;
    private WebElement successfully;

    public CreatedCustomerPage(WebDriver driver) {
        this.driver = driver;
        /*
        //Accedemos a la tabla que contiene los datos del nuevo elemento creado
        List<WebElement> tableRows = driver.findElements(By.cssSelector("table[align=center]"));

        successfully = driver.findElement(By.cssSelector("p.heading3"));

        //Accedemos a la fila que contiene los datos del id del cliente
        WebElement customerIDRow = tableRows.get(3);

        //Accedemos a las columnas dentro de la fila donde está el id
        List<WebElement> tableColumns = customerIDRow.findElements(By.xpath("td"));*/
        successfully = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[1]/td/p"));
        customerID = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[4]/td[2]"));//tableColumns.get(1);
    }

    public String getCustomerId(){
        return customerID.getText();
    }

    public String getSuccessfulMessage(){
        return successfully.getText();
    }

    public void getManagerView(){
        driver.findElement(By.linkText("Continue")).click();
    }


}
