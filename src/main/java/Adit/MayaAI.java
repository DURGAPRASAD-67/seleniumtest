package maya;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Jenkinscode {
    private WebDriver driver;

    public static void main(String[] args) {
        Jenkinscode owlcode = new Jenkinscode();
        try {
            owlcode.setup();
            owlcode.login();
            owlcode.closeBrowser();
            System.exit(0); // Exit with success code
        } catch (Exception e) {
            System.err.println("Login Test Failed: " + e.getMessage());
            System.exit(1); // Exit with error code
        }
    }

    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    public void login() throws InterruptedException {
        try {
            driver.get("https://maya.technicalhub.io/");
            Thread.sleep(1000);
            driver.findElement(By.xpath("//li[@class='header-btn']//a[1]")).click();
            Thread.sleep(1000);
            driver.findElement(By.name("roll_no")).sendKeys("2000000018");
            driver.findElement(By.name("password")).sendKeys("Thub@123");
            Thread.sleep(3000);
            driver.findElement(By.xpath("//button[@class='edu-btn btn-medium']")).click();
            Thread.sleep(3000);

            // Verify login success (Example: Checking dashboard element)
            if (driver.findElements(By.xpath("//h1[contains(text(), 'Dashboard')]")).size() == 0) {
                throw new Exception("Login Failed - Dashboard not found");
            }

            System.out.println("Successfully Logged In");
        } catch (Exception e) {
            throw new Exception("Login Test Failed: " + e.getMessage());
        }
    }

    public void closeBrowser() {
        driver.quit();
    }
}
