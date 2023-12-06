package selenium;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.observer.ExtentObserver;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;
import reports.ExtentFactory;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RegisterTest {

    //private WebDriver driver;
    //private WebDriverWait wait;
    //HomePage homePage = new HomePage(driver);
    //RegisterPage registerPage= new RegisterPage(driver);
    WebDriver driver;
    WebDriverWait wait;
    HomePage homePage;
    RegisterPage registerPage;
    OverviewPage overviewPage;
    AccountPage accountPage;
    TransferFundsPage transferFundsPage;

    // HTML Report
    static ExtentTest test;
    static ExtentSparkReporter info = new ExtentSparkReporter("target/REPORTES-ANTONINI/RegisterTest.html");
    static ExtentReports extent;




    public RegisterTest() {
    }


    @BeforeAll
    public static void createReport() {
        extent = ExtentFactory.getInstance();
        //extent.attachReporter(info);
        extent.attachReporter(new ExtentObserver[]{info});
    }

    @BeforeEach
    public void setUp() throws InterruptedException {
        this.driver = new ChromeDriver();
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(10L));
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.setUp();
        registerPage.url("https://parabank.parasoft.com/parabank/register.htm");
    }

    @Test
    //@Order(1)
    @Tag("Registro")
    public void registerUser() throws InterruptedException {
        test = extent.createTest("PROCESO DE REGISTRO DEL USUARIO");
        test.log(Status.INFO, "Inicia el test...");
        // Sección del Home
        homePage.goToRegister();

        test.log(Status.PASS, "DIRECCIONAMIENTO AL FORMULARIO PARA EL REGISTRO");
        // Registro del usuario - RELLENO Y ENVÍO DE DATOS
        registerPage.fillRegisterFormUser();
        registerPage.submitFormRegister();
        //
        test.log(Status.PASS, "FORMULARIO DE REGISTRO completado exitosamente");
        assertTrue(registerPage.getSuccessMessage().equals("Your account was created successfully. You are now logged in."));
        test.log(Status.PASS, "REGISTRO EXITOSO");



    }

    @Test
    //@Order(2)
    // Falla si ya existe el usuario
    // Reseteo de bd. Usar este usuario en test subsiguientes
    public void registerMainUserTest() throws InterruptedException {
        test = extent.createTest("Proceso de registro de usuario fijo para los siguientes tests");
        test.log(Status.INFO, "Inicia el test...");
        // Home
        homePage.goToRegister();
        test.log(Status.PASS, "Ir al formulario de registro");
        // Register
        registerPage.fillRegisterFormMainUser();
        registerPage.submitFormRegister();
        test.log(Status.PASS, "Completar el formulario de registro");
        assertTrue(registerPage.getSuccessMessage().equals("Your account was created successfully. You are now logged in."));
        test.log(Status.PASS, "Registro exitoso");
    }


    @Test
   // @Order(3)
    @Tag("regression")
    public void newAccountTest() throws InterruptedException {
        test = extent.createTest("Apertura de una nueva cuenta");
        test.log(Status.INFO, "Inicia el test...");
        // Home
        homePage.login();
        test.log(Status.INFO, "Login exitoso");
        // Overview
        overviewPage.goToNewAccount();
        // New account
        accountPage.selectSavings();
        accountPage.submitNewAccount();
        assertTrue(accountPage.getSuccessMessage().equals("Congratulations, your account is now open."));
        test.log(Status.PASS, "Apertura de nueva cuenta exitosa");
    }

    @Test
    @Order(4)
    @Tag("regression")
    public void accountOverviewTest() throws InterruptedException {
        test = extent.createTest("Resumen de las cuentas");
        test.log(Status.INFO, "Inicia el test...");
        // Home
        homePage.login();
        test.log(Status.INFO, "Login exitoso");
        // Overview
        overviewPage.goToAccountsOverview();
        assertTrue(overviewPage.getDisclaimer().contains("*Balance includes deposits that may be subject to holds"));
        test.log(Status.INFO, "Mensaje visible confirmado");
    }

    @Test
    @Order(5)
    @Tag("regression")
    public void transferFundsTest() throws InterruptedException {
        test = extent.createTest("Transferir Fondos");
        test.log(Status.INFO, "Inicia el test...");
        // Home
        homePage.login();
        test.log(Status.INFO, "Login exitoso");
        // Overview
        overviewPage.goToTransferFunds();
        // Transfer funds
        assertTrue(transferFundsPage.getTransferFundsText().equals("Transfer Funds"));
        transferFundsPage.validateMenu();
        transferFundsPage.fillAmmount();
        transferFundsPage.selectFromAccount();
        transferFundsPage.selectToAccount();
        transferFundsPage.submitTransfer();
        assertFalse(!transferFundsPage.getTransferSuccessMessage().equals("Transfer Complete!"));
        test.log(Status.INFO, "Transferencia exitosa");
    }

    @Test
    @Order(6)
    @Tag("regression")
    public void accountActivityTest() throws InterruptedException {
        test = extent.createTest("Actividad de la cuenta (cada mes)");
        test.log(Status.INFO, "Inicia el test...");
        // Home
        homePage.login();
        test.log(Status.INFO, "Login exitoso");
        // Overview
        overviewPage.goToAccountsOverview();
        assertTrue(overviewPage.getDisclaimer().contains("*Balance includes deposits that may be subject to holds"));
        overviewPage.selectAccount();
        overviewPage.getAccountDetailsText();
        overviewPage.selectActivityPeriod();
        overviewPage.selectTransactionType();
        overviewPage.submit();
        test.log(Status.INFO, "Revision de actividad de cuenta exitosa");
    }




    @AfterEach
    public void closeEach() {
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.close();
    }

    @AfterAll
    public static void finish() {
        extent.flush();
    }


}
