package FirstScenario_POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class SearchResultPage {
    private WebDriver driver;

    private By firstItem = By.xpath("/html/body/div[1]/div[1]/div[1]/div[1]/div/span[1]/div[1]/div[3]/div/div/div/div/span/div/div/div[2]/div[1]/h2/a/span");

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
    }

    public void scrollToFirstItem() {
        WebElement firstItemElement = driver.findElement(firstItem);
        Actions actions = new Actions(driver);
        actions.scrollToElement(firstItemElement).perform();
    }

    public void clickFirstItem() {
        driver.findElement(firstItem).click();
    }
}



