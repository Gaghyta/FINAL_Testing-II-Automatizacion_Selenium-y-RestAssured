package selenium;

import Reports.ExtentFactory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.RegisterPage;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class RegisterTest {
    public static  WebDriver driver;
    public static  WebDriverWait wait;

    HomePage homePage;
    RegisterPage registerPage;

    ExtentTest test;
    static ExtentSparkReporter info = new ExtentSparkReporter("/REPORTES-ANTONINI/RegisterTest.html");
    static ExtentReports extent;

    @BeforeAll
    public static void createReport() {
        extent = ExtentFactory.getInstance();
        extent.attachReporter(info);
    }

    @BeforeEach
    public void setUp() throws InterruptedException {
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.setUp();
        registerPage.url("https://parabank.parasoft.com/parabank/register.htm");
    }

    @Test
    //@Order(1)
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
        test.log(Status.PASS, "Formulario de registro completado exitosamente");
        assertTrue(registerPage.getSuccessMessage().equals("Your account was created successfully. You are now logged in."));
        test.log(Status.PASS, "Registro exitoso");



    }


}
