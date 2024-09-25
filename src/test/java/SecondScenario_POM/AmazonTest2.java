package SecondScenario_POM;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AmazonTest2 {
    WebDriver driver;
    private String itemName;
    AmazonHomePage amazonHomePage;

    @BeforeTest
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1024, 768));
        amazonHomePage = new AmazonHomePage(driver);
    }

    @Test
    public void todayDeal() throws InterruptedException {
        amazonHomePage.goToTodayDeals();
        Thread.sleep(2000);
        amazonHomePage.seeMoreDeals();
        Thread.sleep(2000);
    }

    @Test(dependsOnMethods = "todayDeal")
    public void applyFilter() throws InterruptedException {
        Thread.sleep(2000);
        amazonHomePage.applyFilter();
        Thread.sleep(2000);
    }

    @Test(dependsOnMethods = "applyFilter")
    public void checkItem() throws InterruptedException {
        Thread.sleep(2000);
        amazonHomePage.selectItem();
        itemName = amazonHomePage.getItemTitle();
        System.out.println(itemName);
    }

    @Test(dependsOnMethods = "checkItem")
    public void addItemToCart() throws InterruptedException {
        amazonHomePage.addItemToCart();
        Thread.sleep(2000);
        String successAddedToCart = amazonHomePage.getSuccessMessage();
        System.out.println(successAddedToCart);
        Assert.assertEquals(successAddedToCart, "Added to Cart", "Item not added correctly");
    }

    @Test(dependsOnMethods = "addItemToCart")
    public void verifyItemInCart() throws InterruptedException {
        amazonHomePage.goToCart();
        String cartItemTitle = amazonHomePage.getCartItemTitle();
        System.out.println(itemName);
        System.out.println(cartItemTitle);
        Assert.assertTrue(cartItemTitle.contains(itemName), "Item not found in cart!");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}