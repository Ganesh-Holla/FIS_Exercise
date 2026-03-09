import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ExecuteAPI {
    @Test
    public void verifyAPI() {

        RestAssured.baseURI = "https://api.coingecko.com/api/v3/coins/bitcoin";

        // 1. Send GET Request
        Response response =
                given()
                        .when()
                        .get()
                        .then().log().all()
                        .statusCode(200)
                        .extract().response();

        // 2a. Verify BPIs (USD, GBP, EUR)
        response.then()
                .body("market_data.current_price.usd", notNullValue())
                .body("market_data.current_price.gbp", notNullValue())
                .body("market_data.current_price.eur", notNullValue());

        // 2b. Verify Market Cap and Total Volume exist
        response.then()
                .body("market_data.market_cap.usd", greaterThan(0L))
                .body("market_data.market_cap.gbp", greaterThan(0L))
                .body("market_data.market_cap.eur", greaterThan(0L))
                .body("market_data.total_volume.usd", greaterThan(0L))
                .body("market_data.total_volume.gbp", greaterThan(0L))
                .body("market_data.total_volume.eur", greaterThan(0L));

        // 2c. Verify price change percentage over last 24 hours
        response.then()
                .body("market_data.price_change_percentage_24h", notNullValue());

        // 2d. Verify homepage URL is not empty
        response.then()
                .body("links.homepage[0]", not(emptyOrNullString()));
    }
}
