import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;


public class JumiaTest {

    //Declaration of Webdriver
    private WebDriver driver;
    String productSelected;

    @BeforeTest
    public void setUp() {
        //Lunch the Chrome driver
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
        //Initialize the Webdriver
        driver = new ChromeDriver();
        //Open the Url
        driver.get("https://www.jumia.com.ng/");
        // Maximize the window
        driver.manage().window().maximize();
        //Make the page wait implicitly for the page to fully load
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

        //Case 1
        //get the actual page title
        String actualPageTitle = driver.getTitle();
        //Set the expected page title
        String expectedPageTitle = "Jumia Nigeria | Online Shopping for Groceries, Appliances & More!";
        // Verify that the title of the page
        if (expectedPageTitle.equals(actualPageTitle)) {
            System.out.println("Assertion 1: The page title is: " + actualPageTitle);
        } else {
            System.out.println("Assertion 1: The Wrong Webpage is loaded");
        }

        //get the actual Page URL
        String actualURL = driver.getCurrentUrl();
        //Set the expected page URL
        String expectedURL = "https://www.jumia.com.ng/";
        // Verify that the Correct url is loaded
        if (expectedURL.equals(actualURL)) {
            System.out.println("Assertion 2: The correct Webpage is loaded");
        } else {
            System.out.println("Assertion 2: The Wrong Webpage is loaded");
        }
    }

    @Test(priority = 1)
    public void negativeLogin_InvalidEmail() {
        // Click on the Account button
        driver.findElement(By.xpath("//label[@for='dpdw-login']")).click();
        // Click on the SignIn button
        driver.findElement(By.xpath("//a[@class='btn _prim -fw _md']")).click();
        //Type in the Email address in the email field
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys("Testemailgmail.com");
        //Click on the continue button
        driver.findElement(By.xpath("//button[@type='submit']//span[@class='mdc-button__touch']")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

        // Verify that login to the page was not successful

        // Verifying if an error message is displayed
        WebElement emailValidation = driver.findElement(By.xpath("//input[@name='email']"));
        String actualValidationErrorMessage = emailValidation.getAttribute("validationMessage");
        if (emailValidation.isDisplayed()) {
            System.out.println("Assertion 3:The Login failed for invalid email");
        } else {
            System.out.println("Assertion 3:No error message was displayed");
        }
        // Verifying the error message text
        String expectedValidationErrorMessage = "Please include an '@' in the email address. 'Testemailgmail.com' is missing an '@'.";
        if (expectedValidationErrorMessage.equals(actualValidationErrorMessage)) {
            System.out.println("Assertion 4: Error message = " + actualValidationErrorMessage);
        } else {
            System.out.println("Assertion 4:The error message is not for email input");
        }
    }

    @Test(priority = 2)
    public void negativeLogin_InvalidPassword() {
        String homePage = "https://www.jumia.com.ng/";

        if (driver.getCurrentUrl().equals(homePage)) {
            //proceed to log in

        } else {
            driver.navigate().to(homePage);
        }
        // Click on the Account button
        driver.findElement(By.xpath("//label[@for='dpdw-login']")).click();
        // Click on the SignIn button
        driver.findElement(By.xpath("//a[@class='btn _prim -fw _md']")).click();
        //Type in the Email address in the email field
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys("Testemail@gmail.com");
        //Click on the continue button
        driver.findElement(By.xpath("//button[@type='submit']//span[@class='mdc-button__touch']")).click();
        //Type in the password in the password field
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("teassword");
        // Click on the Login button
        driver.findElement(By.id("loginButton")).click();
        //Make the page wait implicitly for the page to fully load
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

        //Test Case 3 begin
        // Verifying if an error message is displayed
        WebElement actualError = driver.findElement(By.xpath("//*[@id=\"passwordForm\"]/div[2]/div[3]/div/div"));
        String actualErrorMessage = actualError.getText();
        String expectedErrorMessage = "Wrong password. Try again or click 'Forgot Password?' to reset it.";
        if (actualError.isDisplayed()) {
            System.out.println("Assertion 5:The Login failed for incorrect password");
        } else {
            System.out.println("Assertion 5:The error is displayed ");
        }

        // Verifying the error message text
        if (expectedErrorMessage.equals(actualErrorMessage)) {
            System.out.println("Assertion 6: Error message = " + actualErrorMessage);
        } else {
            System.out.println("Assertion 6:The error message is not for Password input");
        }
    }

    @Test(priority = 3)
    public void negativeLogin_EmptyField() {

        String homePage = "https://www.jumia.com.ng/";

        if (driver.getCurrentUrl().equals(homePage)) {
            //proceed to log in

        } else {
            driver.navigate().to(homePage);
        }

        // Click on the Account button
        driver.findElement(By.xpath("//label[@for='dpdw-login']")).click();
        // Click on the SignIn button
        driver.findElement(By.xpath("//a[@class='btn _prim -fw _md']")).click();
        //Type in the Email address in the email field
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(" ");
        //Click on the continue button
        driver.findElement(By.xpath("//button[@type='submit']//span[@class='mdc-button__touch']")).click();
        //Make the page wait implicitly for the page to fully load
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        //Test Case 3 begin
        // Verifying if an error message is displayed
        WebElement actualError = driver.findElement(By.id("empty-email-error-message"));
        String actualErrorMessage = actualError.getText();
        String expectedErrorMessage = "Type your email to login or create an account.";
        if (actualError.isDisplayed()) {
            System.out.println("Assertion 7:The Login failed for empty email field");
        } else {
            System.out.println("Assertion 7:No error message is displayed");
        }

        // Verifying the error message text
        if (expectedErrorMessage.equals(actualErrorMessage)) {
            System.out.println("Assertion 8: Error message = " + actualErrorMessage);
        } else {
            System.out.println("Assertion 8:The error message is not for empty input");
        }
    }


    @Test(priority = 4)
    public void positiveLogin() {
        String homePage = "https://www.jumia.com.ng/";

        if (driver.getCurrentUrl().equals(homePage)) {
            //proceed to log in

        } else {
            driver.navigate().to(homePage);
        }
        // Click on the Account button
        driver.findElement(By.xpath("//label[@for='dpdw-login']")).click();
        // Click on the SignIn button
        driver.findElement(By.xpath("//a[@class='btn _prim -fw _md']")).click();
        //Type in the Email address in the email field
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys("Testemail@gmail.com");
        //Click on the continue button
        driver.findElement(By.xpath("//button[@type='submit']//span[@class='mdc-button__touch']")).click();
        //Type in the password in the password field
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("testpassword");
        // Click on the Login button
        driver.findElement(By.id("loginButton")).click();
        //Make the page wait implicitly for the page to fully load
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

        //Test Case 4 begin
        // Verify that login to the page was successful
        WebElement profileName = driver.findElement(By.xpath("//label[@for='dpdw-login']"));
        //Test Case 3 :confirm user profile name is displayed
        if (profileName.isDisplayed()) {
            System.out.println("Assertion 9: A user profile name is displayed hence log in successful");
        } else {
            System.out.println("Assertion 9: A profile is not logged in");
        }

        // Get the actual profile name logged in
        String actualProfileName = profileName.getText();
        // Set the expected profile name
        String expectedProfileName = "testfirstname";
        // Test Case 4:confirm user profile name is correct
        if (actualProfileName.contains(expectedProfileName)) {
            System.out.println("Assertion 10: The user profile logged in is: " + expectedProfileName);
        } else {
            System.out.println("Assertion 10: A profile is not logged in");
        }
    }

    @Test(priority = 5)
    public void searchForProduct() {
        //Type in a search Key word in the search field
        driver.findElement(By.id("fi-q")).sendKeys("shirts");
        //Click on the search button
        driver.findElement(By.xpath("//button[@class='btn _prim _md -mls -fsh0']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        //Test Case 5 begin
        // Get the actual search Product using the url endpoint
        String actualSearchProduct = driver.getCurrentUrl();
        // Set the expected search product
        String expectedSearchProduct = "shirts";
        // Test Case 4:confirm user search product is displayed
        if (actualSearchProduct.endsWith(expectedSearchProduct)) {
            System.out.println("Assertion 11: The search product is " + expectedSearchProduct);
        } else {
            System.out.println("Assertion 11: The correct search product is not displayed");
        }
        //Make the page wait implicitly for the page to fully load
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
    }

    @Test(priority = 6)
    public void selectProduct() {
        //Select a product and view
        driver.findElement(By.xpath("//article[52]//a[1]//div[1]//img[1]")).click();

        productSelected = driver.findElement(By.xpath("//*[@id=\"jm\"]/main/div[1]/section/div/div[2]/div[1]/div/h1")).getText();
        System.out.println("The product name selected is: "+ productSelected);
        //Click add to cart button
        driver.findElement(By.xpath("//*[@id=\"add-to-cart\"]/button")).click();
        //Make the page wait implicitly for the page to fully load
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
    }

    @Test(priority = 7)
    public void addToCart() {
        //Add number of items by clicking the plus button
        WebElement addButton = driver.findElement(By.xpath("//*[@id=\"pop\"]/div/section/div/div/form/button[2]"));
        addButton.click();

        WebElement count = driver.findElement(By.xpath("//*[@id=\"pop\"]/div/section/div/div[1]/form/span"));
        String expectedCount = "1";
        String actualCount = count.getText();

        //Test Case 6 begin
        if (expectedCount.equals(actualCount)) {
            System.out.println("Assertion 12: The Total Shirt selected = " + actualCount);
        } else {
            System.out.println("Assertion 12: The total expected is not equal to the actual");
        }
        //click view in cart and checkout button
        driver.findElement(By.xpath("//*[@id=\"pop\"]/div/section/div/footer/a")).click();
        //Make the page wait implicitly for the page to fully load
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

        //Test Case 7 begin
        // Set the expected Cart page
        String expectedCartView = "https://www.jumia.com.ng/cart/";
        // Set the actual Cart Page
        String actualCartView = driver.getCurrentUrl();
        // Test Case 4:confirm user search product is displayed
        if (expectedCartView.equals(actualCartView)) {
            System.out.println("Assertion 13: The page is on the Cart page");
        } else {
            System.out.println("Assertion 13: The page is not on the cart page");
        }

    }

    @Test(priority = 8)
    public void removeFromCart() {
        //Get the list of all what is in the cart
        List<WebElement> listInCart = driver.findElements(By.xpath("//article[@class='card -mtm']/article"));
        //Getting the number of the product in the cart
        System.out.println("Old cart Size: " + listInCart.size());
        //Iterating through the product in cart
        for (WebElement element : listInCart) {
            String productNameInCart = element.findElement(By.tagName("h3")).getText();
            //Setting a condition to locate the selected product
            if (productNameInCart.contains(productSelected)) {
                element.findElement(By.tagName("button")).click();

                driver.findElement(By.xpath("//*[@id=\"pop\"]/div/section/div/div/form[2]/button")).click();
            }
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.navigate().refresh();
        listInCart = driver.findElements(By.xpath("//article[@class='card -mtm']//article"));

        //Test Case 8 begin
        //Validate that the expected product  in cart is reduced
        int expectedProductInCart = 3;
        int actualProductInCart = listInCart.size();
        if (expectedProductInCart == actualProductInCart) {
            System.out.println("Assertion 14:The Number of product in the cart has reduced by 1,New size = " + actualProductInCart);
        } else {
            System.out.println("Assertion 14:The Number of product in the cart did not change");
        }

        //Test Case 9 Begin
        //Validate the actual product added was removed
        for (WebElement element : listInCart) {
            String productNameInCart = element.findElement(By.tagName("h3")).getText();
            //Setting a condition to locate the selected product
            if (productNameInCart.contains(productSelected)) {
                System.out.println("Assertion 15:The added product was not removed");
                break;
            }else {
                //The page will continue to iterate over the options
            }
        }
        System.out.println("TAssertion 15:The added product was  removed successfully");
    }

    @Test(priority = 9)
    public void logout() {
        //Click on the Username dropdown label
        driver.findElement(By.xpath("//*[@id=\"jm\"]/header/section/div[2]/div[1]/label")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        //Click on the sign-out button
        driver.findElement(By.xpath("//*[@id=\"dpdw-login-box\"]/div/form/button")).click();
        //Make the page wait implicitly for the page to fully load
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

        // Verify the logout returns to the homepage
        String expectedURL = "https://www.jumia.com.ng/";
        String actualURL = driver.getCurrentUrl();
        // Verifying the success message
        if (expectedURL.equals(actualURL)) {
                System.out.println("Assertion 16: we are back on the home page url :" + actualURL);
        } else {
            System.out.println("Test Case 16: The log out was not successful");
        }
    }

    @AfterTest
    public void tearDown() {
       // Close the browser
        driver.close();
    }

}



