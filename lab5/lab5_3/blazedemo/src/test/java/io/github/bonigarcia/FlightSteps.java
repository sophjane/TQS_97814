package io.github.bonigarcia;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlightSteps {

    private WebDriver driver;

    @When("I navigate to {string}")
    public void iNavigateTo(String url) {
        driver = WebDriverManager.chromedriver().create();
        driver.get(url);
    }

    @And("I select to travel from {string} to {string}")
    public void iSelectDepartureAndDestinationCities(String departureCity, String destinationCity) {
        WebElement element = driver.findElement(By.name("fromPort"));
        WebElement dropdown = driver.findElement(By.name("toPort"));
        dropdown.findElement(By.xpath(String.format("//option[. = '%s']", destinationCity))).click();
    }

    @And("I click on Find Flights")
    public void iPressFindFlights() {
        driver.findElement(By.cssSelector(".btn-primary")).click();
    }

    @And("I select the flight number {int}")
    public void iSelectFlight(int flightNumber) {
        driver.findElement(By.cssSelector("tr:nth-child("+ Integer.toString(flightNumber) + ") .btn")).click();
    }


    @And("I type {string} on the {string}")
    public void iRespondToForm(String inputValue, String inputName) {
        driver.findElement(By.id(inputName)).click();
        driver.findElement(By.id(inputName)).sendKeys(inputValue);
    }

    @And("I click on Remember Me")
    public void iPressRememberMe() {
        driver.findElement(By.id("rememberMe")).click();
    }

    @And("I click on Purchase Flight")
    public void iPressPurchaseFlight() {
        driver.findElement(By.cssSelector(".btn-primary")).click();
    }

    @Then("I should get the purchased flight information")
    public void iShouldGetFlightInfo() {
        String title = driver.getTitle(); 
        assertEquals("BlazeDemo Confirmation", title); 
    }

}
