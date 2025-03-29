import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class Jenkinsnode {

    public boolean checkDatabaseStatus(String rollNo, String password) {
        WebDriver driver = null;

        try {
            // Setup WebDriverManager with a fixed version of chromedriver
            WebDriverManager.chromedriver().driverVersion("131.0.6778.204").setup(); // Replace with your desired version
            driver = new ChromeDriver();

            // Maximize the browser window
            driver.manage().window().maximize();
            driver.get("https://maya.technicalhub.io/");

            // Updated WebDriverWait
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Click the login link
            WebElement loginLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='header-btn']//a[1]")));
            loginLink.click();

            // Enter credentials
            WebElement user = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("roll_no")));
            user.sendKeys(rollNo);

            WebElement pass = driver.findElement(By.name("password"));
            pass.sendKeys(password);

            WebElement submit = driver.findElement(By.xpath("//button[normalize-space(text())='Sign in']"));
            submit.click();

            // Check the status
            WebElement rank = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space(text())='Active']")));
            String status = rank.getText();

            return status.equals("Active");

        } catch (Exception e) {
            // Log full exception stack trace for debugging
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            // Ensure the driver is properly closed
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
