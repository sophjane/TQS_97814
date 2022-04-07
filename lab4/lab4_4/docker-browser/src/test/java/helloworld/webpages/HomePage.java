package helloworld.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


// While modeling web pages as Page Object classes, finding and identifying elements can often become a chore.
public class HomePage {
    private WebDriver driver;

    private static String PAGE_URL="https://www.blazedemo.com/index.php";

    @FindBy(name = "fromPort")
    private WebElement departureCityDropdown;

    @FindBy(name = "toPort")
    private WebElement destinationCityDropdown;

    @FindBy(className = "btn-primary")
    private WebElement findFlightsButton;

    //Constructor
    public HomePage(WebDriver driver){
        this.driver=driver;
        driver.get(PAGE_URL);
        PageFactory.initElements(driver, this);
    }

    public void selectFromDepartureCityDropdown(){
        Select cities = new Select(departureCityDropdown);
        cities.selectByValue("Paris");
    }

    public void selectFromDestionationCityDropdown(){
        Select cities = new Select(destinationCityDropdown);
        cities.selectByValue("London");
    }

    public void clickOnFindFlightsButton(){
        findFlightsButton.click();
    }
}
