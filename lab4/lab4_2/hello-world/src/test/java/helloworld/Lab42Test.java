package helloworld;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
// import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;

import java.util.*;

import io.github.bonigarcia.seljup.EnabledIfBrowserAvailable;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import io.github.bonigarcia.seljup.Browser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;

@EnabledIfBrowserAvailable(Browser.CHROME)
@ExtendWith(SeleniumJupiter.class)
public class Lab42Test {

  //private WebDriver driver;
  // private Map<String, Object> vars;
  // JavascriptExecutor js;

  // @BeforeEach
  // public void setUp() {
  //   driver = new ChromeDriver();
  //   js = (JavascriptExecutor) driver;
  //   vars = new HashMap<String, Object>();
  // }
  
  // @AfterEach
  // public void tearDown() {
  //   driver.quit();
  // }



  @Test
  public void lab42(ChromeDriver driver) {
    driver.get("https://blazedemo.com/");
    driver.manage().window().setSize(new Dimension(1228, 910));
    {
      WebElement element = driver.findElement(By.name("fromPort"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).clickAndHold().perform();
    }
    {
      WebElement element = driver.findElement(By.name("fromPort"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    {
      WebElement element = driver.findElement(By.name("fromPort"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).release().perform();
    }
    {
      WebElement dropdown = driver.findElement(By.name("toPort"));
      dropdown.findElement(By.xpath("//option[. = 'London']")).click();
    }
    {
      WebElement element = driver.findElement(By.name("toPort"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).clickAndHold().perform();
    }
    {
      WebElement element = driver.findElement(By.name("toPort"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    {
      WebElement element = driver.findElement(By.name("toPort"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).release().perform();
    }
    driver.findElement(By.cssSelector(".btn-primary")).click();
    driver.findElement(By.cssSelector("tr:nth-child(3) .btn")).click();
    driver.findElement(By.id("inputName")).click();
    driver.findElement(By.id("inputName")).sendKeys("Sherlock Holmes");
    driver.findElement(By.id("address")).click();
    driver.findElement(By.id("address")).sendKeys("221B Baker Street");
    driver.findElement(By.id("city")).click();
    driver.findElement(By.id("city")).sendKeys("London");
    driver.findElement(By.id("state")).click();
    driver.findElement(By.id("zipCode")).click();
    driver.findElement(By.id("zipCode")).sendKeys("NW16XE");
    {
      WebElement element = driver.findElement(By.id("cardType"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).clickAndHold().perform();
    }
    {
      WebElement element = driver.findElement(By.id("cardType"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    {
      WebElement element = driver.findElement(By.id("cardType"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).release().perform();
    }
    driver.findElement(By.id("creditCardNumber")).click();
    driver.findElement(By.id("creditCardNumber")).click();
    driver.findElement(By.id("creditCardNumber")).sendKeys("1234567891011121");
    driver.findElement(By.id("creditCardYear")).click();
    driver.findElement(By.id("creditCardYear")).sendKeys("2025");
    driver.findElement(By.id("nameOnCard")).click();
    driver.findElement(By.id("nameOnCard")).sendKeys("Sherlock Holmes");
    driver.findElement(By.id("rememberMe")).click();
    driver.findElement(By.cssSelector(".btn-primary")).click();
    driver.findElement(By.cssSelector(".hero-unit")).click();
    driver.findElement(By.cssSelector("pre")).click();
    String title = driver.getTitle(); 
    assertEquals("BlazeDemo Confirmation", title); 
    driver.close();
  }
}
