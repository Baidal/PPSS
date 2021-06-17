package PageObject;

import org.openqa.selenium.*;

public class NewCustomerPage {

    private WebDriver driver;

    private WebElement name;
    private WebElement gender;
    private WebElement birth;
    private WebElement adress;
    private WebElement city;
    private WebElement state;
    private WebElement pin;
    private WebElement number;
    private WebElement email;
    private WebElement password;
    private WebElement submit;

    public NewCustomerPage(WebDriver driver){
        this.driver = driver;
        name = driver.findElement(By.name("name"));
        gender = driver.findElement(By.name("rad1"));
        birth = driver.findElement(By.name("dob"));
        adress = driver.findElement(By.name("addr"));
        city = driver.findElement(By.name("city"));
        state = driver.findElement(By.name("state"));
        pin = driver.findElement(By.name("pinno"));
        number = driver.findElement(By.name("telephoneno"));
        email = driver.findElement(By.name("emailid"));
        password = driver.findElement(By.name("password"));
        submit = driver.findElement(By.name("sub"));
    }

    /**
     * Crea un nuevo Customer a partir de los parámetros. Algunos no se indican, ya que son triviales
     * @param name
     * @param adress
     * @param city
     * @param state
     * @param pin
     * @param number
     * @param email
     * @param password
     */
    public void createCustomer(String name, String adress, String city, String state, String pin, String number, String email, String password){

        this.name.sendKeys(name);
        this.gender.click();
        this.birth.sendKeys("20"); this.birth.sendKeys("6"); this.birth.sendKeys("2000");
        this.adress.sendKeys(adress);
        this.city.sendKeys(city);
        this.state.sendKeys(state);
        this.pin.sendKeys(pin);
        this.number.sendKeys(number);
        this.email.sendKeys(email);
        this.password.sendKeys(password);

        //Si no hacemos esto, el botón submit no está a la vista y podremos enviar los datos del formulario
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //This will scroll the page till the element is found
        js.executeScript("arguments[0].scrollIntoView();", submit);
        //ahora ya está visible el botón en la página y ya podemos hacer click sobre él

        submit.click();
    }

    public String getDuplicatedCustomerError(){
        Alert alert = driver.switchTo().alert();

        return alert.getText();
    }

}
