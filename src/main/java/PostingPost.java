import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class PostingPost {

    WebDriver driver;


    @Before
    public void driverStart() {
        String path2 = new File(System.getProperty("user.dir")).getAbsolutePath() + "\\src\\tools\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", path2);
        System.out.println("Driver is started");
    }

    @After
    public void driverStop() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void postingNewPost() {
        driver = new ChromeDriver();
        driver.get("http://127.0.0.1:10080/wordpress/wp-login.php");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        driver.findElement(By.id("user_login")).sendKeys("admin");
        driver.findElement(By.id("user_pass")).sendKeys("password");
        driver.findElement(By.id("wp-submit")).click();
        driver.findElement(By.id("wp-admin-bar-my-account"));
        try {
            driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            System.out.println("Autorisation failed");
        }
        driver.findElement(By.id("menu-posts")).click();

        try {
            driver.findElement(By.xpath("//*[@id=\"wpbody-content\"]//a[contains(text(),'Add New')]")).click();
            driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            System.out.println("Redirecting on posting page failed");
        }
        driver.findElement(By.id("title")).sendKeys("Test post");
        driver.findElement(By.xpath("//body[@id='tinymce']//br")).sendKeys("Test post for cheking how I'm testining post");
        driver.findElement(By.id("publish")).click();
        try {
            driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            System.out.println("Redirecting on posting new post page failed");
        }
        driver.findElement(By.id("menu-posts")).click();
        try {
            driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            System.out.println("Redirecting on post list page failed");
        }
        driver.findElement(By.xpath("//class[contains(text(),'Test post')]"));
        try {
            driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            System.out.println("Can't find post");


        }
    }
}
