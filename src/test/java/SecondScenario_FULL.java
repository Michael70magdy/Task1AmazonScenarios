import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SecondScenario_FULL {

    WebDriver driver;
    private String ItemName;
    Actions action ;

    @BeforeTest
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1024, 768));
        action = new Actions(driver);
    }

    @Test
    public void TodayDeal() throws InterruptedException {
        driver.get("https://www.amazon.eg/ref=nav_logo?language=en_AE");
        WebElement TodayDeals = driver.findElement(By.linkText("Today's Deals"));
        TodayDeals.click();
        Thread.sleep(2000);
        WebElement SeeMore = driver.findElement(By.linkText("See more"));
        action.scrollToElement(SeeMore).perform();
        SeeMore.click();
        WebElement ShowLess = driver.findElement(By.linkText("Show less"));
        action.scrollToElement(ShowLess).perform();
        Thread.sleep(2000);

   }
    @Test(dependsOnMethods = "TodayDeal")
    public void ApplyFilter() throws InterruptedException {
        Thread.sleep(2000);
        WebElement grocery = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[41]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/span[11]/div[1]/label[1]/i[1]"));
        grocery.click();
        WebElement Disscount = driver.findElement(By.id("percentOff"));
        action.scrollToElement(Disscount).perform();

        Thread.sleep(2000);

        WebElement percent = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[41]/div/div[1]/div/div[2]/div[2]/div[6]/div/span[2]/div/label"));
        percent.click();

    }
@Test(dependsOnMethods = "ApplyFilter")
    public void CheckItem() throws InterruptedException {
    Thread.sleep(2000);

    WebElement item =driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[41]/div/div[1]/div/div[2]/div[3]/div/div/div[2]/div[1]/div/div/div[1]"));
    item.click();

    WebElement title = driver.findElement(By.xpath("//*[@id=\"title\"]"));
    ItemName = title.getText();

    System.out.println(ItemName);
}
    @Test(dependsOnMethods = "CheckItem")
    public void addItemToCart() throws InterruptedException {
        WebElement addToCartButton = driver.findElement(By.id("add-to-cart-button"));
        addToCartButton.click();
        Thread.sleep(2000);

        WebElement successMessage = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div[1]/div[1]/div[2]/div/div/div/div/h1"));
        String successAddedToCart= successMessage.getText();
        System.out.println(successAddedToCart);
        Assert.assertEquals(successAddedToCart,"Added to Cart","item not added correctly");
    }
    @Test(dependsOnMethods = "addItemToCart")
    public void verifyItemInCart() throws InterruptedException {

        driver.findElement(By.id("nav-cart")).click();
        WebElement cartItem = driver.findElement(By.cssSelector(".sc-list-item-content span.a-list-item"));
        String cartItemTitle = cartItem.getText();

        Assert.assertTrue(cartItemTitle.contains(ItemName), "Item not found in cart!");
    }
@AfterTest
    public void TearDown()
{
    driver.quit();
}

}
