package maya;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
 
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
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
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
		System.out.println("Successfully Login");
	}
	public void closeBrowser() throws InterruptedException {
		Thread.sleep(3000);
		driver.close();
	}
}
