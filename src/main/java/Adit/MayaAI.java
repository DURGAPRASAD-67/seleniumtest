package maya;
 
 
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.chrome.ChromeOptions;
	import javax.mail.*;
	import javax.mail.internet.*;
	import java.util.Properties;
 
	public class Login {
 
	    // Function to send failure email
	    private static void sendFailureEmail(String recipientEmail) {
	        // SMTP server configuration
	        String host = "smtp.gmail.com";
	        String port = "587";
	        String senderEmail = "gdfsg";  // Your email address
	        String senderPassword = "fdgdf";      // Your email password or App Password
	        String subject = "Login Attempt Failed";
	        String body = "A failed login attempt was detected in your account.";
 
	        // Set email properties
	        Properties properties = new Properties();
	        properties.put("mail.smtp.host", host);
	        properties.put("mail.smtp.port", port);
	        properties.put("mail.smtp.auth", "true");
	        properties.put("mail.smtp.starttls.enable", "true");
 
	        // Create a session with email credentials
	        Session session = Session.getInstance(properties, new Authenticator() {
	            @Override
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(senderEmail, senderPassword);
	            }
	        });
 
	        try {
	            // Create a message
	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress(senderEmail));
	            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
	            message.setSubject(subject);
	            message.setText(body);
 
	            // Send the email
	            Transport.send(message);
	            System.out.println("Login failure email sent successfully.");
	        } catch (MessagingException e) {
	            e.printStackTrace();
	        }
	    }
 
	    // Function to attempt login using Selenium
	    public static boolean attemptLogin(WebDriver driver, String url, String username, String password) {
	        driver.get(url);
 
	        // Locate the username and password fields (adjust the locators based on your login form)
	        driver.findElement(By.xpath("//li[@class='header-btn']//a[1]")).click();
	        driver.findElement(By.name("roll_no")).sendKeys(username);  // Adjust with the actual username input field id
	        driver.findElement(By.name("password")).sendKeys(password);  // Adjust with the actual password input field id
	        driver.findElement(By.xpath("//button[@class='edu-btn btn-medium']")).click();  // Adjust with the actual login button id
 
	        // Check if the login was successful by examining a successful login page element
	        // This could be a successful login message, a user profile page, etc.
	        try {
	            Thread.sleep(2000);  // Wait for page to load (better to use WebDriverWait)
	            if (driver.getTitle().contains("Dashboard") || driver.getCurrentUrl().contains("dashboard")) {
	                return true;  // Successful login
	            } else {
	                return false;  // Failed login
	            }
	        } catch (Exception e) {
	            return false;  // Login failed
	        }
	    }
 
	    public static void main(String[] args) {
	        // Setup WebDriver (make sure to have the ChromeDriver in your system path)
	        ChromeOptions options = new ChromeOptions();
	        options.addArguments("--headless");  // Uncomment this if you don't want the browser to open visually
	        WebDriver driver = new ChromeDriver(options);
 
	        String loginUrl = "https://maya.technicalhub.io/";  // Replace with your login page URL
	        String username = "2000000018";  // Input incorrect username
	        String password = "Thub@123";  // Input incorrect password
 
	        // Attempt login
	        if (!attemptLogin(driver, loginUrl, username, password)) {
	            System.out.println("Login failed. Sending email...");
	            // Send email notification on login failure
	            sendFailureEmail("gvskrishna2545@gmail.com");  // Replace with the recipient's email address
	        } else {
	            System.out.println("Login successful!");
	        }
 
	        // Quit WebDriver
	        driver.quit();
	    }
	}
 
