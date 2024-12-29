package com.springboot;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.baseURI;
import io.restassured.parsing.Parser;

/**
 * CartOfferApplicationTests class contains test cases for verifying the
 * functionality of adding and applying offers in a cart for a restaurant
 * application.
 */
public class CartOfferApplicationTests extends BaseAPI {

    /**
     * Sets up the base URI for the API tests and configures the default parser
     * to JSON. This method is executed before any test methods.
     */
    @BeforeTest
    void setupBaseURI() {
        baseURI = "http://localhost:1080";
        RestAssured.defaultParser = Parser.JSON; // Set default parser to JSON
    }

    /**
     * Test case to verify successful addition of a flat amount offer for a
     * valid restaurant ID and customer segment.
     *
     * Example Request: { "restaurant_id": 1, "offer_type": "FLATX",
     * "offer_value": 10, "customer_segment": ["p1"] }
     */
    @SuppressWarnings("unchecked")
    @Test(priority = 1, description = "Verify successful addition of a flat amount offer for a valid restaurant ID and customer segment.")
    void testAddFlatAmountOffer() {
        JSONObject request = new JSONObject();
        request.put("restaurant_id", 1);
        request.put("offer_type", "FLATX");
        request.put("offer_value", 10);
        List<String> segments = new ArrayList<>();
        segments.add("p1");
        request.put("customer_segment", segments);

        int statusCode = 200;
        String responseMsg = "success";

        BaseAPI.addOffer(request, statusCode, responseMsg);
    }

    /**
     * Test case to verify successful addition of a percentage offer for a valid
     * restaurant ID and customer segment.
     *
     * Example Request: { "restaurant_id": 2, "offer_type": "FLATPERCENT",
     * "offer_value": 15, "customer_segment": ["p2"] }
     */
    @SuppressWarnings("unchecked")
    @Test(priority = 2, description = "Verify successful addition of a percentage offer for a valid restaurant ID and customer segment.")
    void testAddPercentageOffer() {
        JSONObject request = new JSONObject();
        request.put("restaurant_id", 2);
        request.put("offer_type", "FLATPERCENT");
        request.put("offer_value", 15);
        List<String> segments = new ArrayList<>();
        segments.add("p2");
        request.put("customer_segment", segments);

        int statusCode = 200;
        String responseMsg = "success";

        BaseAPI.addOffer(request, statusCode, responseMsg);
    }

    /**
     * Test case to verify error response for invalid offer type.
     *
     * Example Request: { "restaurant_id": 1, "offer_type": "INVALID",
     * "offer_value": 10, "customer_segment": ["p1"] }
     */
    @SuppressWarnings("unchecked")
    @Test(priority = 3, description = "Verify error response for invalid offer type.")
    void testInvalidOfferType() {
        JSONObject request = new JSONObject();
        request.put("restaurant_id", 1);
        request.put("offer_type", "INVALID");
        request.put("offer_value", 10);
        List<String> segments = new ArrayList<>();
        segments.add("p1");
        request.put("customer_segment", segments);

        int statusCode = 400;
        String responseMsg = "Invalid offer type";

        BaseAPI.addOffer(request, statusCode, responseMsg);
    }

    /**
     * Test case to verify error response for negative offer value.
     *
     * Example Request: { "restaurant_id": 1, "offer_type": "FLATX",
     * "offer_value": -10, "customer_segment": ["p1"] }
     */
    @SuppressWarnings("unchecked")
    @Test(priority = 4, description = "Verify error response for negative offer value.")
    void testNegativeOfferValue() {
        JSONObject request = new JSONObject();
        request.put("restaurant_id", 1);
        request.put("offer_type", "FLATX");
        request.put("offer_value", -10);
        List<String> segments = new ArrayList<>();
        segments.add("p1");
        request.put("customer_segment", segments);

        int statusCode = 400;
        String responseMsg = "offer_value must be positive";

        BaseAPI.addOffer(request, statusCode, responseMsg);
    }

    /**
     * Test case to verify error response when percentage offer value exceeds
     * 100%.
     *
     * Example Request: { "restaurant_id": 1, "offer_type": "FLATX",
     * "offer_value": 110, "customer_segment": ["p1"] }
     */
    @SuppressWarnings("unchecked")
    @Test(priority = 5, description = "Verify error response when percentage offer value exceeds 100%.")
    void testInvalidPercentageValue() {
        JSONObject request = new JSONObject();
        request.put("restaurant_id", 1);
        request.put("offer_type", "FLATX");
        request.put("offer_value", 110);
        List<String> segments = new ArrayList<>();
        segments.add("p1");
        request.put("customer_segment", segments);

        int statusCode = 400;
        String responseMsg = "Invalid percentage value";

        BaseAPI.addOffer(request, statusCode, responseMsg);
    }

    /**
     * Test case to verify correct segment information is fetched for an
     * existing user.
     *
     * Example Request: "1"
     */
    @Test(priority = 6, description = "Verify correct segment information is fetched for an existing user.")
    void testFetchValidUserSegment() {
        String request = "1";

        int statusCode = 200;
        String responseMsg = "p1";

        BaseAPI.userSegment(request, statusCode, responseMsg);
    }

    /**
     * Test case to verify error response for non-existent user ID.
     *
     * Example Request: "999"
     */
    @Test(priority = 7, description = "Verify error response for non-existent user ID.")
    void testFetchInvalidUserSegment() {
        String request = "999";

        int statusCode = 400;
        String responseMsg = "User not found";

        BaseAPI.userSegment(request, statusCode, responseMsg);
    }

    /**
     * Test case to verify error response for missing or empty user ID.
     *
     * Example Request: ""
     */
    @Test(priority = 8, description = "Verify error response for missing or empty user ID.")
    void testEmptyUserSegment() {
        String request = "";

        int statusCode = 400;
        String responseMsg = "User ID is invalid";

        BaseAPI.userSegment(request, statusCode, responseMsg);
    }

    /**
     * Test case to verify error response for non-numeric user ID.
     *
     * Example Request: "abc"
     */
    @Test(priority = 9, description = "Verify error response for non-numeric user ID.")
    void testNonNumericUserIdSegment() {
        String request = "abc";

        int statusCode = 400;
        String responseMsg = "User ID is invalid";

        BaseAPI.userSegment(request, statusCode, responseMsg);
    }

    /**
     * Test case to verify successful application of a valid offer for a valid
     * restaurant.
     *
     * Example Request: { "cart_value": 200, "user_id": 1, "restaurant_id": 1 }
     */
    @SuppressWarnings("unchecked")
    @Test(priority = 10, description = "Verify successful application of a valid offer for a valid restaurant.")
    void testApplyOfferWithValidRestaurant() {
        JSONObject request = new JSONObject();
        request.put("cart_value", 200);
        request.put("user_id", 1);
        request.put("restaurant_id", 1);

        int statusCode = 200;
        String responseMsg = "190";

        BaseAPI.applyOffer(request, statusCode, responseMsg);
    }

    /**
     * Test case to verify no discount is applied when the restaurant has no
     * offers.
     *
     * Example Request: { "cart_value": 200, "user_id": 1, "restaurant_id": 2 }
     */
    @SuppressWarnings("unchecked")
    @Test(priority = 11, description = "Verify no discount is applied when the restaurant has no offers.")
    void testNoOfferForRestaurant() {
        JSONObject request = new JSONObject();
        request.put("cart_value", 200);
        request.put("user_id", 1);
        request.put("restaurant_id", 2);

        int statusCode = 400;
        String responseMsg = "200";

        BaseAPI.applyOffer(request, statusCode, responseMsg);
    }

    /**
     * Test case to verify no discount is applied when the user has already used
     * the offer for the day.
     *
     * Example Request: { "cart_value": 200, "user_id": 2, "restaurant_id": 1 }
     */
    @SuppressWarnings("unchecked")
    @Test(priority = 12, description = "Verify no discount is applied when the user has already used the offer for the day.")
    void testOfferAlreadyUsedByUser() {
        JSONObject request = new JSONObject();
        request.put("cart_value", 200);
        request.put("user_id", 2);
        request.put("restaurant_id", 1);

        int statusCode = 400;
        String responseMsg = "200";

        BaseAPI.applyOffer(request, statusCode, responseMsg);
    }

    /**
     * Test case to verify error response when cart value is missing.
     *
     * Example Request: { "user_id": 1, "restaurant_id": 1 }
     */
    @SuppressWarnings("unchecked")
    @Test(priority = 13, description = "Verify error response when cart value is missing.")
    void testMissingCartValue() {
        JSONObject request = new JSONObject();
        request.put("user_id", 1);
        request.put("restaurant_id", 1);

        int statusCode = 400;
        String responseMsg = "The card value is required.";

        BaseAPI.applyOffer(request, statusCode, responseMsg);
    }

    /**
     * Test case to verify error response when user ID is missing.
     *
     * Example Request: { "cart_value": 200, "restaurant_id": 1 }
     */
    @SuppressWarnings("unchecked")
    @Test(priority = 14, description = "Verify error response when user ID is missing.")
    void testMissingUserId() {
        JSONObject request = new JSONObject();
        request.put("cart_value", 200);
        request.put("restaurant_id", 1);

        int statusCode = 400;
        String responseMsg = "The user id is required.";

        BaseAPI.applyOffer(request, statusCode, responseMsg);
    }

    /**
     * Test case to verify error response when restaurant ID is missing.
     *
     * Example Request: { "cart_value": 200, "user_id": 1 }
     */
    @SuppressWarnings("unchecked")
    @Test(priority = 15, description = "Verify error response when restaurant ID is missing.")
    void testMissingRestaurantId() {
        JSONObject request = new JSONObject();
        request.put("cart_value", 200);
        request.put("user_id", 1);

        int statusCode = 400;
        String responseMsg = "The restaurant id is required.";

        BaseAPI.applyOffer(request, statusCode, responseMsg);
    }
}
