# Zomato Cart Offer API Testing  

This repository contains automated test cases for testing the Zomato Cart Offer API functionality. The tests are implemented using **RestAssured** in **Java** to validate the feature where customer-specific offers are applied to their cart value based on user segments.  

## Features Tested  
 - Adding offers to restaurants for different customer segments (FLAT amount off and FLAT % off).  
 - Applying offers based on the user's segment and verifying the resulting cart value.  
 - Mocking the user segment API to simulate fetching user segment details.  

---

## Prerequisites  
 - **Docker** installed for setting up the mock server.  
 - **Java** (version 11 or above).  
 - **Maven** installed for running the test cases.  

---

## Setup Instructions  

### 1. Clone the Repository  
```bash  
git clone https://github.com/iniyavans/Lucidity-Assignment.git  
cd Lucidity-Assignment 
```

### 2. Setup the Mock Server Using Docker

```bash  
cd mockserver
sudo docker-compose up
```
This will start a mock server service that simulates the /api/v1/user_segment API.

### 3. Run the Tests Using Maven
Use Maven to clean, compile, and run the test cases.
```bash  
mvn test -Dtest=com.springboot.CartOfferApplicationTests
```
The test suite will execute all implemented test cases and display the results in the terminal.

## Project Structure
 - src/test/java: Contains all test cases implemented using RestAssured.
 - docker-compose.yml: Used to set up the mock server for testing.
 - pom.xml: Maven dependencies and configurations.

## Reporting Results
After running the tests, a detailed report is generated in the /target/surefire-reports directory. Open the index.html file to view the report.
