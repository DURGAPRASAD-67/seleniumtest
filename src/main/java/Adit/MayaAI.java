package maya;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.testng.Assert;

public class Jenkinscode {
    private WebDriver driver;
    
    public static void main(String[] args) throws InterruptedException {
        Jenkinscode test = new Jenkinscode();
        test.setup();
        test.login();
        test.verifyDatabase();
        test.closeBrowser();
    }

    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage", "--window-size=1920,1080");
        driver = new ChromeDriver(options);
    }

    public void login() throws InterruptedException {
        driver.get("https://maya.technicalhub.io/");
        Thread.sleep(1000);

        driver.findElement(By.xpath("//li[@class='header-btn']//a[1]")).click();
        Thread.sleep(1000);

        driver.findElement(By.name("roll_no")).sendKeys("2000000018");
        driver.findElement(By.name("password")).sendKeys("Thub@123");
        Thread.sleep(3000);

        driver.findElement(By.xpath("//button[@class='edu-btn btn-medium']")).click();
        Thread.sleep(3000);

        driver.findElement(By.xpath("//button[normalize-space(text())='Yes']")).click();
        Thread.sleep(2000);

        System.out.println("Successfully logged in");
    }

    public void verifyDatabase() {
        String uri = "mongodb://localhost:27017"; // Update with actual connection string
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("mayaDB"); // Replace with actual database name
        MongoCollection<Document> collection = database.getCollection("users");

        Document user = collection.find(new Document("roll_no", "2000000018")).first();
        
        if (user != null) {
            System.out.println("User exists in MongoDB: " + user.toJson());
        } else {
            System.err.println("User not found in MongoDB!");
        }

        Assert.assertNotNull(user, "User should exist in MongoDB");
        mongoClient.close();
    }

    public void closeBrowser() throws InterruptedException {
        Thread.sleep(3000);
        if (driver != null) {
            driver.quit();
        }
    }
}
