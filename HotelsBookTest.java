import java.util.List;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class HotelsBookTest {

    WebDriver driver;
    WebDriverWait wait;
    WebElement element;
    String s="  FabHotel Scarlet Marathahalli, Bangalore, Karnataka, India";
    public void searchingForWhere() throws InterruptedException{
    	driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);  
    	//explicitly waiting until the element is visible on the webpage
    	wait=new WebDriverWait(driver,20);
        element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Hotels']")));
        boolean status=element.isDisplayed();
        //if element is visible proceeds 
        if(status) {
    	driver.findElement(By.xpath("//a[text()='Hotels']")).click();
    	driver.findElement(By.xpath("//input[@name='from']")).sendKeys(s);
    	
       	Actions act=new Actions(driver);
    	act.moveToElement(driver.findElement(By.xpath("//input[@name='from']"))).click().build().perform();
        driver.findElement(By.id("Tags")).sendKeys(Keys.TAB);
        searchForDate();
        }else
        {
        	System.out.println("Hotels feature is not visible");
        }
    }
    public void searchForDate() throws InterruptedException{
    	//code to select check-in date
    	driver.findElement(By.xpath("//input[@title='Check-in date']")).click();
    	List<WebElement> data=driver.findElements(By.xpath("//td/a[text()=\"19\"]"));
    	for(WebElement ele:data) {
    		String day=ele.getText();
    		if(day.equalsIgnoreCase("19"))
    		{
    			ele.click();
    			break;
    		}
    	}
    	
    	//code to select check-out date
    	driver.findElement(By.xpath("//input[@title='Check-out date']")).click();
    	List<WebElement> date=driver.findElements(By.xpath("//td/a[text()=\"21\"]"));
    	for(WebElement ele:date) {
    		String day=ele.getText();
    		if(day.equalsIgnoreCase("21"))
    		{
    			ele.click();
    			break;
    		}
    	}
   
        //to move the mouse over to traveller feature on the webpage 	
    	Actions act=new Actions(driver);
    	act.moveToElement(driver.findElement(By.id("travellersOnhome"))).click().build().perform();
    	Select s=new Select(driver.findElement(By.id("travellersOnhome")));
    	s.selectByVisibleText("1 room, 1 adult");
    	
        driver.findElement(By.xpath("//input[@class='booking '  and  @id='SearchHotelsButton']")).click();
        
        endTest();
    }
    
    public void endTest() {
    	driver.quit();
    }
    @Test(priority=1)
    public void launchAndBrowse() throws InterruptedException {

    	ChromeOptions options=new ChromeOptions();
    	options.addArguments("--disable-notifications");
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Rayudu\\Desktop\\software testing\\drivers\\chromedriver.exe");
    	driver=new ChromeDriver(options);
    	driver.get("https://www.cleartrip.com/");
    	driver.manage().window().maximize();
    	driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
    	searchingForWhere();
    }

}
