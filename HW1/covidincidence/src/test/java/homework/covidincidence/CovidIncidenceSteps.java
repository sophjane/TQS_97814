package homework.covidincidence;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.github.bonigarcia.wdm.WebDriverManager;



public class CovidIncidenceSteps {

    private ChromeDriver driver;

    @When("I navigate to {string}")
    public void iNavigateTo(String url) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(url);
    }

    @And("I click on {string}")
    public void iPressOptionButton(String option) {
        driver.findElement(By.id(option.toLowerCase())).click();
    }

    @And("I select {string} on the country option")
    public void iSelectCountry(String country) {
        WebElement option = new WebDriverWait(driver,3).until(driver -> driver.findElement(By.id(country)));
        option.click();
    }

    @And("I select {string} on the date option")
    public void iSelectDate(String date) {
        driver.findElement(By.id("date")).click();
        driver.findElement(By.id("date")).sendKeys("2022-04-07");
}

    @And("I click on Search")
    public void iPressOnSearch() {
        driver.findElement(By.id("search")).click();
    }

    @Then("I should see the results in a table")
    public void iShouldGetCovidIncidenceTable() {
        WebElement table = new WebDriverWait(driver,20).until(driver -> driver.findElement(By.id("results")));
    }
}
