package maya;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Jenkinscode {
    private WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        Jenkinscode test = new Jenkinscode();
        test.setup();
        test.login();
        test.verifyLoginSuccess();
        test.closeBrowser();
    }

    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage", "--window-size=1920,1080");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    public void login() throws InterruptedException {
        driver.get("https://maya.technicalhub.io/");
        Thread.sleep(1000);

        // Click login button
        driver.findElement(By.xpath("//li[@class='header-btn']//a[1]")).click();
        Thread.sleep(1000);

        // Enter credentials
        driver.findElement(By.name("roll_no")).sendKeys("2000000018");
        driver.findElement(By.name("password")).sendKeys("Thub@123");
        Thread.sleep(3000);

        // Click login button
        driver.findElement(By.xpath("//button[@class='edu-btn btn-medium']")).click();
        Thread.sleep(3000);

        // Confirm login
        driver.findElement(By.xpath("//button[normalize-space(text())='Yes']")).click();
        Thread.sleep(2000);
    }

    public void verifyLoginSuccess() {
        boolean loginSuccess = driver.findElements(By.xpath("//h1[contains(text(),'Dashboard')]")).size() > 0;

        if (loginSuccess) {
            System.out.println("✅ Login successful, Dashboard is visible.");
        } else {
            System.err.println("❌ Login failed, Dashboard not found!");
        }
    }

    public void closeBrowser() throws InterruptedException {
        Thread.sleep(2000);
        if (driver != null) {
            driver.quit();
        }
    }
}
