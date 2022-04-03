package helloworld;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

class HelloWorldFirefoxJupiterTest {


    WebDriver browser;

    @BeforeEach
    public void SetUp(){

        browser = new FirefoxDriver();
    }

    @Test
    public void site_header_is_on_home_page() {
        
        browser.get("https://www.saucelabs.com"); 
        WebElement href = browser.findElement(By.xpath("//a[@href='https://accounts.saucelabs.com/']"));        
        assertTrue((href.isDisplayed()));  
        
    }

    @AfterEach
    public void tearDown(){
        browser.close(); 
    }
}