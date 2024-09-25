package FirstScenario_POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class ItemPage {

    private WebDriver driver;

     private By itemTitle = By.xpath("//*[@id=\"title\"]");
    private By addToCartButton = By.id("add-to-cart-button");
   public ItemPage(WebDriver driver) {
        this.driver = driver;
    }


    public String getItemTitle() {
        return driver.findElement(itemTitle).getText();
    }

    public void clickAddToCartButton() {
        driver.findElement(addToCartButton).click();
    }
}
