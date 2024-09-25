package FirstScenario_POM;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class CartPage {

     private WebDriver driver;

        private By successMessage = By.xpath("/html/body/div[1]/div[1]/div/div[1]/div[1]/div[2]/div/div/div/div/h1");
        private By cartItem = By.cssSelector(".sc-list-item-content span.a-list-item");


        public CartPage(WebDriver driver) {
            this.driver = driver;
        }

        public String getSuccessMessage() {
            return driver.findElement(successMessage).getText();
        }

        public void verifyItemInCart(String expectedItemTitle) {
            WebElement cartItemElement = driver.findElement(cartItem);
            String cartItemTitle = cartItemElement.getText();
            System.out.println(cartItemTitle);
            System.out.println(expectedItemTitle);
            Assert.assertTrue(cartItemTitle.contains(expectedItemTitle), "Item not found in cart!");
        }
    }


