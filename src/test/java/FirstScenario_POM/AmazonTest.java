package FirstScenario_POM;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class AmazonTest {
    private WebDriver driver;
    private HomePage amazonHomePage;
    private SearchResultPage amazonSearchResultsPage;
    private ItemPage amazonItemPage;
    private CartPage amazonCartPage;
    private String itemName;

    @BeforeTest
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1024, 768));

        // Initialize Page Objects
        amazonHomePage = new HomePage(driver);
        amazonSearchResultsPage = new SearchResultPage(driver);
        amazonItemPage = new ItemPage(driver);
        amazonCartPage = new CartPage(driver);
    }

    @Test(dataProvider = "SearchData")
    public void searchItem(String searchKeyword) throws InterruptedException {
        driver.get("https://www.amazon.eg/ref=nav_logo?language=en_AE");
        amazonHomePage.enterSearchKeyword(searchKeyword);
        amazonHomePage.clickSearchButton();
        Thread.sleep(2000);
    }

    @DataProvider(name = "SearchData")
    public Object[][] getSearchData() {
        return new Object[][]{
                {"car accessories"}
        };
    }

    @Test(dependsOnMethods = "searchItem")
    public void addItemToCart() throws InterruptedException {
        amazonSearchResultsPage.scrollToFirstItem();
        amazonSearchResultsPage.clickFirstItem();
        Thread.sleep(2000);
        itemName = amazonItemPage.getItemTitle();
        System.out.println(itemName);
        amazonItemPage.clickAddToCartButton();
        Thread.sleep(2000);
        String successMessage = amazonCartPage.getSuccessMessage();
        System.out.println(successMessage);
        Assert.assertEquals(successMessage, "Added to Cart", "Item not added correctly");
    }

    @Test(dependsOnMethods = "addItemToCart")
    public void verifyItemInCart() {
        driver.findElement(By.id("nav-cart")).click();
        amazonCartPage.verifyItemInCart(itemName);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
