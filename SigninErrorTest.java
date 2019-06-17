import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SigninErrorTest {

    static WebDriver driver;
    WebElement element;
    WebDriverWait wait;

    @Test
    public void launchBrowser() throws InterruptedException {

        //to close the show-notifications pop-up
    	ChromeOptions options=new ChromeOptions();
    	options.addArguments("--disable-notifications");
     	System.setProperty("webdriver.chrome.driver","C:\\Users\\Rayudu\\Desktop\\software testing\\drivers\\chromedriver.exe");
     	driver=new ChromeDriver(options);
     	driver.manage().window().maximize();
     	driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
     	driver.get("https://www.cleartrip.com/");
        
        driver.findElement(By.linkText("Your trips")).click();
        driver.findElement(By.xpath("//input[@id='SignIn' and @class='primary large']")).click();
        
        wait=new WebDriverWait(driver,20);
        element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Hotels']")));
        boolean status=element.isDisplayed();
        //if element is visible proceeds 
        if(status) {
        WebElement button=driver.findElement(By.id("signInButton"));
        JavascriptExecutor exe=(JavascriptExecutor) driver;
        exe.executeScript("argument[0].click();",button);
        
        String errors1 = driver.findElement(By.xpath("//body[@class='Signin modal modalError']/div/div/div/div/div/div[@id='errors1']/span[text()='There were errors in your submission']")).getText();
        Assert.assertTrue(errors1.contains("There were errors in your submission"));
        endTest();
        }
        else
        {
        	System.out.println("button not found");
        }
    }

    private void endTest() {
        driver.quit();
    }

}