package restassured;

import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;



@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RequestTest {

    @Test
    @Order(1)
    public void registerTest() {
        String randomUsername = "username" + (int) (Math.random() * 100000);
        String URL = "https://parabank.parasoft.com/parabank/register.htm?customer.firstName=Name&customer.lastName=LastName&customer.address.street=Address&customer.address.city=City&customer.address.state=State&customer.address.zipCode=123&customer.phoneNumber=12345678&customer.ssn=1234567890&customer.username=" + randomUsername + "&customer.password=password&repeatedPassword=password";
        Response response = given()
                .when().post(URL);
        Assertions.assertEquals(200, response.getStatusCode());
    }

    // ABRIR CUENTAL:
    // https://parabank.parasoft.com/parabank/services_proxy/bank/createAccount?customerId=12545&newAccountType=1&fromAccountId=xxxxx
    @Test
    @Order(2)
    public void newAccountTest() {
        String URL = "https://parabank.parasoft.com/parabank/services/bank/createAccount?customerId=12212&newAccountType=1&fromAccountId=13344";
        Response response = given()
                .auth().basic("john", "demo")
                .when().post(URL);

        Assertions.assertEquals("12212", response.body().xmlPath().getString("account.customerId"));
        Assertions.assertEquals("SAVINGS", response.body().xmlPath().getString("account.type"));
        Assertions.assertEquals(200, response.getStatusCode());
    }


    // CUENTAS VISUALIZACION
    // https://parabank.parasoft.com/parabank/overview.htm
    @Test
    @Order(3)
    public void accountOverviewTest() {
        String URL = "https://parabank.parasoft.com/parabank/services/bank/customers/12212/accounts";
        Response response = given()
                .auth().basic("john", "demo")
                .when().get(URL);
        Assertions.assertEquals("12212", response.body().xmlPath().getString("accounts.account[0].customerId"));
        Assertions.assertEquals("CHECKING", response.body().xmlPath().getString("accounts.account[0].type"));
        Assertions.assertEquals(200, response.getStatusCode());
    }



    // TRANSFERIR FONDOS :
    // URL:  https://parabank.parasoft.com/parabank/services_proxy/bank/transfer?fromAccountId=13566&toAccountId=13677&amount=xxxxx
    @Test
    //@Order(4)
    public void transferFundsTest() {
        String URL = "https://parabank.parasoft.com/parabank/services_proxy/bank/transfer?fromAccountId=13344&toAccountId=12345&amount=100";
        Response response = given()
                .auth().basic("john", "demo")
                .when().post(URL);
        Assertions.assertEquals("Successfully transferred $100 from account #13344 to account #12345", response.getBody().prettyPrint());
        Assertions.assertEquals(200, response.getStatusCode());
    }




    // MOVIMIENTOS - TRASNSACCIONES REALIZADAS
    // https://parabank.parasoft.com/parabank/services_proxy/bank/accounts/13566/transactions/month/All/type/All
    @Test
    //@Order(5)
    public void accountActivityTest() {
        String URL = "https://parabank.parasoft.com/parabank/services_proxy/bank/accounts/13344/transactions/month/All/type/All";
        Response response = given()
                .auth().basic("john", "demo")
                .when().get(URL);
        Assertions.assertEquals("13344", response.body().jsonPath().getString("accountId[0]"));
        Assertions.assertEquals(200, response.getStatusCode());
    }
}


