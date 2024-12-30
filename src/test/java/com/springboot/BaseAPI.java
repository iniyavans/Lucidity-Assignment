package com.springboot;

import static org.hamcrest.Matchers.equalTo;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;

public class BaseAPI {

    /**
     * Adds an offer using the provided request data.
     *
     * @param request A JSONObject containing the offer details.
     * @param statusCode The expected HTTP status code of the response.
     * @param responseMsg The expected response message.
     *
     * Example: JSONObject offer = new JSONObject(); offer.put("offer_name",
     * "Summer Sale"); offer.put("discount", 20); addOffer(offer, 201, "Offer
     * added successfully");
     */
    public static void addOffer(JSONObject request, int statusCode, String responseMsg) {
        given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toJSONString())
                .when()
                .post("/api/v1/offer")
                .then()
                .assertThat()
                .statusCode(statusCode)
                .body("response_msg", equalTo(responseMsg));
    }

    /**
     * Applies an offer to the user's cart.
     *
     * @param request A JSONObject containing the offer application details.
     * @param statusCode The expected HTTP status code of the response.
     * @param responseData The expected cart value after applying the offer.
     *
     * Example: JSONObject applyOfferRequest = new JSONObject();
     * applyOfferRequest.put("offer_id", "12345"); applyOffer(applyOfferRequest,
     * 200, "150.00");
     */
    public static void applyOffer(JSONObject request, int statusCode, String responseData) {
        given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toJSONString())
                .when()
                .post("/api/v1/cart/apply_offer")
                .then()
                .assertThat()
                .statusCode(statusCode)
                .body("cart_value", equalTo(responseData));
    }

    /**
     * Retrieves the user segment based on the user ID.
     *
     * @param userID The ID of the user whose segment is to be retrieved.
     * @param statusCode The expected HTTP status code of the response.
     * @param responseMsg The expected segment of the user.
     *
     * Example: userSegment("user123", 200, "premium");
     */
    public static void userSegment(String userID, int statusCode, String responseMsg) {
        given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("/api/v1/user_segment?user_id=" + userID)
                .then()
                .assertThat()
                .statusCode(statusCode)
                .body("segment", equalTo(responseMsg));
    }
}
