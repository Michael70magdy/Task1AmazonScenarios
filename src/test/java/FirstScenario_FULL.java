import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

public class FirstScenario_FULL {
    private WebDriver driver;
    private String ItemName;

    @BeforeTest
    public void setup()
    {
        driver= new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1024,768));
    }


   @Test(dataProvider = "SearchData")
    public void SearchItem(String SearchKeyword) throws InterruptedException {
        driver.get("https://www.amazon.eg/ref=nav_logo?language=en_AE");

        WebElement SearchBox = driver.findElement(By.id("twotabsearchtextbox"));
        SearchBox.sendKeys(SearchKeyword);

        WebElement SearchButton = driver.findElement(By.id("nav-search-submit-button"));
        SearchButton.click();

        Thread.sleep(2000);
    }
    @DataProvider(name = "SearchData")
    public Object[][] getSearchData() {
        return new Object[][]{
                {"car accessories"}
        };
    }

    @Test(dependsOnMethods = "SearchItem")
    public void CheckItem() throws InterruptedException {
        WebElement FirstItem = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div[1]/div/span[1]/div[1]/div[3]/div/div/div/div/span/div/div/div[2]/div[1]/h2/a/span"));
        Actions actions= new Actions(driver);
        actions.scrollToElement(FirstItem).perform();

        FirstItem.click();

        Thread.sleep(2000);

        WebElement title = driver.findElement(By.xpath("//*[@id=\"title\"]"));
        ItemName = title.getText();

        System.out.println(ItemName);
    }

    @Test(dependsOnMethods = "AddItemToCart")
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
