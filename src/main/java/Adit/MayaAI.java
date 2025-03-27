package maya;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Jenkinscode {
    private WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        Jenkinscode owlcode = new Jenkinscode();
        owlcode.setup();
        owlcode.login();
        owlcode.closeBrowser();
    }

    public void setup() {
        // Setup ChromeDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();

        // Set ChromeOptions for headless mode
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Run in headless mode (no UI)
        options.addArguments("--no-sandbox"); // Helps in server environments
        options.addArguments("--disable-dev-shm-usage"); // Prevents crashes in low-memory environments
        options.addArguments("--remote-allow-origins=*"); // Fixes potential WebDriver errors
        options.addArguments("--window-size=1920,1080"); // Ensures proper screen size in headless mode

        // Initialize WebDriver with ChromeOptions
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    public void login() throws InterruptedException {
        driver.get("https://maya.technicalhub.io/");
        Thread.sleep(1000);

        // Click login button
        driver.findElement(By.xpath("//li[@class='header-btn']//a[1]")).click();
        Thread.sleep(1000);

        // Enter login credentials
        driver.findElement(By.name("roll_no")).sendKeys("2000000018");
        driver.findElement(By.name("password")).sendKeys("Thub@123");
        Thread.sleep(3000);

        // Click login button
        driver.findElement(By.xpath("//button[@class='edu-btn btn-medium']")).click();
        Thread.sleep(3000);

        // Confirm login
        driver.findElement(By.xpath("//button[normalize-space(text())='Yes']")).click();
        Thread.sleep(2000);

        System.out.println("Successfully logged in");
    }

    public void closeBrowser() throws InterruptedException {
        Thread.sleep(3000);
        if (driver != null) {
            driver.quit(); // Ensures all browser processes are closed properly
        }
    }
}
