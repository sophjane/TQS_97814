package helloworld.tests;

import helloworld.webpages.PurchasePage;
import helloworld.webpages.ReservePage;
import helloworld.webpages.HomePage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

import io.github.bonigarcia.seljup.BrowserType;
import io.github.bonigarcia.seljup.DockerBrowser;
import io.github.bonigarcia.seljup.SeleniumJupiter;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SeleniumJupiter.class)
public class BlazeDemoTest {

   @Test
   public void selectAndBuyTrip(@DockerBrowser(type = BrowserType.OPERA) WebDriver driver) {
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

}
