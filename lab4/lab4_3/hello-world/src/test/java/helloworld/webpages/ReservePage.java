package helloworld.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ReservePage {
    private WebDriver driver;
    
    @FindBy(tagName = "h3")
    private WebElement heading;

    @FindBy(css = "tr:nth-child(3) .btn")
    private WebElement chooseThisFlightButton;

    public ReservePage (WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isPageOpened(){
        return heading.getText().toString().contains("Flights from Paris to London:");
    }

   public void clickOnChooseThisFlight(){
        chooseThisFlightButton.click();
   }
}