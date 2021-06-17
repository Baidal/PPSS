package ejercicio3.conPOyPFact;

import PageObject.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestNewClientCookies {

    private WebDriver driver;
    private WebDriverWait wait;
    private String clientID;

    @BeforeEach
    public void setUp(){
        ChromeOptions chromeOptions = new ChromeOptions();
        boolean headless = Boolean.parseBoolean(System.getProperty("chromeHeadless"));
        chromeOptions.setHeadless(headless);

        driver = new ChromeDriver(chromeOptions);
        wait = new WebDriverWait(driver,2);
        clientID = null;

        Cookies.storeCookiesToFile("mngr334277","zebabEp");
        Cookies.loadCookiesFromFile(driver);
    }

    @Test
    public void testTestNewClientOk(){

        driver.get("http://demo.guru99.com/V5/manager/Managerhomepage.php");
        ManagerPage managerPage = new ManagerPage(driver);

        //Comprobamos que estamos en la pagina de management
        Assertions.assertEquals("Welcome To Manager's Page of Guru99 Bank",managerPage.getWelcomeMessage());

        //Movemos al usuario a la vista de crear un nuevo usuario
        managerPage.moveToNewCustomerPage();

        //Añadimos el nuevo customer
        NewCustomerPage newCustomerPage = new NewCustomerPage(driver);
        newCustomerPage.createCustomer("Luis",
                "Calle circumloquio 2000",
                "ciudadreal",
                "albacete",
                "123456",
                "123456789",
                "vidal2@gmail.com",
                "12345678");

        //Captamos la informacion del nuevo customer y la guardamos en customerID
        CreatedCustomerPage createdCustomerPage = new CreatedCustomerPage(driver);
        clientID = createdCustomerPage.getCustomerId();

        //Comprobamos que estamos en la vista de "customer creado correctamente"
        Assertions.assertEquals("Customer Registered Successfully!!!",createdCustomerPage.getSuccessfulMessage());
        //Devolvemos el driver a la vista de manager
        createdCustomerPage.getManagerView();

        managerPage = new ManagerPage(driver);

        //Comprobamos que estamos en la vista de manager
        Assertions.assertEquals("Welcome To Manager's Page of Guru99 Bank",managerPage.getWelcomeMessage());


    }

    @Test
    public void testTestNewClientDelete(){
        //Atributos para crear el nuevo usuario
        String name,address, city,state, pin, number,email,password;
        name="luis"; address = "CALLE LUISEPICO 2000"; city = "MONOVAR TT"; state="ALICANTE"; pin="123456";
        number="123456789"; email="luisito12@gmail.com"; password="12345678";

        //Creamos el driver a partir de la pagina de management
        driver.get("http://demo.guru99.com/V5/manager/Managerhomepage.php");
        ManagerPage managerPage = new ManagerPage(driver);

        //Comprobamos que estamos en la pagina de management
        Assertions.assertEquals("Welcome To Manager's Page of Guru99 Bank",managerPage.getWelcomeMessage());

        //Movemos al usuario a la vista de crear un nuevo usuario
        managerPage.moveToNewCustomerPage();

        //Creamos el nuevo consumidor
        NewCustomerPage newCustomerPage = new NewCustomerPage(driver);
        newCustomerPage.createCustomer(name,
                address,
                city,
                state,
                pin,
                number,
                email,
                password);

        //Captamos la informacion del nuevo customer y la guardamos en customerID
        CreatedCustomerPage createdCustomerPage = new CreatedCustomerPage(driver);
        clientID = createdCustomerPage.getCustomerId();
        //Comprobamos que estamos en la vista de "customer creado correctamente"
        Assertions.assertEquals("Customer Registered Successfully!!!",createdCustomerPage.getSuccessfulMessage());

        //Volvemos a cargaR la pagina del manager
        createdCustomerPage.getManagerView();
        managerPage = new ManagerPage(driver);

        //Movemos al usuario a la vista de crear un nuevo usuario
        managerPage.moveToNewCustomerPage();

        newCustomerPage = new NewCustomerPage(driver);

        //Creamos el consumidor repetido
        newCustomerPage.createCustomer(name,
                address,
                city,
                state,
                pin,
                number,
                email,
                password);

        Assertions.assertEquals("Email Address Already Exist !!", newCustomerPage.getDuplicatedCustomerError());
    }

    @AfterEach
    public void tearDown(){
        deleteUser();
    }


    /**
     * Se encarga de eliminar un usuario
     *
     */
    public void deleteUser(){
        WebDriver driverDelete = new ChromeDriver();
        WebDriverWait waitDelete = new WebDriverWait(driverDelete,5);

        Cookies.storeCookiesToFile("mngr334277","zebabEp");
        Cookies.loadCookiesFromFile(driverDelete);

        driverDelete.get("http://demo.guru99.com/V5/manager/Managerhomepage.php");

        ManagerPage managerPage = new ManagerPage(driverDelete);

        managerPage.moveToDeleteCustomerPage();

        DeleteCustomerPage deleteCustomerPage = new DeleteCustomerPage(driverDelete);

        deleteCustomerPage.deleteUser(clientID);
    }
}
