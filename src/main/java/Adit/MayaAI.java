package maya;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class Jenkinscode {
    private WebDriver driver;
    private WebDriverWait wait;

    public static void main(String[] args) {
        Jenkinscode test = new Jenkinscode();
        try {
            test.setup();
            test.login();
            test.verifyLoginSuccess();
        } catch (Exception e) {
            System.err.println("❌ Test execution failed: " + e.getMessage());
        } finally {
            test.closeBrowser();
        }
    }

    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage", "--window-size=1920,1080");
        
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // Increased wait time for slow Jenkins runs
        driver.manage().window().maximize();

        System.out.println("✅ WebDriver setup completed.");
    }

    public void login() {
        System.out.println("🔄 Navigating to login page...");
        driver.get("https://maya.technicalhub.io/");

        try {
            // Click login button
            WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@class='header-btn']//a[1]")));
            loginBtn.click();

            // Enter credentials
            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("roll_no")));
            usernameField.sendKeys("2000000018");
            
            WebElement passwordField = driver.findElement(By.name("password"));
            passwordField.sendKeys("Thub@123");

            // Click login button
            WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='edu-btn btn-medium']")));
            submitBtn.click();

            // Handle confirmation popup (if present)
            try {
                WebElement confirmBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space(text())='Yes']")));
                confirmBtn.click();
                System.out.println("🔹 Confirmation popup handled.");
            } catch (Exception ignored) {
                System.out.println("ℹ No confirmation popup detected.");
            }

            System.out.println("✅ Login request submitted.");
        } catch (Exception e) {
            System.err.println("❌ Login process failed: " + e.getMessage());
        }
    }

    public void verifyLoginSuccess() {
        try {
            WebElement dashboard = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Dashboard')]")));
            System.out.println("✅ Login successful, Dashboard is visible.");
        } catch (Exception e) {
            System.err.println("❌ Login failed, Dashboard not found!");
        }
    }

    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
            System.out.println("🛑 Browser closed.");
        }
    }
}
