package homework.covidincidence;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;

public class CovidIncidenceWebTest {
  // private WebDriver driver;
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

  // @Test
  // public void covidIncidenceWeb() {
  //   driver.get("http://localhost:3000/");
  //   driver.manage().window().setSize(new Dimension(927, 1016));
  //   driver.findElement(By.id("history")).click();
  //   driver.findElement(By.id("australia")).click();
  //   {
  //     WebElement element = driver.findElement(By.id("country"));
  //     Actions builder = new Actions(driver);
  //     builder.moveToElement(element).clickAndHold().perform();
  //   }
  //   {
  //     WebElement element = driver.findElement(By.id("country"));
  //     Actions builder = new Actions(driver);
  //     builder.moveToElement(element).perform();
  //   }
  //   {
  //     WebElement element = driver.findElement(By.id("country"));
  //     Actions builder = new Actions(driver);
  //     builder.moveToElement(element).release().perform();
  //   }
  //   driver.findElement(By.id("date")).click();
  //   driver.findElement(By.id("date")).sendKeys("2022-04-07");
  //   driver.findElement(By.id("search")).click();

  //   driver.findElement(By.id("statistics")).click();
  //   driver.findElement(By.id("barbados")).click();
  //   {
  //     WebElement element = driver.findElement(By.id("country"));
  //     Actions builder = new Actions(driver);
  //     builder.moveToElement(element).clickAndHold().perform();
  //   }
  //   {
  //     WebElement element = driver.findElement(By.id("country"));
  //     Actions builder = new Actions(driver);
  //     builder.moveToElement(element).perform();
  //   }
  //   {
  //     WebElement element = driver.findElement(By.id("country"));
  //     Actions builder = new Actions(driver);
  //     builder.moveToElement(element).release().perform();
  //   }
  //   driver.findElement(By.id("search")).click();
  //   {
  //     WebElement element = driver.findElement(By.id("search"));
  //     Actions builder = new Actions(driver);
  //     builder.moveToElement(element).perform();
  //   }
  //   driver.close();
  // }
}
