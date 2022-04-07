package helloworld.tests;

import helloworld.webpages.PurchasePage;
import helloworld.webpages.ReservePage;
import helloworld.webpages.HomePage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

public class BlazeDemoTest {
   WebDriver driver;

   @BeforeEach
   public void setup(){
       driver = new HtmlUnitDriver();
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
   }

   @Test
   public void selectAndBuyTrip() {
       HomePage home = new HomePage(driver);
       home.selectFromDepartureCityDropdown();
       home.selectFromDestionationCityDropdown();
       home.clickOnFindFlightsButton();

       ReservePage reservePage= new ReservePage(driver);
       assertTrue(reservePage.isPageOpened());
       reservePage.clickOnChooseThisFlight();

       PurchasePage purchasePage =new PurchasePage(driver);
       purchasePage.setName("Sherlock Holmes");
       purchasePage.setAddress("221B Baker Street");
       purchasePage.setCity("London");
       purchasePage.setZipCode("NW16XE");
       purchasePage.setCreditCardNumber("1234567891011121");
       purchasePage.setCreditCardYear("2025");
       purchasePage.setNameOnCard("Sherlock Holmes");
       purchasePage.clickOnRememberMe();
       purchasePage.clickOnPurchaseButton();
   }

    @AfterEach
    public void close(){
          driver.close();
       }
   }
