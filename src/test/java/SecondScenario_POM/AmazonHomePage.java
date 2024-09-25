package SecondScenario_POM;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class AmazonHomePage {
    private WebDriver driver;
    private Actions action;

    // Locators
    private By todayDealsLink = By.linkText("Today's Deals");
    private By seeMoreLink = By.linkText("See more");
    private By showLessLink = By.linkText("Show less");
    private By groceryCheckbox = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[41]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/span[12]/div[1]/label[1]/span[1]/span[1]");
    private By discountFilter = By.id("percentOff");
    private By percentCheckbox = By.xpath("/html/body/div[1]/div[1]/div[41]/div/div[1]/div/div[2]/div[2]/div[6]/div/span[2]/div/label");
    private By itemLink = By.xpath("/html/body/div[1]/div[1]/div[41]/div/div[1]/div/div[2]/div[3]/div/div/div[2]/div[1]/div/div/div[1]");
    private By itemTitle = By.xpath("//*[@id=\"title\"]");
    private By addToCartButton = By.id("add-to-cart-button");
    private By successMessage = By.xpath("/html/body/div[1]/div[1]/div/div[1]/div[1]/div[2]/div/div/div/div/h1");
    private By cartLink = By.id("nav-cart");
    private By cartItemTitle = By.cssSelector(".sc-list-item-content span.a-list-item");

    public AmazonHomePage(WebDriver driver) {
        this.driver = driver;
        action = new Actions(driver);
    }

    public void goToTodayDeals() {
        driver.get("https://www.amazon.eg/ref=nav_logo?language=en_AE");
        driver.findElement(todayDealsLink).click();
    }

    public void seeMoreDeals() {
        action.scrollToElement(driver.findElement(seeMoreLink)).perform();
        driver.findElement(seeMoreLink).click();
        action.scrollToElement(driver.findElement(showLessLink)).perform();
    }

    public void applyFilter() throws InterruptedException {
        driver.findElement(groceryCheckbox).click();
        action.scrollToElement(driver.findElement(discountFilter)).perform();
        Thread.sleep(2000);
        driver.findElement(percentCheckbox).click();
    }

    public void selectItem() {
        driver.findElement(itemLink).click();
    }

    public String getItemTitle() {
        return driver.findElement(itemTitle).getText();
    }

    public void addItemToCart() {
        driver.findElement(addToCartButton).click();
    }

    public String getSuccessMessage() {
        return driver.findElement(successMessage).getText();
    }

    public void goToCart() {
        driver.findElement(cartLink).click();
    }

    public String getCartItemTitle() {
        return driver.findElement(cartItemTitle).getText();
    }
}